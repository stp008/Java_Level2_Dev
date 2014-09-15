package week7.reflection.di;


import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import week7.reflection.Car;


public class Context {

    private static Logger log = LoggerFactory.getLogger(Context.class);

    private static final String TAG_BEAN = "bean";
    private static final String TAG_PROPERTY = "property";

    private static final String ATTR_NAME = "name";
    private static final String ATTR_VALUE = "val";
    private static final String ATTR_REF = "ref";

    private static final String ATTR_BEAN_ID = "id";
    private static final String ATTR_BEAN_CLASS = "class";


    List<Bean> beans = new ArrayList<>();

    Map<String, Object> objectsById = new HashMap<>();
    Map<String, Object> objectsByClass = new HashMap<>();



    public static void main(String[] args) throws Exception {
        Context context = new Context("config.xml");

        Car car = (Car) context.getBean("car");



        log.info("main:" + car.toString());
    }

    public Context(String xmlPath) throws Exception {
        Document config = readXml(xmlPath);
        Element root = config.getDocumentElement();
        NodeList nodes = root.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node n = nodes.item(i);
            if (TAG_BEAN.equals(n.getNodeName())) {
                parseBean(n);
            }
        }
        instantiateBeans();
    }

    public Object getBean(String beanName) {
        return objectsById.get(beanName);
    }

    public void instantiateBeans() throws Exception {
        log.info("beans: {}", beans);
        for (Bean bean : beans) {
            // по имени класса его можно инстанцировать
            // обязательно должен быть дефолтный конструктор
            String className = bean.getClassName();
            Class clazz = Class.forName(className);
            // ищем дефолтный конструктор
            Object ob = clazz.newInstance();


            //!!!!
            processAnnotation(clazz, ob);

            for (String name : bean.getProperties().keySet()) {
                // ищем поле с таким именен внутри класса
                // учитывая приватные
                Field field = clazz.getDeclaredField(name);
                String methodName = "set" + String.valueOf(name.charAt(0)).toUpperCase() + name.substring(1);
                Method method = clazz.getDeclaredMethod(methodName, field.getType());
                /*Method[] methods = clazz.getMethods();
                for (Method met : methods) {
                	System.out.println(met.getName());
                }*/
                if (field == null) {
                    throw new InvalidConfigurationException("Failed to set field [" + name + "] for class " + clazz.getName());
                }
                Property prop = bean.getProperties().get(name);
                // Чтобы изменять приватные поля
                //field.setAccessible(true);

                // храним тип данных
                Type type = field.getType();

                switch (prop.getType()) {
                    case VALUE:
                        //field.set(ob, convert(type.getTypeName(), prop.getValue()));
                    	method.invoke(ob, convert(type.getTypeName(), prop.getValue()));
                        break;
                    case REF:
                        String refName = prop.getValue();
                        if (objectsById.containsKey(refName)) {
                            //field.set(ob, objectsById.get(refName));
                            method.invoke(ob, objectsById.get(refName));
                        } else {
                            throw new InvalidConfigurationException("Failed to instantiate bean. Field " + name);
                        }
                    default:

                }
            }


            log.info("Bean instantiated: {}", bean);
            objectsById.put(bean.getName(), ob);
            objectsByClass.put(bean.getClassName(), ob);

        }


    }

    @SuppressWarnings("unchecked")
    private <T> T convert(Class<T> clazz, String data) throws Exception {
        Method m = clazz.getMethod("valueOf", String.class);
        log.info("invoke: {}", m);
        T res = (T) m.invoke(clazz, data);
        return res;
    }

    // конвертирует строку в объект соответствующего
    private Object convert(String typeName, String data) throws Exception {
        switch (typeName) {
            case "int":
            case "Integer":
                return Integer.valueOf(data);
            case "double":
            case "Double":
                return Double.valueOf(data);
            case "boolean":
            case "Boolean":
                return Boolean.valueOf(data);
            default:
                throw new InvalidConfigurationException("type name = " + typeName);
        }
    }


    private void parseBean(Node bean) throws Exception {
        NamedNodeMap attr = bean.getAttributes();
        Node name = attr.getNamedItem(ATTR_BEAN_ID);
        String nameVal = name.getNodeValue();
        String classVal = attr.getNamedItem(ATTR_BEAN_CLASS).getNodeValue();
        log.info("BEAN: [name: {}, class: {}]", nameVal, classVal);

        // ищем все проперти внутри
        NodeList list = bean.getChildNodes();
        Map<String, Property> properties = new HashMap<>();
        for (int i = 0; i < list.getLength(); i++) {
            Node n = list.item(i);
            if (TAG_PROPERTY.equals(n.getNodeName())) {
                Property property = parseProperty(n);
                log.info("\tSET {}", property);
                properties.put(property.getName(), property);
            }
        }
        //
        beans.add(new Bean(nameVal, classVal, properties));
    }

    private Property parseProperty(Node node) throws Exception {
        NamedNodeMap map = node.getAttributes();
        String name = map.getNamedItem(ATTR_NAME).getNodeValue();
        Node val = map.getNamedItem(ATTR_VALUE);
        if (val != null) {
            // если значение примитивного типа
            return new Property(name, val.getNodeValue(), ValueType.VALUE);
        } else {
            // если значение ссылочного типа
            val = map.getNamedItem(ATTR_REF);
            if (val != null) {
                return new Property(name, val.getNodeValue(), ValueType.REF);
            } else {
                throw new InvalidConfigurationException("Failed to parse property " + name);
            }
        }
    }

    private void processAnnotation(Class clazz, Object instance) throws Exception {
        Field[] fields = clazz.getDeclaredFields();
        Method[] methods = clazz.getDeclaredMethods();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Auto.class)) {
                Auto auto = field.getAnnotation(Auto.class);
                String methodName = "set" + String.valueOf(field.getName().charAt(0)).toUpperCase() + field.getName().substring(1);
                Method method = clazz.getDeclaredMethod(methodName, field.getType());
                log.info("ANNOTATION {}.{} TYPE {} auto isRequired: {}", clazz.getName(), field.getName(), field.getType().getName(), auto.isRequired());

                if (auto.isRequired() && !objectsByClass.containsKey(field.getType().getName())) {
                    throw new InvalidConfigurationException("Failed to wire @Auto " + field.getType() + " : " + field.getName());
                } else if (objectsByClass.containsKey(field.getType().getName())) {
                    Object obj = objectsByClass.get(field.getType().getName());
                    //field.setAccessible(true);
                    method.invoke(instance, obj);
                    //field.set(instance, obj);
                }
            }
        }
        
        for (Method method : methods) {
        	if (method.isAnnotationPresent(PostConstruct.class)) {
        		/* 
        		 * ����� ����� �������� ��������� ����� getParameters, 
        		 * � �� �������� �������� ����� ������ ��������� � �.�.
        		 */
        		log.info("PostConstruct was used");
        		method.invoke(instance, null); 
        	}
        }
    }


    private Document readXml(String path) throws Exception {
        File file = new File(path);
        log.info("path=" + file.getAbsolutePath());
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(file);
        return document;
    }

}

package week7.reflection.di;

import java.util.List;
import java.util.Map;

/**
 *
 */
public class Bean {
    private String name;
    private String className;
    private Map<String, Property> properties;

    public Bean(String name, String className, Map<String, Property> properties) {
        this.name = name;
        this.className = className;
        this.properties = properties;
    }



    public Map<String, Property> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Property> properties) {
        this.properties = properties;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "name='" + name + '\'' +
                ", className='" + className + '\'' +
                ", properties=" + properties +
                '}';
    }
}

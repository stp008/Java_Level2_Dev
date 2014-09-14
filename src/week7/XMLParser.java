/**
 * @author clack008@gmail.com
 */

package week7;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLParser {

	public static void main(String[] args) throws ParserConfigurationException,
			SAXException, IOException {
		DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
		f.setValidating(false);
		DocumentBuilder builder = f.newDocumentBuilder();
		Document doc = builder.parse(new File("test.xml"));
		visit(doc);

	}
	
	public static void visit(Document doc) {
		visit(doc, 0);
	}

	private static void visit(Node node, int level) {
		NodeList list = node.getChildNodes();
		for (int i = 0; i < list.getLength(); i++) {
			Node childNode = (Node) list.item(i);
			process(childNode, level);
			visit(childNode, level+1);

		}
	}

	private static void process(Node node, int level) {
		if (node.getNodeName().equals("#text"))
			return;

		StringBuilder nodeInfo = new StringBuilder();
		for (int i = 0; i < level; i++) {
			nodeInfo.append("\t");
		}

		nodeInfo.append("Node:" + node.getNodeName());
		NamedNodeMap nodeAtts = node.getAttributes();

		if (nodeAtts != null) {
			nodeInfo.append("[");
			for (int i = 0; i < nodeAtts.getLength(); i++) {
				Node att = nodeAtts.item(i);
				nodeInfo.append(att.getNodeName() + " = \""
						+ att.getNodeValue() + "\";");
			}
			nodeInfo.append("]");
		}

		System.out.println(nodeInfo.toString());
	}

}

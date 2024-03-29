package com.xinnuo.se.util;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jomper
 * Artwater
 * 2008-7-11
 */
public class XMLUtil {
    private static final int NODE = 0;
    private static final int VALUE = 1;
    private Document document;
    private String fieldNameOfCDATA = null;
    private Element root;
    private String url;

    private void add(Element father, Object o) throws Exception {
        if (father == null) {
            father = root;
        }
        if (o != null) {
            boolean isList = false;
            for (int i = 0; i < o.getClass().getInterfaces().length; i++) {
                if (o.getClass().getInterfaces()[i] == List.class) {
                    isList = true;
                }
            }
            if (isList) {
                for (Object obj : (List<?>) o) {
                    callbackAdd(father, obj);
                }
            } else {
                father = callbackAdd(father, o);
            }
        } else {
            System.err.println("XMLUtil.add(Element father, Object o) o == null");
        }
    }

    public void add(Object o) throws Exception {
        add(null, o);
    }

    private Element callbackAdd(Element father, Object o) throws Exception {
        Class<? extends Object> clazz = o.getClass();
        Element child = document.createElement(clazz.getSimpleName());
        PropertyDescriptor[] props = Introspector.getBeanInfo(clazz).getPropertyDescriptors();

        for (PropertyDescriptor prop : props) {
            try {
                if (prop.getPropertyType() == List.class) {
                    add(child, prop.getReadMethod().invoke(o));
                } else if (prop.getReadMethod().invoke(o) != null && !"class".equals(prop.getDisplayName())) {
                    if (prop.getDisplayName().equals(fieldNameOfCDATA)) {
                        child.appendChild(document.createCDATASection(prop.getReadMethod().invoke(o).toString()));
                    } else {
                        child.setAttribute(prop.getDisplayName(), prop.getReadMethod().invoke(o).toString());
                    }
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        father.appendChild(child);
        return child;
    }

    public void close() {
        if (document != null) {
            document = null;
        }
        if (root != null) {
            root = null;
        }
    }

    public void create(String rootName, String url) throws ParserConfigurationException {
        this.url = url;
        document = getDocumentBuilder().newDocument();
        root = document.createElement(rootName);
        document.appendChild(root);
    }

    private void createFolder(String url) {
        if (url != null && url.indexOf("/") != -1 && url.indexOf("/") != 0) {
            new File(url.substring(0, url.indexOf("/"))).mkdirs();
        }
    }

    public void delete(String xPath) throws Exception {
        for (Node node : findNodes(xPath)) {
            node.getParentNode().removeChild(node);
        }
    }

    private List<?> find(String xPath, int toggle) throws XPathExpressionException {
        List<Object> result = new ArrayList<Object>();
        NodeList nodes = (NodeList) getElements(xPath, XPathConstants.NODESET);
        for (int i = 0; i < nodes.getLength(); i++) {
            if (toggle == NODE) {
                result.add(nodes.item(i));
                System.out.println(nodes.item(i));
            } else if (toggle == VALUE) {
                result.add(nodes.item(i).getNodeValue());
                System.out.println(nodes.item(i).getNodeValue());
            }
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public List<Node> findNodes(String xPath) throws XPathExpressionException {
        return (List<Node>) find(xPath, NODE);
    }

    @SuppressWarnings("unchecked")
    public List<String> findNodesValue(String xPath) throws XPathExpressionException {
        return (List<String>) find(xPath, VALUE);
    }

    private DocumentBuilder getDocumentBuilder() throws ParserConfigurationException {
        return DocumentBuilderFactory.newInstance().newDocumentBuilder();
    }

    private Object getElements(String xPath, QName qName) throws XPathExpressionException {
        return XPathFactory.newInstance().newXPath().compile(xPath).evaluate(document, qName);
    }

    public String getFieldNameOfCDATA() {
        return fieldNameOfCDATA;
    }

    private Class<?> getGeneric(PropertyDescriptor prop) throws Exception {
        Field readMethodRefField = prop.getClass().getDeclaredField("readMethodRef");
        readMethodRefField.setAccessible(true);
        Object ref = readMethodRefField.get(prop);
        Field referentField = ref.getClass().getSuperclass().getDeclaredField("referent");
        referentField.setAccessible(true);
        Object referent = referentField.get(ref);
        Field signatureField = referent.getClass().getDeclaredField("signature");
        signatureField.setAccessible(true);
        String signature = (String) signatureField.get(referent);
        signature = signature.substring(signature.indexOf("<"), signature.indexOf(">"));
        signature = signature.substring(signature.indexOf("L") + 1, signature.indexOf(";"));
        signature = signature.replaceAll("/", ".");
        return Class.forName(signature);
    }

    public List<Object> getObjects(Object obj, String xpath) throws Exception {
        List<Object> result = new ArrayList<Object>();
        for (Node node : findNodes(xpath)) {
            Object o = obj.getClass().newInstance();
            populate(o, node);
            result.add(o);
        }
        return result;
    }

    public List<Object> getObjectByKey(Object obj, String primaryKeyName) throws Exception {
        Field f = obj.getClass().getDeclaredField(primaryKeyName);
        f.setAccessible(true);
        String primaryKey = (String) f.get(obj);
        return getObjects(obj, "//" + obj.getClass().getSimpleName() + "[@" + primaryKeyName + "='" + primaryKey + "']");
    }

    public List<Object> getObjects(Object obj) throws Exception {
        return getObjects(obj, "//" + obj.getClass().getSimpleName());
    }

    public void open(String url) throws Exception {
        this.url = url;
        document = getDocumentBuilder().parse(new File(url));
        root = document.getDocumentElement();
    }

    /**
     * Read object data to XML node
     *
     * @param node
     * @param obj
     * @throws Exception
     */
    private void populate(Node node, Object obj) throws Exception {
        if (obj.getClass().getSimpleName().equals(node.getNodeName())) {
            PropertyDescriptor[] props = Introspector.getBeanInfo(obj.getClass()).getPropertyDescriptors();
            for (PropertyDescriptor prop : props) {
                if (prop.getPropertyType() == List.class) {
                } else if (!"class".equals(prop.getDisplayName())) {
                    if (node.hasChildNodes() && node.getFirstChild().getNodeType() == Node.CDATA_SECTION_NODE && prop.getDisplayName().equals(fieldNameOfCDATA)) {
                        ((CDATASection) node.getFirstChild()).setData((String) prop.getReadMethod().invoke(obj));
                    } else {
                        Node attribute = node.getAttributes().getNamedItem(prop.getDisplayName());
                        if (attribute != null) {
                            attribute.setNodeValue((String) prop.getReadMethod().invoke(obj));
                        }
                    }
                }
            }
        }
    }

    /**
     * Read XML node value to object
     *
     * @param obj
     * @param node
     * @throws Exception
     */
    private void populate(Object obj, Node node) throws Exception {
        if (obj.getClass().getSimpleName().equals(node.getNodeName())) {
            PropertyDescriptor[] props = Introspector.getBeanInfo(obj.getClass()).getPropertyDescriptors();
            for (PropertyDescriptor prop : props) {
                if (prop.getPropertyType() == List.class) {
                    List<Object> sub = new ArrayList<Object>();
                    Class<?> subClazz = getGeneric(prop);
                    for (Node subNode : findNodes("//" + subClazz.getSimpleName())) {
                        Object subObj = subClazz.newInstance();
                        populate(subObj, subNode);
                        sub.add(subObj);
                    }
                    prop.getWriteMethod().invoke(obj, sub);
                } else if (!"class".equals(prop.getDisplayName())) {
                    if (node.hasChildNodes() && node.getFirstChild().getNodeType() == Node.CDATA_SECTION_NODE && prop.getDisplayName().equals(fieldNameOfCDATA)) {
                        prop.getWriteMethod().invoke(obj, ((CDATASection) node.getFirstChild()).getData());
                    } else {
                        Node attribute = node.getAttributes().getNamedItem(prop.getDisplayName());
                        if (attribute != null) {
                            prop.getWriteMethod().invoke(obj, attribute.getNodeValue());
                        }
                    }
                }
            }
        }
    }

    public void save() throws Exception {
        save(url);
    }

    public void save(String url) throws Exception {
        createFolder(url);
        PrintWriter pw = new PrintWriter(new FileOutputStream(url));
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
            transformer.transform(new DOMSource(document), new StreamResult(pw));
        } finally {
            pw.close();
        }
    }

    public void setFieldNameOfCDATA(String fieldNameOfCDATA) {
        this.fieldNameOfCDATA = fieldNameOfCDATA;
    }

    public void update(Object o, String primaryKeyName) throws Exception {
        Field f = o.getClass().getDeclaredField(primaryKeyName);
        f.setAccessible(true);
        String primaryKey = (String) f.get(o);
        List<Node> objs = findNodes("//" + o.getClass().getSimpleName() + "[@" + primaryKeyName + "='" + primaryKey + "']");
        if (objs.size() != 0) {
            populate(objs.get(0), o);
        }
    }

    public void update(String xPath, String value) throws XPathExpressionException {
        for (Node node : findNodes(xPath)) {
            node.setNodeValue(value);
        }
    }

}

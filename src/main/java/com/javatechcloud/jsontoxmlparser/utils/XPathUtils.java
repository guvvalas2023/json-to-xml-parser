package com.javatechcloud.jsontoxmlparser.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.StringWriter;

public class XPathUtils {

    private static XPath xPath = XPathFactory.newInstance().newXPath();

    public static String evaluate(String expression, Document xmlDocument) throws Exception {
        return xPath.evaluate(expression, xmlDocument);
    }

    /**
     *
     * @param expression
     * @param xmlDocument
     * @return
     * @throws Exception
     */
    public static Element evaluateNode(String expression, Document xmlDocument ) throws Exception {
        return (Element)xPath.evaluate(expression, xmlDocument, XPathConstants.NODE);
    }



    /**
     *
     * @param doc
     * @return
     * @throws Exception
     */
    public static String docAsString(Document doc) {

        try{
            StringWriter writer = new StringWriter();
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            tr.setOutputProperty( OutputKeys.INDENT, "yes" );
            tr.setOutputProperty( OutputKeys.OMIT_XML_DECLARATION, "yes" );
            tr.setOutputProperty( OutputKeys.ENCODING, "UTF-8" );
            tr.transform(new DOMSource(doc), new StreamResult(writer));
            return writer.getBuffer().toString();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

}

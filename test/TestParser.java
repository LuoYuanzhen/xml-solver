package test;

import handler.ParserErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class TestParser {
    public static void main(String[] args) {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<order xmlns=\"http://www.w3school.com.cn\"\n" +
                "        xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "       xsi:noNamespaceSchemaLocation=\"order.xsd\"\n" +
                "    date=\"2020/11/20\">\n" +
                "\n" +
                "    <from name=\"yuanzhenLuo\">\n" +
                "    </from>\n" +
                "\n" +
                "    <to name=\"sanZhang\">\n" +
                "        <address>Beijing</address>\n" +
                "    </to>\n" +
                "\n" +
                "    <item name=\"air force 1\" category=\"shoes\"/>\n" +
                "    <item name=\"iphone12\" category=\"phone\"/>\n" +
                "    <item name=\"mate40\" category=\"phone\"/>\n" +
                "    <item name=\"machine learning\"/>\n" +
                "    \n" +
                "</order>";
        InputStream is = new ByteArrayInputStream(xml.getBytes());
        File schemaFile = new File("/home/tolyzot/IdeaProjects/xml-parser/src/main/resources/order.xsd"); // etc.
        //Source xmlFile = new StreamSource(new File("/home/tolyzot/IdeaProjects/xml-parser/src/main/resources/test_order.xml"));
        Source xmlFile = new StreamSource(is);
        xmlFile.setSystemId("order.xml");
        SchemaFactory schemaFactory = SchemaFactory
                .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = schemaFactory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            validator.setErrorHandler(new ParserErrorHandler());
            validator.validate(xmlFile);
            System.out.println(xmlFile.getSystemId() + " is valid");
        } catch (SAXException e) {
            System.out.println(xmlFile.getSystemId() + " is NOT valid reason:");
            SAXParseException exception = (SAXParseException) e;
            System.out.println(exception.getMessage());
            System.out.println(exception.getLineNumber() + ", " + exception.getColumnNumber());
        } catch (IOException e) {
            System.out.println("file not found.");
        }
    }
}

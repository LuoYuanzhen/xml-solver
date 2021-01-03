package parser;

import entity.ErrorInfo;
import entity.XMLText;
import handler.ParserErrorHandler;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.dom4j.io.SAXValidator;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

public class XSDXMLParser implements XMLParser{

    private ParserErrorHandler parserErrorHandler;
    private XMLText xmlText;

    @Override
    public void parser() {

        try {
            String xsdFileName = "/home/tolyzot/IdeaProjects/xml-parser/src/main/resources/order.xsd";

            this.parserErrorHandler = new ParserErrorHandler();
            InputStream is = new ByteArrayInputStream(this.xmlText.getXmlString().getBytes());

            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setValidating(true);
            factory.setNamespaceAware(true);
            SAXParser parser = factory.newSAXParser();
            SAXReader xmlReader = new SAXReader();
            Document xmlDocument = (Document) xmlReader.read(is);
            //设置 XMLReader 的基础实现中的特定属性。核心功能和属性列表可以在 [url]http://sax.sourceforge.net/?selected=get-set[/url] 中找到。
            parser.setProperty(
                    "http://java.sun.com/xml/jaxp/properties/schemaLanguage",
                    "http://www.w3.org/2001/XMLSchema");
            parser.setProperty(
                    "http://java.sun.com/xml/jaxp/properties/schemaSource",
                    "file:" + xsdFileName);
            //创建一个SAXValidator校验工具，并设置校验工具的属性
            SAXValidator validator = new SAXValidator(parser.getXMLReader());
            //设置校验工具的错误处理器，当发生错误时，可以从处理器对象中得到错误信息。
            validator.setErrorHandler(this.parserErrorHandler);
            //校验
            validator.validate(xmlDocument);
        }catch (Exception e){
            this.parserErrorHandler.addExceptionInfo(e, "UnknownError");
        }
    }

    @Override
    public String reportMessage() {
        return this.parserErrorHandler.getReport();
    }

    @Override
    public void setXmlContent(String xmlContent){
        this.xmlText = new XMLText(xmlContent);
    }

    @Override
    public XMLText getXmlText() {
        return this.xmlText;
    }

    @Override
    public List<ErrorInfo> getErrorInfoList() {
        return this.parserErrorHandler.getErrorInfoList();
    }



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

        XSDXMLParser xsdxmlParser = new XSDXMLParser();
        xsdxmlParser.setXmlContent(xml);
        xsdxmlParser.parser();

        System.out.println(xsdxmlParser.reportMessage());
    }
}

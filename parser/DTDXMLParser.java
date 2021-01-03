package parser;

import entity.ErrorInfo;
import entity.XMLText;
import handler.ParserErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class DTDXMLParser implements XMLParser{

    private ParserErrorHandler parserErrorHandler;
    private XMLText xmlText;


    @Override
    public void parser() {
        try {

            this.parserErrorHandler = new ParserErrorHandler();
            InputStream is = new ByteArrayInputStream(this.xmlText.getXmlString().getBytes());
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setValidating(true);

            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            documentBuilder.setErrorHandler(this.parserErrorHandler);

            documentBuilder.parse(is);
        }
        catch (SAXException e){
            this.parserErrorHandler.addExceptionInfo((SAXParseException) e, "DocumentException");
        }catch (ParserConfigurationException e) {
            this.parserErrorHandler.addExceptionInfo(e, "ParseConfigureException");
        }catch (IOException e){
            this.parserErrorHandler.addExceptionInfo(e, "FileNotFoundException");
        }
    }

    @Override
    public String reportMessage() {
        return this.parserErrorHandler.getReport();
    }

    public void setXmlContent(String xmlContent){
        this.xmlText = new XMLText(xmlContent);
    }

    public XMLText getXmlText(){
        return this.xmlText;
    }

    public List<ErrorInfo> getErrorInfoList(){
        return this.parserErrorHandler.getErrorInfoList();
    }

}

package queryer;

import entity.QueryResultFormat;
import entity.XMLText;
import org.dom4j.*;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.file.InvalidPathException;
import java.util.LinkedList;
import java.util.List;

public class XPathQueryer {

    private XMLText xmlText;
    private File xmlFile;
    private String resultReport;
    private List<Node> nodes;

    public XPathQueryer(File file){
        this.xmlFile = file;
    }

    public XPathQueryer(String xmlContent){
        this.xmlText = new XMLText(xmlContent);
    }

    public void query(String ql){
        try {
            this.resultReport = "";
            InputStream is = new ByteArrayInputStream(this.xmlText.getXmlString().getBytes());
            Document document = new SAXReader().read(is);
            //Document document = new SAXReader().read(xmlFile);
            this.nodes = document.selectNodes(ql);
            for (Node node : this.nodes) {
                this.resultReport += node.asXML() + "\n";
            }
        }catch (DocumentException documentException){
            this.resultReport += documentException.getMessage();
        }catch (InvalidXPathException e){
            this.resultReport += e.getMessage();
            this.nodes = null;
        }
    }

    public String getReport(){
        return this.resultReport;
    }

    public String getHTMLReport(){
        String htmlReport =  new QueryResultFormat(this.nodes).formatToHtmlTableString();
        if (htmlReport.equals("ClassCastException") || htmlReport.equals("NullPointException")){
            return getReport();
        }
        return htmlReport;
    }

    public XMLText getXmlText() {
        return xmlText;
    }

    public void setXmlText(XMLText xmlText) {
        this.xmlText = xmlText;
    }

    public File getXmlFile() {
        return xmlFile;
    }

    public void setXmlFile(File xmlFile) {
        this.xmlFile = xmlFile;
    }



}

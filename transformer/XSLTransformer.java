package transformer;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

public class XSLTransformer {

    private String xmlContent;
    private String xslContent;
    private String outputHtmlContent;

    public XSLTransformer(String xmlContent, String xslContent){
        this.xmlContent = xmlContent;
        this.xslContent = xslContent;
    }

    public void transform(String outputFilePath){
        try{
            TransformerFactory factory = TransformerFactory.newInstance();

            InputStream xslInputStream = new ByteArrayInputStream(xslContent.getBytes());
            InputStream xmlInputStream = new ByteArrayInputStream(xmlContent.getBytes());
            OutputStream htmlOutputStream = new ByteArrayOutputStream();

            StreamSource xslStreamSource = new StreamSource(xslInputStream);
            StreamSource xmlStreamSource = new StreamSource(xmlInputStream);
            StreamResult htmlStreamResult = new StreamResult(htmlOutputStream);

            Transformer transformer = factory.newTransformer(xslStreamSource);
            transformer.transform(xmlStreamSource, htmlStreamResult);

            this.outputHtmlContent = htmlOutputStream.toString();
            this.writeFile(outputFilePath);
        }catch (TransformerConfigurationException e){
            e.printStackTrace();
        }catch (TransformerException e){
            e.printStackTrace();
        }
    }

    private void writeFile(String filePath){
        File file = new File(filePath);
        try{
            OutputStream os = new FileOutputStream(file);
            os.write(this.outputHtmlContent.getBytes());
        }catch (FileNotFoundException e){
            System.out.println("File Not found:" + filePath);
        }catch (IOException e){
            System.out.println("error while writing to file:" + filePath);
        }
        System.out.println("file saved successfully in " + filePath);
    }

    public String getOutputHtmlContent(){
        return this.outputHtmlContent;
    }
}

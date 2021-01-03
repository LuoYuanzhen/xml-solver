package test;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class TestXSLT {
    public static void main(String[] args) {
        String xsl = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n" +
                "<xsl:stylesheet version=\"1.0\"\n" +
                "xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\">\n" +
                "\n" +
                "    <xsl:template match=\"/\">\n" +
                "        <html>\n" +
                "            <body>\n" +
                "                <h1>Indian Languages details</h1>\n" +
                "                <table border=\"1\">\n" +
                "                    <tr>\n" +
                "                        <th>Language</th>\n" +
                "                        <th>Family/Origin</th>\n" +
                "                        <th>No. of speakers</th>\n" +
                "                        <th>Region</th>\n" +
                "                    </tr>\n" +
                "         <xsl:for-each select=\"languages-list/language\">\n" +
                "                    <tr>\n" +
                "                        <td><xsl:value-of select=\"name\"/></td>\n" +
                "                        <td><xsl:value-of select=\"family\"/></td>\n" +
                "                        <td><xsl:value-of select=\"users\"/></td>\n" +
                "                        <td><xsl:value-of select=\"region\"/></td>\n" +
                "                    </tr>\n" +
                "                 </xsl:for-each>\n" +
                "                </table>\n" +
                "            </body>\n" +
                "        </html>\n" +
                "    </xsl:template>\n" +
                "</xsl:stylesheet> ";

        String xml = "<languages-list>\n" +
                "  <language>\n" +
                "    <name>Kannada</name>\n" +
                "    <region>Karnataka</region>\n" +
                "    <users>38M</users>\n" +
                "  <family>Dravidian</family>\n" +
                "  </language>\n" +
                "  <language>\n" +
                "    <name>Telugu</name>\n" +
                "    <region>Andra Pradesh</region>\n" +
                "    <users>74M</users>\n" +
                "    <family>Dravidian</family>\n" +
                "  </language>\n" +
                "  <language>\n" +
                "    <name>Tamil</name>\n" +
                "    <region>TamilNadu</region>\n" +
                "    <users>61M</users>\n" +
                "    <family>Dravidian</family>\n" +
                "  </language>\n" +
                "  <language>\n" +
                "    <name>Malayalam</name>\n" +
                "    <region>Kerela</region>\n" +
                "    <users>33M</users>\n" +
                "    <family>Dravidian</family>\n" +
                "  </language>\n" +
                "  <language>\n" +
                "    <name>Hindi</name>\n" +
                "    <region>Andaman and Nicobar Islands, North india, Parts of North east</region>\n" +
                "    <users>442M</users>\n" +
                "    <family>Indo Aryan</family>\n" +
                "  </language>\n" +
                "  <language>\n" +
                "    <name>Assamese</name>\n" +
                "    <region>Assam, Arunachal Pradesh</region>\n" +
                "    <users>13M</users>\n" +
                "    <family>Indo Aryan</family>\n" +
                "  </language>\n" +
                "</languages-list>";

        String html = "";
        try{
            TransformerFactory factory = TransformerFactory.newInstance();

            InputStream xslInputStream = new ByteArrayInputStream(xsl.getBytes());
            InputStream xmlInputStream = new ByteArrayInputStream(xml.getBytes());
            OutputStream htmlOutputStream = new ByteArrayOutputStream();

            StreamSource xslStreamSource = new StreamSource(xslInputStream);
            StreamSource xmlStreamSource = new StreamSource(xmlInputStream);
            StreamResult htmlStreamResult = new StreamResult(htmlOutputStream);

            Transformer transformer = factory.newTransformer(xslStreamSource);
            transformer.transform(xmlStreamSource, htmlStreamResult);

            //System.out.println(htmlOutputStream.toString());
        }catch (TransformerConfigurationException e){
            e.printStackTrace();
        }catch (TransformerException e){
            e.printStackTrace();
        }
    }
}

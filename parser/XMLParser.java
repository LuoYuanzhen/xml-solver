package parser;

import entity.ErrorInfo;
import entity.XMLText;

import java.util.List;

public interface XMLParser {
    void parser();
    String reportMessage();
    void setXmlContent(String xmlContent);
    XMLText getXmlText();
    List<ErrorInfo> getErrorInfoList();
}

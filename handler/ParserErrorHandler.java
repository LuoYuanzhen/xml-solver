package handler;

import entity.ErrorInfo;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import java.util.ArrayList;
import java.util.List;

public class ParserErrorHandler implements org.xml.sax.ErrorHandler {

    private List<ErrorInfo> errorInfoList = new ArrayList<>();
    private List<String> exceptionInfoList = new ArrayList<>();

    @Override
    public void warning(SAXParseException exception) throws SAXException {
        this.saveErrorInfo(exception, "warning");
    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        this.saveErrorInfo(exception, "error");
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        this.saveErrorInfo(exception, "fatalError");
    }

    private void saveErrorInfo(SAXParseException e, String errorType) {
        int[] pos = new int[2];
        pos[0] = e.getLineNumber();
        pos[1] = e.getColumnNumber();
        this.errorInfoList.add(new ErrorInfo(pos, e.getMessage(), errorType));
    }

    public void addExceptionInfo(Exception exception, String errorType){
        if(exception instanceof SAXParseException)
            this.saveErrorInfo((SAXParseException) exception, errorType);
        this.exceptionInfoList.add(errorType + ":" + exception.getMessage());
    }

    public List<ErrorInfo> getErrorInfoList(){
        return this.errorInfoList;
    }

    public String getReport(){
        String report = "";
        for (ErrorInfo errorInfo : this.errorInfoList) {

            int[] pos = errorInfo.getPos();
            String message = errorInfo.getMessage();
            String type = errorInfo.getErrorType();
            report += type + ": in line:" + pos[0] + " column:" + pos[1] + ", " + message + "\n\n";

        }

        for (String message : this.exceptionInfoList){
            report += message + "\n\n";
        }

        return report;
    }
}

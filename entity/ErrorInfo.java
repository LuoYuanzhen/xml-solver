package entity;

public class ErrorInfo {
    private int[] pos;
    private String Message;
    private String errorType;

    public ErrorInfo(int[] pos, String message, String errorType) {
        this.pos = pos;
        Message = message;
        this.errorType = errorType;
    }

    public int[] getPos() {
        return pos;
    }

    public void setPos(int[] pos) {
        this.pos = pos;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }
}

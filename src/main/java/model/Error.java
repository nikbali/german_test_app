package model;

public class Error {
    private String status_message;
    private boolean success;
    private String error_text;


    public Error(String text, boolean success, String error_text)
    {
        this.status_message = text;
        this.success = success;
        this.error_text = error_text;

    }

    public String getStatus_message() {
        return status_message;
    }

    public String getError_text() {
        return error_text;
    }

    public void setStatus_message(String status_message) {
        this.status_message = status_message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}

package lib.rdajila.helper;

public final class ErrorHelper {
    private String field;
    private String message;
    
    public ErrorHelper() {}
    
    public ErrorHelper(String eField, String eMessage) {
    	this.field = eField;
    	this.message = eMessage;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

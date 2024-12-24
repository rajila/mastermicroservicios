package es.rdajila.helperlib;

import java.util.ArrayList;
import java.util.List;

public final class ResponseHelper {
    private String status = ConstantsHelper.SUCCESS;
    private List<ErrorHelper> errors = new ArrayList<>();
    private Integer idData = 0;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ErrorHelper> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorHelper> errors) {
        this.errors = errors;
    }

    public Integer getIdData() { return idData; }

    public void setIdData(Integer idData) { this.idData = idData; }
}

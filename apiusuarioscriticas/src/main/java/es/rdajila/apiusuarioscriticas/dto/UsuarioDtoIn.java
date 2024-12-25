package es.rdajila.apiusuarioscriticas.dto;

public class UsuarioDtoIn {
    private Integer id;
    private String nombre;
    private String apellido;
    private String correo;
    private String password;
    private String base64;
    private String tipoDocumento;
    private String tipoOrigenDocumento;
    private Integer documentoId;
    private Integer rolId;
    private Integer estado;

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getTipoOrigenDocumento() {
        return tipoOrigenDocumento;
    }

    public void setTipoOrigenDocumento(String tipoOrigenDocumento) {
        this.tipoOrigenDocumento = tipoOrigenDocumento;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Integer getDocumentoId() {
        return documentoId;
    }

    public void setDocumentoId(Integer documentoId) {
        this.documentoId = documentoId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRolId() {
        return rolId;
    }

    public void setRolId(Integer rolId) {
        this.rolId = rolId;
    }
}

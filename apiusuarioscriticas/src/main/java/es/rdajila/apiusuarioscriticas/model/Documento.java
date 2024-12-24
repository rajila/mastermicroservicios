package es.rdajila.apiusuarioscriticas.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_documento")
public class Documento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "tipoorigen", nullable = false, length = 5)
    private String tipoorigen;

    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;

    @Lob
    @Column(name = "base64", nullable = false)
    private String base64;

    public Documento() {}

    public Documento(String tipoorigen, String tipo, String base64) {
        this.tipoorigen = tipoorigen;
        this.tipo = tipo;
        this.base64 = base64;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipoorigen() {
        return tipoorigen;
    }

    public void setTipoorigen(String tipoorigen) {
        this.tipoorigen = tipoorigen;
    }
}
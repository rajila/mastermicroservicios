package es.rdajila.apipeliculas.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_director")
public class Director {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Transient
    private String nombre;

    @Transient
    private String apellido;

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
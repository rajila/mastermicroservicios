package es.rdajila.apipeliculas.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_director")
public class Director {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", nullable = false)
    private Usuario tblUsuario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getTblUsuario() {
        return tblUsuario;
    }

    public void setTblUsuario(Usuario tblUsuario) {
        this.tblUsuario = tblUsuario;
    }

}
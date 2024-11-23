package es.rdajila.apipeliculas.model;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "tbl_actor")
public class Actor {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "fechanacimiento", nullable = false)
    private Instant fechanacimiento;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_pais", nullable = false)
    private Pais idPais;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Instant fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public Pais getIdPais() {
        return idPais;
    }

    public void setIdPais(Pais idPais) {
        this.idPais = idPais;
    }

}
package es.rdajila.apipeliculas.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tbl_actor")
public class Actor {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pais", referencedColumnName = "id", nullable = false)
    private Pais country;

    @MapsId
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false)
    private Usuario user;

    @Column(name = "fechanacimiento", nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd", locale = "es-ES", timezone = "Europe/Madrid")
    @Temporal(TemporalType.DATE)
    private Date fechanacimiento;

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public Pais getCountry() {
        return country;
    }

    public void setCountry(Pais country) {
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
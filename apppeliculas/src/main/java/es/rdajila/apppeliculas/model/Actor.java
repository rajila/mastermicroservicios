package es.rdajila.apppeliculas.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Actor {
    private Integer id;
    private Pais country;
    private Usuario user;
    @JsonFormat(pattern="yyyy-MM-dd", locale = "es-ES", timezone = "Europe/Madrid")
    private Date fechanacimiento;

    public Pais getCountry() {
        return country;
    }

    public void setCountry(Pais country) {
        this.country = country;
    }

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
}

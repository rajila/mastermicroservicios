package es.uah.rdajila.apigateway.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_usuario")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 150)
    private String apellido;

    @Column(name = "correo", length = 150)
    private String correo;

    @Lob
    @Column(name = "password")
    @JsonIgnore
    private String password;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "tbl_usuario_rol",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_rol"))
    @JsonIgnore
    private Set<Rol> roles = new LinkedHashSet<>();

    @ColumnDefault("1")
    @Column(name = "estado")
    private Integer estado;

    @Transient
    private String rolName;

    public String getRolName() {
        if (!this.roles.isEmpty()) {
            return this.getRoles().iterator().next().getCodigo().toUpperCase();
        }
        return "";
    }
}

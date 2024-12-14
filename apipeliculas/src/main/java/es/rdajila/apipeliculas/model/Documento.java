package es.rdajila.apipeliculas.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

}
package es.rdajila.apipeliculas.repository;

import es.rdajila.apipeliculas.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> { }
package es.rdajila.apiusuarioscriticas.repository;

import es.rdajila.apiusuarioscriticas.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByCorreoAndEstado(String eEmail, Integer eEstado);
    Boolean existsByCorreo(String eCorreo);
    @Query("select u from Usuario u join u.roles r where r.codigo in ('ADMIN', 'USER')")
    List<Usuario> getByRolesCodeAdminOrUser();
    @Query("select u from Usuario u left join u.roles r where r.codigo in ('ADMIN', 'USER') AND ((u.nombre || ' ' || u.apellido) like %?1% OR u.correo like %?1% OR ?1 = '') AND (r.id = ?2 OR ?2 = 0)")
    List<Usuario> getByNombresOrCorreoOrRolId(String txt, Integer rolId);
}
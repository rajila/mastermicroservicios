package es.rdajila.apiusuarioscriticas.repository;

import es.rdajila.apiusuarioscriticas.model.Critica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ICriticaRepository extends JpaRepository<Critica, Integer> {
  @Query("select c from Critica c join c.Usuario u where u.id = ?1 order by c.id desc")
  List<Critica> getByUsuarioId(int eUsuarioId);

  @Query(value="select c from Critica c join c.Usuario u where c.peliculaId = ?1 AND u.id = ?2")
  Optional<Critica> getByPeliculaIdAndUsuarioId(int ePeliculaId, int eUsuarioId);

  List<Critica> findByPeliculaIdOrderByIdDesc(int ePeliculaId);

  List<Critica> findAllByOrderByIdDesc();

  @Query("select c from Critica c join c.Usuario u where ((u.nombre || ' ' || u.apellido) like %?1%) AND (c.peliculaId = ?2 OR ?2 = 0) order by c.id desc")
  List<Critica> getAllFilterByNameUserOrPeliculaId(String eNameUser, int ePeliculaId);

  @Query("select c from Critica c join c.Usuario u where ((u.nombre || ' ' || u.apellido) like %?1%) AND (c.peliculaId = ?2 OR ?2 = 0) AND u.id = ?3 order by c.id desc")
  List<Critica> getAllFilterByUserLoginAndNameUserOrPeliculaId(String eNameUser, int ePeliculaId, int eUsuarioId);

  @Modifying
  @Query("DELETE Critica c WHERE c.Usuario.id = ?1")
  void deleteByUsuarioId(int eUsuarioId);

  @Modifying
  @Query("DELETE Critica c WHERE c.peliculaId = ?1")
  void deleteByPeliculaId(int ePeliculaId);
}
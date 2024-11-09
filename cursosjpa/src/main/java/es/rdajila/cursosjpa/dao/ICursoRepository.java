package es.rdajila.cursosjpa.dao;

import es.rdajila.cursosjpa.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICursoRepository extends JpaRepository<Curso, Long> {
    List<Curso> findByNombreContainingIgnoreCase(String nombre);
    List<Curso> findByCategoriaContainingIgnoreCase(String categoria);
    List<Curso> findByProfesor(String profesor);
}
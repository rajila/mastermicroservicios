package es.rdajila.cursosjpa.dao;

import es.rdajila.cursosjpa.model.Curso;
import java.util.List;

public interface ICursoDAO {
    List<Curso> searchAll();
    Curso searchById(Long eId);
    List<Curso> searchByName(String eName);
    List<Curso> seachByCategory(String eCategory);
    List<Curso> searchByTeacher(String eTeacher);
    void save(Curso curso);
    void update(Curso curso);
    void delete(Long eId);
}

package es.rdajila.cursosjpa.dao;

import es.rdajila.cursosjpa.model.Curso;
import java.util.List;

public interface ICursoDAO {
    List<Curso> getAll();
    Curso getById(Long eId);
    List<Curso> getByName(String eName);
    List<Curso> getByCategory(String eCategory);
    List<Curso> getByTeacher(String eTeacher);
    void save(Curso eCourse);
    void update(Curso eCourse);
    void delete(Long eId);
}
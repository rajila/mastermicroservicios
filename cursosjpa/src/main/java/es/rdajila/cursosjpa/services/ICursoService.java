package es.rdajila.cursosjpa.services;

import es.rdajila.cursosjpa.model.Curso;
import java.util.List;

public interface ICursoService {
    List<Curso> getAll();
    Curso getById(Long eId);
    List<Curso> getByName(String eName);
    List<Curso> getByCategory(String eCategory);
    List<Curso> getByTeacher(String eTeacher);
    boolean save(Curso eCourse);
    void update(Curso eCourse);
    boolean delete(Long eId);
}
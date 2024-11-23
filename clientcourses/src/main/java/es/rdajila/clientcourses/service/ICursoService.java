package es.rdajila.clientcourses.service;

import es.rdajila.clientcourses.model.Curso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICursoService {
    Page<Curso> getAll(Pageable ePageable);
    Curso getById(Integer eId);
    Page<Curso> getByName(String eNane, Pageable ePageable);
    Page<Curso> getByCategory(String eCategory, Pageable ePageable);
    Page<Curso> getByTeacher(String eProfesor, Pageable ePageable);
    void save(Curso eCurso);
    void delete(Integer eId);
}
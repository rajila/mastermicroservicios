package es.rdajila.cursosjpa.dao;

import es.rdajila.cursosjpa.model.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CursoDAOImpl implements ICursoDAO{
    @Autowired
    ICursoRepository repository;

    @Override
    public List<Curso> getAll() {
        return repository.findAll();
    }

    @Override
    public Curso getById(Long eId) {
        Optional<Curso> curso = repository.findById(eId);
        return curso.orElse(null);
    }

    @Override
    public List<Curso> getByName(String eName) {
        return repository.findByNombreContainingIgnoreCase(eName);
    }

    @Override
    public List<Curso> getByCategory(String eCategory) {
        return repository.findByCategoriaContainingIgnoreCase(eCategory);
    }

    @Override
    public List<Curso> getByTeacher(String eTeacher) {
        return repository.findByProfesor(eTeacher);
    }

    @Override
    public void save(Curso eCourse) {
        repository.save(eCourse);
    }

    @Override
    public void update(Curso eCourse) {
        repository.save(eCourse);
    }

    @Override
    public void delete(Long eId) {
        repository.deleteById(eId);
    }
}

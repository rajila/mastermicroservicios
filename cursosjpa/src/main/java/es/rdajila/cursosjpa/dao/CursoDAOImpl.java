package es.rdajila.cursosjpa.dao;

import es.rdajila.cursosjpa.model.Curso;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CursoDAOImpl implements ICursoDAO{
    @Autowired
    ICursoRepository repository;

    @Override
    public List<Curso> searchAll() {
        return repository.findAll();
    }

    @Override
    public Curso searchById(Long eId) {
        Optional<Curso> curso = repository.findById(eId);
        if (curso.isPresent()) {
            return curso.get();
        }
        return null;
    }

    @Override
    public List<Curso> searchByName(String eName) {
        return repository.findByNombreContainingIgnoreCase(eName);
    }

    @Override
    public List<Curso> seachByCategory(String eCategory) {
        return repository.findByCategoriaContainingIgnoreCase(eCategory);
    }

    @Override
    public List<Curso> searchByTeacher(String eTeacher) {
        return repository.findByProfesor(eTeacher);
    }

    @Override
    public void save(Curso curso) {
        repository.save(curso);
    }

    @Override
    public void update(Curso curso) {
        repository.save(curso);
    }

    @Override
    public void delete(Long eId) {
        repository.deleteById(eId);
    }
}

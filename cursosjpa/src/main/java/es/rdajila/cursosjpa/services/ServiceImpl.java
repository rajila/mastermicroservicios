package es.rdajila.cursosjpa.services;

import es.rdajila.cursosjpa.dao.ICursoDAO;
import es.rdajila.cursosjpa.model.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceImpl implements ICursoService{
    @Autowired
    ICursoDAO cursoDAO;

    @Override
    public List<Curso> getAll() {
        return cursoDAO.getAll();
    }

    @Override
    public Curso getById(Long eId) {
        return cursoDAO.getById(eId);
    }

    @Override
    public List<Curso> getByName(String eName) {
        return cursoDAO.getByName(eName);
    }

    @Override
    public List<Curso> getByCategory(String eCategory) {
        return cursoDAO.getByCategory(eCategory);
    }

    @Override
    public List<Curso> getByTeacher(String eTeacher) {
        return cursoDAO.getByTeacher(eTeacher);
    }

    @Override
    public boolean save(Curso eCourse) {
        boolean result = cursoDAO.getById(eCourse.getId()) != null;
        if (result) {
            cursoDAO.save(eCourse);
        }
        return result;
    }

    @Override
    public void update(Curso eCourse) {
        if (cursoDAO.getById(eCourse.getId()) != null) {
            cursoDAO.update(eCourse);
        }
    }

    @Override
    public boolean delete(Long eId) {
        boolean result = cursoDAO.getById(eId) != null;
        if (result) {
            cursoDAO.delete(eId);
        }
        return result;
    }
}

package es.rdajila.clientcourses.service;

import es.rdajila.clientcourses.model.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class CursoServiceImp implements ICursoService{
    @Autowired
    RestTemplate template;

    String url = "http://localhost:8080/cursos";

    @Override
    public Page<Curso> getAll(Pageable ePageable) {
        Curso[] cursos = template.getForObject(url, Curso[].class);
        List<Curso> cursosList = Arrays.asList(cursos);
        int pageSize = ePageable.getPageSize();
        int currentPage = ePageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Curso> list;
        if (cursosList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, cursosList.size());
            list = cursosList.subList(startItem, toIndex);
        }
        Page<Curso> page = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), cursosList.size());
        return page;
    }

    @Override
    public Curso getById(Integer eId) {
        Curso curso = template.getForObject(url + "/" + eId, Curso.class);
        return curso;
    }

    @Override
    public Page<Curso> getByName(String eNane, Pageable ePageable) {
        Curso[] cursos = template.getForObject(url + "/nombre/" + eNane,
                Curso[].class);
        List<Curso> lista = Arrays.asList(cursos);
        Page<Curso> page = new PageImpl<>(lista, ePageable, lista.size());
        return page;
    }

    @Override
    public Page<Curso> getByCategory(String eCategory, Pageable ePageable) {
        Curso[] cursos = template.getForObject(url + "/categoria/" + eCategory,
                Curso[].class);
        List<Curso> lista = Arrays.asList(cursos);
        Page<Curso> page = new PageImpl<>(lista, ePageable, lista.size());
        return page;
    }

    @Override
    public Page<Curso> getByTeacher(String eProfesor, Pageable ePageable) {
        Curso[] cursos = template.getForObject(url + "/profesor/" + eProfesor,
                Curso[].class);
        List<Curso> lista = Arrays.asList(cursos);
        Page<Curso> page = new PageImpl<>(lista, ePageable, lista.size());
        return page;
    }

    @Override
    public void save(Curso eCurso) {
        if (eCurso.getId() != null && eCurso.getId() > 0) {
            template.put(url, eCurso);
        } else {
            eCurso.setId(0);
            template.postForObject(url, eCurso, String.class);
        }
    }

    @Override
    public void delete(Integer eId) {
        template.delete(url + "/" + eId);
    }
}

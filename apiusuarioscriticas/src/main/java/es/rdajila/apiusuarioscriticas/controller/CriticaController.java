package es.rdajila.apiusuarioscriticas.controller;

import es.rdajila.apiusuarioscriticas.model.Critica;
import es.rdajila.apiusuarioscriticas.service.ICriticaService;
import lib.rdajila.helper.ConstantsHelper;
import lib.rdajila.helper.ErrorHelper;
import lib.rdajila.helper.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/criticas")
public class CriticaController {
    private final ICriticaService service;

    @Autowired
    public CriticaController(ICriticaService service) {
        this.service = service;
    }

    @CrossOrigin
    @GetMapping({"", "/"})
    public ResponseEntity<List<Critica>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping(value={"", "/"}, params = {"txt", "peliculaId"})
    public ResponseEntity<List<Critica>> getAllByFilters(@RequestParam("txt") Optional<String> txt, @RequestParam("peliculaId") Optional<Integer> peliculaId) {
        String filterTxt = txt.orElse("");
        Integer filterPeliculaId = peliculaId.orElse(0);
        return ResponseEntity.ok(service.getAllFilter(filterTxt, filterPeliculaId));
    }

    @CrossOrigin
    @PostMapping(value = {"", "/"})
    public ResponseEntity<ResponseHelper> create(@RequestBody Critica eDataInput) {
        ResponseHelper _response = service.create(eDataInput);
        if (_response != null) return ResponseEntity.ok(_response);
        return new ResponseEntity<>(null, HttpStatus.FAILED_DEPENDENCY);
    }

    @CrossOrigin
    @GetMapping({ "/pelicula/{ePeliculaId}"})
    public ResponseEntity<List<Critica>> getAllByPeliculaId(@PathVariable("ePeliculaId") Integer ePeliculaId) {
        return ResponseEntity.ok(service.getByPeliculaId(ePeliculaId));
    }

    @CrossOrigin
    @GetMapping(value = {"/mycritica/pelicula/{ePeliculaId}"})
    public ResponseEntity<ResponseHelper> getMiCriticaByPelicula(@PathVariable("ePeliculaId") Integer ePeliculaId) {
        Critica dataDb = service.getByPeliculaIdAndUsuarioLoginId(ePeliculaId);
        ResponseHelper _response = new ResponseHelper();
        // Si es null, no existe la critica del usario login para esa pelicula
        if (dataDb == null) {
            _response.setIdData(0);
            return new ResponseEntity<>(_response, HttpStatus.OK);
        }
        _response.setStatus(ConstantsHelper.FAILURE);
        _response.setIdData(dataDb.getId());
        _response.getErrors().add(new ErrorHelper("object", "Ya existe una critica con id " + dataDb.getId()));
        return new ResponseEntity<>(_response, HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping("/{eId}")
    public ResponseEntity<ResponseHelper> delete(@PathVariable("eId") Integer eId) {
        ResponseHelper _response = service.delete(eId);
        return ResponseEntity.ok(_response);
    }

    @CrossOrigin
    @DeleteMapping("/usuario/{eId}")
    public ResponseEntity<ResponseHelper> deleteByUsuario(@PathVariable("eId") Integer eId) {
        ResponseHelper _response = service.deleteByUsuarioId(eId);
        return ResponseEntity.ok(_response);
    }

    @CrossOrigin
    @DeleteMapping("/pelicula/{eId}")
    public ResponseEntity<ResponseHelper> deleteByPelicula(@PathVariable("eId") Integer eId) {
        ResponseHelper _response = service.deleteByPeliculaId(eId);
        return ResponseEntity.ok(_response);
    }
}

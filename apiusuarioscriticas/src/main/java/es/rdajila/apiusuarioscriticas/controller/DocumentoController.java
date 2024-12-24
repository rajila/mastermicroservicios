package es.rdajila.apiusuarioscriticas.controller;

import es.rdajila.apiusuarioscriticas.model.Documento;
import es.rdajila.apiusuarioscriticas.service.IDocumentoService;
import lib.rdajila.helper.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/documentos")
public class DocumentoController {
    private final IDocumentoService documentoService;

    @Autowired
    public DocumentoController(IDocumentoService documentoService) {
        this.documentoService = documentoService;
    }

    @CrossOrigin
    @GetMapping({"/{eId}"})
    public ResponseEntity<Documento> getById(@PathVariable("eId") Integer eId) {
        Documento _data = documentoService.getById(eId);
        return (_data == null) ?
                ResponseEntity.notFound().build() : ResponseEntity.ok(_data);
    }

    @CrossOrigin
    @PostMapping(value = {"", "/"})
    public ResponseEntity<ResponseHelper> create(@RequestBody Documento eDataInput) {
        ResponseHelper _response = documentoService.create(eDataInput);
        if (_response != null) return ResponseEntity.ok(_response);
        return new ResponseEntity<>(null, HttpStatus.FAILED_DEPENDENCY);
    }

    @CrossOrigin
    @PutMapping(value = {"", "/"})
    public ResponseEntity<ResponseHelper> update(@RequestBody Documento eDataInput) {
        ResponseHelper _response = documentoService.update(eDataInput);
        if (_response != null) return ResponseEntity.ok(_response);
        return new ResponseEntity<>(null, HttpStatus.FAILED_DEPENDENCY);
    }

    @CrossOrigin
    @DeleteMapping("/{eId}")
    public ResponseEntity<ResponseHelper> delete(@PathVariable("eId") Integer eId) {
        ResponseHelper _response = documentoService.delete(eId);
        return ResponseEntity.ok(_response);
    }
}

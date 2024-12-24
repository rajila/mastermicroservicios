package es.rdajila.apiusuarioscriticas.repository;

import es.rdajila.apiusuarioscriticas.model.Documento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDocumentoRepository extends JpaRepository<Documento, Integer> {
}
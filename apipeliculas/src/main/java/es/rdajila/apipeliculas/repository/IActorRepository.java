package es.rdajila.apipeliculas.repository;

import es.rdajila.apipeliculas.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IActorRepository extends JpaRepository<Actor, Integer> { }
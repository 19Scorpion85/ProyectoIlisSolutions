package org.encina.spring.examen.msvc.ciudadanos.repositories;

import org.encina.spring.examen.msvc.ciudadanos.models.entities.Ciudadano;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/*
 * Interface repositorio.
 * Realiza los CRUD de Ciudadano.
 * Valida existencia repetitiva de email por medio de findByEmail.
 * @author Andrés Encina.
 */
public interface CiudadanoRepository extends CrudRepository<Ciudadano,Long> {
    Optional<Ciudadano> findByEmail(String email); //Validación si existe email en la base de datos
}

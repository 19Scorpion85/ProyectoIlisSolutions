package org.encina.spring.examen.msvc.ciudadanos.repositories;
import org.encina.spring.examen.msvc.ciudadanos.models.entities.Ciudadano;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

/**
 * Interface "CiudadanoRepository" con extenxión a repositorios
 * @author: Andrés Encina
 * @version: 24/07/2023/A
 * @See: "org.encina.spring.examen.msvc.ciudadanos/models.entities/Ciudadano"
 */
public interface CiudadanoRepository extends CrudRepository<Ciudadano,Long> {
    /**
     * Parte de la validación si el email ingresado ya existe en la base de datos
     * @param email define correo para validar existencia en base de datos
     */
    Optional<Ciudadano> findByEmail(String email); //Validación si existe email en la base de datos
    //Cierre de opcional
}//Cierre del repositorio
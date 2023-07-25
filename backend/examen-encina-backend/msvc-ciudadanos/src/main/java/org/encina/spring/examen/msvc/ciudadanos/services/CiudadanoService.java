package org.encina.spring.examen.msvc.ciudadanos.services;

import org.encina.spring.examen.msvc.ciudadanos.models.entities.Ciudadano;
import java.util.List;
import java.util.Optional;

/*
 * Servicio interface de Ciudadano.
 * Encargado de conectar los repositorios CRUD con Usuario hacia CiudadanoController.
 * Valida existencia repetitiva de email.
 * @author Andrés Encina.
 */
public interface CiudadanoService {
    List<Ciudadano> listar();
    Optional<Ciudadano> porId(Long id);
    Ciudadano guardar(Ciudadano ciudadano);
    void eliminar(Long id);
    List<Ciudadano> listarPorIds(Iterable<Long>ids);//Lista ciudadanos por ids y los envia a UsuarioController.

    Optional<Ciudadano> porEmail(String email);//Validación si existe email en la base de datos

}

package org.encina.spring.examen.msvc.ciudadanos.services;
import org.encina.spring.examen.msvc.ciudadanos.models.entities.Ciudadano;
import java.util.List;
import java.util.Optional;

/**
 * Interface "CiudadanoService" con extenxión a repositorios
 * @author: Andrés Encina
 * @version: 24/07/2023/A
 * @See: "org.encina.spring.msvc.ciudadanos/repositories/CiudadanoRepository"
 */
public interface CiudadanoService {
    //Inicio CRUD
    List<Ciudadano> listar();
    Optional<Ciudadano> porId(Long id);
    Ciudadano guardar(Ciudadano ciudadano);
    void eliminar(Long id);
    List<Ciudadano> listarPorIds(Iterable<Long>ids);//Lista ciudadanos por ids y los envia a CiudadanoController.
    Optional<Ciudadano> porEmail(String email);//Validación si existe email en la base de datos
  //Fin CRUD
}//Cierre de interface
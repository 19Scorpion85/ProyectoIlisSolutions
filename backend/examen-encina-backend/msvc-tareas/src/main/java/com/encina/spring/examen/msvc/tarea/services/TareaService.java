package com.encina.spring.examen.msvc.tarea.services;
import com.encina.spring.examen.msvc.tarea.models.Ciudadano;
import com.encina.spring.examen.msvc.tarea.models.entities.Tarea;
import java.util.List;
import java.util.Optional;
/**
 * Interface "TareaService" con extenxión a repositorios
 * @author: Andrés Encina
 * @version: 25/07/2023/A
 * @See: "org.encina.spring.examen.msvc.tareas/repositories/TareaRepository"
 */
public interface TareaService {
    List<Tarea> listar();
    Optional<Tarea> porId(Long id);
    Tarea guardar(Tarea tarea);
    void eliminar(Long id);

    Optional<Ciudadano> asignarCiudadano(Ciudadano ciudadano,Long ciudadanoId);
    Optional<Ciudadano> crearCiudadano(Ciudadano ciudadano, Long ciudadanoId);
    Optional<Ciudadano> eliminarCiudadano(Ciudadano ciudadano, Long ciudadanoId);//Solo lo quita de la tarea
    List<Tarea> listarPorIds(Iterable<Long>ids);//Lista tarea por ids y los envia a TareaController.
//Fin CRUD
}//Cierre de interface
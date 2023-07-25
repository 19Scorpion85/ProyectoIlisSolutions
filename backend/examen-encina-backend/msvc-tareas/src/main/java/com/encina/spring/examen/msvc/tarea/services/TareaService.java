package com.encina.spring.examen.msvc.tarea.services;
import com.encina.spring.examen.msvc.tarea.models.entities.Tarea;
import java.util.List;
import java.util.Optional;
/**
 * Interface "TareaService" con extenxión a repositorios
 * @author: Andrés Encina
 * @version: 25/07/2023/A
 * @See: "org.encina.spring.msvc.tareas/repositories/TareaRepository"
 */
public interface TareaService {
    List<Tarea> Listar();
    Optional<Tarea> porId(Long id);
    Tarea guardar(Tarea tarea);
    void eliminar(Long id);
}//Cierre de interface
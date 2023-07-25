package com.encina.spring.examen.msvc.tarea.repositories;
import com.encina.spring.examen.msvc.tarea.models.entities.Tarea;
import org.springframework.data.repository.CrudRepository;
/**
 * Interface "CiudadanoRepository" con extenxión a repositorios
 * @author: Andrés Encina
 * @version: 25/07/2023/A
 * @See: "org.encina.spring.examen.msvc.tareas/models.entities/Tareas"
 */
public interface TareaRepository extends CrudRepository<Tarea, Long> {
}

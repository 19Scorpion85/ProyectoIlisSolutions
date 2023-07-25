package com.encina.spring.examen.msvc.tarea.repositories;
import com.encina.spring.examen.msvc.tarea.models.entities.Tarea;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
/**
 * Interface "CiudadanoRepository" con extenxión a repositorios
 * @author: Andrés Encina
 * @version: 25/07/2023/A
 * @See: "org.encina.spring.examen.msvc.tareas/models.entities/Tareas"
 */
public interface TareaRepository extends CrudRepository<Tarea, Long> {
    @Modifying
    @Query("delete from TareaCiudadano cu where cu.ciudadanoId=?1")
    void eliminarTareaCiudadanoPorId(Long id);//Elimina a ciudadano de tarea
}
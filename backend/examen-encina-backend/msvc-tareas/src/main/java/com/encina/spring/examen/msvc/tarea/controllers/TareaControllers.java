package com.encina.spring.examen.msvc.tarea.controllers;
import com.encina.spring.examen.msvc.tarea.models.entities.Tarea;
import com.encina.spring.examen.msvc.tarea.services.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.*;

/**
 * Clase "TareaControllers", administra controladores de tarea
 * @author: Andrés Encina
 * @version: 25/07/2023/A
 * @See: "org.encina.spring.examen.msvc.tarea/services/TareaService"
 */
@RestController
public class TareaControllers {
    //Inicio controladores
    @Autowired
    private TareaService service;

    /**
     * Controlador "listar()" que muestra todas las tareas registradas
     * @return Lista de todas las tareas
     */
    @GetMapping("tarea/")
    public List<Tarea> listar(){
        return service.listar();
    }//Cierre controlador listar()

    /**
     * Controlador "detalle" que lista los datos de tareas por id
     * @param id Define el parametro que busca la tarea
     * @return tarea por id
     */
    @GetMapping("tarea/{id}")
    //Respuesta solicitud de información ciudadano por id.
    public ResponseEntity<?> detalle(@PathVariable Long id){
        Optional<Tarea> tareaOptional=service.porId(id);
        Tarea c =null;
        Map<String, Object> response = new HashMap<>();

        if(tareaOptional.isPresent()){
            return ResponseEntity.ok(tareaOptional.get());
        }
        if(c == null) {
            response.put("mensaje", "El ciudadano ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.notFound().build();
    }//Cierre controlador "detalle"

    /**
     * Controlador "crear", agrega nuevas tareas con id autoinicrementable y validación de email
     * @param tarea Define los datos de tareas a ingresar
     * @param result Validación de errores o acción de controlador
     * @return Acción del controlador al ingresar tarea
     * @See: "controllers/TareaController - ResponseEntity<Map<String, String>> validar()"
     */
    @PostMapping("tarea/")
    public ResponseEntity<?> crear(@Valid @RequestBody Tarea tarea, BindingResult result){
        if(result.hasErrors()){
            return validar(result);  //Respuesta validación de errores desde private static "validar" (último controlador).
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(tarea));
    }//Cierre controlador "crear"

    /**
     * Controlador "editar" que modifica datos de tarea
     * @param tarea Define los datos de ciudadano a editar
     * @param result Define la entrada de datos erroneos
     * @param id Define el parametro que busca la tarea
     * @return Resultados de la validación de errores
     */
    @PutMapping("tarea/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Tarea tarea, BindingResult result,@PathVariable Long id){
        Tarea c =null;
        Optional<Tarea> o = service.porId(id);
        Map<String, Object> response = new HashMap<>();
        if(result.hasErrors()){
            return validar(result);
        }
        if(o.isPresent()){
            Tarea tareaDB =o.get();
            tareaDB.setNombre(tarea.getNombre());
            tareaDB.setDescripcion(tarea.getDescripcion());
            tareaDB.setFecha_tarea(tareaDB.getFecha_tarea());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(tareaDB));
        }
        if(c == null) {
            response.put("mensaje", "La tarea ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.notFound().build();
    }//Cierre de controlador editar usuario

    /**
     * Controlador "eliminar" seleccionando la tarea y lo elimina
     * @param id Define la tarea a eliminar
     * @return Acción de tarea eliminada
     */
    @DeleteMapping("tarea/{id}")
    public ResponseEntity<?> eliminar( @PathVariable Long id){
        Optional<Tarea> o =service.porId(id);
        Tarea c =null;
        Map<String, Object> response = new HashMap<>();
        if(o.isPresent()){
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        if(c == null) {
            response.put("mensaje", "La tarea ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.notFound().build();
    }//Cierre de controlador eliminar

    /**
     * Controlador de errores
     * @param result Define los mensajes de error
     * @return Mensajes de error
     */
    private static ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores=new HashMap<>();
        result.getFieldErrors().forEach(err->{
            errores.put(err.getField(),"El campo "+err.getField()+" "+err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }//Fin controlador errores
}//Fin clase TareaControllers

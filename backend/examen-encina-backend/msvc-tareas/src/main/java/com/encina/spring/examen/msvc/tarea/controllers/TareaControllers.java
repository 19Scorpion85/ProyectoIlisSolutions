package com.encina.spring.examen.msvc.tarea.controllers;
import com.encina.spring.examen.msvc.tarea.models.Ciudadano;
import com.encina.spring.examen.msvc.tarea.models.entities.Tarea;
import com.encina.spring.examen.msvc.tarea.services.TareaService;
import feign.FeignException;
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
@CrossOrigin(origins = "http://localhost:4200")
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
        Map<String, Object> response = new HashMap<>();
        if(result.hasErrors()){
            return validar(result);  //Respuesta validación de errores desde private static "validar" (último controlador).
        }
        response.put("mensaje", " la tarea fue agregada exitosamente!");
        service.guardar(tarea);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
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
            tareaDB.setDia_semana(tarea.getDia_semana());
            response.put("mensaje", "La tarea ID: ".concat(id.toString().concat(" fue actualizada exitosamente!")));
            service.guardar(tareaDB);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
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
            response.put("mensaje", "La tarea ID: ".concat(id.toString().concat(" fue eliminada exitosamente!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }
        if(c == null) {
            response.put("mensaje", "La tarea ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.notFound().build();
    }//Cierre de controlador eliminar

    /**
     * Controlador "asignaCiudadano" para interacción con msvc-ciudadano
     * @param tareaId Define la tarea que asignará
     * @return Acción tarea asignada a usuario
     */
    @PutMapping("/asignar-ciudadano/{tareaId}")
    public ResponseEntity<?> asignarCiudadano(@RequestBody Ciudadano ciudadano, @PathVariable Long tareaId){
        Optional<Ciudadano> o;
        try{
            o=service.asignarCiudadano(ciudadano, tareaId);
        }catch(FeignException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("mensaje"," " +
                    "el ciudadano no pudo ser asignado: "+e.getMessage()));
        }
        if(o.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
        }
        return ResponseEntity.notFound().build();
    }//Cierre de controlador

    /**
     * Controlador "creaCiudadano" para interacción con msvc-ciudadano
     * @param tareaId Define la tarea para creación de ciudadano
     * @return Acción crea a ciudadano
     */
    @PostMapping("/crear-ciudadano/{tareaId}")
    public ResponseEntity<?> crearCiudadano(@RequestBody Ciudadano ciudadano, @PathVariable Long tareaId){
        Optional<Ciudadano> o;
        try{
            o=service.crearCiudadano(ciudadano, tareaId);
        }catch(FeignException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("mensaje","No se pudo crear " +
                    "o error en al comunicación: "+e.getMessage()));
        }
        if(o.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
        }
        return ResponseEntity.notFound().build();
    }//Cierre de controlador

    /**
     * Controlador "eliminar" seleccionando al ciudadano que no realizará la tarea
     * @param tareaId Define ciudadano a eliminar
     * @return Acción de ciudadano eliminada
     */
    @DeleteMapping("tarea/eliminar-ciudadano/{tareaId}")
    public ResponseEntity<?> eliminarCiudadano(@RequestBody Ciudadano ciudadano,@PathVariable Long tareaId){
        Optional<Ciudadano> o;
        try{
            o=service.eliminarCiudadano(ciudadano, tareaId);
        }catch(FeignException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("mensaje"," " +
                    "No pudo eliminar al ciudadano: "+e.getMessage()));
        }
        if(o.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(o.get());
        }
        return ResponseEntity.notFound().build();
    }//Cierre de controlador

    /**
     * Controlador "eliminar" seleccionando la tarea y lo elimina
     * @param id Define la tarea a eliminar
     * @return Acción de tarea eliminada
     */
    @DeleteMapping("tarea/eliminar-tarea-ciudadano/{id}")
    public ResponseEntity<?> eliminarTareaCiudadanoPorId(@PathVariable Long id){
        service.eliminarTareaCiudadanoPorId(id);
        return ResponseEntity.noContent().build();
    }//Cierre de controlador

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
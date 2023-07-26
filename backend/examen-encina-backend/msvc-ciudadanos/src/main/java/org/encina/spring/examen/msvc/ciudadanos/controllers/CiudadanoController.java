package org.encina.spring.examen.msvc.ciudadanos.controllers;
import org.encina.spring.examen.msvc.ciudadanos.models.entities.Ciudadano;
import org.encina.spring.examen.msvc.ciudadanos.services.CiudadanoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.*;
/**
 * Clase "CiudadanoController", administra controladores de ciudadano
 * @author: Andrés Encina
 * @version: 24/07/2023/A
 * @See: "org.encina.spring.examen.msvc.usuarios/services/CiudadanoService"
 */
//@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = {"Content-Type", "Authorization"})
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CiudadanoController {
    //Inicio controladores
    @Autowired
    private CiudadanoService service;

    /**
     * Controlador "listar()" que muestra todos los ciudadanos registrados
     * @return Lista de todos los ciudadanos
     */
    @GetMapping("ciudadano/")
    public List<Ciudadano> listar(){
        return service.listar();
    }//Cierre controlador listar()

    /**
     * Controlador "detalle" que lista los datos de ciudadanos por id
     * @param id Define el parametro que busca al ciudadanos
     * @return ciudadano por id
     */
    @GetMapping("ciudadano/{id}")
    //Respuesta solicitud de información ciudadano por id.
    public ResponseEntity<?> detalle(@PathVariable Long id){
        Optional<Ciudadano> ciudadanoOptional=service.porId(id);
        Ciudadano c =null;
        Map<String, Object> response = new HashMap<>();
        if(ciudadanoOptional.isPresent()){
            return ResponseEntity.ok(ciudadanoOptional.get());
        }
        if(c == null) {
            response.put("mensaje", "El ciudadano ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.notFound().build();
    }//Cierre controlador "detalle"

    /**
     * Controlador "crear", agrega nuevos ciudadano con id autoinicrementable y validación de email
     * @param ciudadano Define los datos de ciudadano a ingresar
     * @param result Validación de errores o acción de controlador
     * @return Acción del controlador al ingresar ciudadano
     * @See: "controllers/CiudadanoController - ResponseEntity<Map<String, String>> validar()"
     */
    @PostMapping("ciudadano/")
    //Respuesta pública que recibe parametros para creación de nuevo ciudadano.
    public ResponseEntity<?> crear(@Valid @RequestBody Ciudadano ciudadano, BindingResult result){
        Map<String, Object> response = new HashMap<>();
        if(result.hasErrors()){
            return validar(result);  //Respuesta validación de errores desde private static "validar" (último controlador).
        }
        //Condición si existe email repetido en la base de datos.
        if(!ciudadano.getEmail().isEmpty() && service.porEmail(ciudadano.getEmail()).isPresent()){
            return ResponseEntity.badRequest()
                    .body(Collections
                            .singletonMap("error","Ya existe un ciudadano con ese correo electrónico"));
        }
        response.put("mensaje", " Agregado exitosamente!");
        service.guardar(ciudadano);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }//Cierre controlador "crear"

    /**
     * Controlador "editar" que modifica datos de ciudadano
     * @param ciudadano Define los datos de ciudadano a editar
     * @param result Define la entrada de datos erroneos
     * @param id Define el parametro que busca al ciudadano
     * @return Resultados de la validación de errores
     */
    @PutMapping("ciudadano/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Ciudadano ciudadano, BindingResult result,@PathVariable Long id){
        Ciudadano c =null;
        Map<String, Object> response = new HashMap<>();
        Optional<Ciudadano> o = service.porId(id);
        if(result.hasErrors()){
            return validar(result);
        }
        if(o.isPresent()){
            Ciudadano ciudadanoDB =o.get();
            //Validación si existe un registro de emails iguales en la bd.
            if(!ciudadano.getEmail().isEmpty() && !ciudadano.getEmail().equalsIgnoreCase(ciudadanoDB.getEmail())
                    && service.porEmail(ciudadano.getEmail()).isPresent())
            {
                return ResponseEntity.badRequest().body(Collections
                        .singletonMap("mensaje","Ya existe un ciudadano con ese correo electrónico"));
            }
            ciudadanoDB.setNombre(ciudadano.getNombre());
            ciudadanoDB.setApellido_paterno(ciudadano.getApellido_paterno());
            ciudadanoDB.setApellido_materno(ciudadano.getApellido_materno());
            ciudadanoDB.setEmail(ciudadano.getEmail());
            response.put("mensaje", "El ID: ".concat(id.toString().concat(" fue actualizado exitosamente!")));
            service.guardar(ciudadanoDB);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
        }
        if(c == null) {
            response.put("mensaje", "El ciudadano ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.notFound().build();
    }//Cierre de controlador editar usuario

    /**
     * Controlador "eliminar" seleccionando al ciudadano y lo elimina
     * @param id Define el iudadano a eliminar
     * @return Acción de ciudadano eliminado
     */
    @DeleteMapping("ciudadano/{id}")
    //Respuesta pública que elimina al ciudadano después de identificarlo por id.
    public ResponseEntity<?> eliminar( @PathVariable Long id){
        Optional<Ciudadano> o =service.porId(id);
        Ciudadano c =null;
        Map<String, Object> response = new HashMap<>();
        if(o.isPresent()){
            service.eliminar(id);
            response.put("mensaje", "El ID: ".concat(id.toString().concat(" fue eliminado exitosamente!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }
        if(c == null) {
            response.put("mensaje", "El ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.notFound().build();
    }//Cierre de controlador eliminar usuario

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
}//Fin clase CiudadanoController
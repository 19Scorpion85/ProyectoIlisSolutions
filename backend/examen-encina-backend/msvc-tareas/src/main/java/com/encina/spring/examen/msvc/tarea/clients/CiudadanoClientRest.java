package com.encina.spring.examen.msvc.tarea.clients;

import com.encina.spring.examen.msvc.tarea.models.Ciudadano;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="msvc-ciudadanos", url="localhost:8001")
public interface CiudadanoClientRest {
    @GetMapping("/{id}")
    Ciudadano detalle(@PathVariable Long id);

    @PostMapping("/")
    Ciudadano crear(@RequestBody Ciudadano ciudadano);

    @GetMapping("/ciudadanos-por-tarea")
    List<Ciudadano> obtenerCiudadanoPorTarea(@RequestParam Iterable<Long> ids);
}

package com.encina.spring.examen.msvc.tarea.service;

import com.encina.spring.examen.msvc.tarea.models.entities.Tarea;

import java.util.List;
import java.util.Optional;

public interface TareaService {
    List<Tarea> Listar();
    Optional<Tarea> porId(Long id);
    Tarea guardar(Tarea tarea);
    void eliminar(Long id);
}

package com.encina.spring.examen.msvc.tarea.services;

import com.encina.spring.examen.msvc.tarea.models.entities.Tarea;
import com.encina.spring.examen.msvc.tarea.repositories.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class TareaServiceImpl implements  TareaService{

    @Autowired
    private TareaRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Tarea> Listar() {
        return (List<Tarea>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Tarea> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Tarea guardar(Tarea tarea) {
        return repository.save(tarea);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
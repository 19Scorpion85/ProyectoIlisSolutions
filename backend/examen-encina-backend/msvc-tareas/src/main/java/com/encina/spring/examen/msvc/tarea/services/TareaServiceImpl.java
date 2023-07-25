package com.encina.spring.examen.msvc.tarea.services;
import com.encina.spring.examen.msvc.tarea.models.Ciudadano;
import com.encina.spring.examen.msvc.tarea.models.entities.Tarea;
import com.encina.spring.examen.msvc.tarea.repositories.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
/**
 * Interface "CiudadanoServiceImpl" con extenxión a repositorios
 * @author: Andrés Encina
 * @version: 24/07/2023/A
 * @See: "org.encina.spring.examen.msvc.ciudadanos/services/CiudadanoService"
 */
@Service
public class TareaServiceImpl implements  TareaService {

    @Autowired
    private TareaRepository repository;

    @Override
    @Transactional(readOnly=true)
    public List<Tarea> listar() {
        return (List<Tarea>)repository.findAll();
    }

    @Override
    @Transactional(readOnly=true)
    public Optional<Tarea> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Tarea guardar(Tarea tarea) {
        return repository.save(tarea);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Ciudadano> asignarCiudadano(Ciudadano ciudadano, Long ciudadanoId) {
        return Optional.empty();
    }

    @Override
    public Optional<Ciudadano> crearCiudadano(Ciudadano ciudadano, Long ciudadanoId) {
        return Optional.empty();
    }

    @Override
    public Optional<Ciudadano> eliminarCiudadano(Ciudadano ciudadano, Long ciudadanoId) {
        return Optional.empty();
    }

    @Override
    @Transactional(readOnly=true)
    //Implementa llamado a tareas por ids seleccionados y los envía a TareaService.
    public List<Tarea> listarPorIds(Iterable<Long> ids) {
        return (List<Tarea>) repository.findAllById(ids);
    }




}//Cierre de la clase de implementación
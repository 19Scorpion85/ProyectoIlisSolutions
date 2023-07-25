package com.encina.spring.examen.msvc.tarea.services;
import com.encina.spring.examen.msvc.tarea.clients.CiudadanoClientRest;
import com.encina.spring.examen.msvc.tarea.models.Ciudadano;
import com.encina.spring.examen.msvc.tarea.models.entities.Tarea;
import com.encina.spring.examen.msvc.tarea.models.entities.TareaCiudadano;
import com.encina.spring.examen.msvc.tarea.repositories.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Autowired
    private CiudadanoClientRest client;

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

    //Metodos de asignaciones de usuario a curso (CRUD) desde CursoService
    @Override
    @Transactional
    public Optional<Ciudadano> asignarCiudadano(Ciudadano usuario, Long cursoId) {
        Optional<Tarea> o =repository.findById(cursoId);
        if(o.isPresent()){
            Ciudadano ciudadanoMsvc = client.detalle(usuario.getId());
            Tarea tarea = o.get();
            TareaCiudadano tareaCiudadano=new TareaCiudadano();
            tareaCiudadano.setCiudadanoId(ciudadanoMsvc.getId());
            tarea.addTareaCiudadano(tareaCiudadano);
            repository.save(tarea);
            return Optional.of(ciudadanoMsvc);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Ciudadano> crearCiudadano(Ciudadano ciudadano, Long tareaId) {
        Optional<Tarea>o=repository.findById(tareaId);
        if(o.isPresent()){
            Ciudadano ciudadanoNuevoMsvc = client.crear(ciudadano);
            Tarea tarea = o.get();
            TareaCiudadano tareaCiudadano=new TareaCiudadano();
            tareaCiudadano.setCiudadanoId(ciudadanoNuevoMsvc.getId());
            tarea.addTareaCiudadano(tareaCiudadano);
            repository.save(tarea);
            return Optional.of(ciudadanoNuevoMsvc);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Ciudadano> eliminarCiudadano(Ciudadano ciudadano, Long tareaId) {
        Optional<Tarea>o=repository.findById(tareaId);
        if(o.isPresent()){
            Ciudadano ciudadanoMsvc = client.detalle(ciudadano.getId());
            Tarea tarea = o.get();
            TareaCiudadano tareaCiudadano=new TareaCiudadano();
            tareaCiudadano.setCiudadanoId(ciudadanoMsvc.getId());
            tarea.removeTareaCiudadano(tareaCiudadano);
            repository.save(tarea);
            return Optional.of(ciudadanoMsvc);
        }
        return Optional.empty();
    }

    @Override
    @Transactional(readOnly = true)
    //Recolecta lista completa desde microservicio ciudadano
    public Optional<Tarea> porIdConCiudadanos(Long id) {
        Optional<Tarea> o= repository.findById(id);
        if(o.isPresent()){
            Tarea tarea= o.get();
            if(!tarea.getCiudadanos().isEmpty()){
                List<Long> ids = tarea.getTareaCiudadanos().stream().map(cu ->cu.getCiudadanoId())
                        .collect(Collectors.toList());
                List<Ciudadano> ciudadanos=client.obtenerCiudadanoPorTarea(ids);
                tarea.setCiudadanos(ciudadanos);
            }
            return Optional.of(tarea);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public void eliminarTareaCiudadanoPorId(Long id) {
        repository.eliminarTareaCiudadanoPorId(id);
    }

    @Override
    @Transactional(readOnly=true)
    //Implementa llamado a tareas por ids seleccionados y los envía a TareaService.
    public List<Tarea> listarPorIds(Iterable<Long> ids) {
        return (List<Tarea>) repository.findAllById(ids);
    }

}//Cierre de la clase de implementación
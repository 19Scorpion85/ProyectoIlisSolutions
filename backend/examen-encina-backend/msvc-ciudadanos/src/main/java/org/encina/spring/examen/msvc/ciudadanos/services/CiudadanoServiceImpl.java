package org.encina.spring.examen.msvc.ciudadanos.services;

import org.encina.spring.examen.msvc.ciudadanos.models.entities.Ciudadano;
import org.encina.spring.examen.msvc.ciudadanos.repositories.CiudadanoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
/*
 * Clase service implementación de Ciudadano.
 * Implementa los CRUD de CiudadanoRepository enlazado con CiudadanoService hacia CiudadanoController.
 * Valida existencia repetitiva de email.
 * @author Andrés Encina.
 */
@Service
public class CiudadanoServiceImpl implements CiudadanoService{

    @Autowired
    private CiudadanoRepository repository;

    @Override
    @Transactional(readOnly=true)
    public List<Ciudadano> listar() {
        return (List<Ciudadano>)repository.findAll();
    }

    @Override
    @Transactional(readOnly=true)
    public Optional<Ciudadano> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Ciudadano guardar(Ciudadano ciudadano) {
        return repository.save(ciudadano);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly=true)
    public List<Ciudadano> listarPorIds(Iterable<Long> ids) {
        return (List<Ciudadano>) repository.findAllById(ids);//Implementa llamado a usuarios por ids seleccionados y los envía a UsuarioService.
    }

    @Override
    public Optional<Ciudadano> porEmail(String email) {
        return repository.findByEmail(email);
    }



}

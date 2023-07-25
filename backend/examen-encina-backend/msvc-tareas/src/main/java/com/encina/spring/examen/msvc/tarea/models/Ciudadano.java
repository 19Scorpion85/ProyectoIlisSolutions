package com.encina.spring.examen.msvc.tarea.models;

/**
 * Clase "Ciudadano" modelo para conectar con msvc-ciudadanos
 * de la base de datos para conectar tareas con usuario.
 * @author: Andrés Encina.
 * @version: 25/07/2023/A
 */
public class Ciudadano {

    private Long id;

    private String nombre;

    private String apellido_paterno;

    private String apellido_materno;

    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

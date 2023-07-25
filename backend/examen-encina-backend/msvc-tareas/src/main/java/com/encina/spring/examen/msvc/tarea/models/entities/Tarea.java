package com.encina.spring.examen.msvc.tarea.models.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="tareas")
public class Tarea {

   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String descripcion;

    private Date fecha_tarea;

    public Tarea(Long id, String nombre, String descripcion, Date fecha_tarea) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha_tarea = fecha_tarea;
    }

    public Tarea() {
    }

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha_tarea() {
        return fecha_tarea;
    }

    public void setFecha_tarea(Date fecha_tarea) {
        this.fecha_tarea = fecha_tarea;
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fecha_tarea=" + fecha_tarea +
                '}';
    }
}

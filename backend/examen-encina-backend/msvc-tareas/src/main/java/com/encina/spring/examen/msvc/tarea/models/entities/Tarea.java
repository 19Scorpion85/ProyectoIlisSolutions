package com.encina.spring.examen.msvc.tarea.models.entities;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
/**
 * Clase "Tarea" con atributos para la tabla 'tareas' de la base de datos.
 * @author: Andrés Encina.
 * @version: 25/07/2023/A
 */
@Entity
@Table(name="tareas")
public class Tarea {
    //Campos de la clase
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message="no puede estar vacio")
    @Size(min=4, max=20, message="debe tener entre 4 y 20 caracteres")
    private String nombre;
    @NotBlank(message="no puede estar vacio")
    @Size(min=4, message="debe tener más de 4 caracteres")
    private String descripcion;
    @NotBlank(message="no puede estar vacio")
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
}//Cierre de la clase

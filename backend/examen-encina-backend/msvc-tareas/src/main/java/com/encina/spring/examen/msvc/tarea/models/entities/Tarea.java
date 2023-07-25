package com.encina.spring.examen.msvc.tarea.models.entities;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private String dia_semana;

    private LocalDateTime fecha_creacion = LocalDateTime.now();

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    private List<TareaCiudadano> tareaCiudadanos;



    public Tarea(Long id, String nombre, String descripcion, String dia_semana, LocalDateTime fecha_creacion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.dia_semana = dia_semana;
        this.fecha_creacion = fecha_creacion;
    }

    public Tarea() {
        tareaCiudadanos=new ArrayList<>();
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

    public String getDia_semana() {
        return dia_semana;
    }

    public void setDia_semana(String dia_semana) {
        this.dia_semana = dia_semana;
    }

    public LocalDateTime getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(LocalDateTime fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public void addTareaCiudadano(TareaCiudadano tareaCiudadano){
        tareaCiudadanos.add(tareaCiudadano);
    }

    public void removeTareaCiudadano(TareaCiudadano tareaCiudadano){
        tareaCiudadanos.remove(tareaCiudadano);
    }

    public List<TareaCiudadano> getTareaCiudadanos() {
        return tareaCiudadanos;
    }

    public void setTareaCiudadanos(List<TareaCiudadano> tareaCiudadanos) {
        this.tareaCiudadanos = tareaCiudadanos;
    }
}//Cierre de la clase

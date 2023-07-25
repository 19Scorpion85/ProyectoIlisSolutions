package com.encina.spring.examen.msvc.tarea.models.entities;
import com.encina.spring.examen.msvc.tarea.models.Ciudadano;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "tarea_id")
    private List<TareaCiudadano> tareaCiudadanos;   //Configuración cascada
    @Transient
    private  List<Ciudadano> ciudadanos;

    public Tarea() {
        tareaCiudadanos=new ArrayList<>();
        ciudadanos=new ArrayList<>();
    }

    public List<Ciudadano> getCiudadanos() {
        return ciudadanos;
    }

    public void setCiudadanos(List<Ciudadano> ciudadanos) {
        this.ciudadanos = ciudadanos;
    }

    public List<TareaCiudadano> getTareaCiudadanos() {
        return tareaCiudadanos;
    }

    public void addTareaCiudadano(TareaCiudadano tareaCiudadano){
        tareaCiudadanos.add(tareaCiudadano);
    }

    public void removeTareaCiudadano(TareaCiudadano tareaCiudadano){
        tareaCiudadanos.remove(tareaCiudadano);
    }

    public void setTareaCiudadanos(List<TareaCiudadano> tareaCiudadanos) {
        this.tareaCiudadanos = tareaCiudadanos;
    }
    //Término de cascada


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
}//Cierre de la clase

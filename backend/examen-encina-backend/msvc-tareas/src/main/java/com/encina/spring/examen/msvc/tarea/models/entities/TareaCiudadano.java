package com.encina.spring.examen.msvc.tarea.models.entities;

import javax.persistence.*;
/**
 * Clase "TareaCiudadano" con atributos para la tabla 'tareas_ciudadanos'
 * de la base de datos para conectar tareas con usuario.
 * @author: Andrés Encina.
 * @version: 25/07/2023/A
 */
@Entity
@Table(name="tareas_ciudadanos")
public class TareaCiudadano {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="ciudadano_id",unique = true)
    private Long ciudadanoId;

    public TareaCiudadano(Long id, Long ciudadanoId) {
        this.id = id;
        this.ciudadanoId = ciudadanoId;
    }

    public TareaCiudadano() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCiudadanoId() {
        return ciudadanoId;
    }

    public void setCiudadanoId(Long ciudadanoId) {
        this.ciudadanoId = ciudadanoId;
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj){
            return true;
        }
        if(!(obj instanceof TareaCiudadano)){
            return false;
        }
        TareaCiudadano o= (TareaCiudadano) obj;
        return this.ciudadanoId!=null && this.ciudadanoId.equals(o.ciudadanoId);
    }
}//Cierre de la clase
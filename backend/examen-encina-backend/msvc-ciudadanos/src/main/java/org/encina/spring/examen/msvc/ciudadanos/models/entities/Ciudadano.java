package org.encina.spring.examen.msvc.ciudadanos.models.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/*
 * Clase con parametros de Ciudadano.
 * Crea la tabla 'usuarios' en la base de datos msvc_ciudadanos.
 * Valida datos con anotaciones antes de actualizar tabla.
 * @author Andrés Encina.
 */
@Entity
@Table(name="ciudadanos")
public class Ciudadano {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message="no puede estar vacio")
    @Size(min=4, max=12, message="debe tener entre 4 y 12 caracteres")
    private String nombre;
    @NotBlank(message="no puede estar vacio")
    @Size(min=4, max=12, message="debe tener entre 4 y 12 caracteres")
    private String apellido_paterno;
    @NotBlank(message="no puede estar vacio")
    @Size(min=4, max=12, message="debe tener entre 4 y 12 caracteres")
    private String apellido_materno;

    @NotBlank(message="no puede estar vacio")
    @Size(min=8, message="Rut debe ser sin puntos ni digito verificador")
    private String rut;

    @Email
    @Column(unique =true)
    @NotBlank(message="no puede estar vacio")
    private String email;

    public Ciudadano(Long id, String nombre, String apellido_paterno, String apellido_materno, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.email = email;
    }

    public Ciudadano() {
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

    @Override
    public String toString() {
        return "Ciudadano{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido_paterno='" + apellido_paterno + '\'' +
                ", apellido_materno='" + apellido_materno + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

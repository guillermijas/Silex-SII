package entrega1;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    //usuario
    private String username;
    private String password;
    
    //datos
    private String dni;
    private String nombre;
    private String apellidos;
    private Long telefono;
    private String dirección;
    private String email;
    private String rol;
    
    //rol supervisor
    private String zonaCargo;

    //rol operario
    private String tipo;
    private String especializacion;
    private boolean disponibilidad;

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public Long getTelefono() {
        return telefono;
    }

    public String getDirección() {
        return dirección;
    }

    public String getEmail() {
        return email;
    }

    public String getRol() {
        return rol;
    }

    public String getZonaCargo() {
        return zonaCargo;
    }

    public String getTipo() {
        return tipo;
    }

    public String getEspecializacion() {
        return especializacion;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public void setDirección(String dirección) {
        this.dirección = dirección;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setZonaCargo(String zonaCargo) {
        this.zonaCargo = zonaCargo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setEspecializacion(String especializacion) {
        this.especializacion = especializacion;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.dni);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.dni, other.dni)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario{" + "dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", telefono=" + telefono + ", direcci\u00f3n=" + dirección + ", email=" + email + ", rol=" + rol + ", zonaCargo=" + zonaCargo + ", tipo=" + tipo + ", especializacion=" + especializacion + ", disponibilidad=" + disponibilidad + '}';
    }
    
    
}

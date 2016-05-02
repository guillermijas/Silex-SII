package entrega1;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

@Entity
public class Supervisor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String dni;
    private String nombre;
    private String apellidos;
    private Long telefono;
    private String dirección;
    private String email;
    private String zonaCargo;
    @OneToMany(targetEntity = Aviso.class)
    private List<Aviso> listaAvisos;
    @OneToMany(targetEntity = OrdenDeTrabajo.class)
    private List<OrdenDeTrabajo> ordentrabajo;

    public Supervisor() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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

    public String getZonaCargo() {
        return zonaCargo;
    }

    public List<OrdenDeTrabajo> getOrdentrabajo() {
        return ordentrabajo;
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

    public void setZonaCargo(String zonaCargo) {
        this.zonaCargo = zonaCargo;
    }

    public List<Aviso> getListaAvisos() {
        return this.listaAvisos;
    }

    public void setListaAvisos(List<Aviso> l) {
        this.listaAvisos = l;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.dni);
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
        final Supervisor other = (Supervisor) obj;
        if (!Objects.equals(this.dni, other.dni)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Supervisor{" + "dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", telefono=" + telefono + ", direcci\u00f3n=" + dirección + ", email=" + email + ", zonaCargo=" + zonaCargo + '}';
    }

}

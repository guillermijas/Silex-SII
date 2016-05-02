package entrega1;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

@Entity
public class Operario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String dni;
    private String nombre;
    private String apellidos;
    private Long telefono;
    private String dirección;
    private String email;
    private String tipo;
    private String especializacion;
    private boolean disponibilidad;

    @ManyToMany
    @JoinTable(name = "OPERARIO_ORDENTRABAJO",
            joinColumns = @JoinColumn(name = "operario_fk"),
            inverseJoinColumns = @JoinColumn(name = "ordendetrabajo_fk"))
    private List<OrdenDeTrabajo> ordenesTrabajo;

    @OneToMany(mappedBy = "operario", fetch = FetchType.LAZY, targetEntity = Aviso.class)
    private List<Aviso> listaAvisos;

    @ManyToOne(targetEntity = Operario.class)
    private Operario capataz;

    @OneToMany(targetEntity = Operario.class)
    List<Operario> operariosRelacionados;

    public Operario() {
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

    public String getTipo() {
        return tipo;
    }

    public String getEspecializacion() {
        return especializacion;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public List<OrdenDeTrabajo> getOrdenesTrabajo() {
        return ordenesTrabajo;
    }

    public List<Aviso> getListaAvisos() {
        return listaAvisos;
    }

    public Operario getCapataz() {
        return capataz;
    }

    public List<Operario> getOperariosRelacionados() {
        return operariosRelacionados;
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

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setEspecializacion(String especializacion) {
        this.especializacion = especializacion;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public void setOrdenesTrabajo(List<OrdenDeTrabajo> ordenesTrabajo) {
        this.ordenesTrabajo = ordenesTrabajo;
    }

    public void setListaAvisos(List<Aviso> listaAvisos) {
        this.listaAvisos = listaAvisos;
    }

    public void setCapataz(Operario capataz) {
        this.capataz = capataz;
    }

    public void setOperariosRelacionados(List<Operario> operariosRelacionados) {
        this.operariosRelacionados = operariosRelacionados;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.dni);
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
        final Operario other = (Operario) obj;
        if (!Objects.equals(this.dni, other.dni)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Operario{" + "dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", telefono=" + telefono + ", direcci\u00f3n=" + dirección + ", email=" + email + ", tipo=" + tipo + ", especializacion=" + especializacion + ", disponibilidad=" + disponibilidad + '}';
    }

}

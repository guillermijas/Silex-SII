package entrega1;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String username;
    
    @OneToMany(targetEntity = Aviso.class)
    private List<Aviso> listaAvisos_CallCenter;
    
    @OneToMany(targetEntity = Aviso.class)
    private List<Aviso> listaAvisos_supervisor;
    /*
    @OneToMany(targetEntity = OrdenDeTrabajo.class)
    private List<OrdenDeTrabajo> ordentrabajo_supervisor;
    */

    @ManyToMany
    @JoinTable(name = "usuario_ordenesTrabajo", joinColumns = @JoinColumn(name = "usuario_fk"), inverseJoinColumns = @JoinColumn(name = "ordenes_fk"))
    private List<OrdenDeTrabajo> usuario_ordenesTrabajo;

    @OneToMany(mappedBy = "operario", fetch = FetchType.LAZY, targetEntity = Aviso.class)
    private List<Aviso> listaAvisos_operario;

    public List<Aviso> getListaAvisos_CallCenter() {
        return listaAvisos_CallCenter;
    }

    public void setListaAvisos_CallCenter(List<Aviso> listaAvisos_CallCenter) {
        this.listaAvisos_CallCenter = listaAvisos_CallCenter;
    }

    public List<Aviso> getListaAvisos_supervisor() {
        return listaAvisos_supervisor;
    }

    public void setListaAvisos_supervisor(List<Aviso> listaAvisos_supervisor) {
        this.listaAvisos_supervisor = listaAvisos_supervisor;
    }

    public List<OrdenDeTrabajo> getUsuario_ordenesTrabajo() {
        return usuario_ordenesTrabajo;
    }

    public void setUsuario_ordenesTrabajo(List<OrdenDeTrabajo> usuario_ordenesTrabajo) {
        this.usuario_ordenesTrabajo = usuario_ordenesTrabajo;
    }

    public List<Aviso> getListaAvisos_operario() {
        return listaAvisos_operario;
    }

    public void setListaAvisos_operario(List<Aviso> listaAvisos_operario) {
        this.listaAvisos_operario = listaAvisos_operario;
    }

    public Usuario getCapataz() {
        return capataz;
    }

    public void setCapataz(Usuario capataz) {
        this.capataz = capataz;
    }

    @ManyToOne(targetEntity = Usuario.class)
    private Usuario capataz;

    @OneToMany(targetEntity = Usuario.class)
    List<Usuario> operariosRelacionados;

    // Usuario
    
    private String password;
    private String rol;

    // Datos
    private String dni;
    private String nombre;
    private String apellidos;
    private Long telefono;
    private String dirección;
    private String email;
    private String sexo;

    //rol supervisor
    private String zonaCargo;

    //rol operario
    private String tipo;
    private String especializacion;
    private boolean disponibilidad;
    
    // Para el registro, rellenable de forma automática
    private String cadenaValidacion = null;
    private boolean registroOk = false;

    public Usuario() {

    }

    public Usuario(String username, String password, String rol) {
        this.username = username;
        this.password = password;
        this.rol = rol;
    }
    
    public boolean isRegistroOk() {
        return registroOk;
    }

    public void setRegistroOk(boolean registroOk) {
        this.registroOk = registroOk;
    }
    
    public List<OrdenDeTrabajo> getOrdenesTrabajo() {
        return usuario_ordenesTrabajo;
    }

    public void setOrdenesTrabajo(List<OrdenDeTrabajo> ordenes) {
        this.usuario_ordenesTrabajo = ordenes;
    }

    public String getUsername() {
        return this.username;
    }
    
    public String getCadenaValidacion()
    {
        return this.cadenaValidacion;
    }
    
    public void setCadenaValidacion(String cad)
    {
        this.cadenaValidacion = cad;
    }

    public void setUsername(String user) {
        this.username = user;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String pass) {
        this.password = pass;
    }

    public String getDni() {
        return dni;
    }
    
    public String getSexo(){
        return this.sexo;
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

    public boolean getDisponibilidad() {
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
    
    public void setSexo(String sex)
    {
        this.sexo = sex;
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

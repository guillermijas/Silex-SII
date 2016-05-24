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
    @OneToMany(targetEntity = OrdenDeTrabajo.class)
    private List<OrdenDeTrabajo> ordentrabajo_supervisor;
    @ManyToMany
    @JoinTable(name = "usuario_ordenesTrabajo", joinColumns = @JoinColumn(name = "usuario_fk"), inverseJoinColumns = @JoinColumn(name = "ordenes_fk"))
    private List<OrdenDeTrabajo> usuario_ordenesTrabajo;
    @OneToMany(mappedBy = "operario", fetch = FetchType.LAZY, targetEntity = Aviso.class)
    private List<Aviso> listaAvisos_operario;
    @ManyToOne(targetEntity = Usuario.class)
    private Usuario capataz;
    @OneToMany(targetEntity = Usuario.class)
    List<Usuario> operariosRelacionados;

    // Usuario
    private String password;
    private Enumeraciones.Rol rol;

    // Datos
    private String dni;
    private String nombre;
    private String apellidos;
    private Long telefono;
    private String direccion;
    private String email;
    private String sexo;

    //admin
    public static final Usuario ADMIN = new Usuario("admin", "", Enumeraciones.Rol.ADMINISTRADOR);

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

    public Usuario(String username, String password, Enumeraciones.Rol rol) {
        this.username = username;
        this.password = password;
        this.rol = rol;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUsername() {
        return username;
    }

    public List<Aviso> getListaAvisos_CallCenter() {
        return listaAvisos_CallCenter;
    }

    public List<Aviso> getListaAvisos_supervisor() {
        return listaAvisos_supervisor;
    }

    public List<OrdenDeTrabajo> getOrdentrabajo_supervisor() {
        return ordentrabajo_supervisor;
    }

    public List<OrdenDeTrabajo> getUsuario_ordenesTrabajo() {
        return usuario_ordenesTrabajo;
    }

    public List<Aviso> getListaAvisos_operario() {
        return listaAvisos_operario;
    }

    public Usuario getCapataz() {
        return capataz;
    }

    public List<Usuario> getOperariosRelacionados() {
        return operariosRelacionados;
    }

    public String getPassword() {
        return password;
    }

    public Enumeraciones.Rol getRol() {
        return rol;
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

    public String getDireccion() {
        return direccion;
    }

    public String getEmail() {
        return email;
    }

    public String getSexo() {
        return sexo;
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

    public String getCadenaValidacion() {
        return cadenaValidacion;
    }

    public boolean isRegistroOk() {
        return registroOk;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setListaAvisos_CallCenter(List<Aviso> listaAvisos_CallCenter) {
        this.listaAvisos_CallCenter = listaAvisos_CallCenter;
    }

    public void setListaAvisos_supervisor(List<Aviso> listaAvisos_supervisor) {
        this.listaAvisos_supervisor = listaAvisos_supervisor;
    }

    public void setOrdentrabajo_supervisor(List<OrdenDeTrabajo> ordentrabajo_supervisor) {
        this.ordentrabajo_supervisor = ordentrabajo_supervisor;
    }

    public void setUsuario_ordenesTrabajo(List<OrdenDeTrabajo> usuario_ordenesTrabajo) {
        this.usuario_ordenesTrabajo = usuario_ordenesTrabajo;
    }

    public void setListaAvisos_operario(List<Aviso> listaAvisos_operario) {
        this.listaAvisos_operario = listaAvisos_operario;
    }

    public void setCapataz(Usuario capataz) {
        this.capataz = capataz;
    }

    public void setOperariosRelacionados(List<Usuario> operariosRelacionados) {
        this.operariosRelacionados = operariosRelacionados;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRol(Enumeraciones.Rol rol) {
        this.rol = rol;
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

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
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

    public void setCadenaValidacion(String cadenaValidacion) {
        this.cadenaValidacion = cadenaValidacion;
    }

    public void setRegistroOk(boolean registroOk) {
        this.registroOk = registroOk;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.username);
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
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario{" + "username=" + username + ", listaAvisos_CallCenter=" + listaAvisos_CallCenter + ", listaAvisos_supervisor="
                + listaAvisos_supervisor + ", ordentrabajo_supervisor=" + ordentrabajo_supervisor + ", usuario_ordenesTrabajo="
                + usuario_ordenesTrabajo + ", listaAvisos_operario=" + listaAvisos_operario + ", capataz=" + capataz + ", operariosRelacionados="
                + operariosRelacionados + ", password=" + password + ", rol=" + rol + ", dni=" + dni + ", nombre=" + nombre + ", apellidos="
                + apellidos + ", telefono=" + telefono + ", direccion=" + direccion + ", email=" + email + ", sexo=" + sexo + ", zonaCargo="
                + zonaCargo + ", tipo=" + tipo + ", especializacion=" + especializacion + ", disponibilidad=" + disponibilidad + ", cadenaValidacion="
                + cadenaValidacion + ", registroOk=" + registroOk + '}';
    }

}

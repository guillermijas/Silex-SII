package entrega1;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.persistence.*;

@Entity
public class Aviso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idAviso;
    private String descripcion;
    private String imagen;
    private String direccion;
    private String cp;
    private String municipio;
    private Coordenada localizacion;
    private boolean urgente;
    private boolean planificado;
    private Enumeraciones.estado estado;
    private Enumeraciones.prioridad prioridad;
    private Enumeraciones.gravedad gravedad;

    @Temporal(TemporalType.DATE)
    private Date fechainicio;
    @Temporal(TemporalType.DATE)
    private Date fechafin;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OT_fk")
    private OrdenDeTrabajo ordendeTrabajo;
    @ManyToOne
    private Usuario creador;
    @ManyToOne
    private Usuario callcenter;
    @ManyToOne
    private Usuario supervisor;
    @ManyToOne
    private Usuario operario;

    //datos de contacto cliente
    private String nombreCliente;
    private String telefonoCliente;
    private String emailCliente;

    public Aviso() {
        localizacion = new Coordenada();
    }

    public String getFechainicio() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(fechainicio);
    }

    public String getFechafin() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(fechafin);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getIdAviso() {
        return idAviso;
    }

    public Enumeraciones.gravedad getGravedad() {
        return gravedad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCp() {
        return cp;
    }

    public String getMunicipio() {
        return municipio;
    }

    public Coordenada getLocalizacion() {
        if(localizacion == null){
            localizacion = new Coordenada();
        }
        return localizacion;
    }

    public boolean isUrgente() {
        return urgente;
    }

    public boolean isPlanificado() {
        return planificado;
    }

    public Enumeraciones.estado getEstado() {
        return estado;
    }

    public Enumeraciones.prioridad getPrioridad() {
        return prioridad;
    }

    public OrdenDeTrabajo getOrdendeTrabajo() {
        return ordendeTrabajo;
    }

    public Usuario getCreador() {
        return creador;
    }

    public Usuario getCallcenter() {
        return callcenter;
    }

    public Usuario getSupervisor() {
        return supervisor;
    }

    public Usuario getOperario() {
        return operario;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setIdAviso(Long idAviso) {
        this.idAviso = idAviso;
    }

    public void setGravedad(Enumeraciones.gravedad gravedad) {
        this.gravedad = gravedad;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public void setLocalizacion(Coordenada localizacion) {
        this.localizacion = localizacion;
    }

    public void setUrgente(boolean urgente) {
        this.urgente = urgente;
    }

    public void setPlanificado(boolean planificado) {
        this.planificado = planificado;
    }

    public void setEstado(Enumeraciones.estado estado) {
        this.estado = estado;
    }

    public void setPrioridad(Enumeraciones.prioridad prioridad) {
        this.prioridad = prioridad;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public void setOrdendeTrabajo(OrdenDeTrabajo ordendeTrabajo) {
        this.ordendeTrabajo = ordendeTrabajo;
    }

    public void setCreador(Usuario creador) {
        this.creador = creador;
    }

    public void setCallcenter(Usuario callcenter) {
        this.callcenter = callcenter;
    }

    public void setSupervisor(Usuario supervisor) {
        this.supervisor = supervisor;
    }

    public void setOperario(Usuario operario) {
        this.operario = operario;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public void setGPS(String gps) {
        String latitud = gps.substring(0, gps.indexOf(" "));
        String longitud = gps.substring(gps.lastIndexOf(" "), gps.length());
        setLocalizacion(new Coordenada(latitud, longitud));
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.idAviso);
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
        final Aviso other = (Aviso) obj;
        if (!Objects.equals(this.idAviso, other.idAviso)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Aviso{" + "idAviso=" + idAviso + ", gravedad=" + gravedad + ", descripcion=" + descripcion + ", imagen=" + imagen + ", direccion=" + direccion + ", cp=" + cp + ", municipio=" + municipio + ", localizacion=" + localizacion + ", urgente=" + urgente + ", planificado=" + planificado + ", estado=" + estado + ", prioridad=" + prioridad + ", fechainicio=" + fechainicio + ", fechafin=" + fechafin + ", ordendeTrabajo=" + ordendeTrabajo + ", creador=" + creador + ", callcenter=" + callcenter + ", supervisor=" + supervisor + ", operario=" + operario + ", nombreCliente=" + nombreCliente + ", telefonoCliente=" + telefonoCliente + ", emailCliente=" + emailCliente + '}';
    }

}

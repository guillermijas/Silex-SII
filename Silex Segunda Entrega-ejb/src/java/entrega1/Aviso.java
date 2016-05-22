package entrega1;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

@Entity
public class Aviso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idAviso;
    private Enumeraciones.gravedad gravedad;
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
    private Cliente cliente;
    @ManyToOne
    private Usuario callcenter;
    @ManyToOne
    private Usuario supervisor;
    @ManyToOne
    private Usuario operario;

    public Aviso() {
        cliente = new Cliente();
        localizacion = new Coordenada();
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

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public boolean isUrgente() {
        return urgente;
    }

    public boolean isPlanificado() {
        return planificado;
    }

    public Date getFechafin() {
        return fechafin;
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

    public Enumeraciones.estado getEstado() {
        return estado;
    }

    public Enumeraciones.prioridad getPrioridad() {
        return prioridad;
    }

    public OrdenDeTrabajo getOrdendeTrabajo() {
        return ordendeTrabajo;
    }

    public Cliente getCliente() {
        return cliente;
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

    public Coordenada getLocalizacion() {
        return localizacion;
    }

    public String getGPS() {
        return localizacion.toString();
    }

    public Usuario getCreador() {
        return creador;
    }

    public void setCreador(Usuario creador) {
        this.creador = creador;
    }

    
    
    public void setGPS(String gps) {
        String latitud = gps.substring(0, gps.indexOf(" "));
        String longitud = gps.substring(gps.lastIndexOf(" "), gps.length());
        setLocalizacion(new Coordenada(latitud, longitud));
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

    public void setUrgente(boolean urgente) {
        this.urgente = urgente;
    }

    public void setLocalizacion(Coordenada localizacion) {
        this.localizacion = localizacion;
    }

    public void setPlanificado(boolean planificado) {
        this.planificado = planificado;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setEstado(Enumeraciones.estado estado) {
        this.estado = estado;
    }

    public void setPrioridad(Enumeraciones.prioridad prioridad) {
        this.prioridad = prioridad;
    }

    public void setOrdenTrabajo(OrdenDeTrabajo odt) {
        this.ordendeTrabajo = odt;
    }

    public OrdenDeTrabajo getOrdenTrabajo() {
        return this.ordendeTrabajo;
    }

    public void setOrdendeTrabajo(OrdenDeTrabajo ordendeTrabajo) {
        this.ordendeTrabajo = ordendeTrabajo;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getCp() {
        return cp;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
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
        return "Aviso{" + "idAviso=" + idAviso + ", gravedad=" + gravedad + ", descripcion=" + descripcion + ", imagen=" + imagen + ", direccion=" + direccion + ", cp=" + cp + ", municipio=" + municipio + ", localizacion=" + localizacion + ", urgente=" + urgente + ", planificado=" + planificado + ", estado=" + estado + ", prioridad=" + prioridad + ", fechainicio=" + fechainicio + ", fechafin=" + fechafin + ", ordendeTrabajo=" + ordendeTrabajo + ", creador=" + creador + ", cliente=" + cliente + ", callcenter=" + callcenter + ", supervisor=" + supervisor + ", operario=" + operario + '}';
    }
}

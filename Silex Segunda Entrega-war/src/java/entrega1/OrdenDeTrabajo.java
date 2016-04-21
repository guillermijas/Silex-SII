package entrega1;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

@Entity
public class OrdenDeTrabajo implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idOT;
    private Enum.gravedad gravedad;
    private String imagen;
    private String direccion;
    private Enum.estado estado;
    private Enum.prioridad prioridad;
    private String instrucciones;
    @Temporal(TemporalType.DATE)
    private Date fechainicio;
    @Temporal(TemporalType.DATE)
    private Date fechafin;
    @OneToOne(targetEntity = Aviso.class)
    private Aviso aviso;
    @ManyToMany(mappedBy = "ordenesTrabajo")
    private List <Operario> operarios;
    
    
    public OrdenDeTrabajo() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getIdOT() {
        return idOT;
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

    public Date getFechafin() {
        return fechafin;
    }

    public Enum.gravedad getGravedad() {
        return gravedad;
    }

    public String getStringn() {
        return imagen;
    }

    public String getDireccion() {
        return direccion;
    }

    public Enum.estado getEstado() {
        return estado;
    }

    public Enum.prioridad getPrioridad() {
        return prioridad;
    }
                             
    public String getInstrucciones() {
        return instrucciones;
    }

    public String getImagen() {
        return imagen;
    }

    public Aviso getAviso() {
        return aviso;
    }

    public void setIdOT(Long idOT) {
        this.idOT = idOT;
    }

    public void setGravedad(Enum.gravedad gravedad) {
        this.gravedad = gravedad;
    }

    public void setStringn(String imagen) {
        this.imagen = imagen;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setEstado(Enum.estado estado) {
        this.estado = estado;
    }

    public void setPrioridad(Enum.prioridad prioridad) {
        this.prioridad = prioridad;
    }

    public void setInstrucciones(String instrucciones) {
        this.instrucciones = instrucciones;
    }
    
    public void setOperarios(List <Operario> o){
        this.operarios = o;
    }
    
    public List <Operario> getOperarios(){
        return this.operarios;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setAviso(Aviso aviso) {
        this.aviso = aviso;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + Objects.hashCode(this.idOT);
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
        final OrdenDeTrabajo other = (OrdenDeTrabajo) obj;
        if (!Objects.equals(this.idOT, other.idOT)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OrdenDeTrabajo{" + "idOT=" + idOT + ", gravedad=" + gravedad + ", imagen=" + imagen + ", direccion=" + direccion + ", estado=" + estado + ", prioridad=" + prioridad + ", instrucciones=" + instrucciones + '}';
    }
    
    
}

package entrega1;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

@Entity
public class OrdenDeTrabajo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idOT;
    private Cliente cliente;
    private Enumeraciones.estado estado;
    private Enumeraciones.prioridad prioridad;
    private String instrucciones;
    @Temporal(TemporalType.DATE)
    private Date fechainicio;
    @Temporal(TemporalType.DATE)
    private Date fechafin;
    @OneToOne(targetEntity = Aviso.class)
    private Aviso aviso;
    @ManyToMany(mappedBy = "ordenesTrabajo")
    private List<Operario> operarios;

    public OrdenDeTrabajo() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getIdOT() {
        return idOT;
    }

    public Enumeraciones.estado getEstado() {
        return estado;
    }
    public Cliente getCliente(){
        return cliente;
    }

    public Enumeraciones.prioridad getPrioridad() {
        return prioridad;
    }

    public String getInstrucciones() {
        return instrucciones;
    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public Aviso getAviso() {
        return aviso;
    }

    public List<Operario> getOperarios() {
        return operarios;
    }

    public void setIdOT(Long idOT) {
        this.idOT = idOT;
    }

    public void setEstado(Enumeraciones.estado estado) {
        this.estado = estado;
    }

    public void setPrioridad(Enumeraciones.prioridad prioridad) {
        this.prioridad = prioridad;
    }

    public void setInstrucciones(String instrucciones) {
        this.instrucciones = instrucciones;
    }
    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }
    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public void setAviso(Aviso aviso) {
        this.aviso = aviso;
    }

    public void setOperarios(List<Operario> operarios) {
        this.operarios = operarios;
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
        return "OrdenDeTrabajo{" + "idOT=" + idOT + ", estado=" + estado + ", prioridad=" + prioridad + ", instrucciones=" + instrucciones + ", fechainicio=" + fechainicio + ", fechafin=" + fechafin + ", aviso=" + aviso + ", operarios=" + operarios + '}';
    }

}

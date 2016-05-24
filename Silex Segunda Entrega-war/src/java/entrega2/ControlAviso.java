package entrega2;

import baseDeDatos.BaseDeDatosLocal;
import baseDeDatos.EMASAException;
import entrega1.*;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Inject;

@Named(value = "controlAviso")
@SessionScoped
public class ControlAviso implements Serializable {

    private Aviso aviso;

    @EJB
    private BaseDeDatosLocal basededatos;

    @Inject
    private ControlAutorizacion ctrl;

    public ControlAviso() {

    }

    public void setAviso(Aviso aviso) {
        this.aviso = aviso;
    }

    public Aviso getAviso() {
        return aviso;
    }

    public String modificarAviso(Aviso a) {
        setAviso(a);
        String page = null;
        if (aviso != null) {
            page = "modificar_aviso.xhtml";
        }
        return page;
    }

    public String confirmarModificacion() throws EMASAException {
        basededatos.modificarAviso(aviso);
        return ctrl.home();
    }

    public String regAviso() {
        setAviso(new Aviso());
        aviso.setIdAviso(basededatos.getIDNewAviso());
        String page = "regAviso.xhtml";
        return page;
    }

    public String addAviso() throws EMASAException {
        aviso.setEstado(Enumeraciones.estado.INCIDENCIA);
        aviso.setFechainicio(new Date());
        aviso.setCreador(basededatos.getUsuario(ctrl.getUsername()));
        basededatos.insertarAviso(aviso);
        System.out.println("Aviso creado con exito");
        aviso = null;
        return ctrl.home();
    }

    public String finalizarModifAviso() {
        aviso = null;
        return "home.xhtml";
    }

    public String getDireccion() {
        return aviso.getDireccion();
    }

    public void setDireccion(String dire) {
        aviso.setDireccion(dire);
    }

    public String getId() {
        String ident = "";
        Long numero = aviso.getIdAviso();
        while (numero % 10000 > 0) {
            ident += "0";
            numero *= 10;
        }
        return ident + aviso.getIdAviso().toString();
    }

    public String getCP() {
        return aviso.getCp();
    }

    public void setCP(String cp) {
        aviso.setCp(cp);
    }

    public String getMunicipio() {
        return aviso.getMunicipio();
    }

    public void setMunicipio(String muni) {
        aviso.setMunicipio(muni);
    }

    public String getGPS() {
        if (aviso.getLocalizacion() == null) {
            return "";
        }
        return aviso.getLocalizacion().getHeight() + " , " + aviso.getLocalizacion().getLenght();
    }

    public void setGPS(String gps) {
        String latitud = gps.substring(0, gps.indexOf(" "));
        String longitud = gps.substring(gps.lastIndexOf(" "), gps.length());
        aviso.setLocalizacion(new Coordenada(latitud, longitud));
    }

    public String getDescripcion() {
        return aviso.getDescripcion();
    }

    public void setDescripcion(String des) {
        aviso.setDescripcion(des);
    }

    public boolean getUrgente() {
        return aviso.isUrgente();
    }

    public void setUrgente(boolean urg) {
        aviso.setUrgente(urg);
    }

    public boolean getPlanificado() {
        return aviso.isPlanificado();
    }

    public void setPlanificado(boolean plan) {
        aviso.setPlanificado(plan);
    }

    public List<Aviso> getIncidencias() {
        return basededatos.getAvisosIncidencia();
    }

    public List<Aviso> getAvisosNuevos() {
        return basededatos.getAvisosNueva();
    }

    public List<Aviso> getAvisosEnProceso() {
        return basededatos.getAvisosEnProceso();
    }

    public List<Aviso> getAvisosCerrados() {
        return basededatos.getAvisosCerrada();
    }

    public List<OrdenDeTrabajo> getOrdenEnProceso() {
        return basededatos.getOtEnProceso();
    }

    public List<OrdenDeTrabajo> getOrdenCerradas() {
        return basededatos.getOtCerradas();
    }

    public String cerrarAviso(Aviso a) throws EMASAException {
        basededatos.cerrarAviso(a.getIdAviso());
        return "confirmacion.xhtml";
    }
    public String home(){
        return ctrl.home();
    }

    public String getNombreCliente() {
        String nombre = "";
        if (aviso.getNombreCliente() == null) {
            if (ctrl.getUsuario().getNombre() != null && ctrl.getUsuario().getApellidos() != null) {
                nombre = ctrl.getUsuario().getNombre() + " " + ctrl.getUsuario().getApellidos();
            }
        } else {
            nombre = aviso.getNombreCliente();
        }
        return nombre;
    }

    public void setNombreCliente(String nom) {
        aviso.setNombreCliente(nom);
    }

    public String getEmailCliente() {
        String email = "";
        if (aviso.getEmailCliente() == null) {
            if (ctrl.getUsuario().getEmail() != null) {
                email = ctrl.getUsuario().getEmail();
            }
        } else {
            email = aviso.getEmailCliente();
        }
        return email;
    }

    public void setEmailCliente(String email) {
        aviso.setEmailCliente(email);
    }

    public String getTelefonoCliente() {
        String tfn = "";
        if (aviso.getNombreCliente() == null) {
            if (ctrl.getUsuario().getTelefono() != null) {
                tfn = ctrl.getUsuario().getTelefono() + "";
            }
        } else {
            tfn = aviso.getTelefonoCliente();
        }
        return tfn;
    }

    public void setTelefonoCliente(String tfn) {
        aviso.setTelefonoCliente(tfn);
    }

}

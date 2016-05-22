package entrega2;

import baseDeDatos.BaseDeDatosLocal;
import baseDeDatos.EMASAException;
import entrega1.*;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
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
    
    public ControlAviso(){
        aviso = new Aviso();
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
    public String regAviso() {
        setAviso(new Aviso());
        String page = "regAviso.xhtml";
        return page;
    }
    
    public String addAviso() throws EMASAException
    {
        basededatos.insertarAviso(aviso);
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
        if (aviso.getLocalizacion() == null) return "";
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

    public String getNombreCliente() {
        return aviso.getCliente().getNombre();
    }

    public void setNombreCliente(String nom) {
        aviso.getCliente().setNombre(nom);
    }

    public String getApellidosCliente() {
        return aviso.getCliente().getApellidos();

    }

    public void setApellidosCliente(String apell) {
        aviso.getCliente().setApellidos(apell);
    }

    public String getEmailCliente() {
        return aviso.getCliente().getEmail();
    }

    public void setEmailCliente(String email) {
        aviso.getCliente().setEmail(email);
    }

    public String getTelefonoCliente() {
        if (aviso.getCliente().getTelefono() != null) {
            return aviso.getCliente().getTelefono() + "";
        } else {
            return "";
        }
    }

    public void setTelefonoCliente(String telef) {
        if (!telef.equals("")) {
            aviso.getCliente().setTelefono(Long.parseLong(telef));
        }
    }
}
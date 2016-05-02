package entrega2;

import entrega1.*;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@Named(value = "controlAviso")
@SessionScoped
public class Control_Aviso implements Serializable {

    private Aviso aviso;

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

    public String finalizarModifAviso() {
        aviso = null;
        return "home.xhtml";
    }

    public String getDireccion() {
        return aviso.getDireccion();
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

    public String getMunicipio() {
        return aviso.getMunicipio();
    }

    public String getGPS() {
        return aviso.getLocalizacion().getHeight() + " , " + aviso.getLocalizacion().getLenght();
    }

    public String getDescripcion() {
        return aviso.getDescripcion();
    }

    public boolean getUrgente() {
        return aviso.isUrgente();
    }

    public boolean getPlanificado() {
        return aviso.isPlanificado();
    }

    public String getNombreCliente() {
        String nombre = "";
        if (aviso.getCliente() != null) {
            nombre = aviso.getCliente().getNombre();
        }
        return nombre;
    }

    public String getApellidosCliente() {
        String apellidos = "";
        if (aviso.getCliente() != null) {
            apellidos = aviso.getCliente().getApellidos();
        }
        return apellidos;
    }

    public String getEmailCliente() {
       String email = "";
        if (aviso.getCliente() != null) {
            email = aviso.getCliente().getEmail();
        }
        return email;
    }

    public String getTelefonoCliente() {
        String tlfn = "";
        if (aviso.getCliente() != null) {
            tlfn = aviso.getCliente().getTelefono()+"";
        }
        return tlfn;
    }
}

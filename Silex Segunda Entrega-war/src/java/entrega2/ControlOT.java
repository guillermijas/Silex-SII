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

@Named(value = "controlOT")
@SessionScoped
public class ControlOT implements Serializable {

    private OrdenDeTrabajo ot;
    private Aviso av; //aviso vinculado a la ot
    
    @EJB
    private BaseDeDatosLocal basededatos;

    @Inject
    private ControlAutorizacion ctrl;

    public ControlOT() {

    }

    public OrdenDeTrabajo getOt() {
        return ot;
    }

    public void setOt(OrdenDeTrabajo ot) {
        this.ot = ot;
    }

    public void setAviso(Aviso av) {
        this.av = av;
    }

    public String regOT(Aviso av) {
        setOt(new OrdenDeTrabajo());
        setAviso(av);
        System.out.println("asdfghyjukilokjhgfdsa");
        ot.setIdOT(basededatos.getIDNewOT());
        
        System.out.println(av);
        return "regOT.xhtml";
    }

    public String addOT() throws EMASAException {
        ot.setFechainicio(new Date());
        ot.setEmailCliente(av.getEmailCliente());
        ot.setTelefonoCliente(av.getTelefonoCliente());
        ot.setPrioridad(av.getPrioridad());
        ot.setAviso(av);
        av.setOrdendeTrabajo(ot);
        ot.setNombreCliente(av.getNombreCliente());
        basededatos.modificarAviso(av);
        basededatos.insertarOT(ot);
        ot = null;
        av = null;
        return ctrl.home();
    }

    public String modificarOT(OrdenDeTrabajo a) {
        setOt(a);
        String page = null;
        if (ot != null) {
            page = "modificar_OT.xhtml";
        }
        return page;
    }

    public String finalizarModifOT() {
        ot = null;
        return "home.xhtml";
    }

    public String getDireccion() {
        return av.getDireccion();
    }

    public void setDireccion(String dire) {
        av.setDireccion(dire);
    }

    public Date getFechaInicio(){
        return ot.getFechainicio();
    }
    
    public void setFechaInicio(Date d){
        ot.setFechainicio(d);
    }
    
    public String getCP() {
        return av.getCp();
    }

    public void setCP(String cp) {
        av.setCp(cp);
    }

    public String getMunicipio() {
        return av.getMunicipio();
    }

    public void setMunicipio(String muni) {
        av.setMunicipio(muni);
    }

    public String getId() {
        return ot.getIdOT() + "";
    }

    public boolean getUrgente() {
        return av.isUrgente();
    }

    public void setUrgente(boolean urg) {
        av.setUrgente(urg);
    }

    public boolean getPlanificado() {
        return av.isPlanificado();
    }

    public void setPlanificado(boolean plan) {
        av.setPlanificado(plan);
    }

    public String getDescripcion() {
        return av.getDescripcion();
    }

    public void setDescripcion(String des) {
        av.setDescripcion(des);
    }

    public String getInstrucciones() {
        return ot.getInstrucciones();
    }

    public void setInstrucciones(String ins) {
        ot.setInstrucciones(ins);
    }

    public String getGPS() {
        if (av.getLocalizacion() == null) {
            return "";
        }
        return av.getLocalizacion().getHeight() + " , " + av.getLocalizacion().getLenght();
    }

    public void setGPS(String gps) {
        String latitud = gps.substring(0, gps.indexOf(" "));
        String longitud = gps.substring(gps.lastIndexOf(" "), gps.length());
        av.setLocalizacion(new Coordenada(latitud, longitud));
    }

    public int getPrioridad() {
        int num = 1;
        if (av.getPrioridad().equals(Enumeraciones.prioridad.ALTA)) {
            num = 3;
        }
        if (av.getPrioridad().equals(Enumeraciones.prioridad.MEDIA)) {
            num = 2;
        } else {
            num = 1;
        }
        return num;

    }

    public void setPrioridad(int i) {
        switch (i) {
            case 1:
                av.setPrioridad(Enumeraciones.prioridad.BAJA);
                break;
            case 2:
                av.setPrioridad(Enumeraciones.prioridad.MEDIA);
                break;
            case 3:
                av.setPrioridad(Enumeraciones.prioridad.ALTA);
                break;
        }
    }

    public int getGravedad() {
        int num = 1;
        if (av.getGravedad().equals(Enumeraciones.gravedad.ALTA)) {
            num = 3;
        }
        if (av.getGravedad().equals(Enumeraciones.gravedad.MEDIA)) {
            num = 2;
        } else {
            num = 1;
        }
        return num;

    }

    public void setGravedad(int i) {
        switch (i) {
            case 1:
                av.setGravedad(Enumeraciones.gravedad.LEVE);
                break;
            case 2:
                av.setGravedad(Enumeraciones.gravedad.MEDIA);
                break;
            case 3:
                av.setGravedad(Enumeraciones.gravedad.ALTA);
                break;
        }
    }

    public int getEstado() {
        int num = 1;
        if (av.getEstado().equals(Enumeraciones.estado.CERRADO)) {
            num = 2;
        }

        return num;

    }

    public void setEstado(int i) {
        switch (i) {
            case 1:
                av.setEstado(Enumeraciones.estado.EN_PROCESO);
                break;
            case 2:
                av.setEstado(Enumeraciones.estado.CERRADO);
                break;
        }
    }

    //operarios de una ot
    public void setOperarios(List<Usuario> ops) {
        ot.setOperarios(ops);
    }

    public List<Usuario> getOperarios() {
        return ot.getOperarios();
    }

    //lista de operarios
    public List<Usuario> getListaOperarios() {
        return basededatos.getListaOperarios();
    }

}

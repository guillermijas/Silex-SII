package entrega2;

import entrega1.*;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@Named(value = "controlOT")
@SessionScoped
public class ControlOT implements Serializable {

    private OrdenDeTrabajo ot;

    public ControlOT() {

    }

    public OrdenDeTrabajo getOt() {
        return ot;
    }

    public void setOt(OrdenDeTrabajo ot) {
        this.ot = ot;
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

    public Aviso getAviso() {
        return ot.getAviso();
    }

    public void setAviso(Aviso a) {
        ot.setAviso(a);
    }

    public String getId() {
        return ot.getIdOT() + "";
    }

    public int getPrioridad() {
        int num = 1;
        if (ot.getPrioridad().equalsIgnoreCase("ALTA")) {
            num = 3;
        }
        if (ot.getPrioridad().equalsIgnoreCase("MEDIA")) {
            num = 2;
        } else {
            num = 1;
        }
        return num;

    }

    public void setPrioridad(int i) {
        switch (i) {
            case 1:
                ot.setPrioridad("BAJA");
                break;
            case 2:
                ot.setPrioridad("MEDIA");
                break;
            case 3:
                ot.setPrioridad("ALTA");
                break;
        }
    }

    public String getInstrucciones() {
        return ot.getInstrucciones();
    }

    public void setInstrucciones(String inst) {
        ot.setInstrucciones(inst);
    }

}

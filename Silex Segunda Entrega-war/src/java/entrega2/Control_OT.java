package entrega2;

import entrega1.*;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@Named(value = "controlOT")
@SessionScoped
public class Control_OT implements Serializable {

    private OrdenDeTrabajo ot;

    public Control_OT(){
        
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
        if (ot.getPrioridad() == Enumeraciones.prioridad.ALTA) {
            num = 3;
        }
        if (ot.getPrioridad() == Enumeraciones.prioridad.MEDIA) {
            num = 2;
        } else {
            num = 1;
        }
        return num;

    }

    public void setPrioridad(int i) {
        switch (i) {
            case 1:
                ot.setPrioridad(Enumeraciones.prioridad.BAJA);
                break;
            case 2:
                ot.setPrioridad(Enumeraciones.prioridad.MEDIA);
                break;
            case 3:
                ot.setPrioridad(Enumeraciones.prioridad.ALTA);
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

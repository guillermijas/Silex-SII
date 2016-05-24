package entrega2;

import baseDeDatos.BaseDeDatosLocal;
import baseDeDatos.EMASAException;
import entrega1.Usuario;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@ManagedBean
@ViewScoped
public class ControlModificarUsuario implements Serializable {

    @Inject
    private ControlAutorizacion ctrl;

    @EJB
    private BaseDeDatosLocal basededatos;

    @Inject
    private Hash hash;

    private String pass;
    private String pass2;

    public ControlModificarUsuario() {
    }

   
}

package entrega2;

import baseDeDatos.BaseDeDatosLocal;
import baseDeDatos.EMASAException;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import entrega1.*;
import javax.ejb.EJB;

@Named(value = "login")
@RequestScoped
public class Login {

    private String user;
    private String password;

    @Inject
    private ControlAutorizacion ctrl;
    @EJB
    private BaseDeDatosLocal basededatos;
    @Inject
    private Hash hash;

    /**
     * Creates a new instance of Login
     */
    public Login() {
        //Dbaux.init();
    }

    public String getUsuario() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public void setUsuario(String usuario) {
        this.user = usuario;
    }

    public void setPassword(String pass) {
        this.password = hash.getHash(pass); // Guardamos directamente el hash
    }

    public String autenticar() {
        String page;
        if (user != null && basededatos.estaRegistrado(user)) {
            if (basededatos.getUsuario(user).getCadenaValidacion() == null) {
                // Una vez comprobado, compruebo si la contraseña es correcta

                Usuario us = basededatos.getUsuario(user);

                if (us.getPassword().equals(this.password)) // Comparamos con el hash
                {
                    ctrl.setUsuario(us);
                    page = ctrl.home();
                } else {
                    FacesContext ctx = FacesContext.getCurrentInstance();
                    ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario o contraseña inválidos", "Usuario o contraseña inválidos"));
                    page = "login.xhtml";
                }
            } else {
                FacesContext ctx = FacesContext.getCurrentInstance();
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario no activado", "Usuario no activado"));
                page = "login.xhtml";
            }
        } else {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario no registrado", "Usuario no registrado"));
            page = "login.xhtml";
        }
        return page;
    }

    public void deleteUser(Usuario us) throws EMASAException {
        if (basededatos.estaRegistrado(us)) {
            basededatos.eliminarUsuario(us);
        } else {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error eliminando usuario", "Usuario inexistente"));
        }
    }
}

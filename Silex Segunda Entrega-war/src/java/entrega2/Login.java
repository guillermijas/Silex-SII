package entrega2;

import baseDeDatos.BaseDeDatosLocal;
import baseDeDatos.EMASAException;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import entrega1.*;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        if (user.equals("admin") && !basededatos.estaRegistrado("admin")){
            try {
                basededatos.insertarUsuario(new Usuario("admin", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918", Enumeraciones.Rol.ADMINISTRADOR));
            } catch (EMASAException ex) {
            }
                    ctrl.setUsuario(basededatos.getUsuario("admin"));
                    page = ctrl.home();
                    return page;
        }
        
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
                    ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Usuario o contraseña inválidos"));
                    page = "login.xhtml";
                }
            } else {
                 if (basededatos.getUsuario(user).getCadenaValidacion().equals("14")) {
                    FacesContext ctx = FacesContext.getCurrentInstance();
                    ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Esta cuenta de usuario está eliminada"));
                    return "login.xhtml";    
               }
                FacesContext ctx = FacesContext.getCurrentInstance();
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Usuario no activado"));
                page = "login.xhtml";
            }
        } else {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "\n Usuario no registrado"));
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

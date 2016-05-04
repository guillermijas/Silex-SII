package entrega2;

import entrega1.*;
import entrega1.Enumeraciones.Rol;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.context.FacesContext;

@Named(value = "controlAutorizacion")
@SessionScoped
public class ControlAutorizacion implements Serializable {

    private Usuario usuario;

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String home() {
        // Implementar el método
        // Devuelve la página Home dependiendo del rol del usuario
        // Si no hay usuario debe devolver la página de login
        // Si el usuario es el administrador debe devolver la página admin.xhtml
        // Si el usuario es un usuario normal debe devolver la página normal.xhtml

        String page = null;

        if (usuario != null) // Si hay usuario
        {
            if (usuario.getRol().equals("CLIENTE")) // Si el usuario es normal
            {
                page = "normal.xhtml";
            } else if (usuario.getRol().equals("ADMINISTRADOR")) {
                page = "admin.xhtml";
            }
        } else {
            page = "login.xhtml";
        }

        return page;
    }

    public String logout() {
        // Destruye la sesión (y con ello, el ámbito de este bean)
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.getExternalContext().invalidateSession();
        usuario = null;
        return "login.xhtml";
    }

    public ControlAutorizacion() {

    }

    public String index() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.getExternalContext().invalidateSession();
        usuario = null;
        return "index.xhtml";
    }

    public String login() {
        return "login.xhtml";
    }

    public String regOT() {

        return "regOT.xhtml";
    }

    public String regAviso() {
        return "regAviso.xhtml";
    }

    public String register() {
        return "register.xhtml";
    }

    public String normal() {
        return "normal.xhtml";
    }
    public String getUsername(){
        return usuario.getUsername();
    }
    
    public String getRol(){
        return usuario.getRol();
    }
}

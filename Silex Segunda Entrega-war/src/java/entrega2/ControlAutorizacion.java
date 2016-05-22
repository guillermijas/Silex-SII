package entrega2;

import entrega1.*;
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

   
    /**
     * Devuelve la página Home dependiendo del rol del usuario Si no hay usuario
     * debe devolver la página de login Si el usuario es el administrador debe
     * devolver la página admin.xhtml Si el usuario es un usuario normal debe
     * devolver la página normal.xhtml
     *
     * @return
     */
    public String home() {

        String page = "login.xhtml";

        if (usuario != null) { // Si hay usuario
            switch (usuario.getRol()) {
                case "CLIENTE":
                    page = "cliente.xhtml";
                    break;
                case "ADMINISTRADOR":
                    page = "admin.xhtml";
                    break;
                case "OPERARIO":
                    page = "normal.xhtml";
                    break;
                default:
                    page = "normal.xhtml";
                    break;
            }
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

    public String login() {
        return "login.xhtml";
    }

    public String regOT() {
        return "regOT.xhtml";
    }

    public String regAviso() {
        return "regAviso.xhtml";
    }

    public String regOperario() {
        return "register_operario.xhtml";
    }

    public String register() {
        return "register.xhtml";
    }

    public String normal() {
        return "normal.xhtml";
    }

    public String modUser() {
        return "modificar_usuario.xhtml";
    }

    public String getUsername() {
        return usuario.getUsername();
    }

    public String getRol() {
        return usuario.getRol();
    }

}

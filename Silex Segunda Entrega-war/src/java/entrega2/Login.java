package entrega2;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import entrega1.*;

@Named(value = "login")
@RequestScoped
public class Login {

    private String user;
    private String password;

    @Inject
    private ControlAutorizacion ctrl;
    @Inject
    private DataBase database;
    @Inject
    private Hash hash;

    /**
     * Creates a new instance of Login
     */
    public Login() {
        Dbaux.init();
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

        // Una vez comprobado, compruebo si la contraseña es correcta
        if (!database.emptyDataBase() && database.isUsernameContent(this.user)) {
            Usuario us = database.getUserbyIndex(database.getIndexUsername(this.user));

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
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario o contraseña inválidos", "Usuario o contraseña inválidos"));
            page = "login.xhtml";
        }
        return page;
    }

    public void deleteUser(Usuario us) {
        if (database.isUsernameContent(us.getUsername())) {
            database.deleteUser(us);
        } else {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error eliminando usuario", "Usuario inexistente"));
        }
    }
}

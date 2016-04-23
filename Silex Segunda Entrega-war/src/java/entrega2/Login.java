/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entrega2;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import entrega1.*;
import static entrega1.Enum.*;

/**
 *
 * @author francis
 */
@Named(value = "login")
@RequestScoped
public class Login {

    private String usuario;
    private String contrasenia;
    private List<Usuario> usuarios;
    
    @Inject
    private ControlAutorizacion ctrl;

    /**
     * Creates a new instance of Login
     */
    public Login() {
        usuarios = new ArrayList<Usuario>();
        usuarios.add(new Usuario("pepe", "asdf", Rol.CLIENTE));
        usuarios.add(new Usuario("manolo", "qwer", Rol.OPERARIO));
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String autenticar() {
        // Implementar este método
        // Buscamos en el arraylist al usuario, y si está y la contraseña coincide, le damos paso, en función de su rol
        
        String page;
        boolean isContent = false;
        int index = 0;
        
        for(Usuario u : usuarios)
        {
            if (u.getUsername().equals(this.usuario))
            {
                isContent = true;
                index = usuarios.indexOf(u);
                break;
            }
        }
        
        // Una vez comprobado, compruebo si la contraseña es correcta
        
        if (isContent)
        {
            Usuario us = usuarios.get(index);
            if (us.getPassword().equals(this.contrasenia))
            {
                ctrl.setUsuario(us);
                page = ctrl.home();
            }
            else
            {
                FacesContext ctx = FacesContext.getCurrentInstance();
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wrong Password", "Wrong Password")); 
                page = "login.xhtml";
            }
        }
        else
        {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "User not identified", "User not identified")); 
            page = "login.xhtml";
        }
        return page;
    }
}

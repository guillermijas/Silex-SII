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

    private String user;
    private String password;
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
        return user;
    }

    public String getPassword() {
        return password;
    }

    public void setUsuario(String usuario) {
        this.user = usuario;
    }

    public void setContrasenia(String contrasenia) {
        this.password = contrasenia;
    }

    public String autenticar() {
        // Implementar este método
        // Buscamos en el arraylist al usuario, y si está y la contraseña coincide, le damos paso, en función de su rol
        
        String page;
        boolean isContent = false;
        int index = 0;
        
        for(Usuario u : usuarios)
        {
            if (u.getUsername().equals(this.user))
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
            if (us.getPassword().equals(this.password))
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
    
    public String register()
    {
        // Primero comprobamos que ese email no está ya en la base de datos
        String page;
        boolean already = false;
        
        for (Usuario u : usuarios)
        {
            if(u.getUsername().equals(this.user))
            {
                already = true;
            }
        }
        
        if (!already)
        {
            // Ahora especificamos que la contraseña tiene que tener al menos 8 caracteres
            if (this.password.length() >= 8)
            {
                Usuario usuario = new Usuario(getUsuario(),getPassword(),Rol.CLIENTE);
                usuarios.add(usuario);
                ctrl.setUsuario(usuario);
                page = ctrl.home();  
            }
            else
            {
                FacesContext ctx = FacesContext.getCurrentInstance();
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La contraseña debe tener al menos 8 caracteres", "La contraseña debe tener al menos 8 caracteres"));
                page = "register.xhtml";
            }
        }
        else
        {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario ya registrado en el sistema", "Usuario ya registrado en el sistema")); 
            page = "register.xhtml";
        }
        return page;
    }
}

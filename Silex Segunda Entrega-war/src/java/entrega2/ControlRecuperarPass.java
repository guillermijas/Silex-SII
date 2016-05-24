package entrega2;

import baseDeDatos.BaseDeDatosLocal;
import baseDeDatos.EMASAException;
import entrega1.Usuario;
import java.net.URI;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;


@ViewScoped
@ManagedBean
public class ControlRecuperarPass {
    
    @EJB
    private BaseDeDatosLocal basededatos;
    
    @Inject 
    private Hash hash;
    
    @Context
    private UriInfo uri;
    
    private String username;
    private String pwd1;
    private String pwd2;
    private String mensaje;
    private String validacion;

    public ControlRecuperarPass(){
        
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd1() {
        return pwd1;
    }

    public void setPwd1(String pwd1) {
        this.pwd1 = pwd1;
    }

    public String getPwd2() {
        return pwd2;
    }

    public void setPwd2(String pwd2) {
        this.pwd2 = pwd2;
    }
    
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    public String getValidacion() {
        return validacion;
    }

    public void setValidacion(String validacion) {
        this.validacion = validacion;
    }
       
    public boolean comprobarAcceso()
    {
        return username != null && validacion != null;
    }
    
    public void checkUsername() throws EMASAException
    {
        if(username != null && basededatos.estaRegistrado(username))
        {
            // Modificamos el usuario poniendole de nuevo una cadena aleatoria
            Usuario us = basededatos.getUsuario(username);
            String cadena = basededatos.generarCadenaAleatoria();
            us.setCadenaValidacion(cadena);
            basededatos.actualizarUsuario(us);
            URI url = uri.getBaseUriBuilder().path("Silex_Segunda_Entrega-war").path("faces").build();
            basededatos.mandarEmailRecuperacion(us, cadena, url.toString());
            username = null; // Reiniciamos username para usarlo en cambiarContrasena
            mensaje = "Se le ha enviado un correo electrónico para actualizar su contraseña";   
        }
        else
        {
            mensaje = "El usuario que ha introducido no está registrado";
        }
    }
    
    private boolean checkPassword()
    {
        return pwd1.equals(pwd2);
    }
            
    
    public String cambiarPass() throws EMASAException
    {
        if(comprobarAcceso() && checkPassword())
        {
            Usuario us = basededatos.getUsuario(username);
            if(us.getCadenaValidacion().equals(validacion))
            {
                us.setPassword(hash.getHash(pwd1)); // Actualizamos la contraseña
                us.setCadenaValidacion(null); // Y volvemos a poner a null la cadena de validacion
                basededatos.actualizarUsuario(us); // Actualizamos el usuario
                return "login.xhtml";
            }
            else
            {
                mensaje = "Cadena de validación incorrecta";
                return "";
            }
            
        }
        else
        {
            mensaje = "Las contraseñas no coinciden";
            return "";
        }
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entrega2;

import baseDeDatos.BaseDeDatosLocal;
import baseDeDatos.EMASAException;
import entrega1.Enumeraciones;
import entrega1.Usuario;
import java.io.Serializable;
import java.net.URI;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@ViewScoped
@Named
public class ControlRegistro implements Serializable {

    @EJB
    private BaseDeDatosLocal basededatos;
    @Inject
    private ControlAutorizacion ctrl;
    @Inject
    private Hash hash;
    private Enumeraciones.Rol rol = Enumeraciones.Rol.CLIENTE;

    @Context
    private UriInfo uri;

    private Usuario user = new Usuario();
    private String username;
    private String validacion;
    private String mensajeValidacion;
    private String pwd1;
    private String pwd2;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getValidacion() {
        return validacion;
    }

    public void setValidacion(String validacion) {
        this.validacion = validacion;
    }

    public String getMensajeValidacion() {
        return mensajeValidacion;
    }

    public void setMensajeValidacion(String mensajeValidacion) {
        this.mensajeValidacion = mensajeValidacion;
    }

    public String getPwd1() {
        return this.pwd1;
    }

    public void setPwd1(String pwd1) {
        this.pwd1 = pwd1;
    }

    public String getPwd2() {
        return this.pwd2;
    }

    public void setPwd2(String pwd2) {
        this.pwd2 = pwd2;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUsuario(Usuario user) {
        this.user = user;
    }

    public String getRol() {
        String rolnuevo = "0";
        switch (rol) {
            case CLIENTE:
                rolnuevo = "0";
                break;
            case CALL_CENTER:
                rolnuevo = "1";
                break;
            case SUPERVISOR:
                rolnuevo = "2";
                break;
            case OPERARIO:
                rolnuevo = "3";
                break;
            case ADMINISTRADOR:
                rolnuevo = "4";
                break;
            default:
                throw new AssertionError(rol.name());
        }
        return rolnuevo;
    }

    public void setRol(String role) {
        switch (role) {
            case "0":
                rol = Enumeraciones.Rol.CLIENTE;
                break;
            case "1":
                rol = Enumeraciones.Rol.CALL_CENTER;
                break;
            case "2":
                rol = Enumeraciones.Rol.SUPERVISOR;
                break;
            case "3":
                rol = Enumeraciones.Rol.OPERARIO;
                break;
            case "4":
                rol = Enumeraciones.Rol.ADMINISTRADOR;
                break;
            default:
                rol = Enumeraciones.Rol.CLIENTE;
        }
    }

    public String registrar() throws EMASAException {

        // Primero comprobamos que las contraseñas coinciden
        if (checkPasswords()) {
            keepPwd(); // Guardamos esa contraseña en el perfil del usuario
            // Luego establecemos el rol del usuario
            if (user.getTipo() == null) {
                user.setRol(Enumeraciones.Rol.CLIENTE);
            } else if (user.getZonaCargo() != null) {
                user.setRol(Enumeraciones.Rol.SUPERVISOR);
            } else {
                user.setRol(Enumeraciones.Rol.OPERARIO);
            }

            //URI url = uri.getBaseUriBuilder().path("Silex_Segunda_Entrega-war").path("faces").build(); //--> Null pointer
            String url_base = "http://localhost:8080/Silex_Segunda_Entrega-war/faces";
            String cadena = basededatos.generarCadenaAleatoria();
            if (!user.getRol().equals(Enumeraciones.Rol.ADMINISTRADOR)) {
                user.setCadenaValidacion(cadena);
            }
            if (basededatos.insertarUsuario(user)) {
                if (!user.getRol().equals(Enumeraciones.Rol.ADMINISTRADOR)) {
                    basededatos.mandarEmail(user, cadena, url_base);
                }
                ctrl.setUsuario(user);
                return "exitoRegistro.xhtml";
            } else {
                return "register.xhtml";
            }
        } else {
            return "register.xhtml";
        }
    }

    public String registrarOperario() throws EMASAException {
        // Primero comprobamos que las contraseñas coinciden
        if (checkPasswords()) {
            keepPwd(); // Guardamos esa contraseña en el perfil del usuario
            // Luego establecemos el rol del usuario
            user.setRol(rol);
            if (basededatos.insertarUsuario(user)) { // Devuelve true si se ha guardado el usuario correctamente en la BD
                return "admin.xhtml";
            } else {
                return "register.xhtml";
            }

        } else {
            return "register.xhtml";
        }
    }

    public boolean checkPasswords() {
        return pwd1.equals(pwd2);
    }

    public void keepPwd() {
        user.setPassword(hash.getHash(pwd1)); // Guardamos el hash por seguridad
    }

    public String validarCuenta() {
        try {
            if (username != null && validacion != null) {
                basededatos.validarUsuario(username, validacion);
            }
            mensajeValidacion = "La validación ha sido correcta, ahora puede acceder con este usuario.";
        } catch (EMASAException e) {
            mensajeValidacion = "Ha habido un error con la validación, compruebe que la URL es correcta.";
        }
        return null;
    }

    public boolean comprobarRegistro() {
        return (basededatos.getUsuario(ctrl.getUsuario().getUsername())).isRegistroOk();
    }

    public boolean datosCorrectos() {
        return (username != null && validacion != null && basededatos.estaRegistrado(username));
    }

    public String goHome() {
        ctrl.setUsuario(basededatos.getUsuario(username));
        return ctrl.home();
    }
}

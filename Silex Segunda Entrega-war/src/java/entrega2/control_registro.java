/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entrega2;

import entrega1.Usuario;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

@ManagedBean
@ViewScoped
public class control_registro implements Serializable {

    @Inject
    private DataBase database;
    @Inject
    private ControlAutorizacion ctrl;
    @Inject
    private Hash hash;

    private Usuario user = new Usuario();
    private String pwd1;
    private String pwd2;

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

    public String registrar() {
        // Primero comprobamos que las contraseñas coinciden
        if (checkPasswords()) {
            keepPwd(); // Guardamos esa contraseña en el perfil del usuario
            // Luego establecemos el rol del usuario
            if (user.getTipo() == null) {
                user.setRol("CLIENTE");
            } else if (user.getZonaCargo() != null) {
                user.setRol("SUPERVISOR");
            } else {
                user.setRol("OPERARIO");
            }
            
            if (database.insertNewUser(user)) {
                ctrl.setUsuario(user);
                return ctrl.home();
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
}

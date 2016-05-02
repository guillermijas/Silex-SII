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
import org.primefaces.event.FlowEvent;

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

    private boolean skip;

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

    public String save() {
        if (user.getUsername().equals("adminn")) {
            user.setRol("ADMINISTRADOR");
        } else {
            user.setRol("CLIENTE"); // Por defecto se añade como cliente
        }
        database.insertNewUser(user); // Guardamos el nuevo usuario
        ctrl.setUsuario(user);
        return ctrl.home();
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public String onFlowProcess(FlowEvent event) {
        // Primero separamos si se trata de la parte inicial o no
        if (event.getOldStep().equals("Login")) {
            if (checkPasswords()) {
                keepPwd();
                if (skip) {
                    skip = false;
                    return "confirm";
                } else {
                    return event.getNewStep();
                }
            } else {
                return event.getOldStep();
            }
        } else if (skip) {
            skip = false;
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    public boolean checkPasswords() {
        return pwd1.equals(pwd2);
    }

    public void keepPwd() {
        user.setPassword(hash.getHash(pwd1)); // Guardamos el hash por seguridad
    }
}

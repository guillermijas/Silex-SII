/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entrega2;

import entrega1.Usuario;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.event.FlowEvent;

@ManagedBean
@ViewScoped
public class control_registro implements Serializable {

    @Inject
    private Login login;

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

    public void save() {
        FacesMessage msg = new FacesMessage("Successful", "Welcome " + user.getUsername());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        login.insertNewUser(user); // Guardamos el nuevo usuario
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false;   //Si vuelve atrás, los datos se reinician
            return "confirm";
        } else if (checkPasswords()) {
            return event.getNewStep();
        } else {
            return event.getOldStep();
        }
    }

    public boolean checkPasswords() {
        return pwd1.equals(pwd2);
    }

    public void keepPwd() {
        user.setPassword(pwd1);
    }
}

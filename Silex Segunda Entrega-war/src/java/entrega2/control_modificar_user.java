package entrega2;


import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;


@ManagedBean
@ViewScoped
public class control_modificar_user implements Serializable 
{
    @Inject
    private ControlAutorizacion ctrl;
    
    @Inject
    private Hash hash;
    
    private String pass;
    private String pass2;

    public String getEmail() {
        return ctrl.getUsuario().getEmail();
    }

    public void setEmail(String email) {
        ctrl.getUsuario().setEmail(email);
    }

    public String getPass() {
        return this.pass;
    }

    public void setPass(String p) {
        this.pass = p;
    }
    
        public String getPass2() {
        return pass2;
    }

    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }

    public String getNombre() {
        return ctrl.getUsuario().getNombre();
    }

    public void setNombre(String nombre) {
        ctrl.getUsuario().setNombre(nombre);
    }

    public String getApellidos() {
        return ctrl.getUsuario().getApellidos();
    }

    public void setApellidos(String apellidos) {
        ctrl.getUsuario().setApellidos(apellidos);
    }

    public Long getTelefono() {
        return ctrl.getUsuario().getTelefono();
    }

    public void setTelefono(Long telefono) {
        ctrl.getUsuario().setTelefono(telefono);
    }

    public String getDireccion() {
        return ctrl.getUsuario().getDirección();
    }

    public void setDireccion(String direccion) {
        ctrl.getUsuario().setDirección(direccion);
    }

    public String getZonaCargo() {
        return ctrl.getUsuario().getZonaCargo();
    }

    public void setZonaCargo(String zonaCargo) {
        ctrl.getUsuario().setZonaCargo(zonaCargo);
    }

    public String getTipo() {
        return ctrl.getUsuario().getTipo();
    }

    public void setTipo(String tipo) {
        ctrl.getUsuario().setTipo(tipo);
    }

    public String getEspecializacion() {
        return ctrl.getUsuario().getEspecializacion();
    }

    public void setEspecializacion(String especializacion) {
        ctrl.getUsuario().setEspecializacion(especializacion);
    }

    public boolean isDisponibilidad() {
        return ctrl.getUsuario().getDisponibilidad();
    }

    public void setDisponibilidad(boolean disponibilidad) {
        ctrl.getUsuario().setDisponibilidad(disponibilidad);
    }
    
    
    public control_modificar_user()
    {
        
    }
    
    public String update() // Actualiza los cambios en la base de datos y redirige al usuario a la pagina principal
    {
        if(checkPasswords())
        {
            keepPwd();
            FacesMessage msg = new FacesMessage("Modificación realizada con éxito", "Usuario " + getUsername() + " modificado");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return ctrl.home();
        }
        else
        {
            return "modificar_usuario.xhtml";
        }
        
    }
    
    public boolean checkPasswords()
    {
        return pass.equals(pass2);
    }
    
    public void keepPwd()
    {
        ctrl.getUsuario().setPassword(hash.getHash(pass));
    }
    
    public String getUsername()
    {
        return ctrl.getUsuario().getUsername();
    }
}
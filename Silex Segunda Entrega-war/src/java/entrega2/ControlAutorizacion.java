package entrega2;

import baseDeDatos.BaseDeDatosLocal;
import baseDeDatos.EMASAException;
import entrega1.*;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "controlAutorizacion")
@SessionScoped
public class ControlAutorizacion implements Serializable {

    @EJB
    private BaseDeDatosLocal basededatos;

    @Inject
    private Hash hash;

    private String pass;
    private String pass2;
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
                case CLIENTE:
                    page = "cliente.xhtml";
                    break;
                case ADMINISTRADOR:
                    page = "admin.xhtml";
                    break;
                case OPERARIO:
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
        public String modUser(Usuario u) {
        usuario = u;
        return "modificar_usuario.xhtml";
    }

    public String adminUser() {
        return "admin_usuarios.xhtml";
    }

    public String getUsername() {
        return usuario.getUsername();
    }

    public int comprobarRol() {
        int num = -1;
        switch (usuario.getRol()) {
            case CLIENTE:
                num = 0;
                break;
            case CALL_CENTER:
                num = 1;
                break;
            case SUPERVISOR:
                num = 2;
                break;
            case OPERARIO:
                num = 3;
                break;
            case ADMINISTRADOR:
                num = 4;
                break;
            default:
                throw new AssertionError(usuario.getRol().name());
        }
        return num;
    }

    //Metodos para el usuario
    public String getEmail() {
        return usuario.getEmail();
    }

    public void setEmail(String email) {
        usuario.setEmail(email);
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

    public String getApellidos() {
        return usuario.getApellidos();
    }

    public void setApellidos(String apellidos) {
        usuario.setApellidos(apellidos);
    }

    public Long getTelefono() {
        return usuario.getTelefono();
    }

    public void setTelefono(Long telefono) {
        usuario.setTelefono(telefono);
    }

    public String getDireccion() {
        return usuario.getDireccion();
    }

    public void setDireccion(String direccion) {
        usuario.setDirección(direccion);
    }

    public String getZonaCargo() {
        return usuario.getZonaCargo();
    }

    public void setZonaCargo(String zonaCargo) {
        usuario.setZonaCargo(zonaCargo);
    }

    public String getTipo() {
        return usuario.getTipo();
    }

    public void setTipo(String tipo) {
        usuario.setTipo(tipo);
    }

    public String getEspecializacion() {
        return usuario.getEspecializacion();
    }

    public void setEspecializacion(String especializacion) {
        usuario.setEspecializacion(especializacion);
    }

    public boolean isDisponibilidad() {
        return usuario.isDisponibilidad();
    }

    public void setDisponibilidad(boolean disponibilidad) {
        usuario.setDisponibilidad(disponibilidad);
    }

    public boolean checkPasswords() {
        return pass.equals(pass2);
    }

    public void keepPwd() {
        usuario.setPassword(hash.getHash(pass));
    }

    //hasta aqui usuario
    public String update() throws EMASAException { // Actualiza los cambios en la base de datos y redirige al usuario a la pagina principal

        FacesMessage msg = new FacesMessage("Modificación realizada con éxito", "Usuario " + getUsername() + " modificado");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        basededatos.actualizarUsuario(usuario); // Actualizamos el usuario en la base de datos
        return home();
    }

}

package entrega2;

import baseDeDatos.BaseDeDatosLocal;
import baseDeDatos.EMASAException;
import entrega1.*;
import entrega1.Enumeraciones.Rol;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
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
    private Usuario usuarioEditado;
    private String mensaje;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Usuario getUsuarioEditado() {
        return usuarioEditado;
    }

    public void setUsuarioEditado(Usuario usuarioEditado) {
        this.usuarioEditado = usuarioEditado;
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
                case SUPERVISOR:
                    page = "supervisor.xhtml";
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
        usuarioEditado = null;
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

    public String regUsuario() {
        return "registerUsuario.xhtml";
    }

    public String register() {
        return "register.xhtml";
    }

    public String normal() {
        return "normal.xhtml";
    }

    public String modUser() {
        usuarioEditado = usuario;
        return "modificar_usuario.xhtml";
    }

    public String modUser(Usuario u) {
        usuarioEditado = u;
        return "modificar_usuario.xhtml";
    }
    public String elimUser(Usuario u) {
        if(u.getUsername().equals("admin")){
         return "error.xhtml";   
        }
        try {
            basededatos.eliminarUsuario(u);
        } catch (EMASAException ex) {
            Logger.getLogger(ControlAutorizacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "confirmacion.xhtml";
    }

    public String adminUser() {
        usuarioEditado = usuario;
        return "admin_usuarios.xhtml";
    }

    public String getUsernameEditado() {
        return usuarioEditado.getUsername();
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
    public int comprobarRol(Rol u) {
        if(u == null){
            return 0;
        }
        int num = -1;

        switch (u) {
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
        return usuarioEditado.getEmail();
    }

    public void setEmail(String email) {
        usuarioEditado.setEmail(email);
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
        return usuarioEditado.getNombre();
    }

    public void setNombre(String n) {
        usuarioEditado.setNombre(n);
    }

    public String getDni() {
        return usuarioEditado.getDni();
    }

    public void setDni(String s) {
        usuarioEditado.setDni(s);
    }

    public String getApellidos() {
        return usuarioEditado.getApellidos();
    }

    public void setApellidos(String apellidos) {
        usuarioEditado.setApellidos(apellidos);
    }

    public Long getTelefono() {
        return usuarioEditado.getTelefono();
    }

    public void setTelefono(Long telefono) {
        usuarioEditado.setTelefono(telefono);
    }

    public String getDireccion() {
        return usuarioEditado.getDireccion();
    }

    public void setDireccion(String direccion) {
        usuarioEditado.setDireccion(direccion);
    }

    public String getZonaCargo() {
        return usuarioEditado.getZonaCargo();
    }

    public void setZonaCargo(String zonaCargo) {
        usuarioEditado.setZonaCargo(zonaCargo);
    }

    public String getTipo() {
        return usuarioEditado.getTipo();
    }

    public void setTipo(String tipo) {
        usuarioEditado.setTipo(tipo);
    }

    public String getEspecializacion() {
        return usuarioEditado.getEspecializacion();
    }

    public void setEspecializacion(String especializacion) {
        usuarioEditado.setEspecializacion(especializacion);
    }

    public boolean isDisponibilidad() {
        return usuarioEditado.isDisponibilidad();
    }

    public void setDisponibilidad(boolean disponibilidad) {
        usuarioEditado.setDisponibilidad(disponibilidad);
    }

    public String getSexo() {
        return usuarioEditado.getSexo();
    }

    public void setSexo(String n) {
        usuarioEditado.setSexo(n);
    }

    public String getRol() {
        String rol = "";
        switch (usuarioEditado.getRol()) {
            case CLIENTE:
                rol = "0";
                break;
            case CALL_CENTER:
                rol = "1";
                break;
            case SUPERVISOR:
                rol = "2";
                break;
            case OPERARIO:
                rol = "3";
                break;
            case ADMINISTRADOR:
                rol = "4";
                break;
            default:
                throw new AssertionError(usuarioEditado.getRol().name());
        }
        return rol;
    }

    public void setRol(String rol) {
        switch (rol) {
            case "0":
                usuarioEditado.setRol(Enumeraciones.Rol.CLIENTE);
                break;
            case "1":
                usuarioEditado.setRol(Enumeraciones.Rol.CALL_CENTER);
                break;
            case "2":
                usuarioEditado.setRol(Enumeraciones.Rol.SUPERVISOR);
                break;
            case "3":
                usuarioEditado.setRol(Enumeraciones.Rol.OPERARIO);
                break;
            case "4":
                usuarioEditado.setRol(Enumeraciones.Rol.ADMINISTRADOR);
                break;
            default:
                throw new AssertionError(usuarioEditado.getRol().name());
        }
    }

    public boolean checkPasswords() {
        return pass.equals(pass2);
    }

    public void keepPwd() {
        usuario.setPassword(hash.getHash(pass));
    }

 //hasta aqui usuario
    public String update() throws EMASAException { // Actualiza los cambios en la base de datos y redirige al usuario a la pagina principal
        if(pass != null)
        {
            if(checkPasswords())
            {
                usuarioEditado.setPassword(hash.getHash(pass));
                basededatos.actualizarUsuario(usuarioEditado); // Actualizamos el usuario en la base de datos
                mensaje = "";
                return home();
            }
            else
            {
                mensaje = "Las contraseñas no coinciden";
                return "";
            }
        }
        else
        {
            basededatos.actualizarUsuario(usuarioEditado);
            mensaje = "";
            return home();
        }    
    }
}

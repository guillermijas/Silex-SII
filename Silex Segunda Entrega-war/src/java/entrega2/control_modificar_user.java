package entrega2;


import entrega1.Usuario;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;


@ManagedBean
@ViewScoped
public class control_modificar_user implements Serializable 
{
    @Inject
    private ControlAutorizacion ctrl;
    
    @Inject
    private Hash hash;
    
    private Usuario usuario;
    private String email;
    private String pass;
    private String pass2;


    private String nombre;
    private String apellidos;
    private String telefono;
    private String direccion;
    
    // Rol supervisor
    private String zonaCargo;

    //rol operario
    private String tipo;
    private String especializacion;
    private boolean disponibilidad;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String p) {
        this.pass = hash.getHash(p);
    }
    
        public String getPass2() {
        return pass2;
    }

    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getZonaCargo() {
        return zonaCargo;
    }

    public void setZonaCargo(String zonaCargo) {
        this.zonaCargo = zonaCargo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEspecializacion() {
        return especializacion;
    }

    public void setEspecializacion(String especializacion) {
        this.especializacion = especializacion;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
    
    
    public control_modificar_user()
    {
        
    }
    
    public boolean checkSession()
    {
        if(ctrl.getUsuario() != null)
        {
            usuario = ctrl.getUsuario();
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public void setUsuario(Usuario us)
    {
        this.usuario = us;
    }
    
    public Usuario getUsuario()
    {
        return this.usuario;
    }
    
    public String update() // Actualiza los cambios en la base de datos y redirige al usuario a la pagina principal
    {
        ctrl.setUsuario(usuario);
        return ctrl.home();
    }
    
    public String getUsername()
    {
        return usuario.getUsername();
    }
    
    
}

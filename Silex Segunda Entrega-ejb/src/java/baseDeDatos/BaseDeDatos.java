/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseDeDatos;

import entrega1.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class BaseDeDatos implements BaseDeDatosLocal {

    @PersistenceContext(unitName = "DataBasePU")
    
      private static final int TAM_CADENA_VALIDACION = 20;

    private EntityManager em;
    
    @Override
    public void insertarUsuario(Usuario us) throws EMASAException{
       
        Usuario user = em.find(Usuario.class, us.getUsername());
        if (user != null) {
            // El usuario ya existe
            throw new UsuarioExistenteException();
        }
    }

    @Override
    public void eliminarUsuario(Usuario us) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificarUsuario(Usuario us) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean estaRegistrado(Usuario us) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
}

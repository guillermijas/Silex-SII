/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseDeDatos;

import entrega1.Usuario;
import javax.ejb.Local;

/**
 *
 * @author Charlie
 */
@Local
public interface BaseDeDatosLocal 
{
    public void insertarUsuario(Usuario us) throws EMASAException;
    public void eliminarUsuario(Usuario us) throws EMASAException;
    public void modificarUsuario(Usuario us) throws EMASAException;
    public boolean estaRegistrado(Usuario us) throws EMASAException;
}

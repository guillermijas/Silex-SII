/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseDeDatos;

import entrega1.Aviso;
import entrega1.OrdenDeTrabajo;
import entrega1.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Charlie
 */
@Local
public interface BaseDeDatosLocal {

    // Usuarios en la BD
    public boolean insertarUsuario(Usuario us) throws EMASAException;

    public void eliminarUsuario(Usuario us) throws EMASAException;

    public void actualizarUsuario(Usuario us) throws EMASAException;

    public boolean estaRegistrado(Usuario us);

    public boolean estaRegistrado(String username);

    public Usuario getUsuario(String username);

    public void compruebaLogin(Usuario us) throws EMASAException;

    public void validarUsuario(String username, String validacion) throws EMASAException;

    // Avisos en la BD
    public void insertarAviso(Aviso aviso) throws EMASAException;

    public void eliminarAviso(Aviso aviso) throws EMASAException;

    public void modificarAviso(Aviso aviso) throws EMASAException;

    public boolean estaRegistrado(Aviso aviso);

    public List<Aviso> getAvisosNueva();

    public List<Aviso> getAvisosEnProceso();

    public List<Aviso> getAvisosCerrada();

    public List<Aviso> getAvisosIncidencia();
    
    public void cerrarAviso(Long id) throws EMASAException;
    
    public long getIDNewAviso();

    // Ordenes de trabajo en la BD
    public void insertarOT(OrdenDeTrabajo ot) throws EMASAException;

    public void eliminarOT(OrdenDeTrabajo ot) throws EMASAException;

    public void modificarOT(OrdenDeTrabajo ot) throws EMASAException;

    public boolean OTRegistrado(OrdenDeTrabajo ot);

    public List<OrdenDeTrabajo> getOtEnProceso();

    public List<OrdenDeTrabajo> getOtCerradas();

    public void cerrarOt(Long id) throws EMASAException;
}

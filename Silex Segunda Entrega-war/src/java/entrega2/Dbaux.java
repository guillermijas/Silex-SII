/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entrega2;

import entrega1.Aviso;
import entrega1.Cliente;
import entrega1.Coordenada;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import entrega1.Enum.estado;
/**
 *
 * @author operador
 */
@Named(value = "dbaux")
@SessionScoped
public class Dbaux implements Serializable {

    private static List<Aviso> avisos = new ArrayList<>();
   
    private String ayy = "lmao";
    private static long numb = 3L;

    static void addAviso(entrega1.Enum.prioridad prioridad, String direccion, entrega1.Enum.estado estado, entrega1.Enum.gravedad gravedad, entrega1.Cliente cliente, String img, Date fecha_inicio, String Descripcion) {
        Aviso aux = new Aviso();
        aux.setPrioridad(prioridad);
        aux.setDireccion(direccion);
        aux.setEstado(estado);
        aux.setGravedad(gravedad);
        aux.setImagen(img);
        aux.setCliente(cliente);
        aux.setFechainicio(fecha_inicio);
        aux.setDescripcion(Descripcion);
        numb = numb + 1L;
        aux.setIdAviso(numb);
        avisos.add(aux);
    }

    static void addAviso(String direccion, entrega1.Cliente cliente, String Descripcion) {
        Aviso aux = new Aviso();
        aux.setPrioridad(entrega1.Enum.prioridad.MEDIA);
        aux.setDireccion("direccion");
        aux.setEstado(entrega1.Enum.estado.INCIDENCIA);
        aux.setGravedad(entrega1.Enum.gravedad.LEVE);
        aux.setCliente(cliente);
        aux.setDescripcion(Descripcion);
        numb = numb + 1L;
        aux.setIdAviso(numb);
        avisos.add(aux);

    }

    static void init() {
        avisos = new ArrayList<Aviso>();
        
        Aviso avuno = new Aviso();
        avuno.setLocalizacion(new Coordenada("36.715914", "-4.477880"));
        avuno.setPrioridad(entrega1.Enum.prioridad.MEDIA);
        avuno.setDireccion("calle catorce");
        avuno.setEstado(entrega1.Enum.estado.INCIDENCIA);
        avuno.setGravedad(entrega1.Enum.gravedad.LEVE);
        Cliente pep = new Cliente();
        pep.setUsername("pepe");
        avuno.setCliente(pep);
        avuno.setIdAviso(1L);
        avuno.setImagen("img/o.jpg");
        avuno.setDescripcion("Se ha roto todo necesito AYUDA");
        avuno.setUrgente(true);
        avuno.setPlanificado(true);
        avisos.add(avuno);

        Aviso avdos = new Aviso();
        avdos.setPrioridad(entrega1.Enum.prioridad.MEDIA);
        avdos.setDireccion("calle catorce");
        avdos.setEstado(entrega1.Enum.estado.NUEVA);
        avdos.setGravedad(entrega1.Enum.gravedad.LEVE);
        avdos.setLocalizacion(new Coordenada("48.067652", "12.858095"));
        Cliente peep = new Cliente();
        peep.setUsername("pin");
        avdos.setCliente(peep);
        avdos.setIdAviso(2L);
        avdos.setDescripcion("Holaaa");

        avisos.add(avdos);

        Aviso avtres = new Aviso();
        avtres.setPrioridad(entrega1.Enum.prioridad.MEDIA);
        avtres.setDireccion("calle catorce");
        avtres.setEstado(entrega1.Enum.estado.EN_PROCESO);
        avtres.setGravedad(entrega1.Enum.gravedad.LEVE);
        //avtres.setCliente("pepe");
        avtres.setIdAviso(3L);
        avtres.setLocalizacion(new Coordenada("36.715914", "4.477880"));
        avtres.setDescripcion("Mi vecino aun no usa telegram y queiro matarlo");

        avisos.add(avtres);

        
        Aviso avc = new Aviso();
        avc.setPrioridad(entrega1.Enum.prioridad.MEDIA);
        avc.setDireccion("calle catorce");
        avc.setEstado(entrega1.Enum.estado.CERRADA);
        avc.setGravedad(entrega1.Enum.gravedad.LEVE);
        avc.setCliente(pep);
        avc.setIdAviso(3L);
        avc.setImagen("img/o2.jpg");
        avc.setLocalizacion(new Coordenada("43.645074", "-115.993081"));
        avc.setDescripcion("He quitado el usb en modo no seguro aiudenme");
        avisos.add(avc);
        
    }
    
    public String getAyy() {
        return ayy;
    }
    
    public List<Aviso> getAvisosNueva(){
        List<Aviso> lista= new ArrayList<>();
        for(int i=0; i<avisos.size();i++){
            if(avisos.get(i).getEstado().equals(estado.NUEVA)){
                lista.add(avisos.get(i));
            }
        }
        return lista;
    }
    
    public List<Aviso> getAvisosEnProceso(){
        List<Aviso> lista= new ArrayList<>();
        for(int i=0; i<avisos.size();i++){
            if(avisos.get(i).getEstado().equals(estado.EN_PROCESO)){
                lista.add(avisos.get(i));
            }
        }
        return lista;
    }
    
    public List<Aviso> getAvisosCerrada(){
        List<Aviso> lista= new ArrayList<>();
        for(int i=0; i<avisos.size();i++){
            if(avisos.get(i).getEstado().equals(estado.CERRADA)){
                lista.add(avisos.get(i));
            }
        }
        return lista;
    }
    
    public List<Aviso> getAvisosIncidencia(){
        List<Aviso> lista= new ArrayList<>();
        for(int i=0; i<avisos.size();i++){
            if(avisos.get(i).getEstado()==estado.INCIDENCIA){
                lista.add(avisos.get(i));
            }
        }
        return lista;
    }
    public List<Aviso> getAvisos() {
        return avisos;
    }
    
}

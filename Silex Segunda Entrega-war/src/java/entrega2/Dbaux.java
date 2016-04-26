/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entrega2;

import entrega1.Aviso;
import entrega1.Coordenada;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author operador
 */
@Named(value = "dbaux")
@SessionScoped
public class Dbaux implements Serializable{
    private static List<Aviso> avisos = new ArrayList<Aviso>();
    private String ayy = "lmao";
    private static long numb = 3L;
    static void addAviso(entrega1.Enum.prioridad prioridad,String direccion,entrega1.Enum.estado estado, entrega1.Enum.gravedad gravedad, entrega1.Cliente cliente, String img, Date fecha_inicio, String Descripcion){
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
    static void addAviso(String direccion, entrega1.Cliente cliente, String Descripcion){
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
       avuno.setLocalizacion(new Coordenada("http://maps.google.com/maps?z=12&t=m&q=loc:36.715914","-4.477880"));
       avuno.setPrioridad(entrega1.Enum.prioridad.MEDIA);
       avuno.setDireccion("calle catorce");
       avuno.setEstado(entrega1.Enum.estado.INCIDENCIA);
       avuno.setGravedad(entrega1.Enum.gravedad.LEVE);
       //avuno.setCliente("pepe");
       avuno.setIdAviso(1L);
       avuno.setImagen("img/o.jpg");
       avuno.setDescripcion("Se ha roto todo necesito AYUDA");
       avisos.add(avuno);
              Aviso avdos = new Aviso();
       avdos.setPrioridad(entrega1.Enum.prioridad.MEDIA);
       avdos.setDireccion("calle catorce");
       avdos.setEstado(entrega1.Enum.estado.INCIDENCIA);
       avdos.setGravedad(entrega1.Enum.gravedad.LEVE);
       avdos.setLocalizacion(new Coordenada("http://maps.google.com/maps?z=12&t=m&q=loc:48.067652","12.858095"));

       //avdos.setCliente("pepe");
       avdos.setIdAviso(2L);
       avdos.setDescripcion("Holaaa");
       avisos.add(avdos);

              Aviso avtres = new Aviso();
       avtres.setPrioridad(entrega1.Enum.prioridad.MEDIA);
       avtres.setDireccion("calle catorce");
       avtres.setEstado(entrega1.Enum.estado.INCIDENCIA);
       avtres.setGravedad(entrega1.Enum.gravedad.LEVE);
       //avtres.setCliente("pepe");
       avtres.setIdAviso(3L);
       avtres.setLocalizacion(new Coordenada("http://maps.google.com/maps?z=12&t=m&q=loc:36.715914","4.477880"));
       avtres.setDescripcion("Mi vecino aun no usa telegram y queiro matarlo");
       avisos.add(avtres);
       
    
    }
   
    public String getAyy(){
        return ayy;
    }
    public List<Aviso> getAvisos(){
        return avisos;
    }
}

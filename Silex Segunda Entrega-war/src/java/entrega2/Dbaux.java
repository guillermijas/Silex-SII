/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entrega2;

import entrega1.Aviso;
import java.io.Serializable;
import java.util.ArrayList;
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
    static void init() {
       avisos = new ArrayList<Aviso>();
       Aviso avuno = new Aviso();
       avuno.setPrioridad(entrega1.Enum.prioridad.MEDIA);
       avuno.setDireccion("calle catorce");
       avuno.setEstado(entrega1.Enum.estado.INCIDENCIA);
       avuno.setGravedad(entrega1.Enum.gravedad.LEVE);
       //avuno.setCliente("pepe");
       avuno.setIdAviso(1L);
       avuno.setDescripcion("Se ha roto todo necesito AYUDA");
       avisos.add(avuno);
              Aviso avdos = new Aviso();
       avdos.setPrioridad(entrega1.Enum.prioridad.MEDIA);
       avdos.setDireccion("calle catorce");
       avdos.setEstado(entrega1.Enum.estado.INCIDENCIA);
       avdos.setGravedad(entrega1.Enum.gravedad.LEVE);
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

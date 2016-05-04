/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entrega2;

import entrega1.Aviso;
import entrega1.Cliente;
import entrega1.Coordenada;
import entrega1.Enumeraciones;
import entrega1.Enumeraciones.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import entrega1.OrdenDeTrabajo;

@Named(value = "dbaux")
@SessionScoped
public class Dbaux implements Serializable {

    private static List<Aviso> avisos = new ArrayList<>();
    private static List<OrdenDeTrabajo> ots = new ArrayList<>();

    static void addOt(Aviso av, estado est, prioridad pr, Date fI, String instr ){
        OrdenDeTrabajo ot = new OrdenDeTrabajo();
        ot.setIdOT(Enumeraciones.getIdOt());
        Enumeraciones.incrIdOt();
        ot.setAviso(av);
        ot.setPrioridad(pr);
        ot.setEstado(est);
        ot.setFechainicio(fI);
        ot.setInstrucciones(instr);
        ots.add(ot);
    }
    static void addAviso(entrega1.Enumeraciones.prioridad prioridad, String direccion, entrega1.Enumeraciones.estado estado, entrega1.Enumeraciones.gravedad gravedad, entrega1.Cliente cliente, String img, Date fecha_inicio, String Descripcion, Coordenada c, boolean ur, boolean plan) {
        Aviso aux = new Aviso();
        aux.setPrioridad(prioridad);
        aux.setDireccion(direccion);
        aux.setEstado(estado);
        aux.setGravedad(gravedad);
        aux.setImagen(img);
        aux.setCliente(cliente);
        aux.setFechainicio(fecha_inicio);
        aux.setDescripcion(Descripcion);
        aux.setIdAviso(Enumeraciones.getIdAviso());
        Enumeraciones.incrIdAviso();
        aux.setLocalizacion(c);
        aux.setUrgente(ur);
        aux.setPlanificado(plan);
        avisos.add(aux);
    }

    static void addAviso(entrega1.Enumeraciones.prioridad prioridad, String direccion, entrega1.Enumeraciones.estado estado, entrega1.Enumeraciones.gravedad gravedad, String img, Date fecha_inicio, String Descripcion, Coordenada c, boolean ur, boolean plan) {
        Aviso aux = new Aviso();
        aux.setPrioridad(prioridad);
        aux.setDireccion(direccion);
        aux.setEstado(estado);
        aux.setGravedad(gravedad);
        aux.setImagen(img);
        aux.setFechainicio(fecha_inicio);
        aux.setDescripcion(Descripcion);
        aux.setIdAviso(Enumeraciones.getIdAviso());
        Enumeraciones.incrIdAviso();
        aux.setLocalizacion(c);
        aux.setUrgente(ur);
        aux.setPlanificado(plan);
        avisos.add(aux);
    }

    static void init() {
        avisos = new ArrayList<Aviso>();
        ots = new ArrayList<OrdenDeTrabajo>();
        Cliente cl1 = new Cliente();
        cl1.setNombre("pepe");
        cl1.setUsername("pepe");
        addAviso(prioridad.MEDIA,"Calle Álamos, 25", estado.INCIDENCIA, gravedad.LEVE, cl1,"img/fuga (1).jpg", new Date(2016,3,9), "La presión del agua es muy baja en mi casa.",new Coordenada("36.715914", "-4.477880"),true,false);
        addAviso(prioridad.ALTA,"Calle Carretería, 46", estado.NUEVA, gravedad.MEDIA, "img/fuga (1).png", new Date(2016,2,25), "Se ha producido una pequeña fuga en la tubería principal.",new Coordenada("48.067652", "12.858095"),true,false);
        addAviso(prioridad.ALTA,"Avenida de Andalucia", estado.EN_PROCESO, gravedad.LEVE,"img/fuga (2).jpg", new Date(2016,5,1), "Revisión del alcantarillado",new Coordenada("36.715914", "4.477880"),false,true);
        addAviso(prioridad.BAJA,"Avenida Plutarco, 58", estado.CERRADA, gravedad.ALTA, cl1,"img/fuga (3).jpg", new Date(2016,1,28), "Rotura de la válvula",new Coordenada("36.715914", "-4.477880"),true,false);
        addOt(avisos.get(1), estado.EN_PROCESO, prioridad.ALTA, new Date (2016, 4, 7), "Cambiar la tapa de la alcantarilla" );
        addOt(avisos.get(2), estado.CERRADA, prioridad.MEDIA, new Date (2016, 4, 7), "Hacer un reconocimiento de la zona" );

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
    
    public List<OrdenDeTrabajo> getOtEnProceso(){
        List<OrdenDeTrabajo> lista= new ArrayList<>();
        for(int i=0; i<ots.size();i++){
            if(ots.get(i).getEstado().equals(estado.EN_PROCESO)){
                lista.add(ots.get(i));
            }
        }
        return lista;
    }
    public List<OrdenDeTrabajo> getOtCerradas(){
        List<OrdenDeTrabajo> lista= new ArrayList<>();
        for(int i=0; i<ots.size();i++){
            if(ots.get(i).getEstado().equals(estado.CERRADA)){
                lista.add(ots.get(i));
            }
        }
        return lista;
    }
    
    public void cerrarOt(Long id){
        int i=0;
        while(i<ots.size()){
            OrdenDeTrabajo oott = ots.get(i);
            if(ots.get(i).getIdOT().equals(id)){
                oott.setEstado(estado.CERRADA);
                oott.setFechafin(new Date(2016,5,5));
            }
        }
    }
    
   public void cerrarAviso(Long id){
       int i=0;
        while(i<avisos.size()){
            Aviso av = avisos.get(i);
            if(avisos.get(i).getIdAviso().equals(id)){
                av.setEstado(estado.CERRADA);
                av.setFechafin(new Date(2016,5,5));
            }
        }
   }
}

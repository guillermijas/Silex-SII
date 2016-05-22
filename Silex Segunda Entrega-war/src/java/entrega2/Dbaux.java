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
import entrega1.Usuario;

@Named(value = "dbaux")
@SessionScoped
public class Dbaux implements Serializable {

    private static List<Aviso> avisos = new ArrayList<>();
    private static List<OrdenDeTrabajo> ots = new ArrayList<>();
    public static Aviso AvisoPrueba = getViso();

    public static Aviso getViso() {
        Usuario u1 = new Usuario();
        u1.setUsername("pepe");
        u1.setPassword("asdf");
        u1.setRol("OPERARIO");
        Cliente cl1 = new Cliente();
        cl1.setNombre("manolo");
        //cl1.setUsername("manolo");

        addAviso(prioridad.MEDIA, u1, "Calle Álamos, 25", "NUEVA", gravedad.LEVE, cl1, "img/fuga (1).jpg", new Date(2016, 3, 9), "La presión del agua es muy baja en mi casa.", new Coordenada("36.715914", "-4.477880"), true, false);
        return avisos.get(0);
    }

    static void addOt(Aviso av, estado est, prioridad pr, Date fI, String instr, List<Usuario> ops) {
        OrdenDeTrabajo ot = new OrdenDeTrabajo();
        ot.setIdOT(Enumeraciones.getIdOt());
        Enumeraciones.incrIdOt();
        ot.setAviso(av);
        ot.setPrioridad(pr);
        ot.setOperarios(ops);
        ot.setEstado(est);
        ot.setFechainicio(fI);
        ot.setInstrucciones(instr);
        ots.add(ot);
    }

    static void addAviso(entrega1.Enumeraciones.prioridad prioridad, Usuario creador, String direccion, String estado, entrega1.Enumeraciones.gravedad gravedad, entrega1.Cliente cliente, String img, Date fecha_inicio, String Descripcion, Coordenada c, boolean ur, boolean plan) {
        Aviso aux = new Aviso();
        aux.setPrioridad(prioridad);
        aux.setDireccion(direccion);
        aux.setEstado(estado);
        aux.setGravedad(gravedad);
        aux.setImagen(img);
        //aux.setCliente(cliente);
        aux.setFechainicio(fecha_inicio);
        aux.setDescripcion(Descripcion);
        aux.setIdAviso(Enumeraciones.getIdAviso());
        Enumeraciones.incrIdAviso();
        aux.setLocalizacion(c);
        aux.setUrgente(ur);
        aux.setCreador(creador);
        aux.setPlanificado(plan);
        avisos.add(aux);
    }

    static void addAviso(entrega1.Enumeraciones.prioridad prioridad, Usuario cr, String direccion, String estado, entrega1.Enumeraciones.gravedad gravedad, String img, Date fecha_inicio, String Descripcion, Coordenada c, boolean ur, boolean plan) {
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
        aux.setCreador(cr);
        aux.setUrgente(ur);
        aux.setPlanificado(plan);
        avisos.add(aux);
    }

    static void init() {
        avisos = new ArrayList<Aviso>();
        ots = new ArrayList<OrdenDeTrabajo>();
        Usuario u1 = new Usuario();
        u1.setUsername("pepe");
        u1.setPassword("asdf");
        u1.setRol("OPERARIO");

        Usuario u2 = new Usuario();
        u2.setUsername("manolo");
        u2.setPassword("1234");
        u2.setRol("CLIENTE");

        Usuario u3 = new Usuario();
        u3.setNombre("Paco");
        u3.setPassword("abc");
        u3.setRol("CALL_CENTER");

        Usuario u4 = new Usuario();
        u4.setUsername("juan");
        u4.setPassword("4567");
        u4.setRol("OPERARIO");

        Cliente cl1 = new Cliente();
        cl1.setNombre("manolo");
        //cl1.setUsername("manolo");

        addAviso(prioridad.MEDIA, u1, "Calle Álamos, 25", "INCIDENCIA", gravedad.LEVE, "img/fuga (1).jpg", new Date(2016, 3, 9), "La presión del agua es muy baja en mi casa.", new Coordenada("36.715914", "-4.477880"), true, false);
        addAviso(prioridad.ALTA, u2, "Calle Carretería, 46", "NUEVA", gravedad.MEDIA, cl1, "img/fuga (1).png", new Date(2016, 2, 25), "Se ha producido una pequeña fuga en la tubería principal.", new Coordenada("48.067652", "12.858095"), true, false);
        addAviso(prioridad.ALTA, u1, "Avenida de Andalucia", "EN_PROCESO", gravedad.LEVE, "img/fuga (2).jpg", new Date(2016, 5, 1), "Revisión del alcantarillado", new Coordenada("36.715914", "4.477880"), false, true);
        addAviso(prioridad.BAJA, u3, "Avenida Plutarco, 58", "CERRADA", gravedad.ALTA, cl1, "img/fuga (3).jpg", new Date(2016, 1, 28), "Rotura de la válvula", new Coordenada("36.715914", "-4.477880"), true, false);

        List<Usuario> ops = new ArrayList<>();
        ops.add(u1);
        ops.add(u2);
        addOt(avisos.get(1), estado.EN_PROCESO, prioridad.ALTA, new Date(2016, 4, 7), "Cambiar la tapa de la alcantarilla", ops);
        ops.remove(u1);
        addOt(avisos.get(2), estado.CERRADA, prioridad.MEDIA, new Date(2016, 4, 7), "Hacer un reconocimiento de la zona", ops);
        ots.get(1).setFechafin(new Date(2016, 5, 6));
    }
}

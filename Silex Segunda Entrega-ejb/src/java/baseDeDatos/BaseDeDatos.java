/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseDeDatos;

import entrega1.Aviso;
import entrega1.Enumeraciones;
import entrega1.OrdenDeTrabajo;
import entrega1.Usuario;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@Stateless
public class BaseDeDatos implements BaseDeDatosLocal {

    @PersistenceContext(unitName = "DataBase")
    private EntityManager em;

    @Context
    UriInfo uri;

    // Gestión de Usuarios en la BD. PK -> Username
    private int tam_validacion = 20;

    @Override
    public boolean insertarUsuario(Usuario us) throws EMASAException {

        Usuario user = em.find(Usuario.class, us.getUsername());
        boolean ok = false;
        if (user != null) {
            // El usuario ya existe
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario ya existente", "Usuario ya existente"));
            throw new UsuarioExistenteException();
        } else {
            String cadena = generarCadenaAleatoria();
            us.setCadenaValidacion(cadena);
            us.setRegistroOk(true); // Establecemos que el registro se ha producido correctamente -> Para usarlo en exitoRegistro
            em.persist(us);
            ok = true;
            // Y mandamos el email al usuario para que confirme el email
            mandarEmail(us, cadena);
        }
        return ok;
    }

    @Override
    public void eliminarUsuario(Usuario us) throws EMASAException {
        compruebaLogin(us);
        em.remove(em.merge(us));
    }

    @Override
    public void compruebaLogin(Usuario u) throws EMASAException {
        Usuario us = em.find(Usuario.class, u.getUsername());

        if (us == null) // El usuario no se encuentra en la base de datos
        {
            throw new CuentaInexistenteException();
        } else if (us.getCadenaValidacion() != null) {
            throw new CuentaNoActivadaException();
        } else if (!us.getPassword().equals(u.getPassword())) {
            throw new ContrasenaInvalidaException();
        }
    }

    @Override
    public void actualizarUsuario(Usuario us) throws EMASAException {
        compruebaLogin(us);
        em.merge(us);
    }

    @Override
    public boolean estaRegistrado(Usuario u) {
        Usuario us = em.find(Usuario.class, u.getUsername());
        return us != null;
    }

    @Override
    public boolean estaRegistrado(String username) {
        Usuario us = em.find(Usuario.class, username);
        return us != null;
    }

    @Override
    public Usuario getUsuario(String username) {
        return em.find(Usuario.class, username);
    }

    private String generarCadenaAleatoria() {
        Random rnd = new Random(System.currentTimeMillis());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < tam_validacion; i++) {
            int v = rnd.nextInt(62);
            if (v < 26) {
                sb.append((char) ('a' + v));
            } else if (v < 52) {
                sb.append((char) ('A' + v - 26));
            } else {
                sb.append((char) ('0' + v - 52));
            }
        }

        return sb.toString();

    }

    @Override
    public void validarUsuario(String username, String validacion) throws EMASAException {
        Usuario u = em.find(Usuario.class, username);
        if (u == null) {
            throw new CuentaInexistenteException();
        }

        if (u.getCadenaValidacion() == null) {
            // Ya estaba activada
            return;
        }

        if (!u.getCadenaValidacion().equals(validacion)) {
            throw new ValidacionIncorrectaException();
        }

        // Si no hay ningún problema, entonces eliminamos la cadena de validacion, indicando que la cuenta ya está activada
        u.setCadenaValidacion(null);
    }

    private void mandarEmail(Usuario us, String cadenaAleatoria) {
        // Datos del host
        String host = "smtp.gmail.com"; // Gmail
        int port = 587;
        String email = "silexemasa@gmail.com"; // Correo de la cuenta asociada
        String password = "EmasaSilex2016"; // Contraseña del correo

        Properties prp = new Properties();
        prp.put("mail.smtp.user", email);
        prp.put("mail.smtp.host", host);
        prp.put("mail.smtp.port", port);
        prp.put("mail.smtp.starttls.enable", "true");
        prp.put("mail.smtp.debug", "true");
        prp.put("mail.smtp.auth", "true");
        prp.put("mail.smtp.socketFactory.port", port);
        prp.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prp.put("mail.smtp.socketFactory.fallback", "false");

        // Creamos una sesión con las propiedades anteriores
        Session session = Session.getInstance(prp);

        // Definimos el texto del cuerpo del mensaje
        String uri_base = "http://localhost:8080/Silex_Segunda_Entrega-war/faces";  //uri.getBaseUri().toString(); Null Pointer -> Preguntar
        String url_validacion = uri_base + "/validarUsuario.xhtml?username="
                + us.getUsername() + "&codigoValidacion=" + cadenaAleatoria; // Inyectar en el backing bean con uri info
        String body = "Bienvenido a EMASA. Muchas gracias por registrarse.\n"
                + "Para activar su cuenta, pulse aquí: \n " + url_validacion;

        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(email));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(us.getEmail())); // A donde vamos a mandarlo
            message.setSubject("Bienvenido a EMASA");
            message.setText(body); // También puede ser un html

            Transport transport = session.getTransport("smtp");
            transport.connect(host, port, email, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Usuario> getListaUsuarios() {
        TypedQuery<Usuario> query = em.createQuery("select u from Usuario u", Usuario.class); //Comprueba que los objetos que obtenemos son efectivamente usuarios
        return query.getResultList();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////
    // Gestión Avisos en la BD. PK -> ID
    @Override
    public void insertarAviso(Aviso aviso) throws EMASAException {
        if (!estaRegistrado(aviso)) // Es decir, no está ya en el sistema
        {
            em.persist(aviso);
        } else {
            throw new AvisoYaExistenteException();
        }
    }

    @Override
    public void eliminarAviso(Aviso aviso) throws EMASAException {
        if (!estaRegistrado(aviso)) {
            throw new AvisoInexistenteException();
        } else {
            em.remove(em.merge(aviso));
        }
    }

    @Override
    public void modificarAviso(Aviso aviso) throws EMASAException {
        if (!estaRegistrado(aviso)) {
            throw new AvisoInexistenteException();
        } else {
            em.merge(aviso);
        }
    }

    @Override
    public boolean estaRegistrado(Aviso aviso) {
        Aviso av = em.find(Aviso.class, aviso.getIdAviso());
        return av != null;
    }

    private List<Aviso> getListaAvisos() {
        TypedQuery<Aviso> query = em.createQuery("select a from Aviso a", Aviso.class);
        System.out.println("ttttttttttttttttttttttttttt");
        return query.getResultList();
    }

    @Override
    public List<Aviso> getAvisosNueva() {
        List<Aviso> lista = new ArrayList<>();
        List<Aviso> avisos = getListaAvisos();
        for (int i = 0; i < avisos.size(); i++) {
            if (avisos.get(i).getEstado().equalsIgnoreCase("NUEVA")) {
                lista.add(avisos.get(i));
            }
        }
        return lista;
    }

    @Override
    public List<Aviso> getAvisosEnProceso() {
        List<Aviso> lista = new ArrayList<>();
        List<Aviso> avisos = getListaAvisos();
        for (int i = 0; i < avisos.size(); i++) {
            if (avisos.get(i).getEstado().equalsIgnoreCase("EN_PROCESO")) {
                lista.add(avisos.get(i));
            }
        }
        return lista;
    }

    @Override
    public List<Aviso> getAvisosCerrada() {
        List<Aviso> lista = new ArrayList<>();
        List<Aviso> avisos = getListaAvisos();
        for (int i = 0; i < avisos.size(); i++) {
            if (avisos.get(i).getEstado().equalsIgnoreCase("CERRADA")) {
                lista.add(avisos.get(i));
            }
        }
        return lista;
    }

    @Override
    public List<Aviso> getAvisosIncidencia() {
        List<Aviso> lista = new ArrayList<>();
        List<Aviso> avisos = getListaAvisos();
        System.out.println("COREEEEEEEEEEEEEEEEEEEEEEEEEEEEECTIO");
        System.out.println("COREEEEEEEEEEEEEEEEEEEEEEEEEEEEECTIO");
        System.out.println("COREEEEEEEEEEEEEEEEEEEEEEEEEEEEECTIO");

        for (int i = 0; i < avisos.size(); i++) {
            if (avisos.get(i).getEstado().equalsIgnoreCase("INCIDENCIA")) {
                lista.add(avisos.get(i));
            }
        }
        System.out.println("tesy");
        return lista;
    }

    public void cerrarAviso(Long id) throws EMASAException {
        Aviso av = em.find(Aviso.class, id);
        if (av != null) {
            av.setEstado("CERRADA");
            av.setFechafin(new Date(2016, 5, 5));
            modificarAviso(av);
        } else {
            throw new AvisoInexistenteException();
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Gestión de Ordenes de trabajo en la BD. PK -> ID
    @Override
    public void insertarOT(OrdenDeTrabajo ot) throws EMASAException {
        if (OTRegistrado(ot)) {
            throw new OrdenDeTrabajoYaExistenteException();
        } else {
            em.persist(ot);
        }
    }

    @Override
    public void eliminarOT(OrdenDeTrabajo ot) throws EMASAException {
        if (!OTRegistrado(ot)) {
            throw new OrdenDeTrabajoInexistenteException();
        } else {
            em.remove(em.merge(ot));
        }
    }

    @Override
    public void modificarOT(OrdenDeTrabajo ot) throws EMASAException {
        if (!OTRegistrado(ot)) {
            throw new OrdenDeTrabajoInexistenteException();
        } else {
            em.merge(ot);
        }
    }

    @Override
    public boolean OTRegistrado(OrdenDeTrabajo ot) {
        OrdenDeTrabajo orden = em.find(OrdenDeTrabajo.class, ot.getIdOT());
        return orden != null;
    }

    private List<OrdenDeTrabajo> getListaOT() {
        TypedQuery<OrdenDeTrabajo> query = em.createQuery("select ot from OrdenDeTrabajo ot", OrdenDeTrabajo.class); //Comprueba que los objetos que obtenemos son efectivamente usuarios
        return query.getResultList();
    }

    @Override
    public List<OrdenDeTrabajo> getOtEnProceso() {
        List<OrdenDeTrabajo> lista = new ArrayList<>();
        List<OrdenDeTrabajo> ots = getListaOT();
        for (int i = 0; i < ots.size(); i++) {
            if (ots.get(i).getEstado().equals(Enumeraciones.estado.EN_PROCESO)) {
                lista.add(ots.get(i));
            }
        }
        return lista;
    }

    @Override
    public List<OrdenDeTrabajo> getOtCerradas() {
        List<OrdenDeTrabajo> lista = new ArrayList<>();
        List<OrdenDeTrabajo> ots = getListaOT();
        for (int i = 0; i < ots.size(); i++) {
            if (ots.get(i).getEstado().equals(Enumeraciones.estado.CERRADA)) {
                lista.add(ots.get(i));
            }
        }
        return lista;
    }

    @Override
    public void cerrarOt(Long id) throws EMASAException {
        OrdenDeTrabajo ot = em.find(OrdenDeTrabajo.class, id);
        if (ot != null) {
            ot.setEstado(Enumeraciones.estado.CERRADA);
            ot.setFechafin(new Date(2016, 5, 5));
            modificarOT(ot);
        } else {
            throw new OrdenDeTrabajoInexistenteException();
        }
    }
}

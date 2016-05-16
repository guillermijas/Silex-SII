/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseDeDatos;

import entrega1.Usuario;
import java.util.Properties;
import java.util.Random;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class BaseDeDatos implements BaseDeDatosLocal {

    @PersistenceContext(unitName = "DataBase")
    
    private final int TAM_CADENA_VALIDACION = 20;

    private EntityManager em;
    
    @Override
    public void insertarUsuario(Usuario us) throws EMASAException{
       
        Usuario user = em.find(Usuario.class, us.getUsername());
        if (user != null) {
            // El usuario ya existe
            throw new UsuarioExistenteException();
        }
        
        us.setCadenaValidacion(generarCadenaAleatoria());
        em.persist(us);
        
        // Y mandamos el email al usuario para que confirme el email
        
        mandarEmail(us);
    }

    @Override
    public void eliminarUsuario(Usuario us) throws EMASAException {
        compruebaLogin(us);
        em.remove(em.merge(us));
    }
    
    @Override
    public void compruebaLogin(Usuario u) throws EMASAException 
    {
        Usuario us = em.find(Usuario.class, u.getUsername());
        
        if (us == null) // El usuario no se encuentra en la base de datos
        {
            throw new CuentaInexistenteException();
        }
        else
        {
            if (us.getCadenaValidacion() != null)
            {
                throw new CuentaNoActivadaException();
            }
            else
            {
                if (!us.getPassword().equals(u.getPassword()))
                {
                    throw new ContrasenaInvalidaException();
                }
            }
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
    
    private String generarCadenaAleatoria() {
        Random rnd = new Random(System.currentTimeMillis());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < TAM_CADENA_VALIDACION; i++) {
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
    public void validarUsuario(Usuario us, String validacion) throws EMASAException
    {
        Usuario u = em.find(Usuario.class, us.getUsername());
        if (u == null)
        {
            throw new CuentaInexistenteException();
        }
        
        if(u.getCadenaValidacion() == null)
        {
            // Ya estaba activada
            return;
        }
        
        if (!u.getCadenaValidacion().equals(validacion))
        {
            throw new ValidacionIncorrectaException();
        }
        
        // Si no hay ningún problema, entonces eliminamos la cadena de validacion, indicando que la cuenta ya está activada
        u.setCadenaValidacion(null);
    }
    
    private void mandarEmail(Usuario us)
    {
        /*
        http://stackoverflow.com/questions/5235149/sending-email-from-a-java-stateless-ejb-java-ee-6
        http://spitballer.blogspot.com.es/2010/02/sending-email-via-glassfish-v3.html
        */
        // Datos del host
        String host = "smtp.gmail.com"; // Gmail
        int port = 587;
        String email = "xxxx@gmail.com"; // Correo de la cuenta asociada
        String password = "xxxxx"; // Contraseña del correo
        
        Properties prp = new Properties();
        prp.put("mail.smtp.user", email);
        prp.put("mail.smtp.host",host);
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
        String url_validacion = "http://localhost:8080/";
        String body = "Bienvenido a EMASA. Muchas gracias por registrarse. /n"
                + "Para activar su cuenta, pulse aquí "; 
        
        try
        {
            Message message = new MimeMessage(session);
            
            message.setFrom(new InternetAddress(email));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(us.getEmail())); // A donde vamos a mandarlo
            message.setSubject("Bienvenido a EMASA");
            message.setText(body); // También puede ser un html
            
            Transport transport = session.getTransport("smtp");
            transport.connect(host, port, email, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException e)
        {
            throw new RuntimeException(e);
        }
    }  
}

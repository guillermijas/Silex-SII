/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entrega2;

import entrega1.Enum.Rol;
import entrega1.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Charlie
 */
@Named(value = "dataBase")
@ApplicationScoped
public class DataBase {

    /**
     * Creates a new instance of DataBase
     */
    @Inject
    private Hash hash;
    
    private List<Usuario> usuarios = new ArrayList<Usuario>();
    
    public DataBase() 
    {
        // Añadimos dos usuarios de prueba
       // usuarios.add(new Usuario("pepe", hash.getHash("asdf"), Rol.CLIENTE));
        //usuarios.add(new Usuario("manolo", hash.getHash("qwer"), Rol.OPERARIO));
    }
    
    public boolean isAlreadyContent(Usuario us)
    {
        return usuarios.contains(us);
    }
    
    public boolean isUsernameContent(String username)
    {
        boolean isContent = false;
        
        for(Usuario u : usuarios)
        {
            if (u.getUsername().equals(username))
            {
                isContent = true;
                break;
            }
        }
        return isContent;
    }
    
   public void insertNewUser(Usuario us)
    {
        // Primero comprobamos que ese usuario no está ya en la base de datos   
        if(!isAlreadyContent(us))
        {
            usuarios.add(us);
        }
        else
        {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario ya existente", "Usuario ya existente")); 
        }
    }
    
    public void deleteUser(Usuario us)
    {
        usuarios.remove(us);
    }
    
    public int getIndexUser(Usuario us)
    {
        return usuarios.indexOf(us);
    }
    
    public int getIndexUsername(String us)
    {
        int index = 0;
        for (Usuario u : usuarios)
        {
           if(u.getUsername().equals(us))
           {
               index = getIndexUser(u);
           }
        }
        return index;
    }
    
    public Usuario getUserbyIndex(int index)
    {
        return usuarios.get(index);
    }
    
    public boolean emptyDataBase()
    {
        return usuarios.isEmpty();
    }
}

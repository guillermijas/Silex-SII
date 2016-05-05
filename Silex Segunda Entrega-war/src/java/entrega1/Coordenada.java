/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entrega1;

import java.io.Serializable;

/**
 *
 * @author operador
 */
public class Coordenada implements Serializable {

    private String linkGoogle = "http://maps.google.com/maps?z=12&t=m&q=loc:";
    private String height = "0.0";
    private String lenght = "0.0";

    public Coordenada(){
        
    }
    public Coordenada(String h, String l) {
        this.height = h;
        this.lenght = l;
    }

    public String getHeight() {
        return height;
    }

    public String getLenght() {
        return lenght;
    }

    public String getLinkGoogle() {
        return linkGoogle;
    }

    @Override
    public String toString() {
        return height + " , " + lenght;
    }

    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entrega2;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Charlie
 */
@Named(value = "hash")
@ApplicationScoped
public class Hash {

    /**
     * Creates a new instance of Hash
     */
    
    private final int tam_bytes = 16;
    private final String algorithm = "SHA-256";
    
    public Hash() 
    {
        
    }
    
    private byte [] doHash (String text)
    {
        byte [] digest = new byte [tam_bytes];
        byte [] buffer = text.getBytes();

        try {
               MessageDigest mD = MessageDigest.getInstance(algorithm);
               mD.reset();
               mD.update(buffer);
               digest = mD.digest();
        } catch (NoSuchAlgorithmException e)
        {
               e.printStackTrace();
        }
        return digest;
    }
    
    public String getHash(String password)
    {
        return new BigInteger(1, doHash(password)).toString(16);
    }
    
}

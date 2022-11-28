/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kerberosv4;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.concurrent.ThreadLocalRandom;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
/**
 *
 * @author Axel Zarate Lozano
 */
public class KerberosV4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        
        SecretKey yo = KeyGenerator.getInstance("DES").generateKey();
        String cadena = convertAnyKey2String(yo);
        System.out.println(cadena);
        
    }
    
    public static String convertAnyKey2String(Key key) {
        byte[] keyEncoded = key.getEncoded();
        return Base64.getEncoder().encodeToString(keyEncoded);
    }
    
}

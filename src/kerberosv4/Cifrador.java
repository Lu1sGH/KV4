
package kerberosv4;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Cifrador {

    public SecretKey claveSecreta;
    public String ticket;

    public Cifrador(){

    }

    public String Principal(SecretKey cS, String t) throws Exception {
        
        this.claveSecreta = cS;
        this.ticket = t;
        
        String tC = encrypt(this.claveSecreta, this.ticket);
        
        return tC;        
    }

    public static String encrypt(SecretKey cS, String aCifrar) throws Exception {
        Cipher encryptCypher = Cipher.getInstance("DES");
        encryptCypher.init(Cipher.ENCRYPT_MODE, cS);
        return cifrar(encryptCypher, aCifrar);
    }
    
    public static String cifrar(Cipher encryptCipher, String toEncrypt) throws Exception {
        byte[] bytesToEncrypt = toEncrypt.getBytes(StandardCharsets.UTF_8);
        byte[] bytesEncrypted = encryptCipher.doFinal(bytesToEncrypt);
        bytesEncrypted = Base64.getEncoder().encode(bytesEncrypted);
        return new String(bytesEncrypted);
    }

}


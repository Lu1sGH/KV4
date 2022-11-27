
package kerberosv4;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Descifrador{

    public SecretKey claveSecreta;
    public String ticket;

    public Descifrador(){

    }

    public String Principal(SecretKey cS, String t) throws Exception {

        this.claveSecreta = cS;
        this.ticket = t;

        String tD = Decrypt(this.claveSecreta, this.ticket);

        return tD;
    }

    public String Decrypt(SecretKey cS, String cifrado) throws Exception {
        Cipher decryptCypher = Cipher.getInstance("DES");
        decryptCypher.init(Cipher.DECRYPT_MODE, cS);
        return descifrar(decryptCypher, cifrado);
    }
    
    public static String descifrar(Cipher decryptCypher, String toDecrypt) throws Exception {
        byte[] bytesToDecrypt = Base64.getDecoder().decode(toDecrypt.getBytes());
        byte[] bytesDecrypted = decryptCypher.doFinal(bytesToDecrypt);
        return new String(bytesDecrypted);
    }

}

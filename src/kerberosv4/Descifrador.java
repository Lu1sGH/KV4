
package kerberosv4;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Descifrador{

    public SecretKey claveSecreta;
    public String msj;

    public Descifrador(){

    }

    public String Principal(SecretKey cS, String m) throws Exception {

        this.claveSecreta = cS;
        this.msj = m;

        String tD = Decrypt(this.claveSecreta, this.msj);

        return tD;
    }

    private String Decrypt(SecretKey cS, String cifrado) throws Exception {
        Cipher decryptCypher = Cipher.getInstance("DES");
        decryptCypher.init(Cipher.DECRYPT_MODE, cS);
        return descifrar(decryptCypher, cifrado);
    }
    
    private static String descifrar(Cipher decryptCypher, String toDecrypt) throws Exception {
        byte[] bytesToDecrypt = Base64.getDecoder().decode(toDecrypt.getBytes());
        byte[] bytesDecrypted = decryptCypher.doFinal(bytesToDecrypt);
        return new String(bytesDecrypted);
    }

}

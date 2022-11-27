package kerberosv4;

import java.util.Base64;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class GeneradorCS {

    public GeneradorCS() {
    }
    
    public SecretKey cS(String sKS){
        byte[] decodedKey = Base64.getDecoder().decode(sKS);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "DES");
    }
    
}

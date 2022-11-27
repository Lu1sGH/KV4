
package kerberosv4;

import java.io.Serializable;

public class AutenticadorC implements Serializable{
    
    public String idC;
    public String adC;
    public String tS;

    public AutenticadorC() {
    }

    public String getIdC() {
        return idC;
    }

    public void setIdC(String idC) {
        this.idC = idC;
    }

    public String getAdC() {
        return adC;
    }

    public void setAdC(String adC) {
        this.adC = adC;
    }

    public String gettS() {
        return tS;
    }

    public void settS(String tS) {
        this.tS = tS;
    }
    
}

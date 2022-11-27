
package kerberosv4;

import java.io.Serializable;
import java.net.InetAddress;
import javax.crypto.SecretKey;

public class TicketC implements Serializable {
    
    public String cS;
    public String idC;
    public String adC;
    public String idTGSoV;
    public String tS;
    public String lifeTime;

    public TicketC() {
    }

    public String getcS() {
        return cS;
    }

    public void setcS(String cS) {
        this.cS = cS;
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

    public String getIdTGSoV() {
        return idTGSoV;
    }

    public void setIdTGSoV(String idTGSoV) {
        this.idTGSoV = idTGSoV;
    }

    public String gettS() {
        return tS;
    }

    public void settS(String tS) {
        this.tS = tS;
    }

    public String getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(String lifeTime) {
        this.lifeTime = lifeTime;
    }
    
}

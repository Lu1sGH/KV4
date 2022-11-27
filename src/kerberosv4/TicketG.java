
package kerberosv4;

import javax.crypto.SecretKey;

public class TicketG {
    
    public TicketC ticket;
    public Cifrador cifrador;
    public Descifrador descifrador;

    public TicketG() {
    }
    
    public TicketC generador(String pQ, SecretKey cS){
        
        switch(pQ){
            case "TGS":
                return ticket;
            case "V":
                return ticket;
        }
        
        return null;
    }
    
    public TicketC geneByDesc(TicketC ticketCif, SecretKey cS){
        
        return ticket;
    }
    
}

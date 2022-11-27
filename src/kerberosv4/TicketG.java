
package kerberosv4;

import javax.crypto.SecretKey;

public class TicketG {
    
    public TicketC ticket;
    public Cifrador cif;
    public Descifrador desc;

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
    
    public TicketC geneByDesc(TicketC ticketCif, SecretKey cS) throws Exception{
        ticket.setcS( desc.Principal(cS, ticketCif.getcS()) ); 
        ticket.setIdC( desc.Principal(cS, ticketCif.getIdC()) );
        ticket.setAdC( desc.Principal(cS, ticketCif.getAdC()) );
        ticket.setIdTGSoV( desc.Principal(cS, ticketCif.getIdTGSoV()) );
        ticket.settS( desc.Principal(cS, ticketCif.gettS()) );
        ticket.setLifeTime( desc.Principal(cS, ticketCif.getLifeTime()) );
        return ticket;
    }
    
}

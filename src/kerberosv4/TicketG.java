
package kerberosv4;

import javax.crypto.SecretKey;

public class TicketG {
    
    public TicketC ticket;
    public Cifrador cif;
    public Descifrador desc;

    public TicketG() {
    }
    
    public TicketC geneByCif(SecretKey cS, TicketC tickt) throws Exception{
        this.ticket = new TicketC();
        this.cif = new Cifrador();
        this.ticket.setcS( this.cif.Principal(cS, tickt.getcS()) );
        this.ticket.setIdC( this.cif.Principal(cS, tickt.getIdC()) );
        this.ticket.setAdC( this.cif.Principal(cS, tickt.getAdC()) );
        this.ticket.setIdTGSoV( this.cif.Principal(cS, tickt.getIdTGSoV()) );
        this.ticket.settS( this.cif.Principal(cS, tickt.gettS()) );
        this.ticket.setLifeTime( this.cif.Principal(cS, tickt.getLifeTime()) );
        return this.ticket;
    }
    
    public TicketC geneByDesc(TicketC ticketCif, SecretKey cS) throws Exception{
        this.ticket = new TicketC();
        this.desc = new Descifrador();
        this.ticket.setcS( this.desc.Principal(cS, ticketCif.getcS()) ); 
        this.ticket.setIdC( this.desc.Principal(cS, ticketCif.getIdC()) );
        this.ticket.setAdC( this.desc.Principal(cS, ticketCif.getAdC()) );
        this.ticket.setIdTGSoV( this.desc.Principal(cS, ticketCif.getIdTGSoV()) );
        this.ticket.settS( this.desc.Principal(cS, ticketCif.gettS()) );
        this.ticket.setLifeTime( this.desc.Principal(cS, ticketCif.getLifeTime()) );
        return this.ticket;
    }
    
}

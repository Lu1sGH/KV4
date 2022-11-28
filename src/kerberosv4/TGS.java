
package kerberosv4;

import java.sql.Timestamp;
import javax.crypto.SecretKey;

public class TGS {
    
    public static void main(String[] args) {
        
        try{
            
            if(args.length != 4){
                System.out.println("Necesita las siguientes IP's: \n 1.AS \n 2.TGS(IP de este computador) \n 3.Cliente \n 4.Servidor ");
            }
            else{
                String idAS = "AS";
                String pAS = "5000";
                final String ipAS = args[0];
                
                String idTGS = "TGS";
                String pTGS = "5001";
                final String ipTGS = args[1];
                
                String idC = "Cliente";
                String pC = "5002";
                final String ipC = args[2];
                
                String idS = "Servidor";
                String pS = "5003";
                final String ipS = args[3];
                
                
                final String cSTGSString = "PBT2uOvLfnwCgSo9Nd9ClPafUJ9T2NyP";
                SecretKey cSTGS = null;
                final String cSSString = "N0wlzXd0z6iT3KHU0bmsdkqmempQausc";
                SecretKey cSS = null;
                SecretKey cS_CTGS = null;
                String cS_CS = null;
                
                
                String idASR = null;
                String idTGSR = null;
                String idCR = null;
                String idSR = null;
                
                
                Cifrador cif = new Cifrador();
                Descifrador descif = new Descifrador();
                GeneradorCS genCS = new GeneradorCS();
                EMISOR emisor = new EMISOR();
                RECEPTOR receptor = new RECEPTOR();
                TicketC ticket = new TicketC();
                TicketG ticketG = new TicketG();
                AutenticadorC aut = new AutenticadorC();
                TimeStamp tsObj = new TimeStamp();
                Timestamp tS = null;
                Timestamp tLife = null;
                String tSString = null;
                String tLString = null;
                
                cSTGS = genCS.cS(cSTGSString);
                cSS = genCS.cS(cSSString);
                
                idSR = receptor.recibirS(pC);
                ticket = receptor.recibirT(pC);
                aut = receptor.recibirA(pC);
                
                ticket = ticketG.geneByDesc(ticket, cSTGS);
                cS_CTGS = genCS.cS( ticket.getcS() );
                
                aut.setIdC( descif.Principal( cS_CTGS, aut.getIdC() ) );
                aut.setAdC( descif.Principal( cS_CTGS, aut.getAdC() ) );
                aut.settS( descif.Principal( cS_CTGS, aut.gettS() ) );
                
                tLife = tsObj.SaTS( ticket.getLifeTime() );
                tS = tsObj.tiempo();
                
                if(tS.compareTo(tLife) < 0 && aut.getIdC().equals(ticket.getIdC()) && aut.getAdC().equals(ticket.getAdC())){
                    cS_CS = aut.getIdC() + idS;
                    tS = tsObj.tiempo();
                    tLife = new Timestamp(tS.getTime() + tsObj.tl(5,0));
                    tSString = tS.toString();
                    tLString = tLife.toString();
                    
                    ticket.setcS( cif.Principal( cSS, cS_CS)) ;
                    ticket.setIdC( cif.Principal(cSS, aut.getIdC()) );
                    ticket.setAdC( cif.Principal(cSS, ipC) );
                    ticket.setIdTGSoV( cif.Principal(cSS, idS) );
                    ticket.settS( cif.Principal(cSS, tSString) );
                    ticket.setLifeTime( cif.Principal(cSS, tLString) );
                    ticket = ticketG.geneByCif(cS_CTGS, ticket);
                    
                    emisor.enviarS(ipC, pC, cS_CS);
                    emisor.enviarS(ipC, pC, idS);
                    emisor.enviarS(ipC, pC, tSString);
                    emisor.enviarT(ticket, ipC, pC);
                }
                else{
                    System.out.println("Alguno de los datos no coinciden.");
                }
                
                
            }
            
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        
    }
    
}

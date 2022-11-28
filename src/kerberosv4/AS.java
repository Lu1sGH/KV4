
package kerberosv4;

import java.sql.Timestamp;
import javax.crypto.SecretKey;

public class AS {
    
    public static void main(String[] pollis) {
        
        String[] args = new String[4];
        for (int i = 0; i < 3; i++) {
            args[i] = "192.168.10.101";
        }
        
        try{
            
            if(args.length != 4){
                System.out.println("Necesita las siguientes IP's: \n 1.AS(IP de este computador) \n 2.TGS \n 3.Cliente \n 4.Servidor ");
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
                
                
                final String cSCString = "3In01vf+l5I=";
                SecretKey cSC = null;
                final String cSTGSString = "L0NPC0leCEM=";
                SecretKey cSTGS = null;
                String cS_CTGS = null;
                SecretKey cS_CS = null;
                
                
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
                
                
                cSC = genCS.cS(cSCString);
                cSTGS = genCS.cS(cSTGSString);
                
                idCR = receptor.recibirS(pAS);
                idTGSR = receptor.recibirS(pAS);
                tSString = receptor.recibirS(pAS);
                
                if (idC.equals(idCR) && idTGS.equals(idTGS)) {
                    cS_CTGS = "6QSULOl2hTE=";
                    tS = tsObj.tiempo();
                    tLife = new Timestamp(tS.getTime() + tsObj.tl(5,0));
                    tSString = tS.toString();
                    tLString = tLife.toString();
                    
                    ticket.setcS( cif.Principal( cSTGS, cS_CTGS)) ;
                    ticket.setIdC( cif.Principal(cSTGS, idC) );
                    ticket.setAdC( cif.Principal(cSTGS, ipC) );
                    ticket.setIdTGSoV( cif.Principal(cSTGS, idTGS) );
                    ticket.settS( cif.Principal(cSTGS, tSString) );
                    ticket.setLifeTime( cif.Principal(cSTGS, tLString) );
                    ticket = ticketG.geneByCif(cSC, ticket);
                    
                    emisor.enviarS(ipC, pC, cS_CTGS);
                    emisor.enviarS(ipC, pC, idTGS);
                    emisor.enviarS(ipC, pC, tSString);
                    emisor.enviarS(ipC, pC, tLString);
                    emisor.enviarT(ticket, ipC, pC);
                    
                    System.out.println("AS finalizado");
                }
                else{
                    System.out.println("No se concedieron permisos.");
                }
            }
            
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        
    }
    
}

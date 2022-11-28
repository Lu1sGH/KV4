
package kerberosv4;

import java.sql.Timestamp;
import javax.crypto.SecretKey;

public class TGS {
    
    public static void main(String[] pollis) {
        
        String[] args = new String[4];
        for (int i = 0; i < 3; i++) {
            args[i] = "10.226.164.86";
        }
        
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
                
                
                final String cSTGSString = "L0NPC0leCEM=";
                SecretKey cSTGS = null;
                final String cSSString = "vAfZAimu8hk=";
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
                
                idSR = receptor.recibirS(ipC, pTGS);
                ticket = receptor.recibirT(ipC, pTGS);
                aut = receptor.recibirA(ipC, pTGS);
                
                ticket = ticketG.geneByDesc(ticket, cSTGS);
                
                cS_CTGS = genCS.cS( ticket.getcS() );
                
                aut.setIdC( descif.Principal( cS_CTGS, aut.getIdC() ) );
                aut.setAdC( descif.Principal( cS_CTGS, aut.getAdC() ) );
                aut.settS( descif.Principal( cS_CTGS, aut.gettS() ) );
                
                tLife = tsObj.SaTS( ticket.getLifeTime() );
                tS = tsObj.tiempo();
                
                if(tS.compareTo(tLife) < 0 && aut.getIdC().equals(ticket.getIdC()) && aut.getAdC().equals(ticket.getAdC())){
                    cS_CS = "AfhYubU+I/c=";
                    tS = tsObj.tiempo();
                    tLife = new Timestamp(tS.getTime() + tsObj.tl(5,0));
                    tSString = tS.toString();
                    tLString = tLife.toString();
                    
                    ticket.setcS(cS_CS);
                    ticket.setIdC(aut.getIdC());
                    ticket.setAdC(ipC);
                    ticket.setIdTGSoV(idS);
                    ticket.settS(tSString);
                    ticket.setLifeTime( tLString );                    
                    ticket = ticketG.geneByCif(cSS, ticket);                   
                    ticket = ticketG.geneByCif(cS_CTGS, ticket);
                    
                    emisor.enviarS(pTGS, cif.Principal(cS_CTGS, cS_CS));
                    emisor.enviarS(pTGS, cif.Principal(cS_CTGS, idS));
                    emisor.enviarS(pTGS, cif.Principal(cS_CTGS,tSString));
                    emisor.enviarT(ticket, pTGS);
                }
                else{
                    System.out.println("Alguno de los datos no coinciden y se ha rechazado la conexion.");
                }
                
                
            }
            System.out.println("TGS finalizado");
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        
    }
    
}

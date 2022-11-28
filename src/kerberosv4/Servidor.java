
package kerberosv4;

import java.sql.Timestamp;
import javax.crypto.SecretKey;

public class Servidor {
    
    public static void main(String[] pollis) {
        
        String[] args = new String[4];
        for (int i = 0; i < 3; i++) {
            args[i] = "10.226.164.86";
        }
        
        try{
            
            if(args.length != 4){
                System.out.println("Necesita las siguientes IP's: \n 1.AS \n 2.TGS \n 3.Cliente \n 4.Servidor(IP de este computador) ");
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
                
                cSS = genCS.cS(cSSString);
                
                ticket = receptor.recibirT(ipC, pS);
                aut = receptor.recibirA(ipC, pS);
                
                ticket = ticketG.geneByDesc(ticket, cSS);
                cS_CS = genCS.cS( ticket.getcS() );
                
                aut.setIdC( descif.Principal( cS_CS, aut.getIdC() ) );
                aut.setAdC( descif.Principal( cS_CS, aut.getAdC() ) );
                aut.settS( descif.Principal( cS_CS, aut.gettS() ) );
                
                tLife = tsObj.SaTS( ticket.getLifeTime() );
                tS = tsObj.tiempo();
                
                if(tS.compareTo(tLife) < 0 && aut.getIdC().equals(ticket.getIdC()) && aut.getAdC().equals(ticket.getAdC())){
                    tLife = new Timestamp(tS.getTime() + tsObj.tl(5,0));
                    tLString = cif.Principal(cS_CS, tLife.toString());
                    String msj = process();
                    
                    emisor.enviarS(pS, tLString);
                    emisor.enviarS(pS, msj);
                }
            }
            System.out.println("Servidor finalizado");
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        
    }
    
    public static String process(){
        System.out.println("Esta parte simula el proceso solicitado por el cliente");
        return "¡Hola!";
    }
    
}

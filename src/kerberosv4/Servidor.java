
package kerberosv4;

import java.sql.Timestamp;
import javax.crypto.SecretKey;

public class Servidor {
    
    public static void main(String[] args) {
        
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
                
                cSS = genCS.cS(cSSString);
                
                ticket = receptor.recibirT(pS);
                aut = receptor.recibirA(pS);
                
                ticket = ticketG.geneByDesc(ticket, cSS);
            }
            
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

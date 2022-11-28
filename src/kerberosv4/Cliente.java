
package kerberosv4;

import java.sql.Timestamp;
import javax.crypto.SecretKey;

public class Cliente {
    
    public static void main(String[] pollis) {
        
        String[] args = new String[4];
        for (int i = 0; i < 3; i++) {
            args[i] = "192.168.10.101";
        }
        
        try{
            
            if(args.length != 4){
                System.out.println("Necesita las siguientes IP's: \n 1.AS \n 2.TGS \n 3.Cliente(IP de este computador) \n 4.Servidor ");
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
                
                
                cSC = genCS.cS(cSCString);
                
                
                tSString = tsObj.tiempo().toString();
                emisor.enviarS(ipAS, pAS, idC);
                emisor.enviarS(ipAS, pAS, idTGS);
                emisor.enviarS(ipAS, pAS, tSString);
                
                
                cS_CTGS = genCS.cS( descif.Principal(cSC, receptor.recibirS(pAS)) );
                idTGSR = descif.Principal(cSC, receptor.recibirS(pAS));
                tSString = descif.Principal(cSC, receptor.recibirS(pAS));
                tLString = descif.Principal(cSC, receptor.recibirS(pAS));
                ticket = ticketG.geneByDesc(receptor.recibirT(pAS), cSC);
                
                
                if( idTGS.equals(idTGSR)){
                    aut.setIdC( cif.Principal(cS_CTGS, idC) );
                    aut.setAdC( cif.Principal(cS_CTGS, ipC) );
                    aut.settS( cif.Principal( cS_CTGS, tsObj.tiempo().toString() ) );
                    
                    emisor.enviarS(ipTGS, pC, idS);
                    emisor.enviarT(ticket, ipTGS, pC);
                    emisor.enviarA(aut, ipTGS, pC);
                }
                else{
                    System.out.println("No se logro la autentificacion.");
                }
                
                cS_CS = genCS.cS( descif.Principal(cS_CTGS, receptor.recibirS(pTGS)) );
                idSR = descif.Principal(cS_CTGS, receptor.recibirS(pTGS));
                tSString = descif.Principal(cS_CTGS, receptor.recibirS(pTGS));
                ticket = ticketG.geneByDesc(receptor.recibirT(pTGS), cS_CTGS);
                
                
                if(idSR.equals(idS)){
                    aut.setIdC( cif.Principal(cS_CS, idC) );
                    aut.setAdC( cif.Principal(cS_CS, ipC) );
                    aut.settS( cif.Principal( cS_CS, tsObj.tiempo().toString() ) );
                    
                    emisor.enviarT(ticket, ipS, pS);
                    emisor.enviarA(aut, ipS, pS);
                }
                else{
                    System.out.println("No se logro la autentificacion.");
                }
                
                tSString = receptor.recibirS( descif.Principal(cS_CS, receptor.recibirS(pS)) );
                String msj = receptor.recibirS(pS);
                
                System.out.println("Último TimeStamp + 5min: ");
                System.out.println(tSString);
                System.out.println("Respuesta del servidor");
                System.out.println(msj);
                
            }
            
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        
    }
    
}

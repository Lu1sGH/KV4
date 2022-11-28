
package kerberosv4;

import java.sql.Timestamp;
import java.util.Scanner;
import javax.crypto.SecretKey;

public class Cliente {
    
    public static void main(String[] pollis) {
        
        String[] args = new String[4];
        for (int i = 0; i < 3; i++) {
            args[i] = "10.226.164.86";
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
                Scanner leer = new Scanner(System.in);
                
                cSC = genCS.cS(cSCString);
                
                System.out.println("Ingrese el usuario:");
                String user = leer.nextLine();
                System.out.println("Ingrese contraseña:");
                String pass = leer.nextLine();
                
                tSString = tsObj.tiempo().toString();
                emisor.enviarS(pAS, idC);
                emisor.enviarS(pAS, idTGS);
                emisor.enviarS(pAS, tSString);
                
                String csctgs = receptor.recibirS(ipAS, pAS);
                cS_CTGS = genCS.cS( descif.Principal(cSC, csctgs) );
                idTGSR = descif.Principal(cSC, receptor.recibirS(ipAS, pAS));
                tSString = descif.Principal(cSC, receptor.recibirS(ipAS, pAS));
                tLString = descif.Principal(cSC, receptor.recibirS(ipAS, pAS));
                ticket = receptor.recibirT(ipAS, pAS);
                ticket = ticketG.geneByDesc(ticket, cSC);
                
                
                if( idTGS.equals(idTGSR)){
                    aut.setIdC( cif.Principal(cS_CTGS, idC) );
                    aut.setAdC( cif.Principal(cS_CTGS, ipC) );
                    aut.settS( cif.Principal( cS_CTGS, tsObj.tiempo().toString() ) );
                    
                    emisor.enviarS(pTGS, idS);
                    emisor.enviarT(ticket, pTGS);
                    emisor.enviarA(aut, pTGS);
                }
                else{
                    System.out.println("No se logro la autentificacion.");
                }
                
                cS_CS = genCS.cS( descif.Principal(cS_CTGS, receptor.recibirS(ipTGS, pTGS)) );
                idSR = descif.Principal(cS_CTGS, receptor.recibirS(ipTGS, pTGS));
                tSString = descif.Principal(cS_CTGS, receptor.recibirS(ipTGS, pTGS));
                ticket = receptor.recibirT(ipTGS, pTGS);
                ticket = ticketG.geneByDesc(ticket, cS_CTGS);
                
                
                if(idSR.equals(idS)){
                    aut.setIdC( cif.Principal(cS_CS, idC) );
                    aut.setAdC( cif.Principal(cS_CS, ipC) );
                    aut.settS( cif.Principal(cS_CS, tsObj.tiempo().toString() ) );
                    
                    emisor.enviarT(ticket, pS);
                    emisor.enviarA(aut, pS);
                }
                else{
                    System.out.println("No se logro la autentificacion.");
                }
                
                tSString = descif.Principal( cS_CS, receptor.recibirS(ipS, pS) );
                String msj = receptor.recibirS(ipS, pS);
                
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

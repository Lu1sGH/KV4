package kerberosv4;

import java.net.*;
import java.io.*;

public class RECEPTOR{
    
    public RECEPTOR(){
    }
    
    public String recibirS(String p){
        try {
            int puerto = Integer.parseInt(p);
            ServerSocket sSocket = new ServerSocket(puerto);
            Socket socket = sSocket.accept();
            InputStream entrada = socket.getInputStream();
            BufferedReader entradaS = new BufferedReader(new InputStreamReader(entrada));
            String toR = entradaS.readLine();
            entrada.close();
            socket.close();
            sSocket.close();
            return toR;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public TicketC recibirT(String p){
        try {
            
            String[] flujo = new String[6];
            for (int i = 0; i < flujo.length; i++) {
                flujo[i] = recibirS(p);
            }
            TicketC toR = new TicketC();
            toR.setcS(flujo[0]);
            toR.setIdC(flujo[1]);
            toR.setAdC(flujo[2]);
            toR.setIdTGSoV(flujo[3]);
            toR.settS(flujo[4]);
            toR.setLifeTime(flujo[5]);
            return toR;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public AutenticadorC recibirA(String p){
        try {
            String[] flujo = new String[3];
            for (int i = 0; i < flujo.length; i++) {
                flujo[i] = recibirS(p);
            }
            AutenticadorC toR = new AutenticadorC();
            toR.setIdC(flujo[0]);
            toR.setAdC(flujo[1]);
            toR.settS(flujo[2]);
            return toR;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
package kerberosv4;

import java.net.*;
import java.io.*;

public class RECEPTOR{
    
    public RECEPTOR(){
    }
    
    public String recibirS(String ip, String p){
        try {
            InetAddress maquinaAceptadora = InetAddress.getByName(ip);
            int puertoAceptador = Integer.parseInt(p);
            Socket miSocket = new Socket(maquinaAceptadora, puertoAceptador);
            InputStream flujoEntrada = miSocket.getInputStream();
            BufferedReader socketInput = new BufferedReader(new InputStreamReader(flujoEntrada));
            String toR = socketInput.readLine();
            miSocket.close();
            return toR;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public TicketC recibirT(String ip, String p){
        try {
            
            String[] flujo = new String[6];
            for (int i = 0; i < flujo.length; i++) {
                flujo[i] = recibirS(ip, p);
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
    
    public AutenticadorC recibirA(String ip, String p){
        try {
            String[] flujo = new String[3];
            for (int i = 0; i < flujo.length; i++) {
                flujo[i] = recibirS(ip, p);
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
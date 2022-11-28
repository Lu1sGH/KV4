
package kerberosv4;

import java.net.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Arrays;

public class EMISOR{

    public EMISOR(){
    }

    public void enviarS(String ip, String p, String msj){
        try{
            InetAddress receptor = InetAddress.getByName(ip);
            int puerto = Integer.parseInt(p);
            Socket socketE = new Socket(receptor, puerto);
            OutputStream salida = socketE.getOutputStream();
            PrintWriter salidaS = new PrintWriter(new OutputStreamWriter(salida));
            salidaS.println(msj);
            salidaS.flush();
            salida.close();
            socketE.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void enviarT(TicketC ticket, String ip, String p){
        try{
            String[] flujo = new String[6];
            flujo[0] = ticket.getcS();
            flujo[1] = ticket.getIdC();
            flujo[2] = ticket.getAdC();
            flujo[3] = ticket.getIdTGSoV();
            flujo[4] = ticket.gettS();
            flujo[5] = ticket.getLifeTime();
            
            for (int i = 0; i < flujo.length; i++) {
                enviarS(ip, p, flujo[i]);
            }

        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void enviarA(AutenticadorC aut, String ip, String p){
        try{
            
            String[] flujo = new String[3];
            flujo[0] = aut.idC;
            flujo[1] = aut.getAdC();
            flujo[3] = aut.gettS();
            
            for (int i = 0; i < flujo.length; i++) {
                enviarS(ip, p, flujo[i]);
            }
            
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

}
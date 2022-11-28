
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

    public void enviarS(String p, String msj){
        try{
            int numPuerto = Integer.parseInt(p);
            String mensaje = msj;
            ServerSocket socketConexion = new ServerSocket(numPuerto);
            Socket socketDatos = socketConexion.accept();           
            OutputStream flujoSalida = socketDatos.getOutputStream();
            PrintWriter salidaSocket = new PrintWriter(new OutputStreamWriter(flujoSalida));
            salidaSocket.println(mensaje);
            salidaSocket.flush();
            socketDatos.close();
            socketConexion.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void enviarT(TicketC ticket, String p){
        try{
            String[] flujo = new String[6];
            flujo[0] = ticket.getcS();
            flujo[1] = ticket.getIdC();
            flujo[2] = ticket.getAdC();
            flujo[3] = ticket.getIdTGSoV();
            flujo[4] = ticket.gettS();
            flujo[5] = ticket.getLifeTime();
            for (int i = 0; i < flujo.length; i++) {
                enviarS(p, flujo[i]);
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void enviarA(AutenticadorC aut, String p){
        try{
            
            String[] flujo = new String[3];
            flujo[0] = aut.idC;
            flujo[1] = aut.getAdC();
            flujo[2] = aut.gettS();
            
            for (int i = 0; i < flujo.length; i++) {
                enviarS(p, flujo[i]);
            }
            
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

}

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
            InetAddress receptor = InetAddress.getByName(ip);
            int puerto = Integer.parseInt(p);
            Socket socketE = new Socket(receptor, puerto);
            ObjectOutputStream oSalida = new ObjectOutputStream(socketE.getOutputStream());
            oSalida.writeObject(ticket);
            oSalida.close();
            socketE.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void enviarA(AutenticadorC aut, String ip, String p){
        try{
            InetAddress receptor = InetAddress.getByName(ip);
            int puerto = Integer.parseInt(p);
            Socket socketE = new Socket(receptor, puerto);
            ObjectOutputStream oSalida = new ObjectOutputStream(socketE.getOutputStream());
            oSalida.writeObject(aut);
            oSalida.close();
            socketE.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

}
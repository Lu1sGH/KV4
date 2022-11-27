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
            return entradaS.readLine();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public TicketC recibirT(String p){
        try {
            int puerto = Integer.parseInt(p);
            ServerSocket sSocket = new ServerSocket(puerto);
            Socket socket = sSocket.accept();
            InputStream entradaS = socket.getInputStream();
            ObjectInputStream objectReceiver = new ObjectInputStream(entradaS);
            return (TicketC) objectReceiver.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public AutenticadorC recibirA(String p){
        try {
            int puerto = Integer.parseInt(p);
            ServerSocket sSocket = new ServerSocket(puerto);
            Socket socket = sSocket.accept();
            InputStream entradaS = socket.getInputStream();
            ObjectInputStream objectReceiver = new ObjectInputStream(entradaS);
            return (AutenticadorC) objectReceiver.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
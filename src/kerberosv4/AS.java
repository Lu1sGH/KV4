
package kerberosv4;

public class AS {
    
    public static void main(String[] args) {
        
        try{
            
            if(args.length != 4){
                System.out.println("Necesita las siguientes IP's: \n 1.AS(IP de este computador) \n 2.TGS \n 3.Cliente \n 4.Servidor ");
            }
            else{
                String idAS = "AS";
                String puertoAS = "5000";
                String ipAS = args[0];
                
                String idTGS = "TGS";
                String puertoTGS = "5001";
                String ipTGS = args[1];
                
                String idCliente = "Cliente";
                String puertoC = "5002";
                String ipC = args[2];
                
                String idServidor = "Servidor";
                String puertoS = "5003";
                String ipS = args[3];
            }
            
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        
    }
    
}


package control;

import java.io.OutputStream;
import java.net.Socket;

public class HiloSocket extends Thread{
    
    private int caracter;
        
    public HiloSocket(int caracter){
        this.caracter = caracter;
    }
    
    public void run(){
        
        try{
        Socket cliente = new Socket("172.20.2.182",6969);
        OutputStream os = cliente.getOutputStream();
            
            os.write(caracter);
                                         
        }catch(Exception ex){
            System.out.println("Error: " + ex.toString());
        }
    }
}
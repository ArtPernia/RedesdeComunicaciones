
package control;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

public class ServidorSocket { 
           
    private int port;
    private InputStream is;
    private OutputStream os;
    
    public ServidorSocket(int port){
        this.port=port;
        this.init();
    }
    
    public void init(){
        ServerSocket server = null;
        
        try{            
        server = new ServerSocket(port);
        while(true){
//        System.out.println("Escuchando... en el puerto: "+port);                             
        Socket cliente = server.accept(); 
        
            is = cliente.getInputStream(); 
            os = cliente.getOutputStream();
            
        leer();       
        }                                
        }catch(Exception ex){
            System.out.println("Error: " + ex.toString());
        }
    }
    
    public void leer() throws IOException{
        byte [] buffer = new byte[1024];
            int nb = -1;
            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            
            while(is.available()>0){
                nb = is.read(buffer);
                
                if(nb>0){
                    baos.write(buffer, 0, nb); 
                }
            }
            
            String mensaje = new String (baos.toByteArray(), Charset.forName("UTF-8"));
            System.out.print(mensaje);
            
         
            os.close(); 
    }
    
}
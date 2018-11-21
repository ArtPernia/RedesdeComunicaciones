package canalcomunicaciones;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSocket { 
           
    private int port;
    private InputStream is;
    private OutputStream os;
    
    public ServidorSocket(int port){
        this.port=port;
    
        ServerSocket server = null;
        
        try{            
        server = new ServerSocket(port);
        
        Socket cliente = server.accept(); 
        
            this.is = cliente.getInputStream(); 
            this.os = cliente.getOutputStream();
            
        }catch(Exception ex){
            System.out.println("Error: " + ex.toString());
        }
    }
    
    public int leer() throws IOException{
        return is.read();
    }
      
    public void desconectar() throws IOException{
        os.close();
    }
    
}
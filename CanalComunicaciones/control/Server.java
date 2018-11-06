/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

public class Server {


    public static void main(String[] args) throws IOException {
        ServerSocket server = null;
        
        try{            
        server = new ServerSocket(6969);
        System.out.println("Escuchando...");                              
        Socket cliente = server.accept(); 
        
            InputStream is = cliente.getInputStream(); 
            OutputStream os = cliente.getOutputStream(); 
            
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
            System.out.println("El mensaje en el servidor es: " + mensaje);
            
            os.close();            
                                         
        }catch(Exception ex){
            System.out.println("Error: " + ex.toString());
        }
        
       
    }
    
}
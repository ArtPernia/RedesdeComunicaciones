/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canalcomunicaciones;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author gseno
 */
public class ClienteSocket {
    
    String ipAddr;
    int port;
    InputStream in;
    OutputStream out;
    Socket s=null;
    
    
    public ClienteSocket(String ipAddrr, int port){
        
        this.ipAddr =ipAddr;
        this.port = port;       
        try{
            
        this.s = new Socket(this.ipAddr, this.port);
        this.out = s.getOutputStream();
        this.in = s.getInputStream();
        }catch(IOException ex){System.out.println(ex.getMessage());}
        
        
    }
    public void escribir(int dato) throws IOException{
        out.write(dato);
    }
       
    
    
}

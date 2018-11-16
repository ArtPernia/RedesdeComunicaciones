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
public class PruebaConexionEnvio {
    public static void main(String args[]) throws IOException, InterruptedException{
        
        int puerto = 6969;
        String ipaddr = "localhost";
        Socket s = new Socket(ipaddr, puerto);
        OutputStream out = s.getOutputStream();
        InputStream in = s.getInputStream();
        
        String cadena = "qwertyqwerty";
        
        
        for(int c : cadena.getBytes()){
            out.write(c);
        }
//        out.flush();
//        out.close();
        System.out.println("THE END");
        out.close();
        in.close();
        s.close();
    }
}

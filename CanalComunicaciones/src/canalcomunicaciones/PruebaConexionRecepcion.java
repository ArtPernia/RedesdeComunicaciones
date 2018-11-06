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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gseno
 */
public class PruebaConexionRecepcion {
    
    public static void main(String args[]){
        
        try {
            int puerto = 6999;
            //String ipaddr = "localhost";
            ServerSocket ss = new ServerSocket(puerto);
            Socket s = ss.accept();
            OutputStream out = s.getOutputStream();
            InputStream in = s.getInputStream();
            //int nb = -1;
            //String cadena = "qwertyqwerty";
            Boolean salir = false;
            int nb = -1;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while(!salir){
                nb=in.read();
                if(nb != -1)
                    baos.write((char)nb);
                else
                    salir = true;
            }
            s.close();
            ss.close();
//            int size = 64000;
//            byte[] buffer = new byte[size];
//            int nb = 0;
//            while(nb != -1){
//                nb = in.read(buffer, 0, size);
//                if(nb>0)
//                    baos.write(buffer, 0, nb);
//            }
//            byte[] datos = new byte[64];
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            //baos.write(in.read());
//            while ((nb = in.read(datos, 0, datos.length)) != -1) {
//                baos.write(nb);
//            }
            System.out.println(baos.toString("UTF-8"));
            System.out.println("--------------------------------\n");
            System.out.println("THE END");
            
        } catch (IOException ex) {
            System.out.println(ex.toString()+"\n"+ex.getCause());;
        }
    }
}


package javaapplication6;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class hilo extends Thread {
    static final String ruta="textoPrueba.txt";
  
    public void run(){
    int carac;    
    FileReader f1;
        try {
            f1 = new FileReader(ruta);
            carac = f1.read();
            while(carac != -1){
                
                char caracter = (char) carac;
                System.out.println(caracter);
                carac = f1.read();
                try {
                    Thread.sleep(5);
                } catch (InterruptedException ex) {
                    Logger.getLogger(hilo.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(hilo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(hilo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

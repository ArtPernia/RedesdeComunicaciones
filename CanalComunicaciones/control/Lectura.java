
package control;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Lectura extends Thread {
    private String ruta="textoPrueba.txt";
    
    public void run(){
    if(ruta==null){
        System.out.println("No se ha introducido la ruta");
    }else{
    int carac;    
    FileReader f1;
        try {
            f1 = new FileReader(ruta);
            carac = f1.read();
            while(carac != -1){
                
                //char caracter = (char) carac;
                
                //Llamar constructor HiloSocket
                //System.out.print(caracter);
                System.out.println("hola");
                HiloSocket h1 = new HiloSocket(carac);
                h1.start();
                
                carac = f1.read();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Lectura.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Lectura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Lectura.class.getName()).log(Level.SEVERE, null, ex);
        }

        }
    }
}
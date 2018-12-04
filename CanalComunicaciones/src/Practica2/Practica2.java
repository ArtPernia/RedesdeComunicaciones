package Practica2;

import java.util.HashMap;
import java.util.Map;

public class Practica2 {
 
    public static void main (String args){
        
        Map<Integer,String> mensaje = new HashMap<>();
        //Map<Integer,String> mensaje = new HashMap<Integer, String>();
        
        mensaje.put(8,"Fix 4.1");
        String cadena;
        while (){
            int caracter = (char) Socket.leer();
            if caracter == DELIM){
                procesar(cadena);
            }
            else {
                cadena.append(caracter);
            }
        }
    }
}

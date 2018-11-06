package canalcomunicaciones;

import java.io.*;
import java.util.Random;


public class PruebasSinSockets {
    
    public static double PROB_ERR_BIT = 0.2;
    public static double PROB_ERR_DESP = 0.75;
    public static int TAM_BYTE = 8;
    public static void main(String[] args) throws Exception{
        InputStream in = System.in;
        int carac[] = new int[2];
        Random rnd = new Random();
        boolean salir = false;
        // & sale de la funcion
        while( !salir ){
            
            System.out.println("----------------------------------------------");
            for(int i=0; i<carac.length; i++){
                carac[i] = in.read();
                if(carac[i] == 38 )
                    salir = true;
            }
            
            for(int c : carac)
                System.out.println("I:" + (char)c + ":" + c + ":" + Integer.toBinaryString(c));
            for(int i=0; i<carac.length; i++){
                if(Math.random() < PROB_ERR_BIT){
                    int index = rnd.nextInt(TAM_BYTE);
                    System.out.println("Error en el bit " + (index+1) + " del caracter " + (char)carac[i]);
                    carac[i] = carac[i] ^ (1 << index);
                }
            }
            
            double despError = PROB_ERR_DESP;
            int despCont = 0;
            while(Math.random() < (despError *= PROB_ERR_DESP))
                despCont++;
            if(despCont > 0){
                int concCarac = carac[0] | (carac[1] << TAM_BYTE );
                System.out.println("Concatenados antes de error: "+Integer.toBinaryString(concCarac));
                boolean desplazaDerecha = rnd.nextBoolean();
                if(desplazaDerecha){
                    concCarac = concCarac >>> despCont;
                    System.out.println("Desplazamiento sin signo derecha " + despCont + " bits");
                }
                else{
                    concCarac = concCarac << despCont;
                    System.out.println("Desplazamiento izquierda " + despCont + " bits");
                }
                System.out.println("Concatenados después de error: "+Integer.toBinaryString(concCarac));
                carac[1] = concCarac >>> TAM_BYTE;
                carac[0] = concCarac ^ (carac[1] << TAM_BYTE);
            }
            
            for(int c : carac)
                System.out.println("O:" + (char)c + ":" + c + ":" + Integer.toBinaryString(c));
            
        }
    }
}

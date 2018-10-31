package canalcomunicaciones;
import java.io.*;
import java.util.Random;



public class CanalComunicaciones {

public static Double PROB_ERR = 0.5;
public static int TAM_BYTE = 8;

    public static void main(String[] args) throws IOException{
        InputStream in = System.in;
        while(true){
            int carac = in.read();
            System.out.println("I:" + (char)carac+":" + carac + ":" + Integer.toBinaryString(carac));
            Double errorByte = Math.random();
            if(errorByte<PROB_ERR){
            Random rnd = new Random();
              int index = rnd.nextInt(TAM_BYTE)-1;
                carac = carac^(1<<index);
            }
            System.out.println("O:" + (char)carac+":" + carac + ":" + Integer.toBinaryString(carac));
        }
    }
}
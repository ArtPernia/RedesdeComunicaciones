package canalcomunicaciones;
import java.io.*;
import java.util.Random;



public class CanalComunicaciones {

public static Double PROB_ERR = 0.5;
public static int TAM_BYTE = 8;

    public static void main(String[] args) throws IOException{
        int[] b = new int[TAM_BYTE];
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] charArray = br.readLine().toCharArray();
        
        for(int i=0; i<charArray.length; i++){
            b[i] = (int)(charArray[i]);
        }
        for(int i : b){
            if(i==49)
                i=1;
            else if(i==48)
                i=0;
        }
        Double errorByte = Math.random();
        Random rnd = new Random();
        int index = rnd.nextInt(TAM_BYTE);
        if(errorByte<PROB_ERR){
            if(b[index]==49)
                b[index]=48;
            else if(b[index]==48)
                b[index]=49;
        }
        
        for(int i=0; i<b.length; i++){
            charArray[i] = (char)(b[i]);
        }
        
        System.out.println(new String(charArray));
        
    }
}
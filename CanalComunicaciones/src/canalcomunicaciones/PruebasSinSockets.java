/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canalcomunicaciones;

import java.io.*;
import java.util.Random;


public class PruebasSinSockets {
    
    public static Double PROB_ERR_BIT = 0.5;
    public static Double PROB_ERR_OFFSET = 0.25;
    public static int TAM_BYTE = 8;
    public static void main(String[] args) throws IOException, Exception{
        InputStream in = System.in;
        int carac[] = new int[2];
        // & sale de la funcion
        while((carac[0] = in.read()) != '-' && (carac[1] = in.read()) != '1'){
            for(int c : carac)
                System.out.println("I:" + (char)c+":" + c + ":" + Integer.toBinaryString(c));
            Double errorBit = Math.random();
            for(int c : carac){
                if(errorBit<PROB_ERR_BIT){
                    Random rnd = new Random();
                    int index = rnd.nextInt(TAM_BYTE)-1;
                    c = c^(1<<index);
                }
            }
            //Concatenar dos strings con tobynary y hacer valueof del resultado, aplicar el desplazamiento
            if(Math.random()<PROB_ERR_OFFSET){
                //int totalCarac = 
            }
           for(int c : carac)
                System.out.println("O:" + (char)c+":" + c + ":" + Integer.toBinaryString(c));
            //outSalida.writeObject(carac);
        }
    }
}

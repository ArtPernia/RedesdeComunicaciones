package canalcomunicaciones;

import java.io.IOException;

public class testDeco {
    
    public static void main (String arg[]) throws IOException {
    
        int numeros[]= new int[3];
        
        numeros[0]=64;
        numeros[1]=100;
        numeros[2]=39;
        
        int resultados[];
        
        
        resultados = DecoHamming.procesar(numeros);
        
        System.out.println(resultados[0]);
        System.out.println(resultados[1]);
        
    }
}
//01000010   0110

//01000001
//01010001
//01100100
//00100111
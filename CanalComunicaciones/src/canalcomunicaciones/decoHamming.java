package canalcomunicaciones;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class decoHamming {
    
    public static void main(String arg[]) throws IOException {
  
        try{
            InputStream is = System.in;
            OutputStream os = System.out;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int aprocesar[] = new int[3];
            int cont=0;
            
            while ( (aprocesar[1]=is.read()) !=-1) {
                aprocesar[3]=aprocesar[2];
                aprocesar[2]=aprocesar[1];
                
                if (cont==2){
                    procesar(aprocesar);
                    cont=0;
                }
                cont++;
            }
            
        } catch (IOException ex){
            System.out.println("Problema al abrir canales de comunicacion:" +ex.getMessage());
        }   
    }
            
    public static void procesar (int numero[]){
        
        int procesado[] = new int[2];
        
        procesado[1]=(numero[1] << 4) + (numero[2] >> 4);
        System.out.println("X =" + procesado[1]);
        decodificar(procesado[1]);
        
        procesado[2]=((numero[2] & 15) << 8) + numero[3];
        System.out.println("Y=" + procesado[2]);
        
        
    }
    
    public static int[] decodificar(int numero){

        int r[];

        r = crearArray(numero);
        
       
            
            
        
        
        int s[] = new int[4];
        s[0] = r[0] ^ r[2] ^ r[4] ^ r[6] ^ r[8] ^ r[10];
        s[1] = r[1] ^ r[2] ^ r[5] ^ r[6] ^ r[9] ^ r[10];
        s[2] = r[3] ^ r[4] ^ r[5] ^ r[6];
        s[3] = r[7] ^ r[8] ^ r[9] ^ r[10] ^ r[11];

        int dec = (s[0] * 1) + (s[1] * 2) + (s[2] * 4) + (s[3] * 8);
        if (dec == 0) {
            System.out.println("Sin error");
        } else {
            System.out.println("El error esta en: " + dec);
            if (r[dec - 1] == 0) {
                r[dec - 1] = 1;
                //r[dec - 1] = 717;
            } else {
                r[dec - 1] = 0;
               // r[dec - 1] = 707;
            }
            System.out.println("Hamming correcto : ");
            for (int i = 0; i < 12; i++) {
                System.out.print(r[i] + " ");
            }
            System.out.println();
        }
        
        int solucion[]= new int[8];
        solucion[0]=r[2];
        solucion[1]=r[4];
        solucion[2]=r[5];
        solucion[3]=r[6];
        solucion[4]=r[8];
        solucion[5]=r[9];
        solucion[6]=r[10];
        solucion[7]=r[11];
        
        System.out.println("SOLUCION: " + solucion);
        return solucion;
    }
    
    public static int[] crearArray (int numero)  {
        
        String temp = Integer.toString(numero);
        String temp2;
        int temp3;
        int [] array = new int[temp.length()];
        
        for(int i=0;i<=temp.length();i++) {
            if (i!=temp.length()) {
                temp2 = temp.substring(i, i+1);
            } else {
                temp2 = temp.substring(i);
            }
            temp3 =  Integer.parseInt(temp2);    
            array[i] = temp3;
        }
        
        return array;
    }
}
        
    
    
    


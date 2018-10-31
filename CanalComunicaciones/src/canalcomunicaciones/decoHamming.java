package canalcomunicaciones;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
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

        boolean r[] = new boolean[12];

        leerPosicion(numero, 0) = r[0];
        
        
        numero[1] = r[1];
        rd[0] = r[2];
        pr[2] = r[3];
        rd[1] = r[4];
        rd[2] = r[5];
        rd[3] = r[6];
        pr[3] = r[7];
        rd[4] = r[8];
        rd[5] = r[9];
        rd[6] = r[10];
        rd[7] = r[11];
        
        int s[] = new int[4];
        s[0] = pr[0] ^ rd[0] ^ rd[1] ^ rd[3] ^ rd[4] ^ rd[6];
        s[1] = pr[1] ^ rd[0] ^ rd[2] ^ rd[3] ^ rd[5] ^ rd[6];
        s[2] = pr[2] ^ rd[1] ^ rd[2] ^ rd[3];
        s[3] = pr[3] ^ rd[4] ^ rd[5] ^ rd[6] ^ rd[7];

        int dec = (s[0] * 1) + (s[1] * 2) + (s[2] * 4) + (s[3] * 8);
        if (dec == 0) {
            System.out.println("Sin error");
        } else {
            System.out.println("El error esta en: " + dec);
            if (r[dec - 1] == 0) {
                r[dec - 1] = 717;
            } else {
                r[dec - 1] = 707;
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
    
    public static boolean leerPosicion(int numero, int pos){
        
        return ((1<<pos)^numero)>0;
    }
}
        
    
    
    


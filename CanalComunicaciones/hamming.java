package canalcomunicaciones;

import java.math.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
 class DanHamming {
     public static void main(String arg[]) throws IOException {
        /**/
        try {
            InputStream is = System.in;
            OutputStream os = System.out;
            int caracter_leido;
            int caracter_previo = 0;
          
            int [] caracteres_enviados = new int [3];
            set0(caracteres_enviados);
            int flag = 1;
            
            
            while ((caracter_leido= is.read()) != -1) {
                
                if(flag == 2){
                    //logica aqui
                    procesar_doscaracteres(caracter_previo, caracter_leido);
                    if(vacio(caracteres_enviados)){
                        
                    }                    
                    flag = 0;
                }else{
                    flag++;
                }      
                caracter_previo = caracter_leido; 
           
            }

        } catch (Exception ex) {
            System.out.println("Error: " + ex.toString());
        }
     
        
    }
     private static int[] codigoHamming(int a) {
        int d[] = new int[8];
        int p[] = new int[4];             
        for (int i=0;i<7; i++){
            d[i] = a & 1;
            a = a >> 1;
            i--;      
        }
        p[0] = d[0] ^ d[1] ^ d[2] ^ d[3] ^ d[4] ^ d[5] ^ d[6] ^ d[7];
        p[1] = d[0] ^ d[1] ^ d[2] ^ d[3];
        p[2] = d[0] ^ d[1] ^ d[4] ^ d[5];
        p[3] = d[0] ^ d[2] ^ d[4] ^ d[6];
        int c[] = new int[13];
        System.out.println("Complet code word: ");
        c[0] = p[0];
        c[1] = p[1];
        c[2] = d[0];
        c[3] = p[2];
        c[4] = d[1];
        c[5] = d[2];
        c[6] = d[3];
        c[7] = p[3];
        c[8] = d[4];
        c[9] = d[5];
        c[10] = d[6];
        c[11] = d[7];
        
        
        /*Salida de datos por pantalla*/
         for (int i = 0; i < 12; i++) {
            System.out.print(c[i] + "");
        }
        System.out.println();
         return c;
    }
    
    public static  boolean vacio (int a[] ){// devuelve true si array vacio
        for  (int i = 0; i <=  a.length; i++){
            if(a[i] == -1 ){
                return true;
            }
        }        
        return false;
    }
    
    public static void set0 (int a []){ 
        for (int i = 0; i<= a.length; i++){
            a[i] = -1;
        }   
    }
    
    private static int [] procesar_doscaracteres(int leido,int previo){
         return concatenateCode(codigoHamming(previo),codigoHamming(leido)) ;
    }
    
    private static int[] concatenateCode (int a[], int b[]){  
        int [] c = new int [a.length + b.length];
        set0(c);
        for(int i = 0; i <= a.length; i++){
            c[i] = a[i];
            c[i+a.length] = b[i];
        }
        return c;
    }
    
    private static int[] binaryToDecimal (int a[]){
     
        int [] arrayDec = new int [3];
        int[] letraA = new int[8];
        int[] letraB = new int[8];
        int[] letraC = new int[8];
        int sumA, sumB, sumC;
        //              (src   , src-offset  , dest , offset, count)
        System.arraycopy(a, 0           , letraA, 0     , letraA.length);
        System.arraycopy(a, letraA.length, letraB, 0     , letraB.length);
        System.arraycopy(a, letraB.length, letraC, 0, letraC.length);
        
        sumA = decimal(letraA);
        sumB = decimal(letraB);
        sumC = decimal(letraC);
        
        arrayDec[0] = sumA;
        arrayDec[1] = sumB;
        arrayDec[2] = sumC;
        
       
        return arrayDec;
        
    }
private static int decimal (int [] a){
    int c=0;
    for (int i = 0; i<8 ; i++){
        c = c + (int) (a[i]* Math.pow(10, i));   
    }
         
    return c;
}
    
}/*END CLASS*/

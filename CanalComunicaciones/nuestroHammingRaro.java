package canalcomunicaciones;


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
            
            
            
//            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.toString());
        }

        
        
        
        /*Lectura y correccion*/
        System.out.println("Enter the Received codeword");
        int r[] = new int[12];
        for (int i = 0; i < 12; i++) {
           // r[i] = sc.nextInt();
        }

        int pr[] = new int[4];
        int rd[] = new int[8];

        pr[0] = r[0];
        pr[1] = r[1];
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
                r[dec - 1] = 1;
            } else {
                r[dec - 1] = 0;

            }
            System.out.println(
                    "Corrected code word is : ");
            for (int i = 0; i < 12; i++) {
                System.out.print(r[i] + " ");
            }
            System.out.println();
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
        
        
        /*Salida de datos*/

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
      /*convertir un array de 24 bits a 3 numeros de tipo int*/
        return 
    }

    
    
}/*END CLASS*/


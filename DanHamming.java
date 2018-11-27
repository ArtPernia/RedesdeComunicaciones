package danhamming;

import java.math.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

class DanHamming {

    public static void main(String arg[]) throws IOException {
        
        ServidorSocket s1 = new ServidorSocket(6969);
        ClienteSocket c1 = new ClienteSocket("172.20.2.4", 6969);
        
     
            try {

                int caracter_leido;
                int caracter_previo = 0;
                int enviar = 0;
                
                int flag = 0;
                String binario;
                while (true) {
                  
                    caracter_leido =s1.leer();
                    
                    
                    if (flag == 1) {
                        //logica aqui

                       // System.out.println("caracter_previo " + caracter_previo);
                        //System.out.println("caracter_leido " + caracter_leido);

                        enviar = procesar_doscaracteres(caracter_previo, caracter_leido);//un array de 24 bits
                        binario = Integer.toBinaryString(enviar); 
                       
                        System.out.println("Byte 1: "+ seleccionarByte(enviar, 2));
                        c1.escribir(seleccionarByte(enviar, 2));
                        System.out.println("Byte 1: "+ seleccionarByte(enviar, 1));
                        c1.escribir(seleccionarByte(enviar, 1));
                        System.out.println("Byte 1: "+ seleccionarByte(enviar, 0));
                        c1.escribir(seleccionarByte(enviar, 0));


                        flag = 0;
                    } else {
                        flag++;
                    }
                    caracter_previo = caracter_leido;
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex.toString());
            }       
    }/*END MAIN*/

    private static int codigoHamming(int a) {
        int d[] = new int[8];
        int p[] = new int[4];
        for (int i = 0; i < 7; i++) {// almacena el valor menos significativo en la posicion 0
            d[i] = a & 1;
            a = a >> 1;
        }
        p[0] = d[0] ^ d[1] ^ d[2] ^ d[3] ^ d[4] ^ d[5] ^ d[6] ^ d[7];
        p[1] = d[0] ^ d[1] ^ d[2] ^ d[3];
        p[2] = d[0] ^ d[1] ^ d[4] ^ d[5];
        p[3] = d[0] ^ d[2] ^ d[4] ^ d[6];

        int[] c = new int[12];

        //System.out.println("Complete code word: ");

        c[0] = p[3];
        c[1] = p[2];
        c[2] = p[1];
        c[3] = p[0];
        c[4] = d[0];
        c[5] = d[1];
        c[6] = d[2];
        c[7] = d[3];
        c[8] = d[4];
        c[9] = d[5];
        c[10] = d[6];
        c[11] = d[7];

        int allChars = 0;

        /*for (int j = 0; j < 12; j++) {
            System.out.print( c[j]);
        }*/
        for (int i = 0; i < 12; i++) {
            allChars = allChars + (c[i] << i);
        }

        System.out.println("\t"+ allChars);

        return allChars;
    }

    public static boolean vacio(int a[]) {// devuelve true si array vacio
        for (int i = 0; i <= a.length; i++) {
            if (a[i] == -1) {
                return true;
            }
        }
        return false;
    }

    public static void set0(int a[]) {
        for (int i = 0; i <= a.length; i++) {
            a[i] = -1;
        }
    }

    private static int procesar_doscaracteres(int leido, int previo) {
        return concatenateCode(codigoHamming(previo), codigoHamming(leido));
    }

    private static int concatenateCode(int a, int b) {

        return (a << 12) + b;

    }

    private static int seleccionarByte(int datos, int indice) {

        return ((datos >> (indice * 8)) & 255);
    }
    
}/*END CLASS*/

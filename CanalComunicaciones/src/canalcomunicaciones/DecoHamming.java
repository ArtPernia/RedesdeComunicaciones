package canalcomunicaciones;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class DecoHamming {
    
    public static void main(String arg[]) throws IOException {
        
        ServerSocket server = null;
        int puerto = 6969;
        try{
            server = new ServerSocket(puerto);
            System.out.println("Escuchando...");                              
            Socket cliente = server.accept(); 
        
            InputStream is = cliente.getInputStream(); 
            OutputStream os = cliente.getOutputStream(); 
            
            int aprocesar[] = new int[3];
            
            aprocesar[0]=is.read();
            aprocesar[1]=is.read();
            aprocesar[2]=is.read();
            
            int respuesta[]= procesar(aprocesar);
            
            os.write(respuesta[0]);
            os.write(respuesta[1]);
            
        } catch (IOException ex){
            System.out.println("Problema al abrir canales de comunicacion:" +ex.getMessage());
        }   
    }
            
    public static int[] procesar (int numero[]){
        
        int procesado[] = new int[2];
        int solucion[] = new int[2];
        
        procesado[0]=(numero[0] << 4) + (numero[1] >> 4);
            System.out.println("Primer numero =" + procesado[0]);
        solucion[0] = decodificar(procesado[0]);
        
        System.out.println();
        
        procesado[1]=((numero[1] & 15) << 8) + numero[2];
            System.out.println("Segundo numero=" + procesado[1]);
        solucion[1] = decodificar(procesado[1]);
        
        
        return solucion;
        
    }
    
    public static int decodificar(int numero){
        
        boolean s[] = new boolean[4];

//        s[0] = r[2] ^ r[4] ^ r[5] ^ r[6] ^ r[8] ^ r[9] ^ r[10] ^ r[11];
//        s[1] = r[2] ^ r[4] ^ r[5] ^ r[6];
//        s[2] = r[2] ^ r[4] ^ r[8] ^ r[9];
//        s[3] = r[2] ^ r[5] ^ r[8] ^ r[10];

        s[3] = getBit(numero, 3) != (getBit( numero , 11) ^ getBit(numero, 10) ^ getBit(numero, 9) ^ getBit(numero,8) ^ getBit(numero,7) ^ getBit(numero, 6) ^ getBit(numero,5) ^ getBit(numero, 4));
        s[2] = getBit(numero, 2) != (getBit( numero , 11) ^ getBit(numero, 10) ^ getBit(numero, 9) ^ getBit(numero,8));
        s[1] = getBit(numero, 1) != (getBit( numero , 11) ^ getBit(numero, 10) ^ getBit(numero,7) ^ getBit(numero, 6));
        s[0] = getBit(numero, 0) != (getBit(numero,11) ^ getBit(numero, 9) ^ getBit(numero,7) ^ getBit(numero, 5));
        
        //lo pasamos de un array S a un int
        int x=0;
        for (int i=0;i<=3;i++){
            if (s[i]){
                x = x+(1<<i);                
            }
            //System.out.println(x);
            
        }
        
        //comprobamos los errores y los corregimos
        
        if (x == Integer.parseInt("1000", 2)){
            System.out.println("TENEMOS UN ERROR");
            if (x == Integer.parseInt("1111", 2)){
                numero = flipBit(numero,11);
                System.out.println("ERROR en la posicion 1");
            }else if (x == Integer.parseInt("1110", 2)){
                numero = flipBit(numero,10);
                System.out.println("ERROR en la posicion 2");
            }else if (x == Integer.parseInt("1101", 2)){
                numero = flipBit(numero,9);
                System.out.println("ERROR en la posicion 3");
            }else if (x == Integer.parseInt("1100", 2)){
                numero = flipBit(numero,8);
                System.out.println("ERROR en la posicion 4");
            }else if (x == Integer.parseInt("1011", 2)){
                numero = flipBit(numero,7);
                System.out.println("ERROR en la posicion 5");
            }else if (x == Integer.parseInt("1010", 2)){
                numero = flipBit(numero,6);
                System.out.println("ERROR en la posicion 6");
            }else if (x == Integer.parseInt("1001", 2)){
                numero = flipBit(numero,5);
                System.out.println("ERROR en la posicion 7");
            }else {
                numero = flipBit(numero,4);
                System.out.println("ERROR en la posicion 8");
            }
        }
        
        int solucion = numero >> 4;
        
        System.out.println("SOLUCION: " + solucion);
        return solucion;
    }
    
    public static int getBit(int numero, int indice){
        
        return (numero >> indice) & 1;
    }
    
    public static int flipBit(int numero, int indice){
        
        return numero ^ (1<< indice);
    }
}
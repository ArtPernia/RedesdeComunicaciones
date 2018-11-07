package canalcomunicaciones;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class decoHamming {
    
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
            int cont=0;
            
            while ( (aprocesar[1]=is.read()) !=-1) {
                aprocesar[3]=aprocesar[2];
                aprocesar[2]=aprocesar[1];
                
                if (cont==2){
                    procesar(aprocesar, os);
                    cont=0;
                }
                cont++;
            }
            
        } catch (IOException ex){
            System.out.println("Problema al abrir canales de comunicacion:" +ex.getMessage());
        }   
    }
            
    public static void procesar (int numero[], OutputStream os){
        
        int procesado[] = new int[2];
        
        procesado[1]=(numero[1] << 4) + (numero[2] >> 4);
        System.out.println("X =" + procesado[1]);
        int solucion = decodificar(procesado[1]);
        
        try {
            os.write(solucion);
        } catch (IOException ex) {
            Logger.getLogger(decoHamming.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR AL ENVIAR EL PRIMER NUMERO: "+ ex.getMessage());
        }
        
        procesado[2]=((numero[2] & 15) << 8) + numero[3];
        System.out.println("Y=" + procesado[2]);
        int solucion2 = decodificar(procesado[2]);
        
        try {
            os.write(solucion2);
        } catch (IOException ex) {
            Logger.getLogger(decoHamming.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR AL ENVIAR EL SEGUNDO NUMERO: "+ ex.getMessage());
        }
        
        
    }
    
    public static int decodificar(int numero){

        int r[];

        r = crearArray(numero);
        
        int s[] = new int[4];
//editar esto en un momento
        s[0] = r[2] ^ r[4] ^ r[5] ^ r[6] ^ r[8] ^ r[9] ^ r[10] ^ r[11];
        s[1] = r[2] ^ r[4] ^ r[5] ^ r[6];
        s[2] = r[2] ^ r[4] ^ r[8] ^ r[9];
        s[3] = r[2] ^ r[5] ^ r[8] ^ r[10];

        int x=0;
        for (int i=0;i<=3;i++){
            x = x+(s[i]<<i);
            //
            System.out.println(x);
            //
        }
        
        if (x == 1000){
            System.out.println("TENEMOS UN ERROR");
            if (x == 1111){
                System.out.println("ERROR en la posicion 1");
            }else if (x == 1110){
                System.out.println("ERROR en la posicion 2");
            }else if (x == 1101){
                System.out.println("ERROR en la posicion 3");
            }else if (x == 1100){
                System.out.println("ERROR en la posicion 4");
            }else if (x == 1011){
                System.out.println("ERROR en la posicion 5");
            }else if (x == 1010){
                System.out.println("ERROR en la posicion 6");
            }else if (x == 1001){
                System.out.println("ERROR en la posicion 7");
            }else {
                System.out.println("ERROR en la posicion 8");
            }
        }
        
        
        
        
        
        
//        int dec = (s[0] * 1) + (s[1] * 2) + (s[2] * 4) + (s[3] * 8);
//        if (dec == 0) {
//            System.out.println("Sin error");
//        } else {
//            System.out.println("El error esta en: " + dec);
//            if (r[dec - 1] == 0) {
//                r[dec - 1] = 1;
//                //r[dec - 1] = 717;
//            } else {
//                r[dec - 1] = 0;
//               // r[dec - 1] = 707;
//            }
//            System.out.println("Hamming correcto : ");
//            for (int i = 0; i < 12; i++) {
//                System.out.print(r[i] + " ");
//            }
//            System.out.println();
//        }
        
        int solucion[]= new int[8];
        solucion[0]=r[2];
        solucion[1]=r[4];
        solucion[2]=r[5];
        solucion[3]=r[6];
        solucion[4]=r[8];
        solucion[5]=r[9];
        solucion[6]=r[10];
        solucion[7]=r[11];
        
        int y=0;
        for (int i=0;i<=7;i++){
            y = y+(solucion[i]<<i);
        }
        
        System.out.println("SOLUCION: " + y);
        return y;
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
        
    
    
    


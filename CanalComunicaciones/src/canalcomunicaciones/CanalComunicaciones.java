package canalcomunicaciones;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;



public class CanalComunicaciones {

//public static Double PROB_ERR = 0.5;
public static int TAM_BYTE = 8;
public static double PROB_ERR_BIT = 0.2;
public static double PROB_ERR_DESP = 0.75;

    // args[0] = Puerto de Entrada | args[1] = PuertoSalida | args[2] = IP Salida
    public static void main(String[] args) throws IOException, Exception{
        
//        if(args.length<3){
//            System.out.println("Argumentos invalidos\n");
//            System.exit(-1);
//        }
        int portIn = 6969;
        int portOut = 6999;        
        String ipSalida = "170.20.4.247";
        Socket s = null;
        Socket sSalida = new Socket(ipSalida, portOut);
        ServerSocket ss = new ServerSocket(portIn);
        
        s = ss.accept();
        
        OutputStream out = s.getOutputStream();
        InputStream in = s.getInputStream();
        OutputStream outSalida = sSalida.getOutputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //int carac;
        int carac[] = new int[2];
        Random rnd = new Random();
        boolean salir = false;
        
        
        
        //InputStream in = System.in;
        // & sale de la funcion
//        while((carac = (int)in.readObject()) != 38){
//            //int carac = in.read();
//            System.out.println("I:" + (char)carac+":" + carac + ":" + Integer.toBinaryString(carac));
//            Double errorByte = Math.random();
//            if(errorByte<PROB_ERR){
//            Random rnd = new Random();
//              int index = rnd.nextInt(TAM_BYTE)-1;
//                carac = carac^(1<<index);
//            }
//            //System.out.println("O:" + (char)carac+":" + carac + ":" + Integer.toBinaryString(carac));
//            outSalida.writeObject(carac);
//        }
            
            System.out.println("----------------------------------------------");
            carac = readTwoBytes(in);
        while( !salir ){
            System.out.println("----------------------------------------------");
            
            for(int c : carac)
                System.out.println("I:" + (char)c + ":" + c + ":" + Integer.toBinaryString(c));
            for(int i=0; i<carac.length; i++){
                if(Math.random() < PROB_ERR_BIT){
                    int index = rnd.nextInt(TAM_BYTE);
                    System.out.println("Error en el bit " + (index+1) + " del caracter " + (char)carac[i]);
                    carac[i] = carac[i] ^ (1 << index);
                }
            }
            
            double despError = PROB_ERR_DESP;
            int despCont = 0;
            while(Math.random() < (despError *= PROB_ERR_DESP))
                despCont++;
            if(despCont > 0){
                int concCarac = carac[0] | (carac[1] << TAM_BYTE );
                System.out.println("Concatenados antes de error: "+Integer.toBinaryString(concCarac));
                boolean desplazaDerecha = rnd.nextBoolean();
                if(desplazaDerecha){
                    concCarac = concCarac >>> despCont;
                    System.out.println("Desplazamiento sin signo derecha " + despCont + " bits");
                }
                else{
                    concCarac = concCarac << despCont;
                    System.out.println("Desplazamiento izquierda " + despCont + " bits");
                }
                System.out.println("Concatenados después de error: "+Integer.toBinaryString(concCarac));
                carac[1] = concCarac >>> TAM_BYTE;
                carac[0] = concCarac ^ (carac[1] << TAM_BYTE);
            }else
                System.out.println("No hay error de desplazamiento\n");
            
            for(int c : carac){
                System.out.println("O:" + (char)c + ":" + c + ":" + Integer.toBinaryString(c));
                out.write(c);
            }
            carac = readTwoBytes(in);
            for(int c : carac){
                if(c == -1)
                    salir=true;
            }
        }
            //outSalida.write(baos.toByteArray());
        s.close();
        ss.close();
        sSalida.close();
    }
        private static int[] readTwoBytes(InputStream in) throws IOException{
            int[] carac = new int[2];
            int nb = -1;
            for(int i=0; i<carac.length; i++){
                nb = in.read();
               if(nb == -1 ){
                   carac[i] = nb;
                   return carac;
               }else{
                   carac[i] = nb;
               }
            }
            return carac;
        }
    
}
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
        
        if(args.length<3){
            System.out.println("Argumentos invalidos\n");
            System.exit(-1);
        }
        int portIn = Integer.parseInt(args[0]);
        int portOut = Integer.parseInt(args[1]);        
        String ipSalida = args[2];
        Socket s = null;
        Socket sSalida = new Socket(ipSalida, portOut);
        ServerSocket ss = new ServerSocket(portIn);
        s = ss.accept();
        
        ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(s.getInputStream());
        ObjectOutputStream outSalida = new ObjectOutputStream(sSalida.getOutputStream());
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
        while( !salir ){
            
            System.out.println("----------------------------------------------");
            for(int i=0; i<carac.length; i++){
                carac[i] = in.read();
                if(carac[i] == 38 )
                    salir = true;
            }
            
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
                outSalida.writeObject((char)c);
            }
            
        }
    }
}
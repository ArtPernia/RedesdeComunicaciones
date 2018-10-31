package canalcomunicaciones;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;



public class CanalComunicaciones {

public static Double PROB_ERR = 0.5;
public static int TAM_BYTE = 8;

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
        int carac;
        
        
        
        //InputStream in = System.in;
        // & sale de la funcion
        while((carac = (int)in.readObject()) != 38){
            //int carac = in.read();
            System.out.println("I:" + (char)carac+":" + carac + ":" + Integer.toBinaryString(carac));
            Double errorByte = Math.random();
            if(errorByte<PROB_ERR){
            Random rnd = new Random();
              int index = rnd.nextInt(TAM_BYTE)-1;
                carac = carac^(1<<index);
            }
            //System.out.println("O:" + (char)carac+":" + carac + ":" + Integer.toBinaryString(carac));
            outSalida.writeObject(carac);
        }
    }
}
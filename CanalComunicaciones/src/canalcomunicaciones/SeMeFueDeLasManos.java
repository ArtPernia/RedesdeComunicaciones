package canalcomunicaciones;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;



public class SeMeFueDeLasManos extends Thread{

//public static Double PROB_ERR = 0.5;
public static int TAM_BYTE = 8;
public static double PROB_ERR_BIT = 0.2;
public static double PROB_ERR_DESP = 0.75;
private final int portIn = 6969;
private final int portOut = 6999; 
public final String ipSalida = "localhost";

    
@Override
    
    public void run(){
        try {
            Socket s = null;
            while(true){

                ServerSocket ss = new ServerSocket(portIn);
                s = ss.accept();
                trabaja(s);
                s.close();
                ss.close();
                s=null;
                ss=null;
            }
        
        } catch (IOException ex) {
            System.out.println(ex.getMessage());;
        }
    }
    public void trabaja(Socket s) throws IOException{
        Boolean salir = false;
        int[] carac = new int[2];
        Socket sSalida = new Socket(ipSalida, portOut);
        
        while(!salir){
            carac = readBytes(s);
            enterErrs(carac);
            if(carac.length<2)
                salir=true;
            sendBytes(carac, sSalida);
        }
    }
    public int[] readBytes(Socket s) throws IOException{
        OutputStream out = s.getOutputStream();
            InputStream in = s.getInputStream();
            
            int[] carac = new int[2];
            int nb;
            for(int i=0; i<carac.length; i++){
                
                nb = in.read();
                if(nb == 38 || nb == -1)
                    return carac;
                else
                    carac[i] = nb;
            }
            
            return carac;
    }
    public int[] enterErrs(int[] carac){
        Random rnd = new Random();
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
            return carac;
    }
    public void sendBytes(int[] carac, Socket sSalida) throws IOException{
        OutputStream outSalida = sSalida.getOutputStream();
        for(int c : carac){
                System.out.println("O:" + (char)c + ":" + c + ":" + Integer.toBinaryString(c));
                outSalida.write(c);
            }
    }
    
    //-------- args[0] = Puerto de Entrada | args[1] = PuertoSalida | args[2] = IP Salida ------------
    
}
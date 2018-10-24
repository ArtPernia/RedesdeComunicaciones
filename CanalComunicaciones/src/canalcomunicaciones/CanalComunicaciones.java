package canalcomunicaciones;
import java.io.*;
import java.util.Random;


/**
 *
 * @author gseno
 */
public class CanalComunicaciones {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        
       String holla;
       int opt;
       
       InputStream is = System.in;
       OutputStream os = System.out;
       byte[] buffer = new byte[64];
       int nb;
       
       ByteArrayOutputStream baos = new ByteArrayOutputStream();
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));       

        System.out.println("\nEscriba: \n");

        while(is.available()>0){
                
            nb = is.read();
            //nb = is.read(buffer);
                if(nb>0){
                    //1-lee del buffer, 2-Desde 0, 3-nb: numero de bytes
                    Double probabilidad = Math.random();
                    if(probabilidad<=0.3){
                        if(nb < 254) nb++;
                        
                    }else{
                        
                    }
//                    Random r = new Random();
//                    r.nextBoolean();
                    
                    os.write(nb);
                   
                }
        }
    }
} 
       // os.write(baos.toByteArray());
//        do{
//            
//            holla=br.readLine();
//            System.out.println(holla);
//        
//        }while(!holla.equals("//"));        
//    }
    


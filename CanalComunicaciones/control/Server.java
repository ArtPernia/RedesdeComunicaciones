
package control;

import java.io.IOException;

public class Server { 
    
    public static void main(String[] args) throws IOException {
        
        ServidorSocket s1 = new ServidorSocket(6969);
        
        while(true){
            System.out.println(s1.leer());
        }
        
    }
    
}
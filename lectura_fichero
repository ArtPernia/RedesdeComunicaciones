package javaapplication6;

import java.util.Scanner;

public class JavaApplication6 {

    public static void main(String[] args) {
        
        hilo h1 = new hilo();
        String controlador = "";
        Scanner sc1 = new Scanner(System.in);
        System.out.println("Introduce c para comenzar, r para reanudar s para parar y p para pausar");
        controlador = sc1.next();
        if(controlador.equals("c")){
            h1.start();
            System.out.println("hilo arrancado");
           while(true){
                if( controlador.equals("r")){                    
                    h1.resume();
                    System.out.println("hilo reanudado");
                   }
                else if(controlador.equals("p")){
                    h1.suspend();
                    System.out.println("hilo pausado");
                }else if (controlador.equals("s")){
                     System.out.println("programa cerrado");
                    break;
                }
                controlador = sc1.next();
            }
        }
    }
}

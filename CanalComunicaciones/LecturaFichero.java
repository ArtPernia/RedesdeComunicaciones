/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecturafichero;

import java.util.Scanner;

public class LecturaFichero {

    public static void main(String[] args) {
        
        Hilo h1 = new Hilo();
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
                     System.exit(0);
                }
                controlador = sc1.next();
            }
        }
    }
    
}
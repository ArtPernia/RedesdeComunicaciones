/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuardarInformacion;



import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class GuardarInformacion {

private static final String FILENAME = "C:\\Users\\valonso8008\\Documents\\datos.dat";

public static void main(String[] args) {
            
                char [] arrayCaracteres= new char[10];
                String lectura;
                
                
                Scanner scanner = new Scanner(System.in);
                lectura = scanner.nextLine();
                
                
                arrayCaracteres = lectura.toCharArray();
                
                
                
            /*    for (int i=0; i<arrayCaracteres.length; i++ ){
                    System.out.println(arrayCaracteres[i]);
            
        }*/
                
                /*if (lectura == "FIN"){
            System.out.println("hola");
              }else{
                System.out.println("adios");
                }*/
                

BufferedWriter bw = null;
FileWriter fw = null;

try {

String content = "Falta entrada por pantalla";

fw = new FileWriter(FILENAME);
bw = new BufferedWriter(fw);
bw.write(content);

System.out.println("Done");

} catch (IOException e) {

e.printStackTrace();

} finally {

try {

if (bw != null)
bw.close();

if (fw != null)
fw.close();

} catch (IOException ex) {

ex.printStackTrace();

}

}

}

}

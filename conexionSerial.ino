
int incomingByte = 0;  
int cont = 0;
int delimiter = 'a';
char bloque[8];
int count = 0;

void setup(){
  Serial.begin(9600);
}
 
  void loop() {

        // finaliza solo cuando se reciben los datos:
        while (Serial.available() > 0 ) {
                // lee el byte de entrada:
                incomingByte = Serial.read();//convertir a caracter
                

                Serial.println(" ");
                Serial.println(delimiter);
                if( delimiter == incomingByte ){
                  Serial.println("Encontrado delimitar, salimos del bucle.");
                  bloque[count] = (char)incomingByte;
                  count = 0;
                  break;
                  
                }
                
                bloque[count] = (char)incomingByte;
                count++;
                // di lo que tienes:
                Serial.println(" ");
                Serial.println("Recibido: ");
                Serial.print(incomingByte, DEC);
                
        }
}


String encriptar() {
  
}

String desencriptar(){

}

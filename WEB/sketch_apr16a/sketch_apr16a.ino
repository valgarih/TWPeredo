const byte pin[]= {2,3,4,5,6,7,8,9}; // lo que te permite elegir cualquier pin
float temp;
float luz;
const byte pinsalida;
byte sensor = 0;
byte estado;
int estadosalida;
void setup() {

  for (int i=0; i<8; i++) {
        pinMode(pin[i], INPUT); // supongo que los pines estan configurados pull-up o down.
  }

    pinMode(pinsalida, OUTPUT);
    digitalWrite(pinsalida,LOW);
    Serial.begin(9600);
}

void loop() {
  // Lectura de pines
  for (int i=1; i<=8; i++) {
        estado |= digitalRead(pin[i-1]) & (1<<i);
  } 

  if(digitalRead(pinsalida)==0)
  {
  digitalWrite(pinsalida,HIGH); 
  temp=estado;

  }
  else {
      digitalWrite(pinsalida,LOW);
      luz=estado;
      }
  Serial.print("Bits =");
  Serial.println(luz);
  Serial.println(temp);

  
}


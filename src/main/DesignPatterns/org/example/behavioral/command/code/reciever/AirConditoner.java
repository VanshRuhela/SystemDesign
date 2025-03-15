package org.example.behavioral.command.code.reciever;

// Receiver
public class AirConditoner {
        boolean isOn;
        int temp;

        public void turnOnAc(){
            isOn = true;
            System.out.println("Ac is on");
        }

        public void turnOFFAc(){
            isOn = false;
        }

        public void setTemp(int temp){
            this.temp = temp;
        }
}

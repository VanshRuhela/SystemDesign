package org.example.behavioral.command.code;

import org.example.behavioral.command.code.commands.TurnAcOnCommand;
import org.example.behavioral.command.code.invoker.MyRemoteControl;
import org.example.behavioral.command.code.reciever.AirConditoner;

public class Demo {
    public static void main(String[] args){
        AirConditoner ac = new AirConditoner();
        MyRemoteControl remoteControl = new MyRemoteControl();

//  Assuming only one button on the remote ::
        remoteControl.setCommand(new TurnAcOnCommand(ac));
        remoteControl.pressButton();

    }
}

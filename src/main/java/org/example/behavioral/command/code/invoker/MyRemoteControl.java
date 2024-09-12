package org.example.behavioral.command.code.invoker;

import org.example.behavioral.command.code.commands.ICommand;

//Invoker
public class MyRemoteControl {
    ICommand command;
    public MyRemoteControl(){
    }

    public void setCommand(ICommand command){
        this.command = command;
    }

    public void pressButton(){
        command.execute();
    }
}

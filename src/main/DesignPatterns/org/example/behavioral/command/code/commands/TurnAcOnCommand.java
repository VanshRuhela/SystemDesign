package org.example.behavioral.command.code.commands;

import org.example.behavioral.command.code.reciever.AirConditoner;

public class TurnAcOnCommand implements ICommand {

    AirConditoner ac;

    public TurnAcOnCommand(AirConditoner ac){
        this.ac = ac;
    }
    @Override
    public void execute() {
        ac.turnOnAc();
    }
}

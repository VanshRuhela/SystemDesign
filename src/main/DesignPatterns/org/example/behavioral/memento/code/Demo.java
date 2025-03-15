package org.example.behavioral.memento.code;

public class Demo {

    public static void main(String[] args){
        ConfigurationCareTaker careTaker = new ConfigurationCareTaker();
        ConfigurationOriginator originator = new ConfigurationOriginator(5, 10);
        originator.printState();

        ConfigurationMemento snapshot = originator.createMemento();
        careTaker.addMemento(snapshot);

        // originator changing state to new state
        originator.setWidth(4);
        originator.setHeight(90);
        originator.printState();


        ConfigurationMemento snapshot2 = originator.createMemento();
        careTaker.addMemento(snapshot2);

        // originator changing state to new state
        originator.setWidth(9);
        originator.setHeight(10);
        originator.printState();

        // UNDO
        ConfigurationMemento lastMemento = careTaker.undo();
        originator.restoreMemento(lastMemento);
        System.out.println("Originator restoring __ UNDO");
        originator.printState();
    }
}

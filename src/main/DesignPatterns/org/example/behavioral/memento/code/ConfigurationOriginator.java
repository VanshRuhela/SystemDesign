package org.example.behavioral.memento.code;

public class ConfigurationOriginator {
    int height;
    int width;
    ConfigurationOriginator(int height, int width){
        this.height = height;
        this.width = width;
    }

    public void setHeight(int height){
        this.height = height;
    }

    public void setWidth(int width){
        this.width = width;
    }

    public ConfigurationMemento createMemento(){
        return new ConfigurationMemento(this.height, this.width);
    }

    public void restoreMemento(ConfigurationMemento mementoToBeRestored){
        this.height = mementoToBeRestored.height;
        this.width = mementoToBeRestored.width;
    }

    public void printState(){
        System.out.println("Originator height: " + height + " , width: " + width);
    }

}

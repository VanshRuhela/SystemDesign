package org.example.flyweightpattern.codeRobot;

public class Demo {
    public static void main(String[] args){
        IRobot iRobot = RobotFactory.createRobot("Humanoid");
        iRobot.display(10,20);

        IRobot iRobot2 = RobotFactory.createRobot("Humanoid"); // this will use the same object
        iRobot2.display(10,20);
    }
}

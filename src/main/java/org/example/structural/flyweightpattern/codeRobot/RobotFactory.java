package org.example.structural.flyweightpattern.codeRobot;

import java.util.HashMap;

//to cache
public class RobotFactory {
    private static final HashMap<String , IRobot> cache = new HashMap<>();

    public static IRobot createRobot(String type){
        if(cache.containsKey(type))
            return cache.get(type);
        else{
            if(type.equals("Humanoid")){
                IRobot humanoidRobot = new HumanoidRobot(type , "body");
                cache.put(type,humanoidRobot);
                return humanoidRobot;
            }
            else {
                IRobot roboticDog = new RoboticDog(type, "bodyDog");
                cache.put(type, roboticDog);
                return roboticDog;
            }
        }
    }


}

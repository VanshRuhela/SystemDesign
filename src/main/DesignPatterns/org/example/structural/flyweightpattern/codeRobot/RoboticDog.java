package org.example.structural.flyweightpattern.codeRobot;

public class RoboticDog implements IRobot {
    private final String type;
    private final Object body; // big obj Sprites

    public RoboticDog(String type, Object body) {
        this.type = type;
        this.body = body;
    }

    public String getType() {
        return type;
    }

    public Object getBody() {
        return body;
    }

    @Override
    public void display(int x, int y) {
        // render the image
    }

}

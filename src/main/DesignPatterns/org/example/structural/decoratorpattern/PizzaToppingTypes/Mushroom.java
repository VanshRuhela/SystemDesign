package org.example.structural.decoratorpattern.PizzaToppingTypes;

import org.example.structural.decoratorpattern.BasePizza;
import org.example.structural.decoratorpattern.ToppingDecorator;

public class Mushroom extends ToppingDecorator {

    BasePizza basePizza;

    public Mushroom(BasePizza pizza){
        this.basePizza = pizza;
    }
    @Override
    public int cost() {
        return this.basePizza.cost() + 30;
    }
}

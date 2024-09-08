package org.example.decoratorpattern.PizzaToppingTypes;

import org.example.decoratorpattern.BasePizza;
import org.example.decoratorpattern.ToppingDecorator;

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

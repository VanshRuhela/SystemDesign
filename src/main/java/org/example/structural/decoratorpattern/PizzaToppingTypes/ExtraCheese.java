package org.example.structural.decoratorpattern.PizzaToppingTypes;

import org.example.structural.decoratorpattern.BasePizza;
import org.example.structural.decoratorpattern.ToppingDecorator;

public class ExtraCheese extends ToppingDecorator {
    BasePizza basePizza;

    public ExtraCheese(BasePizza pizza){
        this.basePizza = pizza;
    }
    @Override
    public int cost() {
        return this.basePizza.cost() + 10;
    }
}

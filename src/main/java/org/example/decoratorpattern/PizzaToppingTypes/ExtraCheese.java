package org.example.decoratorpattern.PizzaToppingTypes;

import org.example.decoratorpattern.BasePizza;
import org.example.decoratorpattern.ToppingDecorator;

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

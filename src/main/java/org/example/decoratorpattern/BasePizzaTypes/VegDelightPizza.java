package org.example.decoratorpattern.BasePizzaTypes;

import org.example.decoratorpattern.BasePizza;

public class VegDelightPizza extends BasePizza {
    @Override
    public int cost() {
        return 150;
    }
}

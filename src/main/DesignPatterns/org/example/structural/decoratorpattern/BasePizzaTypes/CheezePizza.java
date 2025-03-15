package org.example.structural.decoratorpattern.BasePizzaTypes;

import org.example.structural.decoratorpattern.BasePizza;

public class CheezePizza extends BasePizza {
    @Override
    public int cost() {
        return 200;
    }
}

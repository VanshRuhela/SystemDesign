package org.example.decoratorpattern.BasePizzaTypes;

import org.example.decoratorpattern.BasePizza;

public class CheezePizza extends BasePizza {
    @Override
    public int cost() {
        return 200;
    }
}

package org.example.decoratorpattern.BasePizzaTypes;

import org.example.decoratorpattern.BasePizza;

public class FarmHousePizza extends BasePizza {
    @Override
    public int cost() {
        return 250;
    }
}

package org.example.structural.decoratorpattern;

import org.example.structural.decoratorpattern.BasePizzaTypes.FarmHousePizza;
import org.example.structural.decoratorpattern.PizzaToppingTypes.ExtraCheese;
import org.example.structural.decoratorpattern.PizzaToppingTypes.Mushroom;

public class Demo {
    public static void main(String[] args){


        // create a FarmHousePizza and decorate it with Mushroom
        FarmHousePizza pizza = new FarmHousePizza();
        BasePizza decorateWithMushroom = new Mushroom(pizza);

        System.out.println(decorateWithMushroom.cost());


//        also decorate it with ExtraCheese
        decorateWithMushroom = new ExtraCheese(decorateWithMushroom);
        System.out.println(decorateWithMushroom.cost());
    }
}

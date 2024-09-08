package org.example.decoratorpattern;

import org.example.decoratorpattern.BasePizzaTypes.FarmHousePizza;
import org.example.decoratorpattern.PizzaToppingTypes.ExtraCheese;
import org.example.decoratorpattern.PizzaToppingTypes.Mushroom;

public class CreatePizza {
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

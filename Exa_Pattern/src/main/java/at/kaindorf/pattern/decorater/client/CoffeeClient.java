package at.kaindorf.pattern.decorater.client;

import at.kaindorf.pattern.decorater.Coffee;
import at.kaindorf.pattern.decorater.HouseBlend;
import at.kaindorf.pattern.decorater.MilkDecorater;
import at.kaindorf.pattern.decorater.SugarDecorater;

public class CoffeeClient {

    public static void main(String[] args) {
        Coffee coffee = new HouseBlend();
        System.out.println(coffee.getDescription() + " costs: " + coffee.costs());
        coffee = new MilkDecorater(coffee);
        System.out.println(coffee.getDescription() + " costs: " + coffee.costs());
        coffee = new SugarDecorater(new SugarDecorater(coffee));
        System.out.println(coffee.getDescription() + " costs: " + coffee.costs());
    }
}

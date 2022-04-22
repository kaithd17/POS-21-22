package at.kaindorf.pattern.decorater;

public abstract class CoffeeDecorater extends Coffee {
    protected Coffee coffee;

    public CoffeeDecorater(Coffee coffee) {
        this.coffee = coffee;
    }
}

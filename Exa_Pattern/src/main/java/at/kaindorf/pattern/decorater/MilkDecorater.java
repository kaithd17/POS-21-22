package at.kaindorf.pattern.decorater;

public class MilkDecorater extends CoffeeDecorater{

    public MilkDecorater(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double costs() {
        return 0.5 + coffee.costs();
    }

    @Override
    public String getDescription() {
        return "Milk, " + coffee.getDescription();
    }
}

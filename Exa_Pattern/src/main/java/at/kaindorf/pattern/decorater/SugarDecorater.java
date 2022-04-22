package at.kaindorf.pattern.decorater;

public class SugarDecorater extends CoffeeDecorater {

    public SugarDecorater(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double costs() {
        return 0.35 + coffee.costs();
    }

    @Override
    public String getDescription() {
        return "Sugar, " + coffee.getDescription();
    }
}

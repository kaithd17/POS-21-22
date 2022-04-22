package at.kaindorf.pattern.decorater;

public class Decaffinate extends Coffee{
    @Override
    public double costs() {
        return 1.99;
    }

    @Override
    public String getDescription() {
        return "Decaf";
    }
}

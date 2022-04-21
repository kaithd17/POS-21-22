package at.kaindorf.ui;

import at.kaindorf.observer.Console;
import at.kaindorf.observer.Html;
import at.kaindorf.observer.Subject;
import at.kaindorf.pojos.Game;
import at.kaindorf.singleton.Calculator;
import at.kaindorf.singleton.XmlDal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UIMenu extends Subject {
    private static UIMenu concreteSubject = new UIMenu();
    private List<Game> games = new ArrayList<>();

    public static void main(String[] args) {
        Console console = new Console();
        Html html = new Html();
        concreteSubject.register(console);
        concreteSubject.register(html);

        int number;
        do {
            System.out.println("0 ... Beenden");
            System.out.println("1 ... Daten von der Datei 'bundesliga2122.xml' laden");
            System.out.println("2 ... Daten vom Verzeichnis 'args[0]' Verzeichnis laden");
            System.out.println("3 ... Duplikate entfernen (mit WHILE Schleife)");
            System.out.println("4 ... Duplikate entfernen (mit Java-Stream)");
            System.out.print("Wahl [0-4]: ");
            Scanner scan = new Scanner(System.in);

            number = scan.nextInt();
            XmlDal xmlDal = XmlDal.getInstance();
            Calculator calculator = Calculator.getInstance();
            System.out.println("-------------------------------------------------");

        } while (number != 0);
    }

    @Override
    public void notifyObservers() {
        concreteSubject.observers.forEach(o -> o.update(games.toString()));
    }
}

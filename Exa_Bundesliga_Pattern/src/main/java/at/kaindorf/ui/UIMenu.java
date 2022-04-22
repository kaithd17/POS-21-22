package at.kaindorf.ui;

import at.kaindorf.observer.Console;
import at.kaindorf.observer.Html;
import at.kaindorf.observer.Subject;
import at.kaindorf.pojos.Game;
import at.kaindorf.singleton.Calculator;
import at.kaindorf.singleton.XmlDal;
import at.kaindorf.visitor.MyPath;
import at.kaindorf.visitor.SearchXMLFiles;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UIMenu extends Subject {
    private static UIMenu concreteSubject = new UIMenu();

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
            System.out.println("-------------------------------------------------");

            switch (number) {
                case 0:
                    concreteSubject.unregister(console);
                    concreteSubject.unregister(html);
                    break;

                case 1:
                    Calculator.getInstance().addDataFromTournamentObject();
                    concreteSubject.notifyObservers();
                    break;

                case 2:
                    MyPath.getInstance().xmlFileList.clear();
                    SearchXMLFiles searchXMLFiles = new SearchXMLFiles();
                    searchXMLFiles.setWorkingDirectory(Paths.get(args[0]));
                    searchXMLFiles.searchXmlFiles();
                    searchXMLFiles.getXmlFiles();
                    Calculator.getInstance().addDataFromXmlFiles();
                    concreteSubject.notifyObservers();
                    break;

                case 3:
                    Calculator.getInstance().removeDuplicates1();
                    concreteSubject.notifyObservers();
                    break;

                case 4:
                    Calculator.getInstance().removeDuplicates2();
                    concreteSubject.notifyObservers();
                    break;
                default:
                    break;
            }
            System.out.println("-------------------------------------------------");
        } while (number != 0);
    }

    @Override
    public void notifyObservers() {
        concreteSubject.observers.forEach(o -> o.update(Calculator.getInstance().generateTable()));
    }
}

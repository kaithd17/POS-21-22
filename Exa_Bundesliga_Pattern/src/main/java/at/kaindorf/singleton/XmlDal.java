package at.kaindorf.singleton;

import at.kaindorf.pojos.Game;
import at.kaindorf.pojos.Tournament;
import jakarta.xml.bind.JAXB;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class XmlDal {

    //Singleton
    private static XmlDal theInstance;
    public static XmlDal getInstance() {
        if (theInstance == null) {
            theInstance = new XmlDal();
        }
        return theInstance;
    }
    private XmlDal() {}

    //Path
    private static final Path PATH = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "data", "bundesliga2122.xml");
    private static final Path PATH2 = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "data", "Runde1", "Spiel_1_1.xml");

    public Tournament loadTournament() {
        Tournament tournament;
        tournament = JAXB.unmarshal(PATH.toFile(), Tournament.class);
        return tournament;
    }

    public Game loadGame() {
        Game game;
        game = JAXB.unmarshal(PATH2.toFile(), Game.class);
        return game;
    }
}

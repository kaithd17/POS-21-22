package at.kaindorf.singleton;

import at.kaindorf.pojos.Game;
import at.kaindorf.pojos.Tournament;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Calculator {
    //Singleton
    private static Calculator theInstance;
    public static Calculator getInstance() {
        if (theInstance == null) {
            theInstance = new Calculator();
        }
        return theInstance;
    }
    private Calculator() {}

    private List<Game> gameList = new ArrayList<>();

    public void addDataFromTournamentObject() {
        Tournament tournament = XmlDal.getInstance().loadTournament();
        gameList = tournament.getGames();
    }

    public void loadDataFromXmlFiles() {

    }

    public List<Game> getAllGamesByTeamName(String teamName) {
        List<Game> matches = gameList.stream()
                .filter(game -> game.getTeam1().equals(teamName) || game.getTeam2().equals(teamName))
                .collect(Collectors.toList());
        return matches;
    }


}

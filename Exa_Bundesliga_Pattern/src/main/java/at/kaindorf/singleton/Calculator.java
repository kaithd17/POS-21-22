package at.kaindorf.singleton;

import at.kaindorf.pojos.Game;
import at.kaindorf.pojos.Team;
import at.kaindorf.pojos.Tournament;
import at.kaindorf.visitor.MyPath;
import at.kaindorf.visitor.SearchXMLFiles;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
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

    private Calculator() {
    }

    private List<Game> gameList = new ArrayList<>();

    public void addDataFromTournamentObject() {
        gameList.clear();
        Tournament tournament = XmlDal.getInstance().loadTournament();
        gameList.addAll(tournament.getGames());
    }

    public void addDataFromXmlFiles() {
        gameList.clear();
        MyPath.getInstance().xmlFileList.forEach(p -> {
            gameList.add(XmlDal.getInstance().loadGame(p));
        });
    }

    public List<Game> getAllGamesByTeamName(String teamName) {
        List<Game> matches = gameList.stream()
                .filter(game -> game.getTeam1().equals(teamName) || game.getTeam2().equals(teamName))
                .collect(Collectors.toList());
        return matches;
    }

    public void removeDuplicates1() {
        int counter = 0;
        List<Game> removeList = new ArrayList<>(gameList);
        while (counter < removeList.size()) {
            Game game = gameList.get(counter);
            for (int i = (counter + 1); i < removeList.size(); i++) {
                if (game.equals(removeList.get(i))) {
                    removeList.remove(removeList.get(i));
                }
            }
            counter++;
        }
        gameList = removeList;
    }

    public void removeDuplicates2() {
        gameList = gameList.stream().distinct().collect(Collectors.toList());
    }

    public List<Team> generateTable() {
        List<Team> teams = new ArrayList<>();

        //Get all team names
        List<String> teamNames = gameList.stream().map(Game::getTeam1).distinct().collect(Collectors.toList());

        //Create team objects and set the team name
        teamNames.forEach(t -> {
            Team team = new Team(t, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            teams.add(team);
        });

        //Calculate all the needed data for all teams
        teams.forEach(team -> {
            //Get all matches of one team
            List<Game> matches = getAllGamesByTeamName(team.getName());
            //Matches played
            team.setPlayed(matches.size());

            //Calculate attributes
            matches.forEach(match -> {
                int result;
                if (match.getTeam1().equals(team.getName())) {
                    //Goals
                    team.setGs(team.getGs() + match.getGoal1());
                    team.setGa(team.getGa() + match.getGoal2());

                    //Result
                    result = match.getGoal1() - match.getGoal2();
                } else {
                    //Goals
                    team.setGs(team.getGs() + match.getGoal2());
                    team.setGa(team.getGa() + match.getGoal1());

                    //Result
                    result = match.getGoal2() - match.getGoal1();
                }
                //Calculate goal difference
                team.setGd(team.getGs() - team.getGa());

                //Analyse match result
                if (result > 0) {
                    team.setWon(team.getWon() + 1);
                    team.setPts(team.getPts() + 3);
                } else if (result == 0) {
                    team.setDraw(team.getDraw() + 1);
                    team.setPts(team.getPts() + 1);
                } else {
                    team.setLoss(team.getLoss() + 1);
                }
            });
        });
        teams.sort(Comparator.comparing(Team::getPts).thenComparing(Team::getGd).reversed());
        //Set Position
        for (int i = 0; i < teams.size(); i++) {
            teams.get(i).setPosition(i + 1);
        }
        return teams;
    }

}

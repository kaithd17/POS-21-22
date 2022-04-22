package at.kaindorf.observer;

import at.kaindorf.pojos.Game;
import at.kaindorf.pojos.Team;

import java.util.List;

public class Console implements IObserver {

    @Override
    public void update(List<Team> data) {
        if (data.size() == 0) {
            System.out.println("You have to add data first!");
        } else {
            System.out.println("\tTeam\t\t\t\tSp\tT\tPt");
            data.forEach(team -> {
                //String.format -24s
                System.out.println(team.getPosition() + " " + team.getName() + " \t" + team.getPlayed() + " " + team.getGd() + " " + team.getPts());
            });
        }
    }
}

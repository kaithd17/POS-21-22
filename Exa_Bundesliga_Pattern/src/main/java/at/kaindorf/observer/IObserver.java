package at.kaindorf.observer;

import at.kaindorf.pojos.Game;
import at.kaindorf.pojos.Team;

import java.util.List;

public interface IObserver {
    void update(List<Team> data);
}

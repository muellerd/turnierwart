package de.muellerd.turnierwart.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by daniel on 26.05.2016.
 */
public class SessionData implements Serializable {

    private ArrayList<Tournament> tournaments;
    private HashMap<Tournament, ArrayList<Game>> tournamentToGames;

    public SessionData(){
        tournaments = new ArrayList<Tournament>();
        tournamentToGames = new HashMap<Tournament, ArrayList<Game>>();
    }

    public void addTournament(Tournament tournament){
        tournaments.add(tournament);
    }

    public int getNumberOfTournaments() {
        return this.tournaments.size();
    }

    public ArrayList<Tournament> getListOfTournaments() {
        return this.tournaments;
    }

    public void removeTournament(int id) {
        this.tournaments.remove(id);
    }

    public Tournament getTournament(int position) {
        return this.tournaments.get(position);
    }
}

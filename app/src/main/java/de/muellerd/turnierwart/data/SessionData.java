package de.muellerd.turnierwart.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class SessionData implements Serializable {

    private ArrayList<Tournament> tournaments;
    private HashMap<Tournament, ArrayList<Game>> tournamentToGames;
    private TournamentDbHelper tournamentDbHelper;

    public SessionData(Context context){
        tournaments = new ArrayList<>();
        tournamentToGames = new HashMap<>();
        tournamentDbHelper = new TournamentDbHelper(context);

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

    public TournamentDbHelper getDbHelper() {
        return this.tournamentDbHelper;
    }
}

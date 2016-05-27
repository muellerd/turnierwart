package de.muellerd.turnierwart.classes;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by daniel on 26.05.2016.
 */
public class SessionData implements Serializable {

    private ArrayList<Turnier> turniers;
    private HashMap<Turnier, ArrayList<Spiel>> turnierToSpiele;

    public SessionData(){
        turniers = new ArrayList<Turnier>();
        turnierToSpiele = new HashMap<Turnier, ArrayList<Spiel>>();
    }

    public void addTurnier(Turnier turnier){
        turniers.add(turnier);
    }

    public int getNumberOfTurniers() {
        return this.turniers.size();
    }

    public ArrayList<Turnier> getTurnierListe() {
        return this.turniers;
    }

    public void removeTurnier(int id) {
        this.turniers.remove(id);
    }
}

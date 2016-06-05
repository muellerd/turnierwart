package de.muellerd.turnierwart.data;

import java.io.Serializable;
import java.util.ArrayList;

public class Group implements Serializable{

    private Tournament tournament;
    private String name;
    private ArrayList<Team> teams;
    private long groupDbId;
    private String descr;

    public Group(Tournament t, String n){
        tournament = t;
        name = n;
        teams = new ArrayList<Team>();
    }

    public void addTeam(Team t) {
        this.teams.add(t);
    }

    public String getName() {
        return name;
    }

    public String getDescr() {
        String descr = "";
        for (Team t: teams) {
            descr += t.getName() + "\n";
        }
        return descr.substring(0, descr.length()-2);
    }

    public void setDbId(long dbId) {
        this.groupDbId = dbId;
    }
}

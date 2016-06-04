package de.muellerd.turnierwart.data;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by daniel on 26.05.2016.
 */
public class Tournament implements Serializable {
    private String name;
    private Date start;
    private Date end;
    private String place;
    private String host;
    private Team[] teams;
    private Game[] games;
    private int pointsForWin;
    private int pointsForRemis;
    private String[] groupNames;
    private HashMap<String, ArrayList<Team>> groups;
    private HashMap<Team, String> teamToGroup;
    private HashMap<String, Team> nameToTeam;

    //public: other users can search for Tournaments
    private boolean isPublic;

    public Tournament(){
        this.isPublic = false;
    }

    /**
     *
     * @param na Name of the Tournament
     * @param sta start date string of the Tournament
     * @param en end date string of the Tournament
     * @param or place of the Tournament
     * @param aus host of the Tournament
     *
     */
    public Tournament(String na, String sta, String en, String or, String aus, String wi, String rem,
                      String gro, String tea){
        this.name = na;
        this.place = or;
        this.host = aus;
        this.isPublic = false;
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMAN);
        try {
            this.start = format.parse(sta);
            this.end = format.parse(en);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.pointsForWin = Integer.parseInt(wi);
        this.pointsForRemis = Integer.parseInt(rem);
        this.groupNames = new String[Integer.parseInt(gro)];
        groups = new HashMap<String, ArrayList<Team>>();
        for(int i = 0; i < Integer.parseInt(gro); i++){
            groupNames[i] = "Gruppe " + (i+1);
            groups.put(groupNames[i], new ArrayList<Team>());
        }
        String[] teamSplit = tea.split("\n");
        teams = new Team[teamSplit.length];
        nameToTeam = new HashMap<String, Team>();
        for(int j = 0; j < teamSplit.length; j++){
            Team man = new Team(teamSplit[j]);
            teams[j] = man;
            nameToTeam.put(man.getName(), man);
        }
        teamToGroup = new HashMap<Team, String>();
    }

    public String toString(){
        String tournament = "";
        DateFormat form = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMAN);
        String startDate = form.format(start);
        String endDate = form.format(end);
        tournament += name + " (" + startDate + " - " + endDate + ")";

        return tournament;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        DateFormat form = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMAN);
        String startDate = form.format(start);
        String endDate = form.format(end);

        String descr = startDate + " - " + endDate + "\n" + teams.length + " Mannschaften" +
                "\n" + host + ", " + place;
        return descr;

    }

    public ArrayList<Team> getNotAssignedTeams() {
        ArrayList<Team> naTeams = new ArrayList<Team>();
        for(int i = 0; i < this.teams.length; i++){
            if(!this.teamToGroup.containsKey(teams[i])){
                naTeams.add(teams[i]);
            }
        }
        return naTeams;
    }

    public String[] getGroupNames() {
        return groupNames;
    }

    public int getNumberAssignedTeams() {
        return this.teamToGroup.keySet().size();
    }

    public int getNumberNotAssignedTeams() {
        return this.teams.length - this.teamToGroup.keySet().size();
    }

    public void assignTeamToGroup(CharSequence teamName, CharSequence gn) {
        String groupName = (String) gn;
        Team t = nameToTeam.get(teamName);
        teamToGroup.put(t, groupName);
        if(groups.containsKey(groupName)){
            groups.get(groupName).add(t);
        }
        else{
            groups.put(groupName, new ArrayList<Team>());
            groups.get(groupName).add(t);
        }
    }

    public Team getNextAssignee() {
        if(getNotAssignedTeams().size() > 0){
            return getNotAssignedTeams().get(0);
        }
        return null;
    }

    public int getNumberOfTeams() {
        return this.teams.length;
    }

    public ArrayList<GroupTuple> getGroupTuples() {
        ArrayList<GroupTuple> tuples = new ArrayList<GroupTuple>();

        for (String name : groupNames) {
            String descr = "";

            for (int j = 0; j < groups.get(name).size(); j++) {
                descr += groups.get(name).get(j).getName();
                if (j < groups.get(name).size() - 1) {
                    descr += "\n";
                }
            }

            GroupTuple tup = new GroupTuple(name, descr);
            tuples.add(tup);
        }

        return tuples;
    }
}

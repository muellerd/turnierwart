package de.muellerd.turnierwart.data;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import de.muellerd.turnierwart.MainActivity;

public class Tournament implements Serializable {
    private String name;
    private Date start;
    private Date end;
    private String place;
    private String host;
    private ArrayList<Team> teams;
    private ArrayList<Game> games;
    private int pointsForWin;
    private int pointsForRemis;
    private ArrayList<Group> groups;
    private HashMap<Team, Group> teamToGroup;
    private HashMap<String, Team> nameToTeam;
    private HashMap<String, Group> nameToGroup;

    private long tournamentDbId;

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
        if(gro.equals("")){
            gro="0";
        }
        this.groups = new ArrayList<>();
        this.nameToGroup = new HashMap<>();
        for(int i = 0; i < Integer.parseInt(gro); i++){
            String name = "Gruppe " + (i+1);
            Group g = new Group(this, name);
            this.nameToGroup.put(name, g);
            groups.add(g);
        }
        String[] teamSplit = tea.split("\n");
        teams = new ArrayList<>();
        nameToTeam = new HashMap<>();
        for(int j = 0; j < teamSplit.length; j++){
            Team man = new Team(teamSplit[j], this);
            teams.add(man);
            nameToTeam.put(man.getName(), man);
        }
        teamToGroup = new HashMap<>();
    }

    public Tournament(String na, String sta, String en, String pla, String hos, String pointsW, String pointsR) {
        this.name = na;
        this.place = pla;
        this.host = hos;
        this.isPublic = false;
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMAN);
        try {
            this.start = format.parse(sta);
            this.end = format.parse(en);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.pointsForWin = Integer.parseInt(pointsW);
        this.pointsForRemis = Integer.parseInt(pointsR);
        this.groups = new ArrayList<>();
        this.nameToGroup = new HashMap<>();
        teams = new ArrayList<>();
        nameToTeam = new HashMap<>();
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

        String descr = startDate + " - " + endDate + "\n" + teams.size() + " Mannschaften" +
                "\n" + host + ", " + place;
        return descr;

    }

    public ArrayList<Team> getNotAssignedTeams() {
        ArrayList<Team> naTeams = new ArrayList<Team>();
        for(int i = 0; i < this.teams.size(); i++){
            if(!this.teamToGroup.containsKey(teams.get(i))){
                naTeams.add(teams.get(i));
            }
        }
        return naTeams;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public int getNumberAssignedTeams() {
        return this.teamToGroup.keySet().size();
    }

    public int getNumberNotAssignedTeams() {
        return this.teams.size() - this.teamToGroup.keySet().size();
    }

    public void assignTeamToGroup(CharSequence teamName, CharSequence gn) {
        String groupName = (String) gn;
        Group g = nameToGroup.get(groupName);
        Team t = nameToTeam.get(teamName);
        teamToGroup.put(t, g);
        g.addTeam(t);
    }

    public Team getNextAssignee() {
        if(getNotAssignedTeams().size() > 0){
            return getNotAssignedTeams().get(0);
        }
        return null;
    }

    public int getNumberOfTeams() {
        return this.teams.size();
    }

    public ArrayList<GroupTuple> getGroupTuples() {
        ArrayList<GroupTuple> tuples = new ArrayList<>();

        for (Group g : groups) {
            GroupTuple tup = new GroupTuple(g.getName(), g.getDescr());
            tuples.add(tup);
        }

        return tuples;
    }

    public String getHost() {
        return host;
    }

    public String getPlace() {
        return place;
    }

    public int getPointsForWin() {
        return pointsForWin;
    }

    public int getPointsForRemis() {
        return pointsForRemis;
    }

    public String getStartAsString() {
        DateFormat form = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMAN);
        String startDate = form.format(start);
        return startDate;
    }

    public String getEndAsString() {
        DateFormat form = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMAN);
        String endDate = form.format(end);
        return endDate;
    }

    public void setDbId(long dbId) {
        this.tournamentDbId = dbId;
    }

    public long getDbId() {
        return this.tournamentDbId;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public void addGroups(ArrayList<Group> groups) {
        this.groups.addAll(groups);
    }

    public void addTeam(Team team) {
        this.teamToGroup.put(team, team.getGroup());
        this.teams.add(team);
        this.nameToTeam.put(team.getName(), team);
    }
}

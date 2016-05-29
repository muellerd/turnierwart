package de.muellerd.turnierwart.classes;

import java.io.Serializable;
import java.lang.reflect.Array;
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
public class Turnier implements Serializable {
    private String name;
    private Date start;
    private Date end;
    private String ort;
    private String ausrichter;
    private Mannschaft[] mannschaften;
    private Spiel[] spiele;
    private int pointsForWin;
    private int pointsForRemis;
    private String[] groupNames;
    private HashMap<String, ArrayList<Mannschaft>> groups;
    private HashMap<Mannschaft, String> teamToGroup;

    //public: andere Nutzer können nach dem Turnier suchen
    private boolean isPublic;

    public Turnier(){
        this.isPublic = false;
    }

    /**
     *
     * @param na Name of the Turnier
     * @param sta start date string of the Turnier
     * @param en end date string of the Turnier
     * @param or ort of the Turnier
     * @param aus ausrichter of the Turnier
     *
     */
    public Turnier(String na, String sta, String en, String or, String aus, String wi, String rem,
                   String gro, String tea){
        this.name = na;
        this.ort = or;
        this.ausrichter = aus;
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
        groups = new HashMap<String, ArrayList<Mannschaft>>();
        for(int i = 0; i < Integer.parseInt(gro); i++){
            groupNames[i] = "Gruppe " + (i+1);
            groups.put(groupNames[i], new ArrayList<Mannschaft>());
        }
        String[] teamSplit = tea.split("\n");
        mannschaften = new Mannschaft[teamSplit.length];
        for(int j = 0; j < teamSplit.length; j++){
            Mannschaft man = new Mannschaft(teamSplit[j]);
            mannschaften[j] = man;
        }
        teamToGroup = new HashMap<Mannschaft, String>();
    }

    public String toString(){
        String turnier = "";
        DateFormat form = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMAN);
        String startDate = form.format(start);
        String endDate = form.format(end);
        turnier += name + " (" + startDate + " - " + endDate + ")";

        return turnier;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        DateFormat form = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMAN);
        String startDate = form.format(start);
        String endDate = form.format(end);

        String descr = startDate + " - " + endDate + "\n" + mannschaften.length + " Mannschaften" +
                "\n" + ausrichter + ", " + ort;
        return descr;

    }

    public ArrayList<Mannschaft> getNotAssignedTeams() {
        ArrayList<Mannschaft> naTeams = new ArrayList<Mannschaft>();
        for(int i = 0; i < this.mannschaften.length; i++){
            if(!this.teamToGroup.containsKey(mannschaften[i])){
                naTeams.add(mannschaften[i]);
            }
        }
        return naTeams;
    }
}

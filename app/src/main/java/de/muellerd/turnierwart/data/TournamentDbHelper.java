package de.muellerd.turnierwart.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.ColorMatrix;

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.HashMap;

import de.muellerd.turnierwart.MainActivity;

public class TournamentDbHelper extends SQLiteOpenHelper {

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " +
                TournamentContract.TournamentEntry.TABLE_NAME + "( " +
                TournamentContract.TournamentEntry._ID + " INTEGER PRIMARY KEY," +
                TournamentContract.TournamentEntry.COLUMN_NAME_TOURNAMENT_NAME + TEXT_TYPE + COMMA_SEP +
                TournamentContract.TournamentEntry.COLUMN_NAME_TOURNAMENT_START + TEXT_TYPE + COMMA_SEP +
                TournamentContract.TournamentEntry.COLUMN_NAME_TOURNAMENT_END + TEXT_TYPE + COMMA_SEP +
                TournamentContract.TournamentEntry.COLUMN_NAME_TOURNAMENT_HOST + TEXT_TYPE + COMMA_SEP +
                TournamentContract.TournamentEntry.COLUMN_NAME_TOURNAMENT_PLACE + TEXT_TYPE + COMMA_SEP +
                TournamentContract.TournamentEntry.COLUMN_NAME_TOURNAMENT_POINTS_FOR_WIN + TEXT_TYPE + COMMA_SEP +
                TournamentContract.TournamentEntry.COLUMN_NAME_TOURNAMENT_POINTS_FOR_REMIS + TEXT_TYPE  +
            ");\n" +
            "CREATE TABLE " +
                GroupContract.GroupEntry.TABLE_NAME + "( " +
                GroupContract.GroupEntry._ID + " INTEGER PRIMARY KEY," +
                GroupContract.GroupEntry.COLUMN_NAME_GROUP_NAME + TEXT_TYPE + COMMA_SEP +
                GroupContract.GroupEntry.COLUMN_NAME_TOURNAMENT_ID + INTEGER_TYPE + COMMA_SEP +
                "FOREIGN KEY(" + GroupContract.GroupEntry.COLUMN_NAME_TOURNAMENT_ID + ") References " +
                    TournamentContract.TournamentEntry.TABLE_NAME + "(" + TournamentContract.TournamentEntry._ID + ")" +
            ");\n" +
            "CREATE TABLE " +
                TeamContract.TeamEntry.TABLE_NAME + "( " +
                TeamContract.TeamEntry._ID + " INTEGER PRIMARY KEY," +
                TeamContract.TeamEntry.COLUMN_NAME_TEAM_NAME + TEXT_TYPE + COMMA_SEP +
                TeamContract.TeamEntry.COLUMN_NAME_TOURNAMENT_ID + INTEGER_TYPE + COMMA_SEP +
                TeamContract.TeamEntry.COLUMN_NAME_GROUP_ID + INTEGER_TYPE + COMMA_SEP +
                "FOREIGN KEY(" + TeamContract.TeamEntry.COLUMN_NAME_TOURNAMENT_ID + ") References " +
                    TournamentContract.TournamentEntry.TABLE_NAME + "(" + TournamentContract.TournamentEntry._ID + ")," +
                "FOREIGN KEY(" + TeamContract.TeamEntry.COLUMN_NAME_GROUP_ID + ") References " +
                    GroupContract.GroupEntry.TABLE_NAME + "(" + GroupContract.GroupEntry._ID + ")" +
            ");\n" +
            "CREATE TABLE " +
                GameContract.GameEntry.TABLE_NAME + "( " +
                GameContract.GameEntry._ID + " INTEGER PRIMARY KEY," +
                GameContract.GameEntry.COLUMN_NAME_DATE + TEXT_TYPE + COMMA_SEP +
                GameContract.GameEntry.COLUMN_NAME_FINISHED + TEXT_TYPE + COMMA_SEP +
                GameContract.GameEntry.COLUMN_NAME_SCORE_A + INTEGER_TYPE + COMMA_SEP +
                GameContract.GameEntry.COLUMN_NAME_SCORE_B + INTEGER_TYPE + COMMA_SEP +
                GameContract.GameEntry.COLUMN_NAME_TEAM_A_ID + INTEGER_TYPE + COMMA_SEP +
                GameContract.GameEntry.COLUMN_NAME_TEAM_B_ID + INTEGER_TYPE + COMMA_SEP +
                "FOREIGN KEY(" + GameContract.GameEntry.COLUMN_NAME_TEAM_A_ID + ") References " +
                    TeamContract.TeamEntry.TABLE_NAME + "(" + TeamContract.TeamEntry._ID + ")," +
                "FOREIGN KEY(" + GameContract.GameEntry.COLUMN_NAME_TEAM_B_ID + ") References " +
                    TeamContract.TeamEntry.TABLE_NAME + "(" + TeamContract.TeamEntry._ID + ")" +
            ");"
            ;

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TournamentContract.TournamentEntry.TABLE_NAME + ";" +
            "DROP TABLE IF EXISTS " + GroupContract.GroupEntry.TABLE_NAME + ";" +
            "DROP TABLE IF EXISTS " + TeamContract.TeamEntry.TABLE_NAME + ";" +
            "DROP TABLE IF EXISTS " + GameContract.GameEntry.TABLE_NAME + ";";

    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "turnierwart.db";

    public TournamentDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private boolean alreadyCreated = false;

    @Override
    public void onCreate(SQLiteDatabase db) {
        if(!alreadyCreated){
            db.execSQL(SQL_CREATE_ENTRIES);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void resetDatabase(){
        super.getWritableDatabase().execSQL(SQL_DELETE_ENTRIES);
    }

    public void saveTournament(Tournament t) {
        ContentValues valuesTournament = new ContentValues();
        valuesTournament.put(TournamentContract.TournamentEntry.COLUMN_NAME_TOURNAMENT_NAME, t.getName());
        valuesTournament.put(TournamentContract.TournamentEntry.COLUMN_NAME_TOURNAMENT_START, t.getStartAsString());
        valuesTournament.put(TournamentContract.TournamentEntry.COLUMN_NAME_TOURNAMENT_END, t.getEndAsString());
        valuesTournament.put(TournamentContract.TournamentEntry.COLUMN_NAME_TOURNAMENT_HOST, t.getHost());
        valuesTournament.put(TournamentContract.TournamentEntry.COLUMN_NAME_TOURNAMENT_PLACE, t.getPlace());
        valuesTournament.put(TournamentContract.TournamentEntry.COLUMN_NAME_TOURNAMENT_POINTS_FOR_WIN, t.getPointsForWin());
        valuesTournament.put(TournamentContract.TournamentEntry.COLUMN_NAME_TOURNAMENT_POINTS_FOR_REMIS, t.getPointsForRemis());
        long tournamentDbId = this.getWritableDatabase().insert(TournamentContract.TournamentEntry.TABLE_NAME, "", valuesTournament);
        t.setDbId(tournamentDbId);

    }

    public void saveGroups(Tournament t) {
        for (Group g : t.getGroups()) {
            ContentValues valuesGroup = new ContentValues();
            valuesGroup.put(GroupContract.GroupEntry.COLUMN_NAME_GROUP_NAME, g.getName());
            valuesGroup.put(GroupContract.GroupEntry.COLUMN_NAME_TOURNAMENT_ID, t.getDbId());
            long groupDbId = this.getWritableDatabase().insert(GroupContract.GroupEntry.TABLE_NAME, "", valuesGroup);
            g.setDbId(groupDbId);
        }
    }

    public void saveTeams(Tournament t){
        for(Team team : t.getTeams()){
            ContentValues valuesTeam = new ContentValues();
            valuesTeam.put(TeamContract.TeamEntry.COLUMN_NAME_TOURNAMENT_ID, t.getDbId());
            valuesTeam.put(TeamContract.TeamEntry.COLUMN_NAME_TEAM_NAME, team.getName());
            long teamDbId = this.getWritableDatabase().insert(TeamContract.TeamEntry.TABLE_NAME, "", valuesTeam);
            team.setDbId(teamDbId);
        }
    }

    public Tournament getTournamentWithId(long dbId) {
        String[] projection = {
                TournamentContract.TournamentEntry.COLUMN_NAME_TOURNAMENT_NAME,
                TournamentContract.TournamentEntry.COLUMN_NAME_TOURNAMENT_START,
                TournamentContract.TournamentEntry.COLUMN_NAME_TOURNAMENT_END,
                TournamentContract.TournamentEntry.COLUMN_NAME_TOURNAMENT_HOST,
                TournamentContract.TournamentEntry.COLUMN_NAME_TOURNAMENT_PLACE,
                TournamentContract.TournamentEntry.COLUMN_NAME_TOURNAMENT_POINTS_FOR_WIN,
                TournamentContract.TournamentEntry.COLUMN_NAME_TOURNAMENT_POINTS_FOR_REMIS
        };

        String[] selection = {
                TournamentContract.TournamentEntry._ID
        };

        String[] selectionArgs = {
                String.valueOf(dbId)
        };

        Cursor c = this.getReadableDatabase().query(
                TournamentContract.TournamentEntry.TABLE_NAME,
                projection,
                TournamentContract.TournamentEntry._ID + "=?",
                selectionArgs,
                null,
                null,
                null
        );
        c.moveToFirst();

        String name = c.getString(c.getColumnIndexOrThrow(TournamentContract.TournamentEntry.COLUMN_NAME_TOURNAMENT_NAME));
        String start = c.getString(c.getColumnIndexOrThrow(TournamentContract.TournamentEntry.COLUMN_NAME_TOURNAMENT_START));
        String end = c.getString(c.getColumnIndexOrThrow(TournamentContract.TournamentEntry.COLUMN_NAME_TOURNAMENT_END));
        String place = c.getString(c.getColumnIndexOrThrow(TournamentContract.TournamentEntry.COLUMN_NAME_TOURNAMENT_PLACE));
        String host = c.getString(c.getColumnIndexOrThrow(TournamentContract.TournamentEntry.COLUMN_NAME_TOURNAMENT_HOST));
        String pointsW = c.getString(c.getColumnIndexOrThrow(TournamentContract.TournamentEntry.COLUMN_NAME_TOURNAMENT_POINTS_FOR_WIN));
        String pointsR = c.getString(c.getColumnIndexOrThrow(TournamentContract.TournamentEntry.COLUMN_NAME_TOURNAMENT_POINTS_FOR_REMIS));

        Tournament t = new Tournament(name, start, end, place, host, pointsW, pointsR, "0", "");

        return t;
    }

    public void loadDataFromDb() {
        String[] projection = {
                TournamentContract.TournamentEntry.COLUMN_NAME_TOURNAMENT_NAME,
                TournamentContract.TournamentEntry.COLUMN_NAME_TOURNAMENT_START,
                TournamentContract.TournamentEntry.COLUMN_NAME_TOURNAMENT_END,
                TournamentContract.TournamentEntry.COLUMN_NAME_TOURNAMENT_HOST,
                TournamentContract.TournamentEntry.COLUMN_NAME_TOURNAMENT_PLACE,
                TournamentContract.TournamentEntry.COLUMN_NAME_TOURNAMENT_POINTS_FOR_WIN,
                TournamentContract.TournamentEntry.COLUMN_NAME_TOURNAMENT_POINTS_FOR_REMIS
        };

        Cursor c = this.getReadableDatabase().query(
                TournamentContract.TournamentEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        c.moveToFirst();
        while(!c.isAfterLast()){
            String id = c.getString(c.getColumnIndexOrThrow(TournamentContract.TournamentEntry._ID));
            String name = c.getString(c.getColumnIndexOrThrow(TournamentContract.TournamentEntry.COLUMN_NAME_TOURNAMENT_NAME));
            String start = c.getString(c.getColumnIndexOrThrow(TournamentContract.TournamentEntry.COLUMN_NAME_TOURNAMENT_START));
            String end = c.getString(c.getColumnIndexOrThrow(TournamentContract.TournamentEntry.COLUMN_NAME_TOURNAMENT_END));
            String place = c.getString(c.getColumnIndexOrThrow(TournamentContract.TournamentEntry.COLUMN_NAME_TOURNAMENT_PLACE));
            String host = c.getString(c.getColumnIndexOrThrow(TournamentContract.TournamentEntry.COLUMN_NAME_TOURNAMENT_HOST));
            String pointsW = c.getString(c.getColumnIndexOrThrow(TournamentContract.TournamentEntry.COLUMN_NAME_TOURNAMENT_POINTS_FOR_WIN));
            String pointsR = c.getString(c.getColumnIndexOrThrow(TournamentContract.TournamentEntry.COLUMN_NAME_TOURNAMENT_POINTS_FOR_REMIS));

            Tournament t = new Tournament(name, start, end, place, host, pointsW, pointsR);
            t.setDbId(Long.parseLong(id));

            String[] projgroup = {
                    GroupContract.GroupEntry._ID,
                    GroupContract.GroupEntry.COLUMN_NAME_GROUP_NAME
            };

            String[] selectionArgsGroup = {
                    id
            };

            Cursor cursorGroup = this.getReadableDatabase().query(
                    GroupContract.GroupEntry.TABLE_NAME,
                    projgroup,
                    GroupContract.GroupEntry.COLUMN_NAME_TOURNAMENT_ID + "=?",
                    selectionArgsGroup,
                    null,
                    null,
                    null
            );

            cursorGroup.moveToFirst();
            ArrayList<Group> groups = new ArrayList<>();
            HashMap<Long, Group> idToGroup = new HashMap<>();
            while(!cursorGroup.isAfterLast()) {
                String groupId = c.getString(c.getColumnIndexOrThrow(GroupContract.GroupEntry._ID));
                String groupName = c.getString(c.getColumnIndexOrThrow(GroupContract.GroupEntry.COLUMN_NAME_GROUP_NAME));

                Group gr = new Group(t, groupName);
                gr.setDbId(Long.parseLong(groupId));
                idToGroup.put(Long.parseLong(groupId), gr);
                cursorGroup.moveToNext();
            }
            t.addGroups(groups);

            String[] projteams = {
                    TeamContract.TeamEntry._ID,
                    TeamContract.TeamEntry.COLUMN_NAME_TEAM_NAME,
                    TeamContract.TeamEntry.COLUMN_NAME_GROUP_ID
            };

            String[] selectionArgsTeam = {
                    id
            };

            Cursor cursorTeam = this.getReadableDatabase().query(
                    TeamContract.TeamEntry.TABLE_NAME,
                    projteams,
                    TeamContract.TeamEntry.COLUMN_NAME_TOURNAMENT_ID + "=?",
                    selectionArgsTeam,
                    null,
                    null,
                    null
            );

            cursorTeam.moveToFirst();
            ArrayList<Team> teams = new ArrayList<>();
            while(!cursorTeam.isAfterLast()){
                String teamId = c.getString(c.getColumnIndexOrThrow(TeamContract.TeamEntry._ID));
                String teamName = c.getString(c.getColumnIndexOrThrow(TeamContract.TeamEntry.COLUMN_NAME_TEAM_NAME));
                String groupId = c.getString(c.getColumnIndexOrThrow(TeamContract.TeamEntry.COLUMN_NAME_GROUP_ID));

                Team team = new Team(teamName, t);
                teams.add(team);
                team.setGroup(idToGroup.get(Long.parseLong(groupId)));
                t.addTeam(team);
                cursorTeam.moveToNext();
            }

            c.moveToNext();
        }
    }
}

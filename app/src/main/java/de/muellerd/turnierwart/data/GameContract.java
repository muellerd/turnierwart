package de.muellerd.turnierwart.data;

import android.provider.BaseColumns;

public class GameContract {

    public GameContract(){}

    public static abstract class GameEntry implements BaseColumns {
        public static final String TABLE_NAME = "game";
        public static final String COLUMN_NAME_TEAM_A_ID = "teamAId";
        public static final String COLUMN_NAME_TEAM_B_ID = "teamBId";
        public static final String COLUMN_NAME_DATE = "gameDate";
        public static final String COLUMN_NAME_FINISHED = "finished";
        public static final String COLUMN_NAME_SCORE_A = "scoreA";
        public static final String COLUMN_NAME_SCORE_B = "scoreB";
    }
}

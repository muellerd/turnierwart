package de.muellerd.turnierwart.data;

import android.provider.BaseColumns;

public class TeamContract {

    public TeamContract(){}

    public static abstract class TeamEntry implements BaseColumns {
        public static final String TABLE_NAME = "team";
        public static final String COLUMN_NAME_TEAM_NAME = "teamName";
        public static final String COLUMN_NAME_TOURNAMENT_ID = "tournamentId";
        public static final String COLUMN_NAME_GROUP_ID = "groupId";
    }
}

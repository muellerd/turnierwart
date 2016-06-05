package de.muellerd.turnierwart.data;

import android.provider.BaseColumns;

public class GroupContract {
    public GroupContract(){}

    public static abstract class GroupEntry implements BaseColumns {
        public static final String TABLE_NAME = "groups";
        public static final String COLUMN_NAME_TOURNAMENT_ID = "tournamentId";
        public static final String COLUMN_NAME_GROUP_NAME = "groupName";
    }
}

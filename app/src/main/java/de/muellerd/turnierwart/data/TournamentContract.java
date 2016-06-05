package de.muellerd.turnierwart.data;

import android.provider.BaseColumns;

public final class TournamentContract {
    public TournamentContract(){   }

    public static abstract class TournamentEntry implements BaseColumns{
        public static final String TABLE_NAME = "tournament";
        public static final String COLUMN_NAME_TOURNAMENT_NAME = "tournamentName";
        public static final String COLUMN_NAME_TOURNAMENT_START = "tournamentStart";
        public static final String COLUMN_NAME_TOURNAMENT_END = "tournamentEnd";
        public static final String COLUMN_NAME_TOURNAMENT_PLACE = "tournamentPlace";
        public static final String COLUMN_NAME_TOURNAMENT_HOST = "tournamentHost";
        public static final String COLUMN_NAME_TOURNAMENT_POINTS_FOR_WIN = "tournamentPointsForWin";
        public static final String COLUMN_NAME_TOURNAMENT_POINTS_FOR_REMIS = "tournamentPointsForRemis";
    }
}

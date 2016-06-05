package de.muellerd.turnierwart;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import junit.framework.Assert;

import de.muellerd.turnierwart.data.Tournament;
import de.muellerd.turnierwart.data.TournamentDbHelper;

public class DatabaseTest extends AndroidTestCase {

    private static final String TEST_FILE_PREFIX = "test_";
    private TournamentDbHelper dbHelper;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), TEST_FILE_PREFIX);
        dbHelper = new TournamentDbHelper(context);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.close();
    }

    public void testDbAddTournament(){

        String tournamentTestName = "Test";

        Tournament t = new Tournament(tournamentTestName, "01.01.2016", "02.01.2016", "PB", "Daniel", "3", "1",
                "2", "T1\nT2\nT3\nT4\nT5\nT6\nT7\nT8\nT9\nT10");

        dbHelper.saveTournament(t);

        Tournament testT = dbHelper.getTournamentWithId(t.getDbId());

        Assert.assertTrue(tournamentTestName.equals(testT.getName()));
    }

}

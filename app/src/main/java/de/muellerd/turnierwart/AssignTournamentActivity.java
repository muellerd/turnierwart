package de.muellerd.turnierwart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import de.muellerd.turnierwart.classes.Team;
import de.muellerd.turnierwart.classes.Tournament;

/**
 * Created by daniel on 04.06.2016.
 */
public class AssignTournamentActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_tournament_assign);

        Intent intent = getIntent();
        Tournament tournament = (Tournament) intent.getSerializableExtra("tournament");

        String[] groups = tournament.getGroupNames();

        TextView title = (TextView) findViewById(R.id.assigned_teams);
        title.setText("Eingeteilte Mannschaften: " + tournament.getNumberAssignedTeams() + "/" + tournament.getNumberNotAssignedTeams());

        LinearLayout layout = (LinearLayout) findViewById(R.id.groupsView);

        ArrayList<Team> mannschaften = tournament.getNotAssignedTeams();

        for(int i = 0; i < groups.length; i++){
            TextView tv = new TextView(this);
            tv.setText(groups[i]);
            tv.setTextSize(30);
            layout.addView(tv);

        }
    }
}

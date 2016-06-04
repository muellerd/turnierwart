package de.muellerd.turnierwart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import de.muellerd.turnierwart.classes.Tournament;

/**
 * Created by daniel on 28.05.2016.
 */
public class TurnierDetailActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Tournament tournament = (Tournament) intent.getSerializableExtra("tournament");

        setContentView(R.layout.tournament_detail_activity);
        getSupportActionBar().setTitle(tournament.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*ArrayList<Team> teamsNotAssigned = tournament.getNotAssignedTeams();
        String notassigned = "";
        for(Team team: teamsNotAssigned){
            notassigned += team.getName() + "\n";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_tournament_assign, null))
                .setTitle("Test");
        AlertDialog dialog = builder.create();
        dialog.show();

        String[] groups = tournament.getGroupNames();

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.groupsView);

        for(int i = 0; i < groups.length; i++){
            TextView tv = new TextView(this);
            tv.setText(groups[i]);
            layout.addView(tv);
        }
        */
    }
}

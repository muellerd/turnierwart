package de.muellerd.turnierwart;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import de.muellerd.turnierwart.data.Group;
import de.muellerd.turnierwart.data.Tournament;

public class AssignTournamentActivity extends AppCompatActivity {

    private Tournament tournament;
    private int position;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_tournament_assign);

        Intent intent = getIntent();
        position = (int) intent.getSerializableExtra("tournamentPosition");
        tournament = MainActivity.sessionData.getTournament(position);

        ArrayList<Group> groups = tournament.getGroups();

        TextView title = (TextView) findViewById(R.id.assigned_teams);
        title.setText("Eingeteilte Mannschaften: " + tournament.getNumberAssignedTeams() + "/" + tournament.getNumberOfTeams());

        final TextView toAssign = (TextView) findViewById(R.id.team_name_to_assign);
        toAssign.setText(tournament.getNextAssignee().getName());
        toAssign.setTag(toAssign.getText());
        toAssign.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item(v.getTag().toString());

                ClipData dragData = new ClipData(v.getTag().toString(), new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN}, item);

                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(toAssign);
                v.startDrag(dragData, myShadow, v, 0);
                return true;
            }
        });

        LinearLayout layout = (LinearLayout) findViewById(R.id.groupsView);

        for(int i = 0; i < groups.size(); i++){
            TextView tv = new TextView(this);
            tv.setText(groups.get(i).getName());
            tv.setTextSize(30);
            tv.setOnDragListener(new MyDragListener(tournament, title, toAssign, this));
            layout.addView(tv);

        }
    }

    public void endOfAssignment(){
        Intent intent = new Intent(getApplicationContext(), TournamentDetailActivity.class);
        intent.putExtra("tournamentPosition", position);
        startActivity(intent);
    }
}

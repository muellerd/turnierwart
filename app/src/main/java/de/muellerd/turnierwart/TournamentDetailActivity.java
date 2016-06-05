package de.muellerd.turnierwart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

import de.muellerd.turnierwart.data.GroupTuple;
import de.muellerd.turnierwart.data.Tournament;

public class TournamentDetailActivity extends AppCompatActivity {

    private GroupAdapter groupAdapter;
    private ArrayList<GroupTuple> groups;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        int pos = (int) intent.getSerializableExtra("tournamentPosition");
        Tournament tournament = MainActivity.sessionData.getTournament(pos);

        setContentView(R.layout.tournament_detail_activity);
        getSupportActionBar().setTitle(tournament.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        groups = tournament.getGroupTuples();
        groupAdapter = new GroupAdapter(this, groups);

        final ListView listView = (ListView) findViewById(R.id.groupListView);
        listView.setAdapter(groupAdapter);

    }
}

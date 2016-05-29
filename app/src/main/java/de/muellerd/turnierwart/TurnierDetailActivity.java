package de.muellerd.turnierwart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;

import de.muellerd.turnierwart.classes.Mannschaft;
import de.muellerd.turnierwart.classes.Turnier;

/**
 * Created by daniel on 28.05.2016.
 */
public class TurnierDetailActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Turnier turnier = (Turnier) intent.getSerializableExtra("turnier");

        setContentView(R.layout.turnier_detail_activity);
        getSupportActionBar().setTitle(turnier.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayList<Mannschaft> teamsNotAssigned = turnier.getNotAssignedTeams();
        String notassigned = "";
        for(Mannschaft team: teamsNotAssigned){
            notassigned += team.getName() + "\n";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(notassigned)
                .setTitle("Test");

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

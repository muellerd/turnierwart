package de.muellerd.turnierwart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import de.muellerd.turnierwart.data.Tournament;

/**
 * Created by daniel on 26.05.2016.
 */
public class CreateTournamentActivity extends AppCompatActivity {

    private TournamentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_tournament);
        getSupportActionBar().setTitle("Neues Tournament");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapter = (TournamentAdapter) getIntent().getSerializableExtra("adapter");

        EditText nameEdit = (EditText) findViewById(R.id.edit_tournament_name);
        nameEdit.setText("Handball Turnier");
        EditText ortEdit = (EditText) findViewById(R.id.edit_tournament_ort);
        ortEdit.setText("Elsen");
        EditText ausrichterEdit = (EditText) findViewById(R.id.edit_tournament_ausrichter);
        ausrichterEdit.setText("Daniel");
        EditText startEdit = (EditText) findViewById(R.id.edit_tournament_start);
        startEdit.setText("05.07.2016");
        EditText endEdit = (EditText) findViewById(R.id.edit_tournament_ende);
        endEdit.setText("12.07.2016");
        EditText winEdit = (EditText) findViewById(R.id.edit_tournament_win);
        winEdit.setText("2");
        EditText remisEdit = (EditText) findViewById(R.id.edit_tournament_remis);
        remisEdit.setText("1");
        EditText groupsEdit = (EditText) findViewById(R.id.edit_tournament_groups);
        groupsEdit.setText("2");
        EditText teamsEdit = (EditText) findViewById(R.id.edit_tournament_teams);
        teamsEdit.setText("Elsen\nSchlangen\nNeuhaus\nUni Paderborn\nSande\nWewer\nLöhne");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        getMenuInflater().inflate(R.menu.menu_create_tournament, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save_tournament) {
            saveNewTurnier();
        }

        return super.onOptionsItemSelected(item);
    }

    public void saveNewTurnier(){
        EditText nameEdit = (EditText) findViewById(R.id.edit_tournament_name);
        EditText placeEdit = (EditText) findViewById(R.id.edit_tournament_ort);
        EditText hostEdit = (EditText) findViewById(R.id.edit_tournament_ausrichter);
        EditText startEdit = (EditText) findViewById(R.id.edit_tournament_start);
        EditText endEdit = (EditText) findViewById(R.id.edit_tournament_ende);
        EditText winEdit = (EditText) findViewById(R.id.edit_tournament_win);
        EditText remisEdit = (EditText) findViewById(R.id.edit_tournament_remis);
        EditText groupsEdit = (EditText) findViewById(R.id.edit_tournament_groups);
        EditText teamsEdit = (EditText) findViewById(R.id.edit_tournament_teams);


        Tournament tournament = new Tournament(nameEdit.getText().toString(), startEdit.getText().toString(), endEdit.getText().toString(),
                placeEdit.getText().toString(), hostEdit.getText().toString(), winEdit.getText().toString(),
                remisEdit.getText().toString(), groupsEdit.getText().toString(), teamsEdit.getText().toString());

        Intent result = new Intent();
        result.putExtra("tournament", tournament);
        setResult(Activity.RESULT_OK, result);
        finish();
    }

}

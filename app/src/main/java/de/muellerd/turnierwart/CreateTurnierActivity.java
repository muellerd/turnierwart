package de.muellerd.turnierwart;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import de.muellerd.turnierwart.classes.SessionData;
import de.muellerd.turnierwart.classes.Turnier;

/**
 * Created by daniel on 26.05.2016.
 */
public class CreateTurnierActivity extends AppCompatActivity {

    private TurnierAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_turnier);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        System.out.println(toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Turnierwart");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapter = (TurnierAdapter) getIntent().getSerializableExtra("adapter");

        EditText nameEdit = (EditText) findViewById(R.id.edit_turnier_name);
        nameEdit.setText("Handball Turnier");
        EditText ortEdit = (EditText) findViewById(R.id.edit_turnier_ort);
        ortEdit.setText("Elsen");
        EditText ausrichterEdit = (EditText) findViewById(R.id.edit_turnier_ausrichter);
        ausrichterEdit.setText("Daniel");
        EditText startEdit = (EditText) findViewById(R.id.edit_turnier_start);
        startEdit.setText("05.07.2016");
        EditText endEdit = (EditText) findViewById(R.id.edit_turnier_ende);
        endEdit.setText("12.07.2016");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void saveNewTurnier(View view){
        EditText nameEdit = (EditText) findViewById(R.id.edit_turnier_name);
        EditText ortEdit = (EditText) findViewById(R.id.edit_turnier_ort);
        EditText ausrichterEdit = (EditText) findViewById(R.id.edit_turnier_ausrichter);
        EditText startEdit = (EditText) findViewById(R.id.edit_turnier_start);
        EditText endEdit = (EditText) findViewById(R.id.edit_turnier_ende);
        Turnier turnier = new Turnier(nameEdit.getText().toString(), startEdit.getText().toString(), endEdit.getText().toString(),
                ortEdit.getText().toString(), ausrichterEdit.getText().toString());

        /*Snackbar.make(view, "Du hast ein neues Turnier erzeugt: " + nameEdit.getText()
                , Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();*/

        Intent result = new Intent();
        result.putExtra("turnier", turnier);
        setResult(Activity.RESULT_OK, result);
        finish();
    }

}

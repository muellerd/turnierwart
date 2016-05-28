package de.muellerd.turnierwart;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import de.muellerd.turnierwart.classes.SessionData;
import de.muellerd.turnierwart.classes.Turnier;

public class MainActivity extends AppCompatActivity{

    public static SessionData sessionData;

    private ArrayList<Turnier> turniers;
    private TurnierAdapter turnierAdapter;

    public static final int CREATE_NEW_TURNIER = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Turnierwart");

        sessionData = new SessionData();

        turniers = sessionData.getTurnierListe();
        turnierAdapter = new TurnierAdapter(this,turniers);

        final ListView listView = (ListView) findViewById(R.id.turnierListView);
        listView.setAdapter(turnierAdapter);
        listView.setEmptyView(findViewById(R.id.empty_list));
        registerForContextMenu(listView);

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

    public void openCreateTurnierActivity(View view){
        Intent intent = new Intent(this, CreateTurnierActivity.class);
        startActivityForResult(intent, CREATE_NEW_TURNIER);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == CREATE_NEW_TURNIER) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {

                Turnier newTurnier = (Turnier) data.getSerializableExtra("turnier");
                this.sessionData.addTurnier(newTurnier);

                Context context = getApplicationContext();
                CharSequence text = "Neues Turnier angelegt: " + newTurnier.getName();
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                this.turnierAdapter.notifyDataSetChanged();
                ListView listView = (ListView) findViewById(R.id.turnierListView);
                listView.invalidate();
            }
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.turnier_list_context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()){
            case R.id.context_delete:
                deleteTurnier(info.position);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void deleteTurnier(final int id_turnier) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.safety_delete_turnier_question)
                .setTitle(R.string.safety_delete_turnier_title);

        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
                sessionData.removeTurnier(id_turnier);
                turnierAdapter.notifyDataSetChanged();
                ListView listView = (ListView) findViewById(R.id.turnierListView);
                listView.invalidate();
            }
        });

        builder.setNegativeButton(R.string.abort, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

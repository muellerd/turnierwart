package de.muellerd.turnierwart;

import android.content.Context;
import android.graphics.Color;
import android.view.DragEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import de.muellerd.turnierwart.data.Team;
import de.muellerd.turnierwart.data.Tournament;

/**
 * Created by daniel on 04.06.2016.
 */
public class MyDragListener implements View.OnDragListener {

    private Tournament tournament;
    private String groupName;
    private TextView title;
    private TextView assignee;
    private AssignTournamentActivity activity;

    public MyDragListener(Tournament tourn, TextView tvTitle, TextView tvAssignee, AssignTournamentActivity act) {
        this.tournament = tourn;
        this.title = tvTitle;
        this.assignee = tvAssignee;
        this.activity = act;
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        switch (event.getAction()){
            case DragEvent.ACTION_DRAG_STARTED:
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                v.setBackgroundColor(Color.LTGRAY);
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                v.setBackgroundColor(Color.parseColor("#fff3f3f3"));
                break;
            case DragEvent.ACTION_DROP:
                v.setBackgroundColor(Color.parseColor("#fff3f3f3"));
                tournament.assignTeamToGroup(event.getClipData().getDescription().getLabel(), ((TextView) v).getText());
                title.setText("Eingeteilte Mannschaften: " + tournament.getNumberAssignedTeams() + "/" + tournament.getNumberOfTeams());
                Team assigne = tournament.getNextAssignee();
                if(assigne != null) {
                    assignee.setText(tournament.getNextAssignee().getName());
                    assignee.setTag(assignee.getText());
                }
                else{
                    Context context = v.getContext();
                    CharSequence text = "Du hast alle Teams eingeteilt";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    activity.endOfAssignment();
                }
                break;
            default:
                break;
        }
        return true;
    }
}

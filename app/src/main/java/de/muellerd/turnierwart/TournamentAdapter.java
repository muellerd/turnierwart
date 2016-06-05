package de.muellerd.turnierwart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import de.muellerd.turnierwart.data.Tournament;

public class TournamentAdapter extends BaseAdapter{

    private static ArrayList<Tournament> tournaments;
    private LayoutInflater mInflater;

    public TournamentAdapter(Context context, ArrayList<Tournament> ts){
        mInflater = LayoutInflater.from(context);
        tournaments = ts;
    }

    @Override
    public int getCount() {
        return tournaments.size();
    }

    @Override
    public Object getItem(int position) {
        return tournaments.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TournamentViewHolder tViewHolder;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.tournament_row_view,null);
            tViewHolder = new TournamentViewHolder();
            tViewHolder.txtName = (TextView) convertView.findViewById(R.id.tournament_name);
            tViewHolder.txtDescription = (TextView) convertView.findViewById(R.id.tournament_description);
            convertView.setTag(tViewHolder);
        }
        else{
            tViewHolder = (TournamentViewHolder) convertView.getTag();
        }

        tViewHolder.txtName.setText(tournaments.get(position).getName());
        tViewHolder.txtDescription.setText(tournaments.get(position).getDescription());

        return convertView;
    }

    static class TournamentViewHolder {
        TextView txtName;
        TextView txtDescription;
    }
}

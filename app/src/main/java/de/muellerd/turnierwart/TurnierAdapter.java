package de.muellerd.turnierwart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

import de.muellerd.turnierwart.classes.Turnier;

/**
 * Created by daniel on 26.05.2016.
 */
public class TurnierAdapter extends BaseAdapter{

    private static ArrayList<Turnier> turniers;
    private LayoutInflater mInflater;

    public TurnierAdapter(Context context, ArrayList<Turnier> ts){
        mInflater = LayoutInflater.from(context);
        turniers = ts;
    }

    @Override
    public int getCount() {
        return turniers.size();
    }

    @Override
    public Object getItem(int position) {
        return turniers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TurnierViewHolder tViewHolder;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.custom_row_view,null);
            tViewHolder = new TurnierViewHolder();
            tViewHolder.txtName = (TextView) convertView.findViewById(R.id.name);
            tViewHolder.txtDescription = (TextView) convertView.findViewById(R.id.description);
            convertView.setTag(tViewHolder);
        }
        else{
            tViewHolder = (TurnierViewHolder) convertView.getTag();
        }

        tViewHolder.txtName.setText(turniers.get(position).getName());
        tViewHolder.txtDescription.setText(turniers.get(position).getDescription());

        return convertView;
    }

    static class TurnierViewHolder{
        TextView txtName;
        TextView txtDescription;
    }
}

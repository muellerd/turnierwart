package de.muellerd.turnierwart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import de.muellerd.turnierwart.data.GroupTuple;
import de.muellerd.turnierwart.data.Tournament;

public class GroupAdapter extends BaseAdapter{

    private static ArrayList<GroupTuple> groupTuples;
    private LayoutInflater mInflater;

    public GroupAdapter(Context context, ArrayList<GroupTuple> gts){
        mInflater = LayoutInflater.from(context);
        groupTuples = gts;
    }

    @Override
    public int getCount() {
        return groupTuples.size();
    }

    @Override
    public Object getItem(int position) {
        return groupTuples.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GroupViewHolder tViewHolder;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.group_row_view,null);
            tViewHolder = new GroupViewHolder();
            tViewHolder.txtName = (TextView) convertView.findViewById(R.id.group_name);
            tViewHolder.txtDescription = (TextView) convertView.findViewById(R.id.group_description);
            convertView.setTag(tViewHolder);
        }
        else{
            tViewHolder = (GroupViewHolder) convertView.getTag();
        }

        tViewHolder.txtName.setText(groupTuples.get(position).getName());
        tViewHolder.txtDescription.setText(groupTuples.get(position).getDescription());

        return convertView;
    }

    static class GroupViewHolder {
        TextView txtName;
        TextView txtDescription;
    }
}

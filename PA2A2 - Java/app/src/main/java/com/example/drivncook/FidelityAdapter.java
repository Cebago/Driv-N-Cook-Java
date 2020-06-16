package com.example.drivncook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class FidelityAdapter extends BaseAdapter {

    private List<Fidelity> fidelityList;
    private Context context;

    public FidelityAdapter(Context context, List<Fidelity> fidelityList){
        this.context = context;
        this.fidelityList = fidelityList;
    }


    @Override
    public int getCount() {
        return fidelityList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.fidelityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       if (convertView == null){
           LayoutInflater inflater = LayoutInflater.from(this.context);
           convertView = inflater.inflate(R.layout.row,null);
       }
       TextView title = convertView.findViewById(R.id.title);
       TextView points = convertView.findViewById(R.id.points);
       TextView category = convertView.findViewById(R.id.category);

       Advantage advantage = (Advantage) getItem(position);

       title.setText(advantage.getAdvantageName());
       category.setText(advantage.getCategoryName());
       points.setText(advantage.getNbPoint());

       return convertView;
    }
}

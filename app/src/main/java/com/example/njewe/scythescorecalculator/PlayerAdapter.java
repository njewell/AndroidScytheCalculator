package com.example.njewe.scythescorecalculator;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PlayerAdapter extends BaseAdapter{

    private final Context mContext;
    private final ArrayList<Player> players;

    public PlayerAdapter(Context context, ArrayList<Player> players){
        this.mContext = context;
        this.players = players;
    }

    @Override
    public int getCount(){
        return players.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    // 4
    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView dummyTextView = new TextView(mContext);
        dummyTextView.setText(String.valueOf(position));
        return dummyTextView;
    }
}

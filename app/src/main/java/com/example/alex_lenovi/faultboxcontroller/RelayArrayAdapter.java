package com.example.alex_lenovi.faultboxcontroller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

/**
 * Created by alex-lenovi on 3/23/2016.
 */
public class RelayArrayAdapter extends ArrayAdapter<clsRelay> {

    final int INVALID_ID = -1;
    LayoutInflater layout_inflater;
    private Context context;

    HashMap<clsRelay, Integer> mIdMap = new HashMap<>();

    public RelayArrayAdapter(Context context, List<clsRelay> relays) {
        super(context, R.layout.relay_row_layout, relays);
        this.context = context;
        layout_inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for (int i = 0; i < relays.size(); i++) {
            mIdMap.put(relays.get(i), i);
        }
    }


    // пункт списка
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = convertView;
        if (view == null) {
            view = layout_inflater.inflate(R.layout.relay_row_layout, parent, false);
        }

        final clsRelay relay = getItem(position);
        ((TextView) view.findViewById(R.id.relay_title)).setText(relay.getRelayTitle());
        ((Button) view.findViewById(R.id.relay_on_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, relay.getONCommand(), Toast.LENGTH_SHORT).show();
            }
        });

        ((Button) view.findViewById(R.id.relay_off_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,relay.getOFFCommand(),Toast.LENGTH_SHORT).show();
            }
        });

        ((Button) view.findViewById(R.id.relay_flicker_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,relay.getFlickerCommand(),Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }


    @Override
    public long getItemId(int position) {
        if (position < 0 || position >= mIdMap.size()) {
            return INVALID_ID;
        }
        clsRelay relay = (clsRelay) getItem(position);
        return mIdMap.get(relay);
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

}

package com.example.alex_lenovi.faultboxcontroller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    ArrayList<clsRelay> relays = new ArrayList<>();
    ListView relays_listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clsConnection.getInstance().setContext(this);
        clsConnection.getInstance().connect();


        relays.add(new clsRelay("All Relays", "all"));
        relays.add(new clsRelay("RFIC MCLU VID 2 (1)", "0"));
        relays.add(new clsRelay("MCLU DVR VID 2 (2)", "1"));
        relays.add(new clsRelay("MAST UP CMD (3)", "2"));
        relays.add(new clsRelay("LAUNCHER ON/OFF CMD (4)", "3"));
        relays.add(new clsRelay("ARM EN (5)", "4"));
        relays.add(new clsRelay("MCLU PWR (6)", "5"));
        relays.add(new clsRelay("RFIC SW PWR (7)", "6"));
        relays.add(new clsRelay("ETH SW PWR (8)", "7"));

        RelayArrayAdapter relay_adapter = new RelayArrayAdapter(this,relays);

        relays_listview = (ListView) findViewById(R.id.relays_listview);
        relays_listview.setAdapter(relay_adapter);

    }
}

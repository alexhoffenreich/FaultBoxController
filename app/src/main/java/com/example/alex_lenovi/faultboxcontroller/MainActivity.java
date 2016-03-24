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

        relays.add(new clsRelay("All Relays", "all"));
        relays.add(new clsRelay("Relay 1", "0"));
        relays.add(new clsRelay("Relay 2", "1"));
        relays.add(new clsRelay("Relay 3", "2"));
        relays.add(new clsRelay("Relay 4", "3"));
        relays.add(new clsRelay("Relay 5", "4"));
        relays.add(new clsRelay("Relay 6", "5"));
        relays.add(new clsRelay("Relay 7", "6"));
        relays.add(new clsRelay("Relay 8", "7"));

        RelayArrayAdapter relay_adapter = new RelayArrayAdapter(this,relays);

        relays_listview = (ListView) findViewById(R.id.relays_listview);
        relays_listview.setAdapter(relay_adapter);

    }
}

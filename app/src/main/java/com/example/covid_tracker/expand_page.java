package com.example.covid_tracker;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class expand_page extends AppCompatActivity {
//https://medium.com/@hari19cs036/virtual-internship-program-experience-lgmvip21-at-letsgrowmore-ab4c71b749c2
    TextView notes, active, confirmed, migrated, deceased, recovered, d_confirmed, d_deceased, d_recovered, district;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand_page);
        notes = findViewById(R.id.notes_txt2);
        active = findViewById(R.id.active_txt2);
        confirmed = findViewById(R.id.confirmed_txt2);
        migrated = findViewById(R.id.migrated_txt2);
        deceased = findViewById(R.id.deceased_txt2);
        recovered = findViewById(R.id.recovered_txt2);
        d_confirmed = findViewById(R.id.d_confirmed_txt2);
        d_recovered = findViewById(R.id.d_recovered_txt2);
        d_deceased = findViewById(R.id.d_deceased_txt2);
        district = findViewById(R.id.expand_text);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        district.setText(b.getString("district"));
        notes.setText(b.getString("notes"));
        active.setText(b.getString("active"));
        migrated.setText(b.getString("migrated"));
        confirmed.setText(b.getString("confirmed"));
        deceased.setText(b.getString("deceased"));
        recovered.setText(b.getString("recovered"));
        d_confirmed.setText(b.getString("d_confirmed"));
        d_recovered.setText(b.getString("d_recovered"));
        d_deceased.setText(b.getString("d_deceased"));
    }
}
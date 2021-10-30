package com.example.covid_tracker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    EditText main_edit_text;
    ArrayList<whatever_name> list = new ArrayList<>();
    ArrayList<String> states = new ArrayList<>();


    private main_screen_adapter Madapter;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_edit_text = findViewById(R.id.main_edittext);

        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            JSONObject json = readJsonFromUrl("https://data.covid19india.org/state_district_wise.json");
            Iterator<String> iterator = json.keys();
            while(iterator.hasNext()) {
            {String currentKey = iterator.next();
            states.add(currentKey);}}

            for(int i=0;i<states.size();i++)
            {Iterator<String> iterator2 = json.getJSONObject(states.get(i)).getJSONObject("districtData").keys();
            while(iterator2.hasNext())
            {String d = iterator2.next();
//
//            {districts.add(d + " (" + states.get(i) + ")");
//            d = d + " (" + states.get(i) + ")";}

            if(!states.get(i).equals("State Unassigned"))
            {String notes = (json.getJSONObject(states.get(i)).getJSONObject("districtData").getJSONObject(d).get("notes").toString());
            String active = (json.getJSONObject(states.get(i)).getJSONObject("districtData").getJSONObject(d).get("active").toString());
            String confirmed = (json.getJSONObject(states.get(i)).getJSONObject("districtData").getJSONObject(d).get("confirmed").toString());
            String migrated = (json.getJSONObject(states.get(i)).getJSONObject("districtData").getJSONObject(d).get("migratedother").toString());
            String deceased = (json.getJSONObject(states.get(i)).getJSONObject("districtData").getJSONObject(d).get("deceased").toString());
            String recovered = (json.getJSONObject(states.get(i)).getJSONObject("districtData").getJSONObject(d).get("recovered").toString());
            String d_confirmed = (json.getJSONObject(states.get(i)).getJSONObject("districtData").getJSONObject(d).getJSONObject("delta").get("confirmed").toString());
            String d_recovered = (json.getJSONObject(states.get(i)).getJSONObject("districtData").getJSONObject(d).getJSONObject("delta").get("recovered").toString());
            String d_deceased = (json.getJSONObject(states.get(i)).getJSONObject("districtData").getJSONObject(d).getJSONObject("delta").get("deceased").toString());
            if(d.equals("Foreign Evacuees") || d.equals("Other State") || d.equals("Unknown") || d.equals("Unassigned"))
            {list.add(new whatever_name(d + " (" + states.get(i) + ")", notes, active, migrated, confirmed, deceased, recovered, d_confirmed, d_deceased, d_recovered));}
            else
            {list.add(new whatever_name(d, notes, active, migrated, confirmed, deceased, recovered, d_confirmed, d_deceased, d_recovered));}

            System.out.println(list.size() + " MainActivity_print");}}}
//            System.out.println(districts.size() + " " + notes.size() + " " + active.size() + " " + d_confirmed.size() + " " + migrated.size() + " " + d_deceased.size() + " " + d_recovered.size() + " size()");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        adapter();

        main_edit_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());

            }
        });

    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }
    
    void adapter()
    {LinearLayoutManager mainlayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
    RecyclerView main_recycler = findViewById(R.id.main_recycler_view);
    main_recycler.setLayoutManager(mainlayoutManager);
//    main_screen_adapter main_screen_adapter = new main_screen_adapter(MainActivity.this, districts, notes, active, confirmed, migrated, deceased, recovered, d_confirmed, d_recovered, d_deceased);
    Madapter = new main_screen_adapter(MainActivity.this, list);
    main_recycler.setAdapter(Madapter);}

    void filter(String text)
    {ArrayList<whatever_name> filteredList = new ArrayList<whatever_name>();
    for(whatever_name item : list)
    {
    if(item.getDistrict().toLowerCase().contains(text.toLowerCase()))
    {filteredList.add(item);
//    filteredList.add(new whatever_name(item.getDistrict(), item.getNotes(), item.getActive(), item.getMigrated(), item.getConfirmed(), item.getDeceased(), item.getRecovered(), item.getD_confirmed(), item.getD_deceased(), item.getD_recovered()));
    System.out.println(item + " item.getDistrict()");
    }

    }

        Madapter.filterList(filteredList);
    }

    @Override
    public void onBackPressed() {
        if(!main_edit_text.getText().toString().trim().equals(""))
        {main_edit_text.setText("");
        main_edit_text.clearFocus();}
        else
        {finish();}
    }
}


class whatever_name
{
private String district;
private String notes;
private String active;
private String migrated;
private String confirmed;
private String deceased;
private String recovered;
private String d_confirmed;
private String d_deceased;
private String d_recovered;

    public whatever_name(String name, String notes, String active, String migrated, String confirmed, String deceased, String recovered, String d_confirmed, String d_deceased, String d_recovered) {
        this.district = name;
        this.notes = notes;
        this.active = active;
        this.migrated = migrated;
        this.confirmed = confirmed;
        this.deceased = deceased;
        this.recovered = recovered;
        this.d_confirmed = d_confirmed;
        this.d_deceased = d_deceased;
        this.d_recovered = d_recovered;                
    }

    public String getDistrict()
    {return this.district;}
    
    public String getNotes()
    {return this.notes;}

    public String getActive() {
        return active;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public String getDeceased() {
        return deceased;
    }

    public String getMigrated() {
        return migrated;
    }

    public String getD_confirmed() {
        return d_confirmed;
    }

    public String getRecovered() {
        return recovered;
    }

    public String getD_deceased() {
        return d_deceased;
    }

    public String getD_recovered() {
        return d_recovered;
    }
}
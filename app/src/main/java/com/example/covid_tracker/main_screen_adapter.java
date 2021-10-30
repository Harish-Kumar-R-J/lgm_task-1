package com.example.covid_tracker;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class main_screen_adapter extends RecyclerView.Adapter<main_screen_adapter.ViewHolder> {

    ArrayList<whatever_name> list2 = new ArrayList<>();

    Context context;
    @NonNull
    @Override
    public main_screen_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_list_item, parent, false);
        return new ViewHolder(view);
    }

//    public main_screen_adapter(Context mContext, ArrayList<String> districts, ArrayList<String> notes, ArrayList<String> active, ArrayList<String> confirmed, ArrayList<String> migrated, ArrayList<String> deceased, ArrayList<String> recovered, ArrayList<String> d_confirmed, ArrayList<String> d_recovered, ArrayList<String> d_deceased) {
public main_screen_adapter(Context mContext, ArrayList<whatever_name> list1) {
        this.list2 = list1;
        this.context = mContext;}

    @Override
    public void onBindViewHolder(main_screen_adapter.ViewHolder holder, int position) {
        whatever_name obj = list2.get(position);
        holder.btn.setText(obj.getDistrict());
        System.out.println(list2.size() + " places_size");

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                whatever_name obj = list2.get(position);
                Intent intent = new Intent(context, expand_page.class);
                intent.putExtra("notes", obj.getNotes());
                intent.putExtra("active", obj.getActive());
                intent.putExtra("migrated", obj.getMigrated());
                intent.putExtra("confirmed", obj.getConfirmed());
                intent.putExtra("deceased", obj.getDeceased());
                intent.putExtra("recovered", obj.getRecovered());
                intent.putExtra("d_confirmed", obj.getD_confirmed());
                intent.putExtra("d_recovered", obj.getD_recovered());
                intent.putExtra("d_deceased", obj.getD_deceased());
                intent.putExtra("district", obj.getDistrict());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list2.size();
    }

    public void filterList(ArrayList<whatever_name> filteredlist)
    {System.out.println(filteredlist.size() + " filtered_list_size");
        list2 = filteredlist;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        Button btn;
        public ViewHolder(View itemView) {
            super(itemView);
            btn = itemView.findViewById(R.id.main_list_btn);
        }
    }



}

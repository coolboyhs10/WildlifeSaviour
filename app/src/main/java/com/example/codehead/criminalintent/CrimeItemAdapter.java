package com.example.codehead.criminalintent;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CrimeItemAdapter extends RecyclerView.Adapter<CrimeItemAdapter.CrimeViewHolder> {
    private Context context;
    private ArrayList<Crime> crimeItemList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int posistion);
    }

    public void setOnItemClickListener (OnItemClickListener listener) {
        this.listener = listener;
    }

    public CrimeItemAdapter(Context context, ArrayList<Crime> crimeArrayList) {
        this.context = context;
        this.crimeItemList = crimeArrayList;
    }

    @NonNull
    @Override
    public CrimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.crime_item_card, parent, false);
        return new CrimeViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull CrimeViewHolder holder, int position) {
        Crime crimeItem = crimeItemList.get(position);

        String crimeName = crimeItem.getCrimeName();
        String crimeArea = crimeItem.getCrimeArea();
        String crimeDate = crimeItem.getCrimeDate();
        String speciesName = crimeItem.getSpeciesName();
        String crimeType = crimeItem.getCrimeType();
        LatLng location = crimeItem.getLocation();

        holder.speciesName.setText("Species: " + speciesName);
        holder.crimeArea.setText("Area: "+ crimeArea);
        holder.crimeType.setText("Type: " + crimeType);
        holder.crimeName.setText(crimeName);
        holder.crimeDate.setText("Date of crime: " + crimeDate);

    }

    @Override
    public int getItemCount() {
        return crimeItemList.size();
    }

    public class CrimeViewHolder extends RecyclerView.ViewHolder {
        public TextView crimeName;
        public TextView crimeArea;
        public TextView crimeType;
        public TextView crimeDate;
        public TextView speciesName;

        public CrimeViewHolder(View itemView) {
            super(itemView);

            speciesName = itemView.findViewById(R.id.c_species_name);
            crimeName = itemView.findViewById(R.id.crime_name);
            crimeArea = itemView.findViewById(R.id.crime_area);
            crimeType = itemView.findViewById(R.id.crime_type);
            crimeDate = itemView.findViewById(R.id.crime_date);

            itemView.setOnClickListener(new View.OnClickListener () {

                @Override
                public void onClick(View view) {
                    if(listener != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}

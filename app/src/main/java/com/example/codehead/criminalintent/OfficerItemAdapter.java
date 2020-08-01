package com.example.codehead.criminalintent;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class OfficerItemAdapter extends RecyclerView.Adapter<OfficerItemAdapter.OfficerViewHolder> {
    private Context context;
    private ArrayList<officer> officerArrayList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int posistion);
    }

    public void setOnItemClickListener (OnItemClickListener listener) {
        this.listener = listener;
    }

    public OfficerItemAdapter(Context context, ArrayList<officer> officerArrayList) {
        this.context = context;
        this.officerArrayList = officerArrayList;
    }

    @NonNull
    @Override
    public OfficerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.officer_item_card, parent, false);
        return new OfficerViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull OfficerViewHolder holder, int position) {
        officer officer = officerArrayList.get(position);

        String name = officer.getName();
        String email = officer.getEmail_id();
        String phone = officer.getPhone();
        String area = officer.getArea_name();
        String rank = officer.getRank();
        String pin = officer.getPincode();


        holder.name.setText(name);
        holder.rank.setText("Rank: "+ rank);
        holder.email.setText("Email: " + email);
        holder.phone.setText("Phone: " + phone);
        holder.area.setText("Area: " + area);
        holder.pincode.setText("Pincode: " + pin);

    }

    @Override
    public int getItemCount() {
        return officerArrayList.size();
    }

    public class OfficerViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView area;
        public TextView rank;
        public TextView email;
        public TextView phone;
        public TextView pincode;

        public OfficerViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.of_name);
            area = itemView.findViewById(R.id.of_area);
            rank = itemView.findViewById(R.id.of_rank);
            email = itemView.findViewById(R.id.of_email);
            phone = itemView.findViewById(R.id.of_phone);
            pincode = itemView.findViewById(R.id.of_pin);

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

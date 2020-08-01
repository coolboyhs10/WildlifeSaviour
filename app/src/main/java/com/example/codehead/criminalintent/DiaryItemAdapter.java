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

public class DiaryItemAdapter extends RecyclerView.Adapter<DiaryItemAdapter.DiaryViewHolder> {
    private Context context;
    private ArrayList<DiaryItem> diaryItemArrayList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int posistion);
    }

    public void setOnItemClickListener (OnItemClickListener listener) {
        this.listener = listener;
    }

    public DiaryItemAdapter(Context context, ArrayList<DiaryItem> diaryItemArrayList) {
        this.context = context;
        this.diaryItemArrayList = diaryItemArrayList;
    }

    @NonNull
    @Override
    public DiaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.diary_item_card, parent, false);
        return new DiaryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DiaryViewHolder holder, int position) {
        DiaryItem diaryItem = diaryItemArrayList.get(position);

        String imageUrl = diaryItem.getImageUrl();
        String common_name = diaryItem.getCommonName();
        String areaName = diaryItem.getAreaName();
        String date = diaryItem.getDate();

        holder.commonName.setText(common_name);
        holder.areaName.setText(areaName);
        holder.date.setText(date);

        Picasso.with(context).load(imageUrl).fit().centerInside().into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return diaryItemArrayList.size();
    }

    public class DiaryViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView commonName;
        public TextView areaName;
        public TextView date;

        public DiaryViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.d_species_img);
            commonName = itemView.findViewById(R.id.common_name);
            areaName = itemView.findViewById(R.id.area_name);
            date = itemView.findViewById(R.id.date);

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

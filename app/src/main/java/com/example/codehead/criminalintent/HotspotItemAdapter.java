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

public class HotspotItemAdapter extends RecyclerView.Adapter<HotspotItemAdapter.HotspotViewHolder> {
    private Context context;
    private ArrayList<HotspotItem> hotspotItemList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int posistion);
    }

    public void setOnItemClickListener (OnItemClickListener listener) {
        this.listener = listener;
    }

    public HotspotItemAdapter(Context context, ArrayList<HotspotItem> hotspotItemList) {
        this.context = context;
        this.hotspotItemList = hotspotItemList;
    }

    @NonNull
    @Override
    public HotspotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.hotspot_item_card, parent, false);
        return new HotspotViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HotspotViewHolder holder, int position) {
        HotspotItem hotspotItem = hotspotItemList.get(position);

        String imageUrl = hotspotItem.getImageUrl();
        String speciesName = hotspotItem.getSpeciesName();
        LatLng location = hotspotItem.getLocation();

        Picasso.with(context).load(imageUrl).fit().centerInside().into(holder.imageView);
        holder.speciesName.setText(speciesName);

    }

    @Override
    public int getItemCount() {
        return hotspotItemList.size();
    }

    public class HotspotViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView speciesName;

        public HotspotViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.species_img);
            speciesName = itemView.findViewById(R.id.species_name);

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

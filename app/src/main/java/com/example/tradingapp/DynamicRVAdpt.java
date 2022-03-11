package com.example.tradingapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;


public class DynamicRVAdpt extends RecyclerView.Adapter<DynamicRVAdpt.DynamicRVHolder> {
    ArrayList<DynamicRVModel> dynamicRVModels;

    OnItemClickListener listener;


    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnIconClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    public DynamicRVAdpt(ArrayList<DynamicRVModel> dynamicRVModels){
        this.dynamicRVModels = dynamicRVModels;
    }

    public class DynamicRVHolder extends RecyclerView.ViewHolder {
        TextView playername, rating, rarity,country, team;
        ImageView img;
        ConstraintLayout constraintLayout;
        public DynamicRVHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            playername = itemView.findViewById(R.id.playername);
            rating = itemView.findViewById(R.id.rating);
            rarity = itemView.findViewById(R.id.rarity);
            country = itemView.findViewById(R.id.country);
            team = itemView.findViewById(R.id.team);
            img = itemView.findViewById(R.id.photo);
            constraintLayout = itemView.findViewById(R.id.cll2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener!=null){
                        int position  = getAbsoluteAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }


    @NonNull
    @Override
    public DynamicRVAdpt.DynamicRVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dynamic_rv_card_layout,parent,false);
        DynamicRVHolder dynamicRVHolder = new DynamicRVHolder(view,listener);
        return dynamicRVHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DynamicRVAdpt.DynamicRVHolder holder, int position) {
        DynamicRVModel currentItem = dynamicRVModels.get(position);
        holder.playername.setText(currentItem.getName());
        holder.rating.setText(currentItem.getRating());
        holder.rarity.setText(currentItem.getRarity());
        holder.country.setText(currentItem.getCountry());
        holder.team.setText(currentItem.getTeam());
        String url = currentItem.getUrl();
        ImageView a = holder.img;
        Picasso.get().load(url).into(a);
    }

    @Override
    public int getItemCount() {
        return dynamicRVModels.size();
    }


}
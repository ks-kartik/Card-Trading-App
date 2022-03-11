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


public class DynamicRVAdptTransactions extends RecyclerView.Adapter<DynamicRVAdptTransactions.DynamicRVHolder> {
    ArrayList<DynamicRVModelTransactions> dynamicRVModels;
    OnItemClickListener listener;


    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnIconClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    public DynamicRVAdptTransactions(ArrayList<DynamicRVModelTransactions> dynamicRVModels){
        this.dynamicRVModels = dynamicRVModels;
    }

    public class DynamicRVHolder extends RecyclerView.ViewHolder {
        TextView id, no, upiI, date, amount;
        ConstraintLayout constraintLayout;
        public DynamicRVHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            no = itemView.findViewById(R.id.no);
            upiI = itemView.findViewById(R.id.upi);
            date = itemView.findViewById(R.id.date);
            amount = itemView.findViewById(R.id.amt);
            constraintLayout = itemView.findViewById(R.id.cll222);
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
    public DynamicRVAdptTransactions.DynamicRVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_layout,parent,false);
        DynamicRVHolder dynamicRVHolder = new DynamicRVHolder(view,listener);
        return dynamicRVHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DynamicRVAdptTransactions.DynamicRVHolder holder, int position) {
        DynamicRVModelTransactions currentItem = dynamicRVModels.get(position);
        holder.id.setText(currentItem.getId());
        holder.upiI.setText(currentItem.getUpi());
        holder.no.setText(currentItem.getNo());
        holder.date.setText(currentItem.getDate());
        holder.amount.setText(currentItem.getAmt());
    }

    @Override
    public int getItemCount() {
        return dynamicRVModels.size();
    }


}
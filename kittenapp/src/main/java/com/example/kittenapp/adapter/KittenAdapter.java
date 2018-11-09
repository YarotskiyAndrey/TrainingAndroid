package com.example.kittenapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kittenapp.R;
import com.example.kittenapp.model.Kitten;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class KittenAdapter extends RecyclerView.Adapter<KittenAdapter.KittenViewHolder> {

    private List<Kitten> kittenList = new ArrayList<>();

    public KittenAdapter(List<Kitten> kittenList) {
        this.kittenList = kittenList;
    }

    @NonNull
    @Override
    public KittenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_kitten, parent, false);
        return new KittenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KittenViewHolder holder, int position) {
        String imageUrl = kittenList.get(position).getImageUrl();
        Picasso.get().load(imageUrl).into(holder.kittenImageView);

        holder.nameTextView.setText(kittenList.get(position).getName());
        holder.numberTextView.setText(String.valueOf(kittenList.get(position).getNumber()));
    }

    @Override
    public int getItemCount() {
        return kittenList.size();
    }

    class KittenViewHolder extends RecyclerView.ViewHolder {
        private ImageView kittenImageView;
        private TextView numberTextView;
        private TextView nameTextView;


        public KittenViewHolder(@NonNull View itemView) {
            super(itemView);

            kittenImageView = itemView.findViewById(R.id.kitten_image);
            numberTextView = itemView.findViewById(R.id.kitten_number);
            nameTextView = itemView.findViewById(R.id.kitten_name);
        }
    }
}

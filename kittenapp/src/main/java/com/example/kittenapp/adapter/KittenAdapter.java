package com.example.kittenapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kittenapp.R;

import org.w3c.dom.Text;

public class KittenAdapter extends RecyclerView.Adapter<KittenAdapter.KittenViewHolder> {

    class KittenViewHolder extends RecyclerView.ViewHolder {
        private ImageView kittenImageView;
        private TextView numberTextView;
        private TextView nameTextView;


        public KittenViewHolder(@NonNull View itemView) {
            super(itemView);
            kittenImageView = itemView.findViewById(R.id.kitten_image_view);
            numberTextView = itemView.findViewById(R.id.number_text_view);
            nameTextView = itemView.findViewById(R.id.name_text_view);
        }


    }

//    @NonNull
//    @Override
//    public KittenAdapter.KittenViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        return null;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull KittenAdapter.KittenViewHolder kittenViewHolder, int i) {
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
}

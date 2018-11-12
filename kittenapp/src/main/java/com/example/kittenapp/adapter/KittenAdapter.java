package com.example.kittenapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.TypefaceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kittenapp.R;
import com.example.kittenapp.model.Kitten;
import com.squareup.picasso.Picasso;

import java.util.List;

public class KittenAdapter extends RecyclerView.Adapter<KittenAdapter.KittenViewHolder> {

    private List<Kitten> kittenList;

    private ItemClickListener clickListener;

    public KittenAdapter(List<Kitten> kittenList) {
        this.kittenList = kittenList;
    }


    public void addKittens(List<Kitten> newKittens) {
        kittenList.addAll(newKittens);
        this.notifyDataSetChanged();
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

        String kittenName = kittenList.get(position).getName();
        SpannableStringBuilder multiFontKittenName = new SpannableStringBuilder(kittenName);
        multiFontKittenName.setSpan(new TypefaceSpan("roboto-regular"), 0, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.nameTextView.setText(multiFontKittenName);

        holder.numberTextView.setText(String.valueOf(kittenList.get(position).getNumber()));
    }

    @Override
    public int getItemCount() {
        return kittenList.size();
    }

    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public Kitten getKitten(int position) {
        return kittenList.get(position);
    }

    class KittenViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView kittenImageView;
        private TextView numberTextView;
        private TextView nameTextView;


        public KittenViewHolder(@NonNull View itemView) {
            super(itemView);

            kittenImageView = itemView.findViewById(R.id.kitten_image);
            numberTextView = itemView.findViewById(R.id.kitten_number);
            nameTextView = itemView.findViewById(R.id.kitten_name);
            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) {
                clickListener.onClick(view, getAdapterPosition());
            }
        }
    }
}

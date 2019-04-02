package com.example.shopon_4users.Vendor_product_collection;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shopon_4users.R;

import java.util.ArrayList;

public class Adapter_view extends RecyclerView.Adapter<Adapter_view.Holder> {
    Context context;
    private ArrayList<firebase_data_inflator> object;

    public Adapter_view(Context c, ArrayList<firebase_data_inflator> Object) {
        context = c;
        object = Object;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.vendor_product_collection_design, viewGroup, false);
        return new Holder(v, context, object);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder myViewHolder, int i) {


        myViewHolder.brand.setText(object.get(i).getBrand());

        myViewHolder.price.setText(object.get(i).getPrice());
        myViewHolder.description.setText(object.get(i).description);
      //  myViewHolder.model.setText(object.get(i).model);

        Glide.with(context)
                .load(object.get(i).getImagurl())
                .into(myViewHolder.pro_image);
    }

    @Override
    public int getItemCount() {
        //Toast.makeText(context, "Size is"+object.size(), Toast.LENGTH_SHORT).show();
        return object.size();
    }

    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name, brand,price,description,model;
        ImageView pro_image;
        RelativeLayout parentLayout;
        Context context;
        private ArrayList<firebase_data_inflator> object;

        Holder(@NonNull View itemView, Context context, ArrayList<firebase_data_inflator> object) {
            super(itemView);
            this.object = object;
            this.context = context;
            itemView.setOnClickListener(this);
            brand = itemView.findViewById(R.id.brand);
            pro_image= itemView.findViewById(R.id.pro_image);
            price=itemView.findViewById(R.id.price);
            //model=itemView.findViewById(R.id.model);
            description=itemView.findViewById(R.id.description);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }


        @Override
        public void onClick(View view) {
             int position = getAdapterPosition();
             firebase_data_inflator record = this.object.get(position);
             String brandNM = record.getBrand();
            String priceNM = record.getPrice();
            String imageNM=record.getImagurl();
            String Vendor_uid=record.getUid();
             //Toast.makeText(context, "Brand is  " + data, Toast.LENGTH_SHORT).show();
             Intent i = new Intent(this.context, Product_Summary.class);
            i.putExtra("vendor_uid", Vendor_uid);
              i.putExtra("desc", brandNM);
             i.putExtra("price", priceNM);
             i.putExtra("image", imageNM );
                context.startActivity(i);

        }
    }
}



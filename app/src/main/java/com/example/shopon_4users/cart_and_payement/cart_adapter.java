package com.example.shopon_4users.cart_and_payement;

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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.shopon_4users.R;
import com.example.shopon_4users.Vendor_product_collection.Product_Summary;

import java.util.ArrayList;

public class cart_adapter extends RecyclerView.Adapter<cart_adapter.MyHolder> {
    Context context;
    private ArrayList<inflatorOfCart> object;


    public cart_adapter(Context applicationContext, ArrayList<inflatorOfCart> arrayList) {
        context = applicationContext;
        object = arrayList;
    }
    

    @NonNull
    @Override
    public cart_adapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.cart_view_design, viewGroup, false);
        return new cart_adapter.MyHolder(v, context, object);
    }

    @Override
    public void onBindViewHolder(@NonNull cart_adapter.MyHolder myViewHolder, int i) {


        myViewHolder.brand.setText(object.get(i).getBrand());

        myViewHolder.price.setText(object.get(i).getPrice());
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

    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name, brand,price,description,modelz,Sum;
        ImageView pro_image;
        RelativeLayout cartparentLayout;
        Context context;
        private ArrayList<inflatorOfCart> object;

        MyHolder(@NonNull View itemView, Context context, ArrayList<inflatorOfCart> object) {
            super(itemView);
            this.object = object;
            this.context = context;
            itemView.setOnClickListener(this);
            brand = itemView.findViewById(R.id.pro_name_cart);
            pro_image= itemView.findViewById(R.id.pro_image_cart);

            price=itemView.findViewById(R.id.pro_price_cart);
            //model=itemView.findViewById(R.id.model);
            cartparentLayout = itemView.findViewById(R.id.cartparent_layout);
        }


        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            inflatorOfCart record = this.object.get(position);
            String brandNM = record.getBrand();
            String priceNM = record.getPrice();
            String imageNM=record.getImagurl();
            String VendorUID=record.getVendor_uid();
            //Toast.makeText(context, "Brand is  " + data, Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this.context, cart_product_summary.class);
            i.putExtra("desc", brandNM);
            i.putExtra("price", priceNM);

            i.putExtra("image", imageNM );
            i.putExtra("vendor_uid",VendorUID);

            context.startActivity(i);

        }
    }

}

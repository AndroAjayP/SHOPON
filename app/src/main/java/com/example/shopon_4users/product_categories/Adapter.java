package com.example.shopon_4users.product_categories;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopon_4users.R;
import com.example.shopon_4users.Vendor_product_collection.product_collection;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter< Adapter.MyViewHolder> {
    Context context;
    String value;
    String uid;
    private ArrayList<inflator> object;

    public Adapter(Context c, ArrayList<inflator> Object)
    {
        context=c;
        object=Object;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(context).inflate(R.layout.product_categories_view_design,viewGroup,false);
        return new MyViewHolder(v,context,object);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {

        // Toast.makeText(context, "value of i is "+i, Toast.LENGTH_SHORT).show();
        myViewHolder.pro_name.setText(object.get(i).getPro());
        uid=object.get(i).getUID();
    }
    @Override
    public int getItemCount()
    {
        //Toast.makeText(context, "Size is"+object.size(), Toast.LENGTH_SHORT).show();
        return object.size();
    }
    class  MyViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener
    {
        TextView pro_name;
        RelativeLayout parentLayout;
        Context context;
        private ArrayList<inflator>  object;
        MyViewHolder(@NonNull View itemView,Context context,ArrayList<inflator>  object) {
            super(itemView);
            this.object=object;
            this.context=context;
            itemView.setOnClickListener(this);
            pro_name=itemView.findViewById(R.id.pro_name);
            parentLayout=itemView.findViewById(R.id.parent_layout);
        }

        @Override
        public void onClick(View view) {
            int position=getAdapterPosition();
            inflator record=this.object.get(position);
            String data=record.getPro();
            String UID=record.getUID();

            Intent i=new Intent(this.context, product_collection.class);
            if(data.equals("Clothing"))
            {
                i.putExtra("uid",UID);
                i.putExtra("data","Clothing");
                this.context.startActivity(i);
            }
            if(data.equals("CellPhones"))
            {
                i.putExtra("uid",UID);
                i.putExtra("data","CellPhones");
                this.context.startActivity(i);
            }
            if(data.equals("Electronics"))
            {
                i.putExtra("uid",UID);
                i.putExtra("data","Electronics");
                this.context.startActivity(i);
            }
            if(data.equals("Shoes"))
            {
                i.putExtra("uid",UID);
                i.putExtra("data","Shoes");
                this.context.startActivity(i);
            }
            if(data.equals("Books"))
            {
                i.putExtra("uid",UID);
                i.putExtra("data","Books");
                this.context.startActivity(i);
            }
            if(data.equals("Handmade"))
            {
                i.putExtra("uid",UID);
                i.putExtra("data","Handmade");
                this.context.startActivity(i);
            }
            if(data.equals("Health"))
            {
                i.putExtra("uid",UID);
                i.putExtra("data","Health");
                this.context.startActivity(i);
            }
            if(data.equals("Luggage"))
            {
                i.putExtra("uid",UID);
                i.putExtra("data","Luggage");
                this.context.startActivity(i);
            }
            if(data.equals("Music"))
            {
                i.putExtra("uid",UID);
                i.putExtra("data","Music");
                this.context.startActivity(i);
            }
            if(data.equals("MusicalInstrument"))
            {
                i.putExtra("uid",UID);
                i.putExtra("data","MusicalInstrument");
                this.context.startActivity(i);
            }
            if(data.equals("Sports"))
            {
                i.putExtra("uid",UID);
                i.putExtra("data","Sports");
                this.context.startActivity(i);
            }
            if(data.equals("Toys"))
            {
                i.putExtra("uid",UID);
                i.putExtra("data","Toys");
                this.context.startActivity(i);
            }



        }
    }


}

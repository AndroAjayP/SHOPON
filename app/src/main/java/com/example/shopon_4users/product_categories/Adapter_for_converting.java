package com.example.shopon_4users.product_categories;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.shopon_4users.R;

import java.util.ArrayList;

public class Adapter_for_converting extends RecyclerView.Adapter<Adapter_for_converting.MyViewHolder>{
    Context context;
    private ArrayList<cartItem_inflator>  object;
   // String UID;


    public  Adapter_for_converting(Context c,ArrayList<cartItem_inflator> Object)
    {
         context=c;
         object=Object;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(context).inflate(R.layout.malls_view_design,viewGroup,false);
        return new MyViewHolder(v,context,object);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

       // Toast.makeText(context, "value of i is "+i, Toast.LENGTH_SHORT).show();

        myViewHolder.Address.setText(object.get(i).getAddress());
        myViewHolder.Shop_name.setText(object.get(i).getShop_name());
          //  UID=object.get(i).getUID(); //geting a UID

        Glide.with(context)
                .load(object.get(i).getimage())
                .into(myViewHolder.Pic);

     /*  myViewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();
           //    Toast.makeText(context, ""+UID, Toast.LENGTH_SHORT).show();
           }
       });*/
    }

    @Override
    public int getItemCount()
    {
        //Toast.makeText(context, "Size is"+object.size(), Toast.LENGTH_SHORT).show();
        return object.size();
    }

    class  MyViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener
    {
        TextView Shop_name,Address;
        ImageView Pic;
        ConstraintLayout parentLayout;
        Context context;
        private ArrayList<cartItem_inflator>  object;

        MyViewHolder(@NonNull View itemView,Context context,ArrayList<cartItem_inflator>  object) {
            super(itemView);
            this.object=object;
            this.context=context;
            itemView.setOnClickListener(this);
            Shop_name=itemView.findViewById(R.id.Shop_NaMe);
            Address=itemView.findViewById(R.id.Address);
            Pic=itemView.findViewById(R.id.Shop_image);
            parentLayout=itemView.findViewById(R.id.parent_layout);
        }

        @Override
        public void onClick(View view) {
            int position=getAdapterPosition();
            cartItem_inflator record=this.object.get(position);
            String data=record.getUID();

            Intent i=new Intent(this.context, Choose_product_categories.class);
            i.putExtra("UID",data);
              context.startActivity(i);


        }
    }

}

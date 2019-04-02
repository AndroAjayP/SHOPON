package com.example.shopon_4users.product_categories;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.shopon_4users.R;
import com.example.shopon_4users.Vendor_product_collection.Product_Summary;
import com.example.shopon_4users.Vendor_product_collection.product_collection;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    DatabaseReference mDatabaseRef;
    private EditText mSearchField;
    private ImageButton mSearchBtn;
    RecyclerView recyclerView;
    ArrayList<cartItem_inflator> arrayList;
    Adapter_for_converting adapter_for_converting;
    ProgressBar loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);
        mDatabaseRef= FirebaseDatabase.getInstance().getReference().child("Shop_Detail");

        recyclerView=findViewById(R.id.Malls_list);
        mSearchField =  findViewById(R.id.search_field);
        mSearchBtn =  findViewById(R.id.search_btn);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loading=findViewById(R.id.loading);


        arrayList= new ArrayList<cartItem_inflator>();
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    cartItem_inflator obj=dataSnapshot1.getValue(cartItem_inflator.class);
                    arrayList.add(obj);
                }
                adapter_for_converting=new Adapter_for_converting(getApplicationContext(),arrayList);
                recyclerView.setAdapter(adapter_for_converting);
                loading.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Home.this, "oops,Daya Kuch To Ganban hai", Toast.LENGTH_SHORT).show();
            }
        });
        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String searchText = mSearchField.getText().toString();

                firebaseUserSearch(searchText);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mSearchBtn.performClick();
    }

    private void firebaseUserSearch(String searchText) {

        Query firebaseSearchQuery = mDatabaseRef.orderByChild("Shop_name").startAt(searchText).endAt(searchText + "\uf8ff");

        FirebaseRecyclerAdapter<cartItem_inflator, UsersViewHolder> FirebaseRecyclerAdapter = new FirebaseRecyclerAdapter<cartItem_inflator, UsersViewHolder>(

                cartItem_inflator.class,
                R.layout.malls_view_design,
                UsersViewHolder.class,
                firebaseSearchQuery

        ) {

            @Override
            protected void populateViewHolder(UsersViewHolder viewHolder, final cartItem_inflator model, int position) {
                viewHolder.setDetails(getApplicationContext(), model.getShop_name(), model.getAddress(), model.getimage());
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String uid =model.getUID();
                        Intent i = new Intent(Home.this, Choose_product_categories.class);
                        i.putExtra("UID",uid);
                        Home.this.startActivity(i);
                    }
                });
            }
        };

        recyclerView.setAdapter(FirebaseRecyclerAdapter);


    }
    public static class UsersViewHolder extends RecyclerView.ViewHolder {
        public View mView;

        public UsersViewHolder(View itemView) {
            super(itemView);

            mView = itemView;

        }

        public void setDetails(Context ctx, String Shop_NaM, String Address, String Shop_image){

            TextView user_name =  mView.findViewById(R.id.Shop_NaMe);
            TextView user_status =  mView.findViewById(R.id.Address);
            ImageView user_image =  mView.findViewById(R.id.Shop_image);


            user_name.setText(Shop_NaM);
            user_status.setText(Address);

            Glide.with(ctx).load(Shop_image).into(user_image);


        }


    }



}

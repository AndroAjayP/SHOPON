package com.example.shopon_4users.product_categories;

import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.shopon_4users.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Choose_product_categories extends AppCompatActivity {
    DatabaseReference mDatabaseRef;
    RecyclerView recyclerView;
    ArrayList<inflator> arrayList;
    Adapter adapter_for_converting;
    AppBarLayout ab;
    ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // requestWindowFeature(Window.FEATURE_NO_TITLE);
        //  setContentView(R.layout.activity_home);
        setContentView(R.layout.activity_choose_product_categories);

        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        recyclerView=findViewById(R.id.Malls_list);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Product_catalog").child(getIntent().getStringExtra("UID"));
        loading=findViewById(R.id.loading);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        arrayList = new ArrayList<inflator>();
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    inflator obj = dataSnapshot1.getValue(inflator.class);
                    arrayList.add(obj);
                }
                adapter_for_converting = new Adapter(getApplicationContext(), arrayList);
                recyclerView.setAdapter(adapter_for_converting);
                loading.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Choose_product_categories.this, "oops,Daya Kuch To Ganban hai", Toast.LENGTH_SHORT).show();
            }
        });


    }
}

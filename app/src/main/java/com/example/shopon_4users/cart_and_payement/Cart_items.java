package com.example.shopon_4users.cart_and_payement;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopon_4users.R;
import com.example.shopon_4users.product_categories.cartItem_inflator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

public class Cart_items extends AppCompatActivity {
    DatabaseReference mDatabaseRef;
    RecyclerView recyclerView;
    ArrayList<inflatorOfCart> arrayList;
    cart_adapter cart_adapter;
    TextView priceAmmount;
    ProgressBar loading;
    int Sum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_items);

        mDatabaseRef= FirebaseDatabase.getInstance().getReference().child("cart_items").child(FirebaseAuth.getInstance().getUid());

        recyclerView=findViewById(R.id.Cart_Malls_list);
        priceAmmount=findViewById(R.id.price_ammount);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loading=findViewById(R.id.loading);


        arrayList= new ArrayList<inflatorOfCart>();
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    inflatorOfCart obj=dataSnapshot1.getValue(inflatorOfCart.class);
                    arrayList.add(obj);
                }
                cart_adapter =new cart_adapter(getApplicationContext(),arrayList);
                recyclerView.setAdapter(cart_adapter);
                loading.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Cart_items.this, "oops,Daya Kuch To Ganban hai", Toast.LENGTH_SHORT).show();
            }
        });
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    Map<String,Object> map=(Map<String,Object>)ds.getValue();
                    if(map!=null)
                    {
                        Object price=map.get("Price");
                        if(price!=null)
                        { int pValue=Integer.parseInt(String.valueOf(price));
                            Sum = Sum + pValue;
                        }
                    }




                }
                String SumString = NumberFormat.getNumberInstance(Locale.UK).format(Sum);
                 priceAmmount.setText(SumString);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

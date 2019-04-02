package com.example.shopon_4users.cart_and_payement;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopon_4users.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.Locale;

public class Delivery extends AppCompatActivity {
    String name_url="https://shoponv-9af6d.firebaseio.com/user_login/".concat(FirebaseAuth.getInstance().getCurrentUser().getUid())+"/"+"Full_Name";
    String Adress_url="https://shoponv-9af6d.firebaseio.com/user_login/".concat(FirebaseAuth.getInstance().getCurrentUser().getUid())+"/"+"Address";
    String mobile_no_of_user="https://shoponv-9af6d.firebaseio.com/user_login/".concat(FirebaseAuth.getInstance().getCurrentUser().getUid())+"/"+"Mobile_no";
    String Address,mobile;
    String Full_name;
    Firebase name=new Firebase(name_url);
    Firebase address=new Firebase(Adress_url);
    Firebase mobile_number=new Firebase(mobile_no_of_user);

    TextView Name_of_user,Address_of_user,mobile_no,desc_of_product,Simple_price,dAmmount,ammount_pay,price_ammount;
    Button continue_for_nextPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        Name_of_user=findViewById(R.id.Name_of_user);
        Address_of_user=findViewById(R.id.Address_of_user);
        mobile_no=findViewById(R.id.mobile_no);
        desc_of_product=findViewById(R.id.desc_of_pro);
        Simple_price=findViewById(R.id.simple_price);
        dAmmount=findViewById(R.id.d_price);
        ammount_pay=findViewById(R.id.Ammount_pay);
        continue_for_nextPage=findViewById(R.id.continue_for_nextPage);
        price_ammount=findViewById(R.id.price_ammount);
        //String price_value = NumberFormat.getNumberInstance(Locale.UK).format(Integer.parseInt(getIntent().getStringExtra("price")));
       // mDatabaseRef= FirebaseDatabase.getInstance().getReference().child("user_login").child(FirebaseAuth.getInstance().getUid()).child("Full_Name");
        name.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                 Full_name = (String) dataSnapshot.getValue();
                Name_of_user.setText(Full_name);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        address.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Address = (String) dataSnapshot.getValue();
                Address_of_user.setText(Address);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        mobile_number.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mobile = (String) dataSnapshot.getValue();
                mobile_no.setText(mobile);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        desc_of_product.setText(getIntent().getStringExtra("description"));
        Simple_price.setText(getIntent().getStringExtra("price"));
        dAmmount.setText(getIntent().getStringExtra("price"));
        ammount_pay.setText(getIntent().getStringExtra("price"));
        price_ammount.setText(getIntent().getStringExtra("price"));
        continue_for_nextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Delivery.this,Final_payement_process.class);
                  i.putExtra("price",getIntent().getStringExtra("price"));
                  i.putExtra("description",getIntent().getStringExtra("description"));
                  i.putExtra("Vendor_id",getIntent().getStringExtra("Vendor_id"));
                  i.putExtra("Address",Address);
                  i.putExtra("Phon",mobile);
                  i.putExtra("name",Full_name);
                  i.putExtra("image",getIntent().getStringExtra("image"));
                  startActivity(i);
            }
        });


    }
}

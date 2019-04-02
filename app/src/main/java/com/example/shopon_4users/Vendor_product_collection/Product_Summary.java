package com.example.shopon_4users.Vendor_product_collection;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.shopon_4users.R;
import com.example.shopon_4users.cart_and_payement.Cart_items;
import com.example.shopon_4users.cart_and_payement.Delivery;
import com.example.shopon_4users.cart_and_payement.cart_product_summary;
import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;


public class Product_Summary extends AppCompatActivity  {
    ImageView image,back;
    TextView desc,price;
    Button addTocart,bye_now;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_product__summary);
        image=findViewById(R.id.summary_product_image);
        price=findViewById(R.id.summary_product_price);
        desc=findViewById(R.id.summary_product_description);
        back=findViewById(R.id.back_btn);
        bye_now=findViewById(R.id.bye_now);
        addTocart=findViewById(R.id.addTocart);

        Glide.with(Product_Summary.this)
                .load(getIntent().getStringExtra("image"))
                .into(image);
        desc.setText(getIntent().getStringExtra("desc"));
        price.setText(getIntent().getStringExtra("price"));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        addTocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storing_to_firebase();
            }
        });

        bye_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Product_Summary.this, "Bye now button is clicked", Toast.LENGTH_SHORT).show();
               // startActivity(new Intent(Product_Summary.this, Delivery.class));

                Intent i=new Intent(Product_Summary.this,Delivery.class);
                  i.putExtra("description",getIntent().getStringExtra("desc"));
                  i.putExtra("price",getIntent().getStringExtra("price"));
                  i.putExtra("Vendor_id",getIntent().getStringExtra("vendor_uid"));
                  i.putExtra("image",getIntent().getStringExtra("image"));
                  startActivity(i);


            }
        });


       }
    public void storing_to_firebase() {
        Firebase.setAndroidContext(this);

        Firebase database;
        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();

        database = new Firebase("https://shoponv-9af6d.firebaseio.com/");
        Firebase cart= database.child("cart_items");
        Firebase VendorLoginDeatail = cart.child(currentuser);
        String key = VendorLoginDeatail.push().getKey();
        Firebase pro= VendorLoginDeatail.child(key);

        pro.child("brand").setValue(getIntent().getStringExtra("desc"));

         pro.child("Vendor_uid").setValue(getIntent().getStringExtra("vendor_uid"));

        pro.child("imagurl").setValue(getIntent().getStringExtra("image"));

        pro.child("Price").setValue(getIntent().getStringExtra("price"));

        pro.child("Sum").setValue("0");

        pro.child("User_uid").setValue(currentuser);
        Toast.makeText(this, "Product is Added to cart..", Toast.LENGTH_SHORT).show();
        //startActivity(new Intent(Product_Summary.this, Cart_items.class));

       }

}





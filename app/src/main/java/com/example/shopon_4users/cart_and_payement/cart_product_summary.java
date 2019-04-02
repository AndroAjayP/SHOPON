package com.example.shopon_4users.cart_and_payement;

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
import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;

public class cart_product_summary extends AppCompatActivity {
    ImageView image,back;
    TextView desc,price;
    Button bye_now;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
       setContentView(R.layout.activity_cart_product_summary);
        image=findViewById(R.id.summary_product_image);
        price=findViewById(R.id.summary_product_price);
        desc=findViewById(R.id.summary_product_description);
        back=findViewById(R.id.back_btn);
        bye_now=findViewById(R.id.bye_now);

        Glide.with(cart_product_summary.this)
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
        bye_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(cart_product_summary.this,Delivery.class);
                i.putExtra("price",getIntent().getStringExtra("price"));
                i.putExtra("description",getIntent().getStringExtra("desc"));
                i.putExtra("Vendor_id",getIntent().getStringExtra("vendor_uid"));
                i.putExtra("image",getIntent().getStringExtra("image"));
                startActivity(i);
            }
        });



       }
    }

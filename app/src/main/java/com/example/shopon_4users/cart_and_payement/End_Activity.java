package com.example.shopon_4users.cart_and_payement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.shopon_4users.R;
import com.example.shopon_4users.product_categories.Home;

public class End_Activity extends AppCompatActivity {
    TextView order_id;
    Button home,exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_);
        order_id=findViewById(R.id.orderID);
        home=findViewById(R.id.home);
        exit=findViewById(R.id.Exit);
        order_id.setText(getIntent().getStringExtra("orderKey"));
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(End_Activity.this, Home.class));

            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
}

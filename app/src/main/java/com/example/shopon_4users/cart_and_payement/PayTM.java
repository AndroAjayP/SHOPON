package com.example.shopon_4users.cart_and_payement;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.shopon_4users.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PayTM extends AppCompatActivity {

    TextView no;
    static ImageView qr;
    String  qrurl,paytm_nourl;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_tm);

        Firebase.setAndroidContext(this);

        String qr_code_url="https://shoponv-9af6d.firebaseio.com/PayTm_Details/".concat(getIntent().getStringExtra("V_UID"))+"/"+"QR";
        String paytm_no_strings_url="https://shoponv-9af6d.firebaseio.com/PayTm_Details/".concat(getIntent().getStringExtra("V_UID"))+"/"+"No";

        Firebase qr_code=new Firebase(qr_code_url);
        Firebase paytm_no=new Firebase(paytm_no_strings_url);


        qr=findViewById(R.id.qr);
        no=findViewById(R.id.no);

        qr_code.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                qrurl = (String) dataSnapshot.getValue();
                Glide.with(getApplicationContext())
                        .load( qrurl)
                        .into(qr);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        paytm_no.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                paytm_nourl = (String) dataSnapshot.getValue();
                no.setText(paytm_nourl);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PayTM.this, "image is clicked", Toast.LENGTH_SHORT).show();
            }
        });




    }

}

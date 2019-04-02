package com.example.shopon_4users.cart_and_payement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopon_4users.R;
import com.example.shopon_4users.Vendor_product_collection.Product_Summary;
import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;

public class Final_payement_process extends AppCompatActivity {
    TextView final_price;
    String price,Description_of_goods,image,vendorId,Address,mobile,name;
    RadioButton cod,payTM;
    Button payement;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_payement_process);



        cod=findViewById(R.id.cod);
        payement=findViewById(R.id.payement);

        price=getIntent().getStringExtra("price");
        Description_of_goods=getIntent().getStringExtra("description");
        image=getIntent().getStringExtra("image");
        payTM=findViewById(R.id.payTM);
        vendorId=getIntent().getStringExtra("Vendor_id");
        Address=getIntent().getStringExtra("Address");
        mobile=getIntent().getStringExtra("Phon");
        name=getIntent().getStringExtra("name");
        final_price=findViewById(R.id.price_ammount);
        final_price.setText(price);
        payement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cod.isChecked())
                {
                    StoringPayementDetailToFirebase();
                }

                if(payTM.isChecked())
                {
                    Intent i=new Intent(Final_payement_process.this,PayTM.class);
                    i.putExtra("V_UID",vendorId);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(Final_payement_process.this, "Choose Any..", Toast.LENGTH_SHORT).show();
                }
            }


        });

    }
    private void StoringPayementDetailToFirebase() {
            Firebase.setAndroidContext(this);

            Firebase database;
            String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();

            database = new Firebase("https://shoponv-9af6d.firebaseio.com/");
            Firebase cart= database.child("orders");
            Firebase user_uid = cart.child(vendorId);
             key = user_uid.push().getKey();

            Firebase pro= user_uid.child(key);

            pro.child("Product_descripyion").setValue(Description_of_goods);

            pro.child("Vendor_uid").setValue(vendorId);

            pro.child("Order_id").setValue(key);

            pro.child("imagurl").setValue(image);

            pro.child("Address").setValue(Address);

            pro.child("Mobile").setValue(mobile);

            pro.child("Price").setValue(price);

            pro.child("name").setValue(name);

            pro.child("Sum").setValue("0");

            pro.child("User_uid").setValue(currentuser);

            Toast.makeText(this, "Your Order is Successful", Toast.LENGTH_SHORT).show();
            Intent i=new Intent(Final_payement_process.this, End_Activity.class);
            i.putExtra("orderKey",key);
            startActivity(i);

    }
}

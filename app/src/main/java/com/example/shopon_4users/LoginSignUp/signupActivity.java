package com.example.shopon_4users.LoginSignUp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopon_4users.cart_and_payement.Gallery_image_click;
import com.example.shopon_4users.R;
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import io.paperdb.Paper;

public class signupActivity extends AppCompatActivity {

    Button sign;
    FirebaseAuth firebaseAuth;
    StorageReference mStorageRef;
    TextView login;
    EditText email, c_pass, password, Full_name, add,mobile_no;
    ProgressDialog mprogress;
    String Extracted_email = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Firebase.setAndroidContext(this);

        firebaseAuth = FirebaseAuth.getInstance();
        mprogress=new ProgressDialog(this);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        email = findViewById(R.id.email);
        Paper.init(signupActivity.this);

        password = findViewById(R.id.password);
        c_pass=findViewById(R.id.c_pass);
        Full_name = findViewById(R.id.Full_name);
        add = findViewById(R.id.add);
        login=findViewById(R.id.login);
        mobile_no=findViewById(R.id.mobile_no);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(signupActivity.this,MainActivity.class));
            }
        });
        sign = findViewById(R.id.sign);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!c_pass.getText().toString().equals(password.getText().toString())||email.getText().toString().length() < 1 || password.getText().toString().length() < 1 || Full_name.getText().toString().length() < 1 || add.getText().toString().length() < 1) {

                    if (Full_name.getText().toString().length() < 1) {
                        Full_name.setError("Full Name is Empty");
                    }
                    if(mobile_no.getText().toString().length()<1)
                    {
                        mobile_no.setError("Mobile number is Empty");
                    }

                    if (add.getText().toString().length() < 1) {
                        add.setError("Address is Empty");
                    }
                    if(!c_pass.getText().toString().equals(password.getText().toString()))
                    {
                        c_pass.setError("password not matched");
                    }

                    if (email.getText().toString().length() < 1) {
                        email.setError("Email is empty");
                    }
                    if (password.getText().toString().length() < 1) {
                        password.setError("password is empty");
                    }
                } else {

                    final ProgressDialog progressDialog = ProgressDialog.show(signupActivity.this, "Please wait...", "Processing...", true);
                    (firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()))
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressDialog.dismiss();

                                    if (task.isSuccessful()) {


                                        Toast.makeText(signupActivity.this, "Registration successful", Toast.LENGTH_LONG).show();
                                        Intent i = new Intent(signupActivity.this, Gallery_image_click.class);
                                        storing_to_firebase();
                                        i.putExtra("Email_id", Extracted_email);
                                        startActivity(i);
                                    } else {
                                        Log.e("ERROR", task.getException().toString());
                                        Toast.makeText(signupActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        Paper.book().write(userClass.emailkey, email.getText().toString());
                        Paper.book().write(userClass.passkey,c_pass.getText().toString());
                }
            }

        });
    }




    public void storing_to_firebase() {
        Firebase.setAndroidContext(this);

        Firebase database;

        database = new Firebase("https://shoponv-9af6d.firebaseio.com/user_login");
        Firebase user = database.child(FirebaseAuth.getInstance().getCurrentUser().getUid());

         Firebase Mobile_no=user.child("Mobile_no");
         Mobile_no.setValue(mobile_no.getText().toString());

        Firebase email_id = user.child("Email");
        email_id.setValue(this.email.getText().toString());

        Firebase Shop_name = user.child("Full_Name");
        Shop_name.setValue(Full_name.getText().toString());

        Firebase passwordword = user.child("password");
        passwordword.setValue(password.getText().toString());

        Firebase Address = user.child("Address");
        Address.setValue(add.getText().toString());


    }
}

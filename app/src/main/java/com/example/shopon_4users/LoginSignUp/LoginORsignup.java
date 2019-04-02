package com.example.shopon_4users.LoginSignUp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.shopon_4users.R;
import com.example.shopon_4users.product_categories.Home;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import io.paperdb.Paper;

public class LoginORsignup extends AppCompatActivity {
    Button login,signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_orsignup);

        login=findViewById(R.id.login);
        signUp=findViewById(R.id.sign);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginORsignup.this,MainActivity.class));
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginORsignup.this,signupActivity.class));

            }
        });
    }

    private void Allowacces(String emailKey, String passKey) {
        final ProgressDialog progressDialog = ProgressDialog.show(LoginORsignup.this, "Already Logged in...", "Please wait...", true);

        final String Extracted_email = "";
        final FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();

        (firebaseAuth.signInWithEmailAndPassword(emailKey, passKey))
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if (task.isSuccessful()) {
                            Toast.makeText(LoginORsignup.this, "Login successful", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(LoginORsignup.this, Home.class);
                            i.putExtra("Email", firebaseAuth.getCurrentUser().getEmail());
                            i.putExtra("Email_id", Extracted_email);
                            startActivity(i);
                        } else {
                            Log.e("ERROR", task.getException().toString());
                            Toast.makeText(LoginORsignup.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }
}

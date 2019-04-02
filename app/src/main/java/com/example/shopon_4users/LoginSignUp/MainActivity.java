package com.example.shopon_4users.LoginSignUp;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopon_4users.product_categories.Home;
import com.example.shopon_4users.R;
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import io.paperdb.Paper;


public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView,sign_up;
    EditText email, pass;
    Button  sign_in;
    int count = 0;
    String Extracted_email = "";
    CheckBox keeploged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
          Paper.init(MainActivity.this);
        String emailkey = Paper.book().read(userClass.emailkey);
        String passkey = Paper.book().read(userClass.passkey);
        if (!(TextUtils.isEmpty(emailkey) && TextUtils.isEmpty(passkey))) {
            Allowacces(emailkey, passkey);
        }
        //Toast.makeText(this, "categories data is"+getIntent().getStringExtra("CATEGORIES IS"), Toast.LENGTH_SHORT).show();

        Firebase.setAndroidContext(this);
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
        sign_up = findViewById(R.id.sign_up);
        email = findViewById(R.id.Email_id);
        keeploged = findViewById(R.id.kepploged);
        Paper.init(MainActivity.this);
        pass = findViewById(R.id.password);
        sign_in = findViewById(R.id.sign_in);

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, signupActivity.class));
            }
        });
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (email.getText().toString().length() < 1) {
                    email.setError("Email Filled is empty");
                }
                if (pass.getText().toString().length() < 1) {
                    pass.setError("Password Filled is empty");
                }
                if (pass.getText().toString().length() < 1 && email.getText().toString().length() < 1) {
                    pass.setError("Email Filled is empty");
                    email.setError("Password Filled is Empty");
                } else {
                    authentication();
                }

            }

            private void authentication() {
                final FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();

                for (int i = 0; i <= email.getText().toString().length() && email.getText().toString().charAt(i) != '@'; i++) {
                    Extracted_email = Extracted_email + email.getText().toString().charAt(i);

                }
                final ProgressDialog progressDialog = ProgressDialog.show(MainActivity.this, "Please wait...", "Proccessing...", true);

                (firebaseAuth.signInWithEmailAndPassword(email.getText().toString(), pass.getText().toString()))
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();

                                if (task.isSuccessful()) {
                                    Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(MainActivity.this, Home.class);
                                    i.putExtra("Email", firebaseAuth.getCurrentUser().getEmail());
                                    i.putExtra("Email_id", Extracted_email);
                                    startActivity(i);
                                } else {
                                    Log.e("ERROR", task.getException().toString());
                                    Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();

                                }
                            }
                        });
                if (keeploged.isChecked()) {
                    Paper.book().write(userClass.emailkey, email.getText().toString());
                    Paper.book().write(userClass.passkey, pass.getText().toString());
                }
            }
        });

    }

    private void Allowacces(String emailKey, String passKey) {
        final ProgressDialog progressDialog = ProgressDialog.show(MainActivity.this, "Already Logged in...", "Please wait...", true);

        final String Extracted_email = "";
        final FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();

        (firebaseAuth.signInWithEmailAndPassword(emailKey, passKey))
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(MainActivity.this, Home.class);
                            i.putExtra("Email", firebaseAuth.getCurrentUser().getEmail());
                            i.putExtra("Email_id", Extracted_email);
                            startActivity(i);
                        } else {
                            Log.e("ERROR", task.getException().toString());
                            Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }
}

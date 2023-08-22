package com.example.firebase_prac;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login_page extends AppCompatActivity {
    TextInputEditText editTextEmail,editTextPassword;
    Button buttonReg;
    FirebaseAuth mAuth;
//    ProgressDialog pd=new ProgressDialog(Login_page.this);
    TextView txt;
    @SuppressLint("MissingInflatedId")
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent i= new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
            finish();
            //check
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        editTextEmail=findViewById(R.id.Email);
        editTextPassword=findViewById(R.id.Password);
        buttonReg=findViewById(R.id.btn_login);
        mAuth=FirebaseAuth.getInstance();
        txt=findViewById(R.id.Register);

        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),Register.class);
                startActivity(i);


            }
        });
        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,password;
                email= String.valueOf(editTextEmail.getText());
                password= String.valueOf(editTextPassword.getText());
//                showProgressDia("Registering","Feching the data");

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Login_page.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Login_page.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Login_page.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Login_page.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                    Intent i= new Intent(getApplicationContext(),MainActivity.class);
                                    startActivity(i);
                                    finish();

                                } else {

                                    Toast.makeText(Login_page.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });

    }
//    void showProgressDia(String Title,String Message){
//        pd.setMax(100);
//        pd.setMessage(Message);
//        pd.setTitle(Title);
//        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//        pd.show();
//    }
}
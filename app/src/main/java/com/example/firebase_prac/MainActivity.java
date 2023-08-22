package com.example.firebase_prac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    TextView txt;
    FirebaseUser user;
    FirebaseAuth mAuth;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth=FirebaseAuth.getInstance();
        button=findViewById(R.id.log_out);
        txt=findViewById(R.id.user);
        user=mAuth.getCurrentUser();
        if (user==null){
            Intent i= new Intent(getApplicationContext(),Login_page.class);
            startActivity(i);
            finish();
        }
        else {
            txt.setText(user.getEmail());
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirebaseAuth.getInstance().signOut();
                    Intent i= new Intent(getApplicationContext(),Login_page.class);
                     startActivity(i);
                    finish();
                //text listener method and
                }
            });
        }
    }
}
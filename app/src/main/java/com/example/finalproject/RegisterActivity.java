package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {
    EditText etName,etEmail;
    Button btnSignUp;
    String name,email;
    Intent outIntent;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName=findViewById(R.id.etName);
        etEmail=findViewById(R.id.etEmail);
        btnSignUp=findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=etName.getText().toString();
                email=etEmail.getText().toString();

                preferences=getSharedPreferences("userInfo",MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences.edit();

                editor.putString("name",name);
                editor.putString("email",email);
                editor.commit();

                outIntent=new Intent(RegisterActivity.this, HomeActivity.class);
                startActivity(outIntent);
            }
        });
    }
}
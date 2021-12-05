package com.dtech.lklearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    TextView signup_redirect;
    Button login;
    EditText mobile;
    String mob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signupoptions);
        signup_redirect=findViewById(R.id.signup_redirect_id);
        login=findViewById(R.id.login_button_id);
        mobile = findViewById(R.id.mobile_number);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mob = mobile.getText().toString();
                Intent intent = new Intent(Login.this, otp.class);
                intent.putExtra("mobileno", mob);
                startActivity(intent);
            }
        });

        //on click on signup redirect btton
        signup_redirect.setOnClickListener(v->{
            startActivity(new Intent(Login.this,signup_activity.class));
        });
    }
}
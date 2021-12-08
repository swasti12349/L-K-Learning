package com.dtech.lklearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class DifferentOptions extends AppCompatActivity {

    private Button puc,batchelor_degree,master_degree ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_different_options);

        puc=findViewById(R.id.puc_but_differentoptions_id);
        batchelor_degree=findViewById(R.id.batchelordegrree_but_differentoptions_id);
        master_degree=findViewById(R.id.masterdegree_but_differentoptions_id);

        puc.setOnClickListener(v->{
            startActivity(new Intent(DifferentOptions.this,SignUpOptions.class));

        });
        batchelor_degree.setOnClickListener(v->{
            startActivity(new Intent(DifferentOptions.this,SignUpOptions.class));

        });
        master_degree.setOnClickListener(v->{
            startActivity(new Intent(DifferentOptions.this,SignUpOptions.class));
        });


    }
}
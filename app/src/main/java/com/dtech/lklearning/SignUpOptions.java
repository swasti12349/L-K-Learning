package com.dtech.lklearning;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SignUpOptions extends AppCompatActivity {
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    AutoCompleteTextView autoCompleteTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signupoptions);
        arrayList = new ArrayList<>();
        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        arrayList.add("Hello");
        arrayList.add("Hello");
        arrayList.add("Hello");
        arrayList.add("Hello");
        arrayList.add("Hello");

        arrayAdapter = new ArrayAdapter<>(this, R.layout.singlelist, arrayList);
        autoCompleteTextView.setAdapter(arrayAdapter);


    }
}

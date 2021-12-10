package com.dtech.lklearning;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AvailableMaterial extends AppCompatActivity {
    ArrayList<String> university, branch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_material);
        university = new ArrayList<>();
        branch = new ArrayList<>();

        getUniversity();
//        getBranch();
        // hello

    }

    private void getUniversity() {


        String univURL = "http://94.130.8.49:3000/universities/getUniversities";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, univURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        university.add(object.getString("university_name"));
                        Log.d("lokp", university.toString());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(AvailableMaterial.this, "Error", Toast.LENGTH_SHORT).show();
                    Log.d("errors", e.toString());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }


    private void getBranch() {


        String URL = "http://94.130.8.49:3000/branches/getBranches";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        branch.add(object.getString("branch_name"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(AvailableMaterial.this, "Error", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }

}
package com.dtech.lklearning;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AvailableMaterial extends AppCompatActivity {

    AutoCompleteTextView University , Course , Branch , Semester;
    String[] semesterarray=new String[]{"1","2","3","4","5","6","7","8"};
    ArrayList<String> universityarray =new ArrayList<String>();
    ArrayList<String> coursearray =new ArrayList<String>();
    ArrayList<String> brancharray =new ArrayList<String>();
    RequestQueue rq;
    String url = "http://94.130.8.49:3000/materials/getAcademicMaterials";
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_material);

        University=(AutoCompleteTextView) findViewById(R.id.us);
        Course=findViewById(R.id.us3);
        Branch=findViewById(R.id.us2);
        Semester=(AutoCompleteTextView) findViewById(R.id.us5);

        ArrayAdapter<String> semesteradapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,semesterarray);
        ArrayAdapter<String> universityadapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,universityarray);
        ArrayAdapter<String> courseadapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,coursearray);
        ArrayAdapter<String> branchadapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,brancharray);

        Semester.setAdapter(semesteradapter);
        University.setAdapter(universityadapter);
        Course.setAdapter(courseadapter);
        Branch.setAdapter(branchadapter);


        Semester.setThreshold(0);

        /*rq= Volley.newRequestQueue(this);

        JsonArrayRequest jsaonArray=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

            }
        });

                Toast.makeText(this, name, Toast.LENGTH_SHORT).show();*/









    }
    public void sendjsonrequest(){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    name= response.getString("material_type");
                } catch (JSONException e) {

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }


}
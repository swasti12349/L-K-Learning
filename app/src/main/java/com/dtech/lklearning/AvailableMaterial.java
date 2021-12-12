package com.dtech.lklearning;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.util.Log;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AvailableMaterial extends AppCompatActivity {

    AutoCompleteTextView University , Course , Branch , Semester;
    ArrayList<String> semesters;
    ArrayList<String> universityi ;
    ArrayList<String> courses;
    ArrayList<String> branches ;
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
        getSemester();
        get_Courses();
        getBranch();
        getUniversity();

        semesters=new ArrayList<>();
        branches=new ArrayList<>();
        courses=new ArrayList<>();
        universityi=new ArrayList<>();


        ArrayAdapter<String> semesteradapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,semesters);
        ArrayAdapter<String> universityadapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,universityi);
        ArrayAdapter<String> courseadapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,courses);
        ArrayAdapter<String> branchadapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,branches);

        Semester.setAdapter(semesteradapter);
        University.setAdapter(universityadapter);
        Course.setAdapter(courseadapter);
        Branch.setAdapter(branchadapter);


        Semester.setThreshold(0);
        University.setThreshold(0);
        Course.setThreshold(0);
        Branch.setThreshold(0);











    }


    private void getSemester() {


        String Semester_URL = "http://94.130.8.49:3000/semesters/getSemesters";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Semester_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);

                        semesters.add(object.getString("semester_name"));


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

    private void getBranch() {


        String Branch_URL = "http://94.130.8.49:3000/branches/getBranches";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Branch_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        branches.add(object.getString("branch_name"));


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

    private void get_Courses() {

        String courses_url = "http://94.130.8.49:3000/courses/getCourses";
        StringRequest stringreqforcourses = new StringRequest(Request.Method.GET, courses_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray coljsonarray = new JSONArray(response);
                    for (int l = 0; l < coljsonarray.length(); l++) {
                        JSONObject colobject = coljsonarray.getJSONObject(l);

                        courses.add(colobject.getString("course_name"));


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringreqforcourses);


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
                        universityi.add(object.getString("university_name"));

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
                Log.d("verror", error.toString());
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }


}
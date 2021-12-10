package com.dtech.lklearning;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class SignUpOptions extends AppCompatActivity {

    String[] states=new String[] { "Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chhattisgarh", "Goa", "Gujarat", "Haryana", "Himachal Pradesh", "Jammu and Kashmir", "Jharkhand", "Karnataka", "Kerala", "Madhya Pradesh", "Maharashtra", "Manipur", "Meghalaya", "Mizoram", "Nagaland", "Odisha", "Punjab", "Rajasthan", "Sikkim", "Tamil Nadu", "Telangana", "Tripura", "Uttarakhand", "Uttar Pradesh", "West Bengal", "Andaman and Nicobar Islands", "Chandigarh", "Dadra and Nagar Haveli", "Daman and Diu", "Delhi", "Lakshadweep", "Puducherry"};

    ArrayList<String> districts=new ArrayList<>();
    String[] year=new String[] {"2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020","2021","2022","2023"};

    ArrayList<String> universityi;


    String state;
    AutoCompleteTextView stateview , districtview,university,academicyear,college,course;
    String JSON;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signupoptions);
        universityi = new ArrayList<>();






        try {
            InputStream is = getApplication().getAssets().open("statesandcity.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            JSON = new String(buffer, "UTF-8");
        }catch (IOException e){
            e.printStackTrace();
        }

        //AsyncTaskClass asyncTaskClass = new AsyncTaskClass();

        university=findViewById(R.id.university_signupoptions_id);
        academicyear=findViewById(R.id.academic_year_signupoptions_id);
        college=findViewById(R.id.college_signupoptions_id);
        course=findViewById(R.id.course_signupoptions_id);









        stateview=findViewById(R.id.select_state_signupoptions_id);
        districtview=findViewById(R.id.district_signupoptions_id);

        ArrayAdapter<String> stateAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,states);
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,year);


        stateview.setAdapter(stateAdapter);
        stateview.setThreshold(0);

        academicyear.setAdapter(yearAdapter);
        academicyear.setThreshold(0);

        districtview.setThreshold(0);

        stateview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                state=adapterView.getItemAtPosition(i).toString();
                //asyncTaskClass.execute(JSON);
                get_json();
                getUniversity();



            }
        });



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
                        Log.d("lokp", universityi.toString());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(SignUpOptions.this, "Error", Toast.LENGTH_SHORT).show();
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

//    private void get_Colleges(){
//
//        String colleges_url="94.130.8.49:3000/colleges/getColleges";
//        StringRequest stringreqforcolleges = new StringRequest(Request.Method.GET, colleges_url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONArray coljsonarray = new JSONArray(response);
//                    for (int l=0;l<coljsonarray.length();l++){
//                        JSONObject colobject = coljsonarray.getJSONObject(l);
//                        //name yet to be checked
//                        colleges.add(colobject.getString("college_name"));
//
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//
//
//
//
//    }

//    private void get_Courses(){
//
//        String colleges_url="94.130.8.49:3000/courses/getCourses";
//        StringRequest stringreqforcolleges = new StringRequest(Request.Method.GET, colleges_url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONArray coljsonarray = new JSONArray(response);
//                    for (int l=0;l<coljsonarray.length();l++){
//                        JSONObject colobject = coljsonarray.getJSONObject(l);
//                        //name yet to be checked
//                        colleges.add(colobject.getString("college_name"));
//
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
////        ArrayAdapter<String> col_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,colleges);
////        college.setAdapter(col_adapter);
//
//
//
//    }

//    private void getBranch() {
//
//
//        String URL = "https://94.130.8.49:3000/branches/getBranches";
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONArray array = new JSONArray(response);
//                    for (int i = 0; i < array.length(); i++) {
//                        JSONObject object = array.getJSONObject(i);
//                        branch.add(object.getString("branch_name"));
//                        String ba=object.getString("branch_name");
//                        Log.d("branches",ba);
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    Toast.makeText(SignUpOptions.this, "Error", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//
//        RequestQueue queue = Volley.newRequestQueue(this);
//        queue.add(stringRequest);
//    }



    private void get_json(){
        String json;

        try {


            JSONObject jsonObject = new JSONObject(JSON);
            JSONArray jsonArray = jsonObject.getJSONArray("states");


            for (int i=0 ; i<jsonArray.length();i++){
                JSONObject J_inside = jsonArray.getJSONObject(i);
                String nam = J_inside.getString("state");

                if (nam.equals(state)){
                    JSONArray jsonArray1=J_inside.getJSONArray("districts");

                    ArrayAdapter<String> districtAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,districts);
                    districtview.setAdapter(districtAdapter);
                    for (int j=0;j<jsonArray1.length();j++){

                        districts.add(jsonArray1.getString(j));

                        Log.d("districts",jsonArray1.getString(j));

                    }
                }
                Log.d("items", nam);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


//    class AsyncTaskClass extends AsyncTask<String, String, Void> {
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        @Override
//        protected Void doInBackground(String...stringss) {
//
//
//            try {
////                is = getAssets().open("statesandcity.json");
////                int size = is.available();
////                byte[] buffer = new byte[size];
////                is.read();
////                is.close();
//
////                String json =new String(buffer,"UTF-8"
//
//                JSONObject jsonObject = new JSONObject(JSON);
//                JSONArray jsonArray = jsonObject.getJSONArray("states");
//                for (int i=0 ; i<jsonArray.length();i++){
//                    JSONObject J_inside = jsonArray.getJSONObject(i);
//                    String nam = J_inside.getString("state");
//
//                    if (nam.equals(state)){
//                        JSONArray jsonArray1=J_inside.getJSONArray("districts");
//                        for (int j=0;j<jsonArray1.length();j++){
//
//                            districts.add(jsonArray1.getString(j));
//                            Log.d("districts",jsonArray1.getString(j));
//
//                        }
//                    }
//                    Log.d("items", nam);
//
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void unused) {
//            super.onPostExecute(unused);
//        }
//    }
}

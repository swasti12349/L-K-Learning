package com.dtech.lklearning;


import android.content.Intent;


import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SignUpOptions extends AppCompatActivity {

    String[] states = new String[]{"Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chhattisgarh", "Goa", "Gujarat", "Haryana", "Himachal Pradesh", "Jammu and Kashmir", "Jharkhand", "Karnataka", "Kerala", "Madhya Pradesh", "Maharashtra", "Manipur", "Meghalaya", "Mizoram", "Nagaland", "Odisha", "Punjab", "Rajasthan", "Sikkim", "Tamil Nadu", "Telangana", "Tripura", "Uttarakhand", "Uttar Pradesh", "West Bengal", "Andaman and Nicobar Islands", "Chandigarh", "Dadra and Nagar Haveli", "Daman and Diu", "Delhi", "Lakshadweep", "Puducherry"};

    ArrayList<String> districts = new ArrayList<>();
    String[] year = new String[]{"2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023"};

    ArrayList<String> semesters;
    ArrayList<String> college_id;


    ArrayList<String> university_id;
    ArrayList<String> course_id;
    ArrayList<String> branch_id;
    ArrayList<String> semester_id;

    ArrayList<String> universityi;
    ArrayList<String> colleges;
    ArrayList<String> courses;
    ArrayList<String> branches;

    Button sign_up,cancel;
    EditText name, mobile, email, password, confirmpassword;
    FirebaseAuth mAuth;

    Button sign_up;
    EditText name, mobile, email, password, confirmpassword;



    String state;
    AutoCompleteTextView stateview, districtview, university, academicyear, college, course, branch, taluka, semester, education;
    String JSON;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signupoptions);


        mAuth=FirebaseAuth.getInstance();
        String user=mAuth.getCurrentUser().getPhoneNumber();
        String user2 = user.substring(3);
        Toast.makeText(this, user2, Toast.LENGTH_SHORT).show();


        universityi = new ArrayList<>();
        colleges = new ArrayList<>();
        courses = new ArrayList<>();
        branches = new ArrayList<>();
        college_id = new ArrayList<>();
        university_id = new ArrayList<>();
        course_id = new ArrayList<>();
        branch_id = new ArrayList<>();
        semester_id = new ArrayList<>();
        semesters = new ArrayList<>();


        getUniversity();
        get_Colleges();
        get_Courses();
        getBranch();
        getSemester();


        try {
            InputStream is = getApplication().getAssets().open("states.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            JSON = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //AsyncTaskClass asyncTaskClass = new AsyncTaskClass();

        cancel=findViewById(R.id.cancel_signupoptions_id);

        cancel.setOnClickListener(v->{
            finish();
        });




        university = findViewById(R.id.university_signupoptions_id);
        academicyear = findViewById(R.id.academic_year_signupoptions_id);
        college = findViewById(R.id.college_signupoptions_id);
        course = findViewById(R.id.course_signupoptions_id);
        branch = findViewById(R.id.branch_signupoptions_id);
        name = findViewById(R.id.name_signupoptions_id);
        taluka = findViewById(R.id.taluka_signupoptions_id);
        mobile = findViewById(R.id.mobileno_signupoptions_id);
        semester = findViewById(R.id.semester_signupoptions_id);
        education = findViewById(R.id.educationstatus_signupotptions_id);


        mobile.setText(user2);

        //mobile.setText(user);



        email = findViewById(R.id.email_adress_signupoptions_id);
        password = findViewById(R.id.password_signupoptions_id);
        confirmpassword = findViewById(R.id.confirm_password_signupoptions_id);


        sign_up = findViewById(R.id.signup_signupoptions_id);


        sign_up.setOnClickListener(v -> {
            String name1, mobile1, email1, password1, confirmpassword1, state1, district1, taluka1, university1, college1, course1, branch1, academic1, semster1, educationstatus1;

            name1 = name.getText().toString();

            mobile1=mobile.getText().toString();

   

            email1 = email.getText().toString();
            password1 = password.getText().toString();
            confirmpassword1 = confirmpassword.getText().toString();
            state1 = stateview.getText().toString();
            taluka1 = taluka.getText().toString();
            district1 = districtview.getText().toString();
            university1 = university.getText().toString();
            college1 = college.getText().toString();
            course1 = course.getText().toString();
            branch1 = branch.getText().toString();
            academic1 = academicyear.getText().toString();
            semster1 = semester.getText().toString();
            educationstatus1 = education.getText().toString();


          



            if (!name1.isEmpty()) {

                if (!email1.isEmpty()) {
                    if (!password1.isEmpty()) {
                        if (!confirmpassword1.isEmpty() && password1.equals(confirmpassword1)) {
                            if (!state1.isEmpty()) {
                                if (!taluka1.isEmpty()) {
                                    if (!district1.isEmpty()) {
                                        if (!university1.isEmpty()) {
                                            if (!college1.isEmpty()) {
                                                if (!branch1.isEmpty()) {
                                                    if (!academic1.isEmpty()) {
                                                        if (!course1.isEmpty()) {
                                                            if (!semster1.isEmpty()) {
                                                                if (!educationstatus1.isEmpty()) {



                                                                    if(mobile1.equals(user2)){
                                                                        signup(name1, mobile1, email1, password1, state1, taluka1, district1, university1, college1, branch1, academic1, course1, semster1, educationstatus1);



                                                                    }
                                                                    else{
                                                                        mobile.setError("Please give the verified mobile no.");

                                                                   

                                                                   if(mobile1.equals(user2)){
                                                                      signup(name1, mobile1, email1, password1, state1, taluka1, district1, university1, college1, branch1, academic1, course1, semster1, educationstatus1);



                                                                   }
                                                                   else{
                                                                       mobile.setError("Please give the verified mobile no.");
                                                                   }


                                                                } else {
                                                                    education.setError("Empty fields are not allowed!");
                                                                }
                                                            } else {
                                                                semester.setError("Empty fields are not allowed!");
                                                            }
                                                        } else {
                                                            course.setError("Empty fields are not allowed!");
                                                        }
                                                    } else {
                                                        academicyear.setError("Empty fields are not allowed!");
                                                    }
                                                } else {
                                                    branch.setError("Empty fields are not allowed!");
                                                }
                                            } else {
                                                college.setError("Empty fields are not allowed!");
                                            }
                                        } else {
                                            university.setError("Empty fields are not allowed!");
                                        }
                                    }
                                } else {
                                    taluka.setError("Empty fields are not allowed!");
                                }
                            } else {
                                stateview.setError("Empty fields are not allowed!");
                            }
                        } else {
                            confirmpassword.setError("Password must be same");
                        }
                    } else {
                        password.setError("Empty fields are not allowed!");
                    }
                } else {
                    email.setError("Empty fields are not allowed!");
                }

            } else {
                name.setError("Empty fields are not allowed!");
            }


        });


        stateview = findViewById(R.id.select_state_signupoptions_id);
        districtview = findViewById(R.id.district_signupoptions_id);

        ArrayAdapter<String> stateAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, states);
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, year);
        ArrayAdapter<String> university_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, universityi);
        ArrayAdapter<String> college_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, colleges);
        ArrayAdapter<String> course_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, courses);
        ArrayAdapter<String> branch_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, branches);
        ArrayAdapter<String> semester_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, semesters);

        branch.setAdapter(branch_adapter);
        branch.setThreshold(0);

        semester.setAdapter(semester_adapter);
        semester.setThreshold(0);


        course.setAdapter(course_adapter);
        course.setThreshold(0);

        university.setAdapter(university_adapter);
        university.setThreshold(0);

        college.setAdapter(college_adapter);
        college.setThreshold(0);

        stateview.setAdapter(stateAdapter);
        stateview.setThreshold(0);

        academicyear.setAdapter(yearAdapter);
        academicyear.setThreshold(0);

        districtview.setThreshold(0);

        stateview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                state = adapterView.getItemAtPosition(i).toString();
                //asyncTaskClass.execute(JSON);
                get_json();


            }
        });




        college.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String college2 = adapterView.getItemAtPosition(i).toString();


            }
        });



    }

    private void signup(String name1, String mobile1, String email1, String password1, String state1, String taluka1, String district1, String university1, String college1, String branch1, String academic1, String course1, String semster1, String educationstatus1) {
        RequestQueue queue = Volley.newRequestQueue(SignUpOptions.this);
        JSONObject params;

        Toast.makeText(this, "Signing In", Toast.LENGTH_SHORT).show();


        String College_id, University_id, Course_id, Branch_id, Semester_id;
        String newurl = "http://94.130.8.49:3000/auth/register";


        try {
            int college_index = colleges.indexOf(college1);
            int university_index = universityi.indexOf(university1);
            int course_index = courses.indexOf(course1);
            int branch_index = branches.indexOf(branch1);
            int semester_index = semesters.indexOf(semster1);

            College_id = college_id.get(college_index);
            University_id = university_id.get(university_index);
            Course_id = course_id.get(course_index);
            Branch_id = branch_id.get(branch_index);
            Semester_id = semester_id.get(semester_index);


            String json = "{\n" +
                    "    \"student_name\" : \"" + name1 +
                    "\" , \n" +
                    "    \"student_password\" : \"" + password1 +
                    "\" , \n" +
                    "    \"student_mobile_number\" : \"" + mobile1 +
                    "\",\n" +
                    "    \"student_email\" : \"" + email1 +
                    "\" ,\n" +
                    "    \"student_state\"  : \"" + state1 +
                    "\" , \n" +
                    "    \"student_district\" : \"" + district1 +
                    "\" , \n" +
                    "    \"student_taluka\" : \"" + taluka1 +
                    "\" , \n" +
                    "    \"college_id\" : \"" + College_id +
                    "\",\n" +
                    "    \"university_id\" : \"" + University_id +
                    "\" ,\n" +
                    "    \"course_id\" : \"" + Course_id +
                    "\" , \n" +
                    "    \"branch_id\" : \"" + Branch_id +
                    "\" ,\n" +
                    "    \"semester_id\" : \"" + Semester_id +
                    "\" , \n" +
                    "    \"student_edu_status\" : \"S" + educationstatus1 +
                    "\" , \n" +
                    "    \"student_academic_yr\" : \"" + academic1 +
                    "\"\n" +
                    "\n" +
                    "\n" +
                    " }\n" +
                    "\n";


            params = new JSONObject(json);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, newurl, params, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {

                        String str = response.getString("insertId");

                        startActivity(new Intent(SignUpOptions.this,Home.class));
                        Log.d("responsed", str);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d("jsonerror",e.toString());
                    }
                    Log.d("polyu", response.toString());

                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("vol",error.toString());

                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();

                    return params;

                }

            };

            queue.add(jsonObjectRequest);
        } catch (
                JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "Please enter all the information correctly!", Toast.LENGTH_SHORT).show();
        }

                        Toast.makeText(SignUpOptions.this, str, Toast.LENGTH_LONG).show();
                        Log.d("responsed", str);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.d("polyu", response.toString());

                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();

                    return params;

                }

            };

            queue.add(jsonObjectRequest);
        } catch (
                JSONException e) {
            e.printStackTrace();
        }


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
                        university_id.add(object.getString("university_id"));
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

    private void get_Colleges() {

        String colleges_url = "http://94.130.8.49:3000/colleges/getColleges";
        StringRequest stringreqforcolleges = new StringRequest(Request.Method.GET, colleges_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray coljsonarray = new JSONArray(response);
                    for (int l = 0; l < coljsonarray.length(); l++) {
                        JSONObject colobject = coljsonarray.getJSONObject(l);
                        //name yet to be checked
                        colleges.add(colobject.getString("college_name"));
                        college_id.add(colobject.getString("college_id"));


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
        queue.add(stringreqforcolleges);

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
                        course_id.add(colobject.getString("course_id"));

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
                        branch_id.add(object.getString("branch_id"));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(SignUpOptions.this, "Error", Toast.LENGTH_SHORT).show();
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
                        university_id.add(object.getString("university_id"));
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

    private void get_Colleges() {

        String colleges_url = "http://94.130.8.49:3000/colleges/getColleges";
        StringRequest stringreqforcolleges = new StringRequest(Request.Method.GET, colleges_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray coljsonarray = new JSONArray(response);
                    for (int l = 0; l < coljsonarray.length(); l++) {
                        JSONObject colobject = coljsonarray.getJSONObject(l);

                        colleges.add(colobject.getString("college_name"));
                        college_id.add(colobject.getString("college_id"));


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
        queue.add(stringreqforcolleges);

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
                        course_id.add(colobject.getString("course_id"));

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
                        branch_id.add(object.getString("branch_id"));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(SignUpOptions.this, "Error", Toast.LENGTH_SHORT).show();
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
                        semester_id.add(object.getString("semester_id"));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(SignUpOptions.this, "Error", Toast.LENGTH_SHORT).show();
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

    private void get_json() {

        try {
            InputStream is = getApplication().getAssets().open("states.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            JSON = new String(buffer, "UTF-8");

            JSONObject jsonObject = new JSONObject(JSON);
            JSONArray jsonArray = jsonObject.getJSONArray("states");


            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject J_inside = jsonArray.getJSONObject(i);
                String nam = J_inside.getString("state");

                if (nam.equals(state)) {
                    JSONArray jsonArray1 = J_inside.getJSONArray("districts");

                    ArrayAdapter<String> districtAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, districts);
                    districtview.setAdapter(districtAdapter);
                    for (int j = 0; j < jsonArray1.length(); j++) {

                        districts.add(jsonArray1.getString(j));

                        Log.d("districts", jsonArray1.getString(j));

                    }
                }
                Log.d("items", nam);

            }
        } catch (JSONException | IOException e) {
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

////                is = getAssets().open("states.json");

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
package project.himanshu.com.eventmanager.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import project.himanshu.com.eventmanager.Events;
import project.himanshu.com.eventmanager.R;
import project.himanshu.com.eventmanager.fragment.StudentLoginFragment;
import project.himanshu.com.eventmanager.utility.StudentEventAdapter;
import project.himanshu.com.eventmanager.utility.StudentSharedPref;

public class StudentEventActivity extends AppCompatActivity {

    TextView nameTV, typeTV, startdateTV, descriptionTV, feeTV;
    Button register;
    StudentSharedPref studentSharedPref;
    ProgressDialog dialog;
    RequestQueue requestQueue;
    String url = "http://www.xplosion.in/xplosionapi/api/emGetEventByName";
    String registrationURL = "http://www.xplosion.in/xplosionapi/api/emRegisterStudentEvent";
    public static String EventName;
    public static double fee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_event);

        dialog = new ProgressDialog(StudentEventActivity.this);
        dialog.setMessage("...");
        dialog.show();

        studentSharedPref = StudentSharedPref.getInstance(StudentEventActivity.this);

        nameTV = findViewById(R.id.eventname);
        typeTV = findViewById(R.id.eventtype);
        startdateTV = findViewById(R.id.eventstart);
        descriptionTV = findViewById(R.id.eventdescription);
        register = findViewById(R.id.register);
        feeTV = findViewById(R.id.eventfee);

        requestQueue = Volley.newRequestQueue(StudentEventActivity.this);

        JSONObject object = new JSONObject();
        try {
            object.put("name",EventName);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, url, object, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                dialog.dismiss();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject object1 = response.getJSONObject(i);

                        String name = object1.getString("name");
                        String start_date = object1.getString("start_date");
                        String description = object1.getString("description");
                        String type = object1.getString("type");
                        double fees = object1.getDouble("amount");
                        studentSharedPref.setPay(fees + "");

                        fee = fees;

                        java.util.Date startDate = null;
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            startDate = df.parse(start_date);
                        } catch (ParseException e) {
                            System.out.println("invalid format");
                        }

                        nameTV.setText("" + name);
                        typeTV.setText("" + type);
                        String startDateTime[] = startDate.toString().split(" ");
                        startdateTV.setText(startDateTime[2] + " " + startDateTime[1] + "\n" + startDateTime[5]);
                        descriptionTV.setText("" + description);

                        if(fee > 0) {
                            feeTV.setText("Rs. " + fee + "/-");
                        } else {
                            feeTV.setText("FREE");
                        }



                    } catch (JSONException e) {

                        Toast.makeText(StudentEventActivity.this, "" + e.toString(), Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(StudentEventActivity.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue.add(request);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                requestQueue = Volley.newRequestQueue(StudentEventActivity.this);

                JSONObject object = new JSONObject();

                try {

                    object.put("enrollment",studentSharedPref.getEnrollment());
                    object.put("name",nameTV.getText().toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, registrationURL, object, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        String successValue = "";
                        try {
                            successValue = response.getString("success");
                            if(successValue.equals("Succesfull")) {

                                Toast.makeText(StudentEventActivity.this, "" + successValue, Toast.LENGTH_SHORT).show();

                                startActivity(new Intent(StudentEventActivity.this, StudentRegisteredActivity.class));

                                finish();

                            } else  {
                                Toast.makeText(StudentEventActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(StudentEventActivity.this, "" + error.toString(), Toast.LENGTH_SHORT).show();

                    }
                });
                requestQueue.add(request);


            }
        });

    }
}

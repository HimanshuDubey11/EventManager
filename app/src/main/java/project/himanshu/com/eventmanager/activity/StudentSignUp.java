package project.himanshu.com.eventmanager.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import project.himanshu.com.eventmanager.R;
import project.himanshu.com.eventmanager.fragment.StudentLoginFragment;
import project.himanshu.com.eventmanager.utility.AllDatabase;
import project.himanshu.com.eventmanager.utility.SharedPref;
import project.himanshu.com.eventmanager.utility.StudentSharedPref;

public class StudentSignUp extends AppCompatActivity {

    String url = "http://www.xplosion.in/xplosionapi/api/emAddStudent";
    SharedPref sharedPref;
    Button submit;
    StudentSharedPref studentSharedPref;
    EditText enroll, name, pass, email, contact;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sign_up);

        requestQueue = Volley.newRequestQueue(StudentSignUp.this);

        studentSharedPref = StudentSharedPref.getInstance(StudentSignUp.this);
        sharedPref = SharedPref.getInstance(StudentSignUp.this);
        enroll = findViewById(R.id.stusignupenroll);
        name = findViewById(R.id.stusignupname);
        pass = findViewById(R.id.stusignuppass);
        submit = findViewById(R.id.stusignupbtn);
        contact = findViewById(R.id.studignupcontact);
        email = findViewById(R.id.stusignupemail);

        startActivty();

    }

    private void startActivty() {

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                JSONObject object = new JSONObject();

                try {

                    object.put("enrollment",enroll.getText());
                    object.put("name",name.getText());
                    object.put("password",pass.getText());
                    object.put("contact", contact.getText());
                    object.put("email",email.getText());

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, object, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        String successValue = "";
                        try {
                            StudentLoginFragment.user_enrollment = enroll.getText().toString();
                            successValue = response.getString("success");
                            if(successValue.equals("Succesfull")) {
                                sharedPref.setLogin(true);
                                studentSharedPref.setEnrollment(enroll.getText().toString());
                                sharedPref.setName("student");
                                studentSharedPref.setEmailId(email.getText().toString());
                                studentSharedPref.setContact(contact.getText().toString());
                                studentSharedPref.setName(name.getText().toString());
                                startActivity(new Intent(StudentSignUp.this,StudentHome.class));
                                finish();

                            } else  {
                                Toast.makeText(StudentSignUp.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(StudentSignUp.this, StudentSignUp.class));
                                finish();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(StudentSignUp.this, "" + error.toString(), Toast.LENGTH_SHORT).show();

                    }
                });
                requestQueue.add(request);
            }
        });

    }
}

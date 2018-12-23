package project.himanshu.com.eventmanager.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import project.himanshu.com.eventmanager.utility.AllDatabase;
import project.himanshu.com.eventmanager.R;
import project.himanshu.com.eventmanager.utility.SharedPref;

public class AdminSignUp extends AppCompatActivity {

    String url = "http://www.xplosion.in/xplosionapi/api/emAddAdmin";
    SharedPref sharedPref;
    Button submit;
    EditText admin_id, name, pass;
    AllDatabase allDatabase;
    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_sign_up);

        allDatabase = new AllDatabase(this);

        sharedPref = SharedPref.getInstance(AdminSignUp.this);

        requestQueue = Volley.newRequestQueue(AdminSignUp.this);
        admin_id = findViewById(R.id.adminsignupenroll);
        name = findViewById(R.id.adminsignupname);
        pass = findViewById(R.id.adminsignuppass);

        submit = findViewById(R.id.adminsignupbtn);

        startActivty();
        
    }

    private void startActivty() {

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                JSONObject object = new JSONObject();

                try {

                    object.put("email",admin_id.getText());
                    object.put("name",name.getText());
                    object.put("password",pass.getText());

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, object, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        String successValue = "";
                        try {
                            successValue = response.getString("success");
                            if(successValue.equals("Succesfull")) {
                                sharedPref.setLogin(true);
                                sharedPref.setName("admin");
                                Toast.makeText(AdminSignUp.this, "" + successValue, Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(AdminSignUp.this,AdminHome.class));
                                finish();


                            } else  {
                                Toast.makeText(AdminSignUp.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(AdminSignUp.this, "" + error.toString(), Toast.LENGTH_SHORT).show();

                    }
                });
                requestQueue.add(request);
            }
        });

    }
}

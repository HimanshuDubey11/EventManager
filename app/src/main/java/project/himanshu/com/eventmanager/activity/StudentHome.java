package project.himanshu.com.eventmanager.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
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

import project.himanshu.com.eventmanager.R;
import project.himanshu.com.eventmanager.fragment.StuEvents;
import project.himanshu.com.eventmanager.fragment.StuSettings;
import project.himanshu.com.eventmanager.fragment.StudentLoginFragment;
import project.himanshu.com.eventmanager.utility.StudentSharedPref;

public class StudentHome extends AppCompatActivity {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_events:

                    fragmentManager = getSupportFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.myframelayout,new StuEvents());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                    return true;
                case R.id.navigation_settings:
                    fragmentManager = getSupportFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.myframelayout,new StuSettings());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    return true;
            }
            return false;
        }
    };

    StudentSharedPref studentSharedPref;
    RequestQueue requestQueue;
    String url = "http://www.xplosion.in/xplosionapi/api/emReadStudent";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        requestQueue = Volley.newRequestQueue(StudentHome.this);

        studentSharedPref = StudentSharedPref.getInstance(StudentHome.this);

        JSONObject object = new JSONObject();

        try {
            object.put("enrollment",studentSharedPref.getEnrollment().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.POST, url, object, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {


                for (int i = 0; i < response.length(); i++) {

                    try {
                        JSONObject request = response.getJSONObject(i);
                        studentSharedPref.setEmailId(request.getString("email"));
                        studentSharedPref.setContact(request.getString("contact"));
                        studentSharedPref.setName(request.getString("name"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(StudentHome.this, "" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(arrayRequest);


        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.myframelayout,new StuEvents());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);




    }
}
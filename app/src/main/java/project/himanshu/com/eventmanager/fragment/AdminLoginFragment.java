package project.himanshu.com.eventmanager.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import project.himanshu.com.eventmanager.activity.AdminSignUp;
import project.himanshu.com.eventmanager.utility.AllDatabase;
import project.himanshu.com.eventmanager.R;
import project.himanshu.com.eventmanager.utility.SharedPref;
import project.himanshu.com.eventmanager.activity.AdminHome;


public class AdminLoginFragment extends Fragment {

    String url = "http://www.xplosion.in/xplosionapi/api/emLoginAdmin";
    SharedPref sharedPref;
    AllDatabase allDatabase;
    EditText adminId, pass;
    Button submit;
    RequestQueue requestQueue;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_login, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        requestQueue = Volley.newRequestQueue(getContext());
        sharedPref = SharedPref.getInstance(getContext());
        allDatabase = new AllDatabase(getContext());
        adminId = view.findViewById(R.id.adminfragenroll);
        pass = view.findViewById(R.id.adminfragpass);
        submit = view.findViewById(R.id.adminfragsubmitbtn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                JSONObject object = new JSONObject();

                try {

                    object.put("email",adminId.getText());
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
                            if(successValue.equals("Successfull")) {
                                sharedPref.setLogin(true);
                                sharedPref.setName("admin");
                                Toast.makeText(getContext(), "" + successValue, Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getContext(),AdminHome.class));
                            } else  {
                                Toast.makeText(getContext(), "INVALID LOGIN", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getContext(), "" + error.toString(), Toast.LENGTH_SHORT).show();

                    }
                });
                requestQueue.add(request);
            }
        });
    }
}

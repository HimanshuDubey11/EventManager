package project.himanshu.com.eventmanager.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import project.himanshu.com.eventmanager.Events;
import project.himanshu.com.eventmanager.R;
import project.himanshu.com.eventmanager.utility.AllDatabase;
import project.himanshu.com.eventmanager.utility.EventAdapter;
import project.himanshu.com.eventmanager.utility.StudentEventAdapter;


public class StuEvents extends Fragment {


    ProgressDialog dialog;
    String url="http://www.xplosion.in/xplosionapi/api/emReadEvent";
    RequestQueue requestQueue;
    ArrayList<Events> events = new ArrayList<Events>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stu_events, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dialog = new ProgressDialog(getContext());
        requestQueue = Volley.newRequestQueue(getContext());

        dialog.setMessage("...");
        dialog.show();
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                events = new ArrayList<Events>();
                dialog.dismiss();
                for (int i = 0; i < response.length(); i++) {

                    try {

                        JSONObject object = response.getJSONObject(i);

                        String name = object.getString("name");
                        String start_date = object.getString("start_date");
                        String description = object.getString("description");
                        String type = object.getString("type");
                        double amount = object.getDouble("amount");

                        java.util.Date startDate = null;
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            startDate = df.parse(start_date);
                        } catch (ParseException e) {
                            System.out.println("invalid format");
                        }

                        events.add(new Events(name, description, startDate, type, amount));

                        RecyclerView recyclerView = view.findViewById(R.id.stueventrecyclerview);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(new StudentEventAdapter(getContext(), events));

                    } catch (JSONException e) {

                        Toast.makeText(getContext(), "" + e.toString(), Toast.LENGTH_SHORT).show();

                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue.add(request);


    }
}

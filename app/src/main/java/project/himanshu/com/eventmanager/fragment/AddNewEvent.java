package project.himanshu.com.eventmanager.fragment;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import project.himanshu.com.eventmanager.R;
import project.himanshu.com.eventmanager.activity.AdminHome;
import project.himanshu.com.eventmanager.activity.AdminSignUp;
import project.himanshu.com.eventmanager.activity.MainActivity;
import project.himanshu.com.eventmanager.utility.AllDatabase;

public class AddNewEvent extends Fragment {

    String url = "http://www.xplosion.in/xplosionapi/api/emAddEvent";
    EditText name, description, startDate, type, amount;
    Button createButton;
    ImageView startDateCal;
    RequestQueue requestQueue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_new_event, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        requestQueue = Volley.newRequestQueue(getContext());
        name = view.findViewById(R.id.addeventname);
        description = view.findViewById(R.id.addeventdescription);
        startDate = view.findViewById(R.id.addeventstartdate);
        type = view.findViewById(R.id.addeventtype);
        createButton = view.findViewById(R.id.addeventcreatebtn);
        startDateCal = view.findViewById(R.id.addeventstartdatecal);
        final Calendar calendar = Calendar.getInstance();
        amount = view.findViewById(R.id.addeventamount);

        final java.util.Date date = new Date();
        long dateNum = date.getTime();
        java.sql.Date sqlDate = new java.sql.Date(dateNum);
        startDate.setText(sqlDate.toString());

        final DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                datePicker.setMinDate(date.getTime());

                startDate.setText(datePicker.getYear() + "-" + (datePicker.getMonth() + 1) + "-" + datePicker.getDayOfMonth());

            }
        }, calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));

        startDateCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                datePickerDialog.show();

            }
        });




        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                JSONObject object = new JSONObject();

                try {

                    object.put("name",name.getText());
                    object.put("start_date",startDate.getText());
                    object.put("description",description.getText());
                    object.put("type",type.getText());
                    object.put("amount",amount.getText());

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
                                Toast.makeText(getContext(), "" + successValue, Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getContext(),AdminHome.class));

                            } else  {
                                Toast.makeText(getContext(), "Registration Failed", Toast.LENGTH_SHORT).show();
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

    public void getDate_Time() {}
}

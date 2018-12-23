package project.himanshu.com.eventmanager.utility;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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

import java.util.ArrayList;

import project.himanshu.com.eventmanager.Events;
import project.himanshu.com.eventmanager.R;
import project.himanshu.com.eventmanager.activity.StudentEventActivity;
import project.himanshu.com.eventmanager.activity.StudentHome;
import project.himanshu.com.eventmanager.activity.StudentSignUp;
import project.himanshu.com.eventmanager.fragment.StudentLoginFragment;

public class StudentEventAdapter extends RecyclerView.Adapter<StudentEventAdapter.EventsManager1> {



    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    Context context;
    ArrayList<Events> events;

    public StudentEventAdapter(Context context, ArrayList<Events> events) {
        this.context = context;
        this.events = events;
    }

    @NonNull
    @Override
    public StudentEventAdapter.EventsManager1 onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View eventView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_student_event_adapter,null);
        return new EventsManager1(eventView);

    }

    @Override
    public void onBindViewHolder(@NonNull final StudentEventAdapter.EventsManager1 eventsManager1, final int i) {

        eventsManager1.eventName.setText(events.get(i).getName());
        eventsManager1.eventType.setText(events.get(i).getType());
        String startDateTime[] = events.get(i).getStartDate().toString().split(" ");
        eventsManager1.eventStartDate.setText(startDateTime[2] + " " + startDateTime[1] + "\n" + startDateTime[5]);

        eventsManager1.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StudentEventActivity.EventName = events.get(i).getName();
                view.getContext().startActivity(new Intent(context,StudentEventActivity.class));

            }
        });

    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public class EventsManager1 extends RecyclerView.ViewHolder {

        TextView eventName, eventStartDate, eventType;
        RelativeLayout relativeLayout;

        public EventsManager1(@NonNull View itemView) {
            super(itemView);

            eventType = itemView.findViewById(R.id.eventtype);
            eventName = itemView.findViewById(R.id.eventname);
            eventStartDate = itemView.findViewById(R.id.eventstart);
            relativeLayout = itemView.findViewById(R.id.eventadapterrelativelayout);

        }
    }
}

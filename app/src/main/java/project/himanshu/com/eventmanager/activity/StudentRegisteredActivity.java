package project.himanshu.com.eventmanager.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import project.himanshu.com.eventmanager.PayUPaymentActivity;
import project.himanshu.com.eventmanager.R;
import project.himanshu.com.eventmanager.utility.StudentSharedPref;

public class StudentRegisteredActivity extends AppCompatActivity {

    StudentSharedPref studentSharedPref;
    Button payNow, moreevents;
    TextView eventName, enrollment, name, email, contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registered);

        studentSharedPref = StudentSharedPref.getInstance(StudentRegisteredActivity.this);

        eventName = findViewById(R.id.resgiteredeventname);
        enrollment = findViewById(R.id.resgiteredenrollment);
        name = findViewById(R.id.resgiteredname);
        email = findViewById(R.id.resgiteredemail);
        contact = findViewById(R.id.resgiteredcontact);
        payNow = findViewById(R.id.paynow);
        moreevents = findViewById(R.id.moreevents);

        eventName.setText(studentSharedPref.getEventName());
        enrollment.setText(studentSharedPref.getEnrollment());
        name .setText(studentSharedPref.getName());
        email.setText(studentSharedPref.getEmailId());
        contact.setText(studentSharedPref.getContact());

        payNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(StudentRegisteredActivity.this,PayUPaymentActivity.class));
                finish();

            }
        });

        moreevents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studentSharedPref.setEventName(null);
                startActivity(new Intent(StudentRegisteredActivity.this,StudentHome.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        studentSharedPref.setEventName(null);
    }
}

package project.himanshu.com.eventmanager.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import project.himanshu.com.eventmanager.R;
import project.himanshu.com.eventmanager.utility.StudentSQLDatabase;
import project.himanshu.com.eventmanager.utility.SharedPref;

public class SplashScreen extends AppCompatActivity {

    SharedPref sharedPref;
    StudentSQLDatabase studentSQLDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        sharedPref = SharedPref.getInstance(SplashScreen.this);

        studentSQLDatabase = new StudentSQLDatabase(SplashScreen.this);

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivty();

            }
        },1000);

    }

    private void startActivty() {

        if(sharedPref.isLogin()) {

            if(sharedPref.getName().equals("student")) {
                startActivity(new Intent(SplashScreen.this,StudentHome.class));
                finish();
            } else {
                startActivity(new Intent(SplashScreen.this,AdminHome.class));
                finish();
            }

        } else {
            startActivity(new Intent(SplashScreen.this,MainActivity.class));
            finish();
        }


    }

}
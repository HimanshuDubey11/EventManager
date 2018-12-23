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

import project.himanshu.com.eventmanager.R;
import project.himanshu.com.eventmanager.activity.MainActivity;
import project.himanshu.com.eventmanager.utility.SharedPref;


public class StuSettings extends Fragment {

    SharedPref sharedPref;
    Button logoutButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stu_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedPref = SharedPref.getInstance(getContext());
        logoutButton = view.findViewById(R.id.stusettinglogoutbtn);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPref.clearPreference();
                startActivity(new Intent(getContext(),MainActivity.class));
            }
        });

    }
}

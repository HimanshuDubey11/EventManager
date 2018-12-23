package project.himanshu.com.eventmanager.utility;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import project.himanshu.com.eventmanager.R;
import project.himanshu.com.eventmanager.fragment.StudentLoginFragment;
import project.himanshu.com.eventmanager.fragment.AdminLoginFragment;

public class MyViewPagerAdapter extends FragmentPagerAdapter{

    private Context context;

    public MyViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new StudentLoginFragment();

            case 1:
                return  new AdminLoginFragment();

        }

        return null;

    }

    @Override
    public int getCount() {
        return 2;
    }


    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {

            case 0 :
                return context.getString(R.string.loginstudent);
            case 1:
                return context.getString(R.string.loginadmin);
        }

        return super.getPageTitle(position);
    }

}

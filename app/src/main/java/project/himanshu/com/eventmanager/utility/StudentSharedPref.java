package project.himanshu.com.eventmanager.utility;

import android.content.Context;
import android.content.SharedPreferences;

public class StudentSharedPref {

    static SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    static StudentSharedPref mySharedPref2;
    String name;

    public final String KEY_ENROLLMENT = "enrollment";
    public final String KEY_NAME = "key_name";
    public final String DATABASE = "StudentSharedPrefDatabase";
    public final String KEY_BRANCH = "branch";
    public final String KEY_SECTION = "section";
    public final String KEY_YEAR = "year";
    public final String KEY_CONTACT = "contact";
    public final String KEY_EMAILID = "emailid";
    public final String KEY_EVENT = "eventname";
    public final String KEY_PAY = "pay";

    public StudentSharedPref(Context context) {
        sharedPreferences = context.getSharedPreferences(DATABASE,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static StudentSharedPref getInstance(Context context) {
        if(mySharedPref2 == null)
            mySharedPref2 = new StudentSharedPref(context);

        return mySharedPref2;
    }


    public String getPay() {
        return sharedPreferences.getString(KEY_PAY,"");
    }

    public void setPay(String pay) {
        editor.putString(KEY_PAY,pay);
        editor.commit();
    }

    public String getEventName() {
        return sharedPreferences.getString(KEY_EVENT,"");
    }

    public void setEventName(String event) {
        editor.putString(KEY_EVENT,event);
        editor.commit();
    }

    public String getBranch() {
        return sharedPreferences.getString(KEY_BRANCH,"");
    }

    public void setBranch(String Branch) {
        editor.putString(KEY_BRANCH,Branch);
        editor.commit();
    }


    public String getSection() {
        return sharedPreferences.getString(KEY_SECTION,"");
    }

    public void setSection(String Section) {
        editor.putString(KEY_SECTION,Section);
        editor.commit();
    }


    public String getYear() {
        return sharedPreferences.getString(KEY_YEAR,"");
    }

    public void setYear(String Year) {
        editor.putString(KEY_YEAR,Year);
        editor.commit();
    }


    public String getContact() {
        return sharedPreferences.getString(KEY_CONTACT,"");
    }

    public void setContact(String Contact) {
        editor.putString(KEY_CONTACT,Contact);
        editor.commit();
    }


    public String getEmailId() {
        return sharedPreferences.getString(KEY_EMAILID,"");
    }

    public void setEmailId(String emailId) {
        editor.putString(KEY_ENROLLMENT,emailId);
        editor.commit();
    }

    public String getEnrollment() {
        return sharedPreferences.getString(KEY_ENROLLMENT,"");
    }

    public void setEnrollment(String enrollment) {
        editor.putString(KEY_ENROLLMENT,enrollment);
        editor.commit();
    }

    public String getName() {
        return sharedPreferences.getString(KEY_NAME,"");
    }

    public void setName(String name) {
        editor.putString(KEY_NAME,name);
        editor.commit();
    }

    public void clearPreference() {
        editor.clear();
        editor.commit();
    }
}

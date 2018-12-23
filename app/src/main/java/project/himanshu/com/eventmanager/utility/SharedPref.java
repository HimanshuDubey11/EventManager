package project.himanshu.com.eventmanager.utility;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {

    static SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    static SharedPref mySharedPref2;
    String name;

    public final String LOGIN_STATE= "login_state";
    public final String KEY_NAME = "key_name";
    public final String DATABASE = "SharedPrefDatabase";

    public SharedPref(Context context) {
        sharedPreferences = context.getSharedPreferences(DATABASE,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static SharedPref getInstance(Context context) {
        if(mySharedPref2 == null)
            mySharedPref2 = new SharedPref(context);

        return mySharedPref2;
    }

    public String getName() {
        return sharedPreferences.getString(KEY_NAME,"");
    }

    public void setName(String name) {
        editor.putString(KEY_NAME,name);
        editor.commit();
    }

    public void setLogin(Boolean login) {
        editor.putBoolean(LOGIN_STATE,login);
        editor.commit();
    }

    public boolean isLogin() {
        return sharedPreferences.getBoolean(LOGIN_STATE,false);
    }

    public void clearPreference() {
        editor.clear();
        editor.commit();
    }

}

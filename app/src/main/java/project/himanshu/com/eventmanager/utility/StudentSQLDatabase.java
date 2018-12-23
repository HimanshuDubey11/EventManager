package project.himanshu.com.eventmanager.utility;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import project.himanshu.com.eventmanager.utility.AdminSQLDatabase;

public class StudentSQLDatabase extends SQLiteOpenHelper {

    SQLiteDatabase sqLiteDatabase;
    AdminSQLDatabase adminSQLDatabase;

    private static final String ENROLMENT_NO ="enrollment" ;
    private static final String PASSWORD ="password" ;
    private static final String NAME ="name" ;
    private static final String DATABASE_NAME = "EventManager";
    private static final String TABLE_NAME = "STUDENTS";
    private static final int VERSION = 1;


    public StudentSQLDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql = "Create TABLE " + TABLE_NAME + "( "
                + ENROLMENT_NO + " Varchar(200) NOT NULL constraint employee_pk PRIMARY KEY UNIQUE,"
                + PASSWORD + " varchar(200) NOT NULL,"
                + NAME + " varchar(200) NOT NULL"
                + ")";
        sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);

    }

    public boolean InsertData(String enrollmentNo, String name, String password) {

        getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ENROLMENT_NO,enrollmentNo);
        contentValues.put(NAME,name);
        contentValues.put(PASSWORD,password);

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert(TABLE_NAME,null,contentValues) != -1;

    }

    public boolean checkLogin(String enrolmentNo, String password) {

        Cursor c = getReadableDatabase().rawQuery(
                "SELECT * FROM " + TABLE_NAME + " WHERE "
                        + ENROLMENT_NO + " = '" + enrolmentNo +"' AND " + PASSWORD + " = '"+ password +"' " ,  null);


        if(c.getCount() <= 0) {

            return false;
        } else {

            return true;
        }
    }

}

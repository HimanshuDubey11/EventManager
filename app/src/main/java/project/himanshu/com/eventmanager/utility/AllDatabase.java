package project.himanshu.com.eventmanager.utility;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import project.himanshu.com.eventmanager.Events;

public class AllDatabase extends SQLiteOpenHelper {



    private static final String DATABASE_NAME = "EventManager";
    private static final int VERSION = 1;

    //student database
    private static final String ENROLMENT_NO ="enrollment" ;
    private static final String PASSWORD_STUDENT ="password" ;
    private static final String NAME_STUDENT ="name" ;
    private static final String TABLE_NAME_STUDENT = "STUDENTS";

    //admin database
    private static final String ADMIN_ID ="admin_id" ;
    private static final String PASSWORD_ADMIN ="password" ;
    private static final String NAME_ADMIN ="name" ;
    private static final String TABLE_NAME_ADMIN = "ADMIN";


    //event database
    private static final String EVENT_NAME = "event_name";
    private static final String EVENT_START_DATE = "event_start_date";
    private static final String EVENT_END_DATE = "event_end_date";
    private static final String EVENT_DESCRIPTION = "event_description";
    private static final String TABLE_NAME_EVENT = "EVENTS";


    public AllDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        String sql_student = "Create TABLE " + TABLE_NAME_STUDENT + "( "
                + ENROLMENT_NO + " Varchar(200) NOT NULL constraint student_pk PRIMARY KEY UNIQUE,"
                + PASSWORD_STUDENT + " varchar(200) NOT NULL,"
                + NAME_STUDENT + " varchar(200) NOT NULL"
                + ")";
        String sql_admin = "Create TABLE " + TABLE_NAME_ADMIN + "( "
                + ADMIN_ID + " Varchar(200) NOT NULL constraint admin_pk PRIMARY KEY UNIQUE,"
                + PASSWORD_ADMIN + " varchar(200) NOT NULL,"
                + NAME_ADMIN + " varchar(200) NOT NULL"
                + ")";
        String sql_events = "Create TABLE " + TABLE_NAME_EVENT + "( "
                + EVENT_NAME + " varchar(200) NOT NULL,"
                + EVENT_START_DATE + " varchar(200) ,"
                + EVENT_END_DATE + " varchar(200) ,"
                + EVENT_DESCRIPTION + " varchar(200)"
                + ")";


        sqLiteDatabase.execSQL(sql_student);
        sqLiteDatabase.execSQL(sql_admin);
        sqLiteDatabase.execSQL(sql_events);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String sql_student = "DROP TABLE IF EXISTS " + TABLE_NAME_STUDENT;
        String sql_admin = "DROP TABLE IF EXISTS " + TABLE_NAME_ADMIN;
        String sql_events = "DROP TABLE IF EXISTS " + TABLE_NAME_EVENT;

        sqLiteDatabase.execSQL(sql_student);
        sqLiteDatabase.execSQL(sql_admin);
        sqLiteDatabase.execSQL(sql_events);

        onCreate(sqLiteDatabase);
    }

    public boolean InsertData_Student(String enrollmentNo, String name, String password) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(ENROLMENT_NO,enrollmentNo);
        contentValues.put(NAME_STUDENT,name);
        contentValues.put(PASSWORD_STUDENT,password);

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert(TABLE_NAME_STUDENT,null,contentValues) != -1;

    }

    public boolean checkLogin_Student(String enrolmentNo, String password) {

        Cursor c = getReadableDatabase().rawQuery(
                "SELECT * FROM " + TABLE_NAME_STUDENT + " WHERE "
                        + ENROLMENT_NO + " = '" + enrolmentNo +"' AND " + PASSWORD_STUDENT + " = '"+ password +"' " ,  null);


        if(c.getCount() <= 0) {

            return false;
        } else {

            return true;
        }
    }

    public boolean InsertData_Admin(String admin_id, String name, String password) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(ADMIN_ID,admin_id);
        contentValues.put(NAME_ADMIN,name);
        contentValues.put(PASSWORD_ADMIN,password);

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert(TABLE_NAME_ADMIN,null,contentValues) != -1;

    }

    public boolean checkLogin_Admin(String admin_id, String password) {

        Cursor c = getReadableDatabase().rawQuery(
                "SELECT * FROM " + TABLE_NAME_ADMIN + " WHERE "
                        + ADMIN_ID + " = '" + admin_id +"' AND " + PASSWORD_ADMIN + " = '"+ password +"' " ,  null);


        if(c.getCount() <= 0) {

            return false;
        } else {

            return true;
        }
    }

    public boolean InsertData_Events(String event_name, String startDate, String endDate, String description) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(EVENT_NAME, event_name);
        contentValues.put(EVENT_START_DATE, startDate);
        contentValues.put(EVENT_END_DATE, endDate);
        contentValues.put(EVENT_DESCRIPTION, description);

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert(TABLE_NAME_EVENT,null,contentValues) != -1;

    }

    public List<Events> getEvents() {

        List<Events> eventList = new ArrayList<Events>();

        String read = "Select * From " + TABLE_NAME_EVENT;
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(read,null);
        if(cursor.moveToFirst()) {
            do {
//
//                Events events = new Events();
//                events.setName(cursor.getString(0));
//                events.setStartDate(cursor.getString(1));
//                events.setEndDate(cursor.getString(2));
//                events.setDescription(cursor.getString(3));
//                eventList.add(events);

            } while (cursor.moveToNext());
        }

        return eventList;
    }

}

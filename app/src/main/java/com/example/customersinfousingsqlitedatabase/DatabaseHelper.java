package com.example.customersinfousingsqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "customers_db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "customer";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_MOBILE = "mobile_no";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_PIN_CODE = "pin_code";

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){

        String query = "CREATE TABLE "+TABLE_NAME+"("+KEY_ID+" INTEGER,"+KEY_NAME+" TEXT,"+KEY_MOBILE+" TEXT,"+KEY_ADDRESS+" TEXT,"
                +KEY_PIN_CODE+" INTEGER"+");";
        db.execSQL(query);

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion,int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public Boolean insertData(int id,String name,String mobile,String address,int pinCode){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_ID,id);
        cv.put(KEY_NAME,name);
        cv.put(KEY_MOBILE,mobile);
        cv.put(KEY_ADDRESS,address);
        cv.put(KEY_PIN_CODE,pinCode);
        long status = database.insert(TABLE_NAME,null,cv);
        if (status == -1){
            return false;
        }else {
            return true;
        }
    }

    public ArrayList<CustomerModel> getCustomers(int id){
        ArrayList<CustomerModel> list = new ArrayList<>();
        SQLiteDatabase db =this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+KEY_ID+" =?",new String[]{String.valueOf(id)});
        while (cursor.moveToNext()){
            CustomerModel model = new CustomerModel();
            model.id = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ID));
            model.name = cursor.getString(cursor.getColumnIndexOrThrow(KEY_NAME));
            model.mobile_no = cursor.getString(cursor.getColumnIndexOrThrow(KEY_MOBILE));
            model.address = cursor.getString(cursor.getColumnIndexOrThrow(KEY_ADDRESS));
            model.pin_code = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_PIN_CODE));

            list.add(model);
        }
        return list;
    }
}

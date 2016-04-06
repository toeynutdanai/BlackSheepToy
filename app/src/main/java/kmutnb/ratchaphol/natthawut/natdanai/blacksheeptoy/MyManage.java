package kmutnb.ratchaphol.natthawut.natdanai.blacksheeptoy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by teay on 4/6/2016.
 */
public class MyManage {

    //explicit
    private MyOpenHelper myOpenHelper;
    private SQLiteDatabase sqLiteDatabase;

    public MyManage(Context context) {

        myOpenHelper = new MyOpenHelper(context);
        sqLiteDatabase = myOpenHelper.getWritableDatabase();



    } //Constructor
} // Main Class

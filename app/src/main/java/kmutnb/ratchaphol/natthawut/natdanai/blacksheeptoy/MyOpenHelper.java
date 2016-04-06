package kmutnb.ratchaphol.natthawut.natdanai.blacksheeptoy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by teay on 4/6/2016.
 */
public class MyOpenHelper extends SQLiteOpenHelper{

    //Explicit
    public static final String database_name = "Sheep.db";
    private static final int database_version = 1;

    private static final String create_user_table = "create table userTABLE (" +
            "_id integer primary key, " +
            "Name text," +
            "Surname text," +
            "idCard text," +
            "User text," +
            "Password text," +
            "Email text," +
            "Phone text);";

    private static final String create_product_table = "create table productTABLE (" +
            "_id integer primary key," +
            "Name text," +
            "Brand text," +
            "Price text," +
            "Stock text," +
            "Used text," +
            "Detail text," +
            "Image1 text," +
            "Image2 text," +
            "Image3 text," +
            "Image4 text," +
            "Image5 text);";



    public MyOpenHelper(Context context) {
        super(context, database_name, null, database_version);
    } //Constructor

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(create_product_table);
        sqLiteDatabase.execSQL(create_user_table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
} //Main Class

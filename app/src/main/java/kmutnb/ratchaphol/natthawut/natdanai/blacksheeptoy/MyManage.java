package kmutnb.ratchaphol.natthawut.natdanai.blacksheeptoy;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by teay on 4/6/2016.
 */
public class MyManage {

    //explicit
    private MyOpenHelper myOpenHelper;
    private SQLiteDatabase sqLiteDatabase;

    public static final String user_table = "userTABLE";
    public static final String column_id = "_id";
    public static final String column_Name = "Name";
    public static final String column_Surname = "Surname";
    public static final String column_idCard = "idCard";
    public static final String column_User = "User";
    public static final String column_Password = "Password";
    public static final String column_Email = "Email";
    public static final String column_Phone = "Phone";


    public static final String product_table = "productTABLE";
    public static final String column_Brand = "Brand";
    public static final String column_Price = "Price";
    public static final String column_Stock = "Stock";
    public static final String column_Used = "Used";
    public static final String column_Detail = "Detail";
    public static final String column_Image1 = "Image1";
    public static final String column_Image2 = "Image2";
    public static final String column_Image3 = "Image3";
    public static final String column_Image4 = "Image4";
    public static final String column_Image5 = "Image5";

    public static final String order_table = "orderTABLE";
    public static final String colunm_IDuser = "ID_User";
    public static final String colunm_Date = "Date";
    public static final String colunm_Product = "Product";
    public static final String colunm_SentTo = "Sent_To";

    public MyManage(Context context) {

        myOpenHelper = new MyOpenHelper(context);
        sqLiteDatabase = myOpenHelper.getWritableDatabase();


    } //Constructor

    public long addOrder(String strID_User,
                         String strDate,
                         String strSent_To,
                         String strProduct,
                         String strPrice) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(colunm_IDuser, strID_User);
        contentValues.put(colunm_Date, strDate);
        contentValues.put(colunm_SentTo, strSent_To);
        contentValues.put(colunm_Product, strProduct);
        contentValues.put(column_Price, strPrice);



        return sqLiteDatabase.insert(order_table, null, contentValues);
    }

    public long addProduct(String strName,
                           String strBrand,
                           String strPrice,
                           String strStock,
                           String strUsed,
                           String strDetail,
                           String strImage1,
                           String strImage2,
                           String strImage3,
                           String strImage4,
                           String strImage5) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(column_Name, strName);
        contentValues.put(column_Brand, strBrand);
        contentValues.put(column_Price, strPrice);
        contentValues.put(column_Stock, strStock);
        contentValues.put(column_Used, strUsed);
        contentValues.put(column_Detail, strDetail);
        contentValues.put(column_Image1, strImage1);
        contentValues.put(column_Image2, strImage2);
        contentValues.put(column_Image3, strImage3);
        contentValues.put(column_Image4, strImage4);
        contentValues.put(column_Image5, strImage5);
        return sqLiteDatabase.insert(product_table, null, contentValues);
    }


    public long addUser(String strID,
                        String strName,
                        String strSurname,
                        String strIDcard,
                        String strUser,
                        String strPassword,
                        String strEmail,
                        String strPhone) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(column_id, strID);
        contentValues.put(column_Name, strName);
        contentValues.put(column_Surname, strSurname);
        contentValues.put(column_idCard, strIDcard);
        contentValues.put(column_User, strUser);
        contentValues.put(column_Password, strPassword);
        contentValues.put(column_Email, strEmail);
        contentValues.put(column_Phone, strPhone);
        return sqLiteDatabase.insert(user_table, null, contentValues);
    }

} // Main Class

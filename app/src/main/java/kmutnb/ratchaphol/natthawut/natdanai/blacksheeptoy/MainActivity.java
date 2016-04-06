package kmutnb.ratchaphol.natthawut.natdanai.blacksheeptoy;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private MyManage myManage;
    private EditText userEditText, passwordEditText;
    private String userString, passwordString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        userEditText = (EditText) findViewById(R.id.editText);
        passwordEditText = (EditText) findViewById(R.id.editText2);

        myManage = new MyManage(this);

        //Test Add Value
        //testAdd();

        //Delete All SQLite
        deleteAllSQLite();

        //Synchronize JSON to SQLite
        synJSON();


    } //Main Method

    public void clickSignIn(View view) {

        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

        //Check Space
        if (userString.equals("") || passwordString.equals("")) {

            MyAlertDialog myAlertDialog = new MyAlertDialog();
            myAlertDialog.myDialog(this,R.drawable.icon_myaccount,
                    "มีช่องว่าง","กรุณากรอกทุกช่องค่ะ");

        } else {

            checkUser();

        }

    }//clickSignIn

    private void checkUser() {

        try {

            SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                    MODE_PRIVATE, null);

        } catch (Exception e) {
            MyAlertDialog myAlertDialog = new MyAlertDialog();
            myAlertDialog.myDialog(this, R.drawable.icon_myaccount,
                    "ไม่มี User นี้","ไม่มี" + userString + "ในฐานข้อมูลของเรา");
        }


    }//CheckUser


    private void synJSON() {

        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy
                .Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);

        int intTABLE = 0;
        while (intTABLE <=1) {

            InputStream inputStream = null;
            String strJSON = null;
            String strLine = null;
            String[] urlJSONStrings = {"http://swiftcodingthai.com/sheep/php_get_user.php",
                    "http://swiftcodingthai.com/sheep/php_get_product.php"};

            try {

                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(urlJSONStrings[intTABLE]);
                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                inputStream = httpEntity.getContent();

                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(inputStream, "UTF-8")
                );
                StringBuilder stringBuilder = new StringBuilder();
                while ((strLine = bufferedReader.readLine()) != null) {

                    stringBuilder.append(strLine);

                }
                inputStream.close();
                strJSON = stringBuilder.toString();

                JSONArray jsonArray = new JSONArray(strJSON);
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    switch (intTABLE) {
                        case 0:

                            String strName = jsonObject.getString(MyManage.column_Name);
                            String strSurname = jsonObject.getString(MyManage.column_Surname);
                            String strIDcard = jsonObject.getString(MyManage.column_idCard);
                            String strUser = jsonObject.getString(MyManage.column_User);
                            String strPassword = jsonObject.getString(MyManage.column_Password);
                            String strEmail = jsonObject.getString(MyManage.column_Email);
                            String strPhone = jsonObject.getString(MyManage.column_Phone);

                            myManage.addUser(strName, strSurname, strIDcard, strUser, strPassword, strEmail, strPhone);
                            break;
                        case 1:

                            String strNameProduct = jsonObject.getString(MyManage.column_Name);
                            String strBrand = jsonObject.getString(MyManage.column_Brand);
                            String strPrice = jsonObject.getString(MyManage.column_Price);
                            String strStock = jsonObject.getString(MyManage.column_Stock);
                            String strUsed = jsonObject.getString(MyManage.column_Used);
                            String strDetail = jsonObject.getString(MyManage.column_Detail);
                            String strImage1 = jsonObject.getString(MyManage.column_Image1);
                            String strImage2 = jsonObject.getString(MyManage.column_Image2);
                            String strImage3 = jsonObject.getString(MyManage.column_Image3);
                            String strImage4 = jsonObject.getString(MyManage.column_Image4);
                            String strImage5 = jsonObject.getString(MyManage.column_Image5);

                            myManage.addProduct(strNameProduct, strBrand, strPrice, strStock, strUsed, strDetail, strImage1,
                                    strImage2, strImage3, strImage4, strImage5);

                            break;
                    } // switch
                } //for


            } catch (Exception e) {
                Log.d("Sheep", "Error ==>" + e.toString());
            }


            intTABLE += 1;
        } //while


    } //synJSON

    private void deleteAllSQLite() {
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);
        sqLiteDatabase.delete(MyManage.product_table, null, null);
        sqLiteDatabase.delete(MyManage.user_table, null, null);
    }

    private void testAdd() {
        myManage.addUser("name", "sur", "idcard", "user", "pass", "email", "phone");
        myManage.addProduct("name", "brand", "price", "stock", "used",
                "detail", "image1", "image2", "image3", "image4", "image5");
    }

    public void clickSignUp(View view) {
        startActivity(new Intent(MainActivity.this, SignUpActivity.class));
    }

} //Main class

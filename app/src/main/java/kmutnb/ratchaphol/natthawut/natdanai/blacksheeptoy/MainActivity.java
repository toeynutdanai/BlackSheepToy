package kmutnb.ratchaphol.natthawut.natdanai.blacksheeptoy;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private MyManage myManage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myManage = new MyManage(this);

        //Test Add Value
        //testAdd();

        //Delete All SQLite
        deleteAllSQLite();


    } //Main Method

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

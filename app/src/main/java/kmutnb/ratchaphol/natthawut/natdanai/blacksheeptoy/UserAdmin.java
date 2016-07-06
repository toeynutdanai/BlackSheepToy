package kmutnb.ratchaphol.natthawut.natdanai.blacksheeptoy;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class UserAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_admin);

        createListview();
    }

    private void createListview() {
        ListView listView = (ListView) findViewById(R.id.listView3);
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM userTABLE WHERE _id != 40",
                null);
        cursor.moveToFirst();
        int intCount = cursor.getCount();

        final String[] userStrings = new String[intCount];
        final String[] nameStrings = new String[intCount];
        final String[] surnameStrings = new String[intCount];
        final String[] emailStrings = new String[intCount];
        final String[] phoneStrings = new String[intCount];


        for (int i = 0; i < intCount; i++) {

            userStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_User));
            nameStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Name));
            surnameStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Surname));
            emailStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Email));
            phoneStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Phone));


            cursor.moveToNext();

        }//for
        cursor.close();

        UserAdminAdapter userAdminAdapter = new UserAdminAdapter(this, userStrings,
                nameStrings, surnameStrings, emailStrings, phoneStrings);

        listView.setAdapter(userAdminAdapter);

    }

    public void cilckBack(View view) {
        finish();
    }
}

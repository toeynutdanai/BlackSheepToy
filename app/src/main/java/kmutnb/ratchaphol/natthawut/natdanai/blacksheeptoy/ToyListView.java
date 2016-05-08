package kmutnb.ratchaphol.natthawut.natdanai.blacksheeptoy;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.AvoidXfermode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class ToyListView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toy_list_view);

        ListView listView = (ListView) findViewById(R.id.listView);

        //Read All productTABLE
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM productTABLE", null);
        cursor.moveToFirst();
        int intCount = cursor.getCount();

        String[] nameStrings = new String[intCount];
        String[] brandStrings = new String[intCount];
        String[] priceStrings = new String[intCount];
        String[] stockStrings = new String[intCount];
        String[] usedStrings = new String[intCount];
        String[] detailStrings = new String[intCount];
        String[] image1String = new String[intCount];
        String[] image2String = new String[intCount];
        String[] image3String = new String[intCount];
        String[] image4String = new String[intCount];
        String[] image5String = new String[intCount];

        for (int i = 0; i < intCount; i++) {

            nameStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Name));
            brandStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Brand));
            priceStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Price));
            stockStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Stock));
            usedStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Used));
            detailStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Detail));
            image1String[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Image1));
            image2String[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Image2));
            image3String[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Image3));
            image4String[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Image4));
            image5String[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Image5));

            cursor.moveToNext();

        }//for
        cursor.close();

        ToyAdapter toyAdapter = new ToyAdapter(this, nameStrings, brandStrings, priceStrings, stockStrings,
                usedStrings, detailStrings, image1String);
        listView.setAdapter(toyAdapter);

    } // Main Method

    public void clickReadOrder(View view) {

    }// clickReadOrder

} // Main Class

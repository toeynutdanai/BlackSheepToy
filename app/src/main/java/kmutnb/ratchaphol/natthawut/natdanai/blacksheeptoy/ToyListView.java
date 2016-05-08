package kmutnb.ratchaphol.natthawut.natdanai.blacksheeptoy;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.AvoidXfermode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ToyListView extends AppCompatActivity {

    private String strID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toy_list_view);

        ListView listView = (ListView) findViewById(R.id.listView);

        strID = getIntent().getStringExtra("ID_User");

        //Read All productTABLE
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM productTABLE", null);
        cursor.moveToFirst();
        int intCount = cursor.getCount();

        final String[] nameStrings = new String[intCount];
        final String[] brandStrings = new String[intCount];
        final String[] priceStrings = new String[intCount];
        final String[] stockStrings = new String[intCount];
        final String[] usedStrings = new String[intCount];
        final String[] detailStrings = new String[intCount];
        final String[] image1String = new String[intCount];
        final String[] image2String = new String[intCount];
        final String[] image3String = new String[intCount];
        final String[] image4String = new String[intCount];
        final String[] image5String = new String[intCount];

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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                Intent intent = new Intent(ToyListView.this, ToyDetail.class);

                intent.putExtra("ID_User", strID);
                intent.putExtra("Name", nameStrings[i]);
                intent.putExtra("Brand", brandStrings[i]);
                intent.putExtra("Price", priceStrings[i]);
                intent.putExtra("Stock", stockStrings[i]);
                intent.putExtra("Used", usedStrings[i]);
                intent.putExtra("Detail", detailStrings[i]);
                intent.putExtra("Image1", image1String[i]);
                intent.putExtra("Image2", image2String[i]);
                intent.putExtra("Image3", image3String[i]);
                intent.putExtra("Image4", image4String[i]);
                intent.putExtra("Image5", image5String[i]);

                startActivity(intent);

            } //onItemClick
        });

    } // Main Method

    public void clickReadOrder(View view) {


        if (checkOrder()) {
            //have data

        } else {
            //no data
            MyAlertDialog myAlertDialog = new MyAlertDialog();
            myAlertDialog.myDialog(this, R.drawable.icon_myaccount,
                    "ยังไม่มี Order" , "โปรดเลือกสินค้า");

        }

    }// clickReadOrder

    private boolean checkOrder() {

        boolean result = true;
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,MODE_PRIVATE,null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + MyManage.order_table,null);
        cursor.moveToFirst();

        if (cursor.getCount() == 0) {
            result = false;

        }

        return result;
    }

} // Main Class

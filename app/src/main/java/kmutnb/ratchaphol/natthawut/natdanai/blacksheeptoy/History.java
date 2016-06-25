package kmutnb.ratchaphol.natthawut.natdanai.blacksheeptoy;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class History extends AppCompatActivity {


    private String strID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        strID = getIntent().getStringExtra("ID_User");


        createListview();

    }//Main Method

    private void createListview() {

        ListView listView = (ListView) findViewById(R.id.listView3);

        final SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);
        final Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM historyTABLE WHERE IDUser =" + "'" +
                strID + "'",null);
        cursor.moveToFirst();

        String[] Ref = new String[cursor.getCount()];
        String[] IDUser = new String[cursor.getCount()];
        String[] Date = new String[cursor.getCount()];
        String[] Name = new String[cursor.getCount()];
        String[] Surname = new String[cursor.getCount()];
        String[] Address = new String[cursor.getCount()];
        String[] Product = new String[cursor.getCount()];
        String[] Price = new String[cursor.getCount()];
        String[] Piece = new String[cursor.getCount()];
        String[] Total = new String[cursor.getCount()];
        String[] Status = new String[cursor.getCount()];

        //for (int i = 0; i < cursor.getCount(); i++) {
            int count = cursor.getCount();
        //}

        for (int i=0;i<cursor.getCount();i++) {

            Ref[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Ref));
            IDUser[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_IDuser));
            Date[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Date));
            Name[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Name));
            Surname[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Surname));
            Address[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Address));
            Product[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Product));
            Price[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Price));
            Piece[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Piece));
            Total[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Total));
            Status[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Status));

            cursor.moveToNext();
        }   // for
        cursor.close();

        HistoryAdapter historyAdapter = new HistoryAdapter(this,Ref,IDUser,Date,Name,
                Surname,Address,Product,Price,Piece,Total,Status);

        listView.setAdapter(historyAdapter);


    }

    public void clickCancel(View view) {
        Intent intent = new Intent(History.this, ToyListView.class);
        intent.putExtra("ID_User", strID);
        startActivity(intent);
    }
}//Main Class

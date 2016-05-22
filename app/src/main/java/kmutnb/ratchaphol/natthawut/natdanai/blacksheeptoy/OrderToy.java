package kmutnb.ratchaphol.natthawut.natdanai.blacksheeptoy;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class OrderToy extends AppCompatActivity {

    //Explicit
    private String idString, dateString, recieveNoString;
    private TextView nameUserTextView, surnameTextView, dateTextView,
            recieveNoTextView, totalTextView;

    private String[] useStrings;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_toy);

        //Bind Widget
        BindWidget();

        //find NameSurname
        findNameSurname();

        //Find Date
        findDate();

        //Find receiveNo
        findRecieveNo();


        //Create Listview
        createListView();

    } // Main Method

    private void findRecieveNo() {

        String[] resultStrings = dateString.split("/");
        recieveNoString = "Ref-" +
                useStrings[4] + "-" +
                resultStrings[0] + "-" +
                resultStrings[1] + "-" +
                resultStrings[2];

        recieveNoTextView.setText(recieveNoString);


    }

    private void createListView() {

        int intTotal = 0;

        final SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);
        final Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM orderTABLE", null);
        cursor.moveToFirst();

        String[] productStrings = new String[cursor.getCount()];
        String[] priceStrings = new String[cursor.getCount()];
        int[] priceInts = new int[cursor.getCount()];

        for (int i = 0; i < cursor.getCount(); i++) {

            productStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.colunm_Product));
            priceStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Price));
            priceInts[i] = Integer.parseInt(priceStrings[i]);

            intTotal = intTotal + priceInts[i];

            cursor.moveToNext();

        }//for
        cursor.close();

        totalTextView.setText(Integer.toString(intTotal));


        ReceiveAdapter receiveAdapter = new ReceiveAdapter(this,
                productStrings, priceStrings);
        listView.setAdapter(receiveAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                final SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                        MODE_PRIVATE, null);
                final Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM orderTABLE", null);
                cursor.moveToFirst();

                cursor.moveToPosition(i);
                int intID = Integer.parseInt(cursor.getString(0));
                Log.d("22MayV2", "intID ==>" + intID);

                sqLiteDatabase.delete("orderTABLE", "_id = " + intID, null);
                createListView();

            } //onItem
        });
        cursor.close();

    }//create listview

    private void findDate() {

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateString = dateFormat.format(date);
        Log.d("22MayV1", "dateString ==> " + dateString);
        dateTextView.setText(dateString);

    }

    private void findNameSurname() {
        idString = getIntent().getStringExtra("ID_User");
        Log.d("22MayV1", "idString ==> " + idString);

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM userTABLE WHERE _id = " + "'" + idString + "'", null);
        cursor.moveToFirst();

        useStrings = new String[cursor.getColumnCount()];
        for (int i = 0; i < cursor.getColumnCount(); i++) {
            useStrings[i] = cursor.getString(i);
        } //for

        nameUserTextView.setText(useStrings[1]);
        surnameTextView.setText(useStrings[2]);

    } //findNameSurname

    private void BindWidget() {
        nameUserTextView = (TextView) findViewById(R.id.textView16);
        surnameTextView = (TextView) findViewById(R.id.textView17);
        dateTextView = (TextView) findViewById(R.id.textView15);
        listView = (ListView) findViewById(R.id.listView2);
        recieveNoTextView = (TextView) findViewById(R.id.textView23);
        totalTextView = (TextView) findViewById(R.id.textView19);
    }

    public void clickAddMore(View view) {
        finish();
    }

    public void clickCheckBill(View view) {

    }

} // Main Calss

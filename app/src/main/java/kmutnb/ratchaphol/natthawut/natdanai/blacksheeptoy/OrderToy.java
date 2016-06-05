package kmutnb.ratchaphol.natthawut.natdanai.blacksheeptoy;;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderToy extends AppCompatActivity {

    //Explicit
    private String idString, dateString, receiveNoString, addressString;
    private TextView nameUserTextView, surnameTextView,
            dateTextView, receiveNoTextView, totalTextView;
    private String[] userStrings;
    private ListView listView;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_toy);

        //Bind Widget
        bindWidget();

        //find Name Surname
        findNameSurname();

        //Find Date
        findDate();

        //Find receiveNo
        findReceiveNo();

        //Create ListView
        createListView();


    }   // Main Method

    private void findReceiveNo() {

        String[] resultStrings = dateString.split("/");
        receiveNoString = "Ref-" +
                userStrings[4] + "-" +
                resultStrings[0] + "-" +
                resultStrings[1] + "-" +
                resultStrings[2];

        receiveNoTextView.setText(receiveNoString);

    }

    public void createListView() {

        int intTotal = 0;

        final SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);
        final Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM orderTABLE", null);
        cursor.moveToFirst();

        String[] productStrings = new String[cursor.getCount()];
        String[] priceStrings = new String[cursor.getCount()];
        int[] priceInts = new int[cursor.getCount()];

        for (int i=0;i<cursor.getCount();i++) {

            productStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.colunm_Product));
            priceStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Price));
            priceInts[i] = Integer.parseInt(priceStrings[i]);

            intTotal = intTotal + priceInts[i];

            cursor.moveToNext();
        }   // for
        cursor.close();

        totalTextView.setText(Integer.toString(intTotal));

        ReceiveAdapter receiveAdapter = new ReceiveAdapter(this,
                productStrings, priceStrings);
        listView.setAdapter(receiveAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                final SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.
                                database_name,
                        MODE_PRIVATE, null);
                final Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM orderTABLE",
                        null);
                cursor.moveToFirst();

                cursor.moveToPosition(i);
                int intID = Integer.parseInt(cursor.getString(0));
                Log.d("22MayV2", "intID ==> " + intID);

                sqLiteDatabase.delete("orderTABLE", "_id = " + intID, null);
                createListView();

            }   // onItem
        });
        cursor.close();
    }   // createListView

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
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM userTABLE WHERE _id = "
                + "'" + idString + "'", null);
        cursor.moveToFirst();

        userStrings = new String[cursor.getColumnCount()];
        for (int i=0;i<cursor.getColumnCount();i++) {
            userStrings[i] = cursor.getString(i);
        }   // for

        nameUserTextView.setText(userStrings[1]);
        surnameTextView.setText(userStrings[2]);

    }   // findNameSurname

    private void bindWidget() {
        nameUserTextView = (TextView) findViewById(R.id.textView16);
        surnameTextView = (TextView) findViewById(R.id.textView17);
        dateTextView = (TextView) findViewById(R.id.textView15);
        listView = (ListView) findViewById(R.id.listView2);
        receiveNoTextView = (TextView) findViewById(R.id.textView23);
        totalTextView = (TextView) findViewById(R.id.textView19);
        editText = (EditText) findViewById(R.id.editText8);
    }

    public void clickAddMore(View view) {

        finish();
    }

    public void clickCheckBill(View view) {

        addressString = editText.getText().toString().trim();

        if (addressString.equals("")) {
            //Not Fill Address
            MyAlertDialog myAlertDialog = new MyAlertDialog();
            myAlertDialog.myDialog(this, R.drawable.danger,
                    "ส่งที่ไหน ?", "โปรดระบุสถานที่ส่งของด้วยคะ");
        } else {
            //Have Address
            checkProduct();
            uploadOrderToServer();
            SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                    MODE_PRIVATE, null);
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT*FROM orderTABLE", null);
            cursor.moveToFirst();
            sqLiteDatabase.delete("orderTABLE",null,null);
            Toast.makeText(this, "ขอบคุณครับ ทางเราได้รับ order แล้ว", Toast.LENGTH_SHORT).show();
        }

    }   // clickCheckBill

    private void checkProduct() {


        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM orderTABLE ", null);
        cursor.moveToFirst();
        int count = cursor.getCount();
        Log.i("Count order", Integer.toString(count));
        String[] NameProduct = new String[count];

        for (int i = 0 ; i< count ; i++) {

            NameProduct[i] = cursor.getString(cursor.getColumnIndex("Product"));
            //NameProduct[i] = cursor.getString(cursor.getColumnIndex("Product"));
            cursor.moveToNext();
            Log.i("NameProduct/11", NameProduct[i]);
        }
        cursor.close();

        for (int i = 0; i< count; i++) {
            Log.i("Namestring[0]", NameProduct[0]);
            Cursor cursor1 = sqLiteDatabase.rawQuery("SELECT * FROM productTABLE WHERE Name = "+"'"+ NameProduct[i]+"'",null);
            //Cursor cursor1 = sqLiteDatabase.rawQuery("SELECT * FROM productTABLE WHERE 'Name' ="
            //        + "'" + NameProduct[i] + "'", null);
            cursor1.moveToFirst();

            int check = cursor1.getCount();
            Log.i("check", Integer.toString(check));


            //int Stock = cursor1.getCount();
            String Stock = cursor1.getString(cursor1.getColumnIndex("Stock"));
            int Stocknow = Integer.parseInt(Stock) - 1;

            Log.i("Stock", Stock);
            Log.i("Stocknow", Integer.toString(Stocknow));


            ContentValues contentValues = new ContentValues();
            contentValues.put("Stock", Stocknow);

            sqLiteDatabase.update("productTABLE", contentValues, "Name =" +
                    "'"+ NameProduct[i] + "'", null);

        }




    }//Check Product

    private void uploadStockToServer() {

    }

    private void uploadOrderToServer() {

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM orderTABLE", null);
        cursor.moveToFirst();

        String[] productStrings = new String[cursor.getCount()];
        String[] priceStrings = new String[cursor.getCount()];

        for (int i=0;i<cursor.getCount();i++) {

            String strURL = "http://swiftcodingthai.com/sheep/add_order.php";
            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("isAdd", "true")
                    .add("Ref", receiveNoString)
                    .add("Date", dateString)
                    .add("Name", userStrings[1])
                    .add("Surname", userStrings[2])
                    .add("Address", addressString)
                    .add("Product", cursor.getString(cursor.getColumnIndex(MyManage.
                            colunm_Product)))
                    .add("Price", cursor.getString(cursor.getColumnIndex(MyManage.
                            column_Price)))
                    .build();
            Request.Builder builder = new Request.Builder();
            Request request = builder.url(strURL).post(requestBody).build();
            Call call = okHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {

                }

                @Override
                public void onResponse(Response response) throws IOException {
                    finish();
                }
            });


            cursor.moveToNext();
        }   //for

    }   // upload

}   // Main Class
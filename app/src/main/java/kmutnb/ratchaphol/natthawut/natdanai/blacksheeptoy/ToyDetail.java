package kmutnb.ratchaphol.natthawut.natdanai.blacksheeptoy;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ToyDetail extends AppCompatActivity {

    //Explicit
    private TextView nameTextView, brandTextView, priceTextView, usedTextView,
            stockTextView, detailTextView;

    private ImageView imageView;
    private String idString, nameString, brandString, priceString,
            usedString, stockString, detailString, image1String, image2String, image3String, image4String,
            image5String;
    private String[] imageStrings;
    private int indexAnInt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toy_detail);

        //Bind Widget
        bindWidget();

        //Recieve Value
        recieveValue();

        //Show View
        showView();

        //Change Image
        changeImage(imageStrings[0]);

    } //Main Method

    private void changeImage(String imageString) {
        Picasso.with(this).load(imageString).resize(150,200).into(imageView);


    }//change image

    private void showView() {

        nameTextView.setText(nameString);
        brandTextView.setText(brandString);
        priceTextView.setText(priceString);
        usedTextView.setText(findUsed(usedString));
        stockTextView.setText(stockString);
        detailTextView.setText(detailString);




    }//ShowView

    private String findUsed(String usedString) {

        String[] resultStrings = {"", "มือ 1", "มือ 2"};
        int index = Integer.parseInt(usedString);

        return resultStrings[index];
    };

    private void recieveValue() {

        idString = getIntent().getStringExtra("ID_User");
        nameString = getIntent().getStringExtra("Name");
        brandString = getIntent().getStringExtra("Brand");
        priceString = getIntent().getStringExtra("Price");
        usedString = getIntent().getStringExtra("Used");
        stockString = getIntent().getStringExtra("Stock");
        detailString = getIntent().getStringExtra("Detail");

        imageStrings = new String[32];
        imageStrings[0] = getIntent().getStringExtra("Image1");
        imageStrings[1] = getIntent().getStringExtra("Image2");
        imageStrings[2] = getIntent().getStringExtra("Image3");
        imageStrings[3] = getIntent().getStringExtra("Image4");
        imageStrings[4] = getIntent().getStringExtra("Image5");
        imageStrings[5] = getIntent().getStringExtra("Image6");
        imageStrings[6] = getIntent().getStringExtra("Image7");
        imageStrings[7] = getIntent().getStringExtra("Image8");
        imageStrings[8] = getIntent().getStringExtra("Image9");
        imageStrings[9] = getIntent().getStringExtra("Image10");
        imageStrings[10] = getIntent().getStringExtra("Image11");
        imageStrings[11] = getIntent().getStringExtra("Image12");
        imageStrings[12] = getIntent().getStringExtra("Image13");
        imageStrings[13] = getIntent().getStringExtra("Image14");
        imageStrings[14] = getIntent().getStringExtra("Image15");
        imageStrings[15] = getIntent().getStringExtra("Image16");
        imageStrings[16] = getIntent().getStringExtra("Image17");
        imageStrings[17] = getIntent().getStringExtra("Image18");
        imageStrings[18] = getIntent().getStringExtra("Image19");
        imageStrings[19] = getIntent().getStringExtra("Image20");
        imageStrings[20] = getIntent().getStringExtra("Image21");
        imageStrings[21] = getIntent().getStringExtra("Image22");
        imageStrings[22] = getIntent().getStringExtra("Image23");
        imageStrings[23] = getIntent().getStringExtra("Image24");
        imageStrings[24] = getIntent().getStringExtra("Image25");
        imageStrings[25] = getIntent().getStringExtra("Image26");
        imageStrings[26] = getIntent().getStringExtra("Image27");
        imageStrings[27] = getIntent().getStringExtra("Image28");
        imageStrings[28] = getIntent().getStringExtra("Image29");
        imageStrings[29] = getIntent().getStringExtra("Image30");
        imageStrings[30] = getIntent().getStringExtra("Image31");
        imageStrings[31] = getIntent().getStringExtra("Image32");



    }//RecieveValue

    private void bindWidget() {

        nameTextView = (TextView) findViewById(R.id.textView10);
        brandTextView = (TextView) findViewById(R.id.textView11);
        priceTextView = (TextView) findViewById(R.id.textView12);
        usedTextView = (TextView) findViewById(R.id.textView13);
        stockTextView = (TextView) findViewById(R.id.textView14);
        detailTextView = (TextView) findViewById(R.id.editText5);
        imageView = (ImageView) findViewById(R.id.imageView2);


    }

    public void clickDEC(View view) {

        indexAnInt -= 1;
        if (indexAnInt < 0) {
            indexAnInt = 31;
        }
        changeImage(imageStrings[indexAnInt]);

    }

    public void clickINC(View view) {

        indexAnInt += 1;
        if (indexAnInt >= 32 ) {
            indexAnInt = 0;
        }
        changeImage(imageStrings[indexAnInt]);
    }

    public void clickCancelDetail(View view) {

        finish();

    }

    //public void clickOrderDetail(View view) { ของเก่ารอมาแก้
        public void clickOrderDetail(View view) {

        //if (checkProduct(nameString) || checkOrderTABLE()) { รอมาแก้
            if (true) {
            //True สั่งได้

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            String strDate = dateFormat.format(date);

            MyManage myManage = new MyManage(this);
            myManage.addOrder(idString, strDate, "n/a", nameString, priceString);

            Toast.makeText(this, "บันทึก" + nameString + "แล้ว", Toast.LENGTH_SHORT).show();

            finish();
        } else {
            //False สั่งไม่ได้

            MyAlertDialog myAlertDialog = new MyAlertDialog();
            myAlertDialog.myDialog(this,R.drawable.icon_myaccount,
                    "ไม่สามารถสั่งซื้อได้" , "มีสินค้าอยู่ในตะกร้าแล้ว");
        }
    } //clickOrderdetail

    private boolean checkOrderTABLE() {

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM orderTABLE", null);
        cursor.moveToFirst();

        if (cursor.getCount() == 0) {
            return true;
        } else {
            return false;
        }

    }

    private boolean checkProduct(String nameString) {
        try {

            SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                    MODE_PRIVATE, null);
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM orderTABLE WHERE Product = " + "'" + nameString + "'", null);
            cursor.moveToFirst();

            return false;

        } catch (Exception e) {
            return true;

        }


    }


} //Main Class

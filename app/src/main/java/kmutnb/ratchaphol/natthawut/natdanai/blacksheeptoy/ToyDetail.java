package kmutnb.ratchaphol.natthawut.natdanai.blacksheeptoy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ToyDetail extends AppCompatActivity {

    //Explicit
    private TextView nameTextView, brandTextView, priceTextView, usedTextView,
            stockTextView, detailTextView;

    private ImageView imageView;
    private String idString, nameString, brandString, priceString,
            usedString, stockString, detailString, image1String, image2String, image3String, image4String,
            image5String;
    private String[] imageStrings;

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
        Picasso.with(this).load(imageString).resize(200,200).into(imageView);


    }//change image

    private void showView() {

        nameTextView.setText(nameString);
        brandTextView.setText(brandString);
        priceTextView.setText(priceString);
        usedTextView.setText(usedString);
        stockTextView.setText(stockString);
        detailTextView.setText(detailString);




    }//ShowView

    private void recieveValue() {

        idString = getIntent().getStringExtra("ID_User");
        nameString = getIntent().getStringExtra("Name");
        brandString = getIntent().getStringExtra("Brand");
        priceString = getIntent().getStringExtra("Price");
        usedString = getIntent().getStringExtra("Used");
        stockString = getIntent().getStringExtra("Stock");
        detailString = getIntent().getStringExtra("Detail");

        imageStrings = new String[5];
        imageStrings[0] = getIntent().getStringExtra("Image1");
        imageStrings[1] = getIntent().getStringExtra("Image2");
        imageStrings[2] = getIntent().getStringExtra("Image3");
        imageStrings[3] = getIntent().getStringExtra("Image4");
        imageStrings[4] = getIntent().getStringExtra("Image5");

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

    }

    public void clickINC(View view) {

    }

    public void clickCancelDetail(View view) {

    }

    public void clickOrderDetail(View view) {

    }


} //Main Class

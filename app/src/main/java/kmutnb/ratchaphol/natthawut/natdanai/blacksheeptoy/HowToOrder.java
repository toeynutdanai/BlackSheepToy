package kmutnb.ratchaphol.natthawut.natdanai.blacksheeptoy;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HowToOrder extends AppCompatActivity {

    private String strID = new String();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_order);

        strID = getIntent().getStringExtra("ID_User");

    }//Main Method

    public void onClickContactUs(View view) {

        Intent contactIntent = new Intent(Intent.ACTION_VIEW);
        contactIntent.setData(Uri.parse("https://www.facebook.com/BlackSheepTOY/?fref=ts"));
        startActivity(contactIntent);


    }

    public void onClickCancel(View view) {
        Intent intent = new Intent(HowToOrder.this, ToyListView.class);
        intent.putExtra("ID_User", strID);
        startActivity(intent);
    }


}//Main class

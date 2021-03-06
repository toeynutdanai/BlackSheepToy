package kmutnb.ratchaphol.natthawut.natdanai.blacksheeptoy;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class OrderAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_admin);

        createListview();
    }

    private void createListview() {

        ListView listView = (ListView) findViewById(R.id.listView4);

        final SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);
        final Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM historyTABLE WHERE Admin = 'available'", null);
        cursor.moveToFirst();


        int count = cursor.getCount();
        String[] Bill = new String[count];

        for (int i = 0; i < cursor.getCount(); i++ ) {
            Bill[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Ref));
            if (i > 1 && Bill[i].matches(Bill[i - 1])) {
                count--;
                cursor.moveToNext();

            } else {
                cursor.moveToNext();
            }

        }
        cursor.moveToFirst();

        final String[] Ref = new String[count];
        String[] IDUser = new String[count];
        final String[] Date = new String[count];
        final String[] Name = new String[count];
        final String[] Surname = new String[count];
        final String[] Address = new String[count];
        String[] Product = new String[count];
        String[] Price = new String[count];
        String[] Piece = new String[count];
        final String[] Total = new String[count];
        final String[] Status = new String[count];



        for (int i = 0; i < count;) {
            Ref[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Ref));
            if (i > 1 && Ref[i].matches(Ref[i - 1])) {
                cursor.moveToNext();

            } else {
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

                i++;

                cursor.moveToNext();

            }


        }

        cursor.close();

        HistoryAdapter historyAdapter = new HistoryAdapter(this,Ref,IDUser,Date,Name,
                Surname,Address,Product,Price,Piece,Total,Status);

        listView.setAdapter(historyAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(OrderAdmin.this, OrderAdminDetail.class);

                intent.putExtra("Ref", Ref[position]);
                intent.putExtra("Admin", "delete");
                intent.putExtra("Title", "ต้องการลบบิลนี้ใช่หรือไม่?");

                startActivity(intent);

            }//on item click
        });


    }

    public void clickAllOrder(View view) {
        ListView listView = (ListView) findViewById(R.id.listView4);

        final SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);
        final Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM historyTABLE WHERE Admin = 'available'", null);
        cursor.moveToFirst();


        int count = cursor.getCount();
        String[] Bill = new String[count];

        for (int i = 0; i < cursor.getCount(); i++ ) {
            Bill[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Ref));
            if (i > 1 && Bill[i].matches(Bill[i - 1])) {
                count--;
                cursor.moveToNext();

            } else {
                cursor.moveToNext();
            }

        }
        cursor.moveToFirst();

        final String[] Ref = new String[count];
        String[] IDUser = new String[count];
        final String[] Date = new String[count];
        final String[] Name = new String[count];
        final String[] Surname = new String[count];
        final String[] Address = new String[count];
        String[] Product = new String[count];
        String[] Price = new String[count];
        String[] Piece = new String[count];
        final String[] Total = new String[count];
        final String[] Status = new String[count];



        for (int i = 0; i < count;) {
            Ref[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Ref));
            if (i > 1 && Ref[i].matches(Ref[i - 1])) {
                cursor.moveToNext();

            } else {
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

                i++;

                cursor.moveToNext();

            }


        }

        cursor.close();

        HistoryAdapter historyAdapter = new HistoryAdapter(this,Ref,IDUser,Date,Name,
                Surname,Address,Product,Price,Piece,Total,Status);

        listView.setAdapter(historyAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(OrderAdmin.this, OrderAdminDetail.class);

                intent.putExtra("Ref", Ref[position]);
                intent.putExtra("Admin", "delete");
                intent.putExtra("Title", "ต้องการลบบิลนี้ใช่หรือไม่?");

                startActivity(intent);

            }//on item click
        });
    }

    public void clickDeleteOrderAdmin(View view) {
        ListView listView = (ListView) findViewById(R.id.listView4);

        final SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);
        final Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM historyTABLE WHERE Admin = 'delete'", null);
        cursor.moveToFirst();


        int count = cursor.getCount();
        String[] Bill = new String[count];

        for (int i = 0; i < cursor.getCount(); i++ ) {
            Bill[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Ref));
            if (i > 1 && Bill[i].matches(Bill[i - 1])) {
                count--;
                cursor.moveToNext();

            } else {
                cursor.moveToNext();
            }

        }
        cursor.moveToFirst();

        final String[] Ref = new String[count];
        String[] IDUser = new String[count];
        final String[] Date = new String[count];
        final String[] Name = new String[count];
        final String[] Surname = new String[count];
        final String[] Address = new String[count];
        String[] Product = new String[count];
        String[] Price = new String[count];
        String[] Piece = new String[count];
        final String[] Total = new String[count];
        final String[] Status = new String[count];



        for (int i = 0; i < count;) {
            Ref[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Ref));
            if (i > 1 && Ref[i].matches(Ref[i - 1])) {
                cursor.moveToNext();

            } else {
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

                i++;

                cursor.moveToNext();

            }


        }

        cursor.close();

        HistoryAdapter historyAdapter = new HistoryAdapter(this,Ref,IDUser,Date,Name,
                Surname,Address,Product,Price,Piece,Total,Status);

        listView.setAdapter(historyAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(OrderAdmin.this, OrderAdminDetail.class);

                intent.putExtra("Ref", Ref[position]);
                intent.putExtra("Admin", "available");
                intent.putExtra("Title", "ต้องการปลดบลอคบิลนี้ใช่หรือไม่?");

                startActivity(intent);

            }//on item click
        });

    }

    public void clickWait(View view) {
        ListView listView = (ListView) findViewById(R.id.listView4);

        final SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);
        final Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM historyTABLE WHERE Admin = 'available' AND Status = 'รอชำระ'", null);
        cursor.moveToFirst();


        int count = cursor.getCount();
        String[] Bill = new String[count];

        for (int i = 0; i < cursor.getCount(); i++ ) {
            Bill[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Ref));
            if (i > 1 && Bill[i].matches(Bill[i - 1])) {
                count--;
                cursor.moveToNext();

            } else {
                cursor.moveToNext();
            }

        }
        cursor.moveToFirst();

        final String[] Ref = new String[count];
        String[] IDUser = new String[count];
        final String[] Date = new String[count];
        final String[] Name = new String[count];
        final String[] Surname = new String[count];
        final String[] Address = new String[count];
        String[] Product = new String[count];
        String[] Price = new String[count];
        String[] Piece = new String[count];
        final String[] Total = new String[count];
        final String[] Status = new String[count];



        for (int i = 0; i < count;) {
            Ref[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Ref));
            if (i > 1 && Ref[i].matches(Ref[i - 1])) {
                cursor.moveToNext();

            } else {
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

                i++;

                cursor.moveToNext();

            }


        }

        cursor.close();

        HistoryAdapter historyAdapter = new HistoryAdapter(this,Ref,IDUser,Date,Name,
                Surname,Address,Product,Price,Piece,Total,Status);

        listView.setAdapter(historyAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(OrderAdmin.this, OrderAdminDetail.class);

                intent.putExtra("Ref", Ref[position]);
                intent.putExtra("Admin", "delete");
                intent.putExtra("Title", "ต้องการลบบิลนี้ใช่หรือไม่?");


                startActivity(intent);

            }//on item click
        });

    }

    public void clickSendMoney(View view) {
        ListView listView = (ListView) findViewById(R.id.listView4);

        final SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);
        final Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM historyTABLE WHERE Admin = " +
                "'available' AND Status = 'โอนเงินแล้ว'", null);
        cursor.moveToFirst();


        int count = cursor.getCount();
        String[] Bill = new String[count];

        for (int i = 0; i < cursor.getCount(); i++ ) {
            Bill[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Ref));
            if (i > 1 && Bill[i].matches(Bill[i - 1])) {
                count--;
                cursor.moveToNext();

            } else {
                cursor.moveToNext();
            }

        }
        cursor.moveToFirst();

        final String[] Ref = new String[count];
        String[] IDUser = new String[count];
        final String[] Date = new String[count];
        final String[] Name = new String[count];
        final String[] Surname = new String[count];
        final String[] Address = new String[count];
        String[] Product = new String[count];
        String[] Price = new String[count];
        String[] Piece = new String[count];
        final String[] Total = new String[count];
        final String[] Status = new String[count];



        for (int i = 0; i < count;) {
            Ref[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Ref));
            if (i > 1 && Ref[i].matches(Ref[i - 1])) {
                cursor.moveToNext();

            } else {
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

                i++;

                cursor.moveToNext();

            }


        }

        cursor.close();

        HistoryAdapter historyAdapter = new HistoryAdapter(this,Ref,IDUser,Date,Name,
                Surname,Address,Product,Price,Piece,Total,Status);

        listView.setAdapter(historyAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(OrderAdmin.this, OrderAdminDetail.class);

                intent.putExtra("Ref", Ref[position]);
                intent.putExtra("Admin", "delete");
                intent.putExtra("Title", "ต้องการลบบิลนี้ใช่หรือไม่?");

                startActivity(intent);

            }//on item click
        });
    }

    public void clickGoSent(View view) {
        ListView listView = (ListView) findViewById(R.id.listView4);

        final SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);
        final Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM historyTABLE WHERE Admin = " +
                "'available' AND Status = 'ส่งเรียบร้อยแล้ว'", null);
        cursor.moveToFirst();


        int count = cursor.getCount();
        String[] Bill = new String[count];

        for (int i = 0; i < cursor.getCount(); i++ ) {
            Bill[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Ref));
            if (i > 1 && Bill[i].matches(Bill[i - 1])) {
                count--;
                cursor.moveToNext();

            } else {
                cursor.moveToNext();
            }

        }
        cursor.moveToFirst();

        final String[] Ref = new String[count];
        String[] IDUser = new String[count];
        final String[] Date = new String[count];
        final String[] Name = new String[count];
        final String[] Surname = new String[count];
        final String[] Address = new String[count];
        String[] Product = new String[count];
        String[] Price = new String[count];
        String[] Piece = new String[count];
        final String[] Total = new String[count];
        final String[] Status = new String[count];



        for (int i = 0; i < count;) {
            Ref[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Ref));
            if (i > 1 && Ref[i].matches(Ref[i - 1])) {
                cursor.moveToNext();

            } else {
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

                i++;

                cursor.moveToNext();

            }


        }

        cursor.close();

        HistoryAdapter historyAdapter = new HistoryAdapter(this,Ref,IDUser,Date,Name,
                Surname,Address,Product,Price,Piece,Total,Status);

        listView.setAdapter(historyAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(OrderAdmin.this, OrderAdminDetail.class);

                intent.putExtra("Ref", Ref[position]);
                intent.putExtra("Admin", "delete");
                intent.putExtra("Title", "ต้องการลบบิลนี้ใช่หรือไม่?");

                startActivity(intent);

            }//on item click
        });
    }

    public void clickBack(View view) {

        startActivity(new Intent(OrderAdmin.this, MainAdmin.class));
    }



}

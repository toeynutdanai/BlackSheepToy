package kmutnb.ratchaphol.natthawut.natdanai.blacksheeptoy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by teay on 5/8/2016.
 */
public class ToyAdapter extends BaseAdapter {

    //Explicit
    private Context context;
    private String[] nameStrings, brandStrings, priceStrings, stockStrings, usedStrings,
            detailStrings, image1Strings, image2Strings, image3Strings, image4Strings, image5Strings;

    public ToyAdapter(Context context,
                      String[] nameStrings,
                      String[] brandStrings,
                      String[] priceStrings,
                      String[] stockStrings,
                      String[] usedStrings,
                      String[] detailStrings,
                      String[] image1Strings
    ) {
        this.context = context;
        this.nameStrings = nameStrings;
        this.brandStrings = brandStrings;
        this.priceStrings = priceStrings;
        this.stockStrings = stockStrings;
        this.usedStrings = usedStrings;
        this.detailStrings = detailStrings;
        this.image1Strings = image1Strings;

    }

    @Override
    public int getCount() {
        return nameStrings.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = layoutInflater.inflate(R.layout.toy_listview, viewGroup, false);

        TextView nameTextView = (TextView) view1.findViewById(R.id.textView10);
        TextView brandTextView = (TextView) view1.findViewById(R.id.textView11);
        TextView priceTextView = (TextView) view1.findViewById(R.id.textView12);
        TextView stockTextView = (TextView) view1.findViewById(R.id.textView13);
        TextView usedTextView = (TextView) view1.findViewById(R.id.textView14);
        TextView detailTextView = (TextView) view1.findViewById(R.id.textView15);
        ImageView imageView = (ImageView) view1.findViewById(R.id.imageView2);

        nameTextView.setText(nameStrings[i]);
        brandTextView.setText("Brand : " + brandStrings[i]);
        priceTextView.setText("Price : " + priceStrings[i]);
        stockTextView.setText("Stock : " + stockStrings[i]);
        usedTextView.setText("Status : " + usedStrings[i]);
        detailTextView.setText(detailStrings[i]);

        Picasso.with(context).load(image1Strings[i]).resize(120,120).into(imageView);

        return view1;
    }
}//main class
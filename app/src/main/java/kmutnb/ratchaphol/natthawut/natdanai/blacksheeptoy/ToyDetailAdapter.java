package kmutnb.ratchaphol.natthawut.natdanai.blacksheeptoy;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ToyDetailAdapter extends BaseAdapter {

    //Explicit
    private Context context;
    private String[] productStr, priceStr, vatStr, shippingStr, pieceStr, netStr;

    public ToyDetailAdapter(Context context,
                            String[] productStr,
                            String[] priceStr,
                            String[] vatStr,
                            String[] shippingStr,
                            String[] pieceStr,
                            String[] netStr) {
        this.context = context;
        this.productStr = productStr;
        this.priceStr = priceStr;
        this.vatStr = vatStr;
        this.shippingStr = shippingStr;
        this.pieceStr = pieceStr;
        this.netStr = netStr;
    }

    @Override
    public int getCount() {
        return productStr.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.
                LAYOUT_INFLATER_SERVICE);
        View view1 = layoutInflater.inflate(R.layout.toy_listview, parent, false);

        TextView productTextView = (TextView) view1.findViewById(R.id.textView57);
        TextView priceTextView = (TextView) view1.findViewById(R.id.textView58);
        TextView vatTextView = (TextView) view1.findViewById(R.id.textView59);
        TextView shippingTextView = (TextView) view1.findViewById(R.id.textView60);
        TextView pieceTextView = (TextView) view1.findViewById(R.id.textView61);
        TextView netTextView = (TextView) view1.findViewById(R.id.textView62);

        productTextView.setText(productStr[i]);
        priceTextView.setText(priceStr[i]);
        vatTextView.setText(vatStr[i]);
        shippingTextView.setText(shippingStr[i]);
        pieceTextView.setText(pieceStr[i]);
        netTextView.setText(netStr[i]);

        return view1;
    }
}

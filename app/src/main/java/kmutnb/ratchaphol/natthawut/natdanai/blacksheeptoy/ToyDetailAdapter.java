package kmutnb.ratchaphol.natthawut.natdanai.blacksheeptoy;


import android.content.Context;
import android.util.Log;
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
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.
                LAYOUT_INFLATER_SERVICE);
        View view1 = layoutInflater.inflate(R.layout.order_detail_listview, viewGroup, false);

        TextView productTextView = (TextView) view1.findViewById(R.id.textView57);
        productTextView.setText(productStr[i]);
        TextView priceTextView = (TextView) view1.findViewById(R.id.textView58);
        priceTextView.setText(priceStr[i]);
        TextView vatTextView = (TextView) view1.findViewById(R.id.textView59);
        vatTextView.setText(vatStr[i]);
        TextView shippingTextView = (TextView) view1.findViewById(R.id.textView60);
        shippingTextView.setText(shippingStr[i]);
        TextView pieceTextView = (TextView) view1.findViewById(R.id.textView61);
        pieceTextView.setText(pieceStr[i]);
        TextView netTextView = (TextView) view1.findViewById(R.id.textView62);
        netTextView.setText(netStr[i]);

        Log.d("iAdapter", Integer.toString(i));
        Log.d("ProductString Adaoter", productStr[0]);



        return view1;
    }
}

package smart.cart.client.shared;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import smart.cart.client.R;

public class MyListView extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] title;

    public MyListView(Activity context, String[] title) {
        super(context, R.layout.mylist, title);
        this.context=context;
        this.title=title;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.mylist, null, true);

        TextView titleText = (TextView) rowView.findViewById(R.id.title);
        titleText.setText(title[position]);
        return rowView;
    };
}

package smart.cart.client.shared;

import android.annotation.SuppressLint;
import android.app.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import smart.cart.client.R;

public class MyListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] mainTitle;
    private final String[] subtitle;
    private final Integer[] imgId;

    public MyListAdapter(Activity context, String[] maintitle, String[] subtitle, Integer[] imgId) {
        super(context, R.layout.mylist, maintitle);

        this.context = context;
        this.mainTitle = maintitle;
        this.subtitle = subtitle;
        this.imgId = imgId;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        @SuppressLint("ViewHolder") View rowView = inflater.inflate(R.layout.mylist, null, true);

        TextView titleText = rowView.findViewById(R.id.title);
        ImageView imageView = rowView.findViewById(R.id.icon);
        TextView subtitleText = rowView.findViewById(R.id.subtitle);

        titleText.setText(mainTitle[position]);

        imageView.setImageResource(imgId[position]);
        subtitleText.setText(subtitle[position]);

        return rowView;

    }

}

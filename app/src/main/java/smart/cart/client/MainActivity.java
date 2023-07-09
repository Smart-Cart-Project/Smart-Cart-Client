package smart.cart.client;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import smart.cart.client.shared.MyListAdapter;

public class MainActivity extends AppCompatActivity {

    ListView list;

    String[] maintitle = {
            "Tomatoes","Potatoes",
            "Cucumber","Milk",
            "Cheese",
    };
    String[] subtitle = {
            "First Item","Second Item",
            "Third Item","Fourth Item",
            "Fifth Item",
    };

    Integer[] imgid = {
            R.drawable.tomato,R.drawable.potato,
            R.drawable.cucumber,R.drawable.milk,
            R.drawable.cheese,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextInputEditText textInputEditText = findViewById(R.id.cartIdInput);
        final Button button = findViewById(R.id.mainButton);
        button.setOnClickListener(view -> {
            if (textInputEditText.getText() != null && textInputEditText.getText().length() != 0) {
                String cartId = textInputEditText.getText().toString();
                setContentView(R.layout.items_list);
                setTitle("Cart Items - " + cartId);

                MyListAdapter adapter = new MyListAdapter(this, maintitle, subtitle,imgid);
                list = (ListView)findViewById(R.id.itemsList);
                list.setAdapter(adapter);
            }
        });
    }

}
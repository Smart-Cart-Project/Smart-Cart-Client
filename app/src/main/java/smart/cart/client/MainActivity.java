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

public class MainActivity extends AppCompatActivity {

    private List<String> sampleItems = new ArrayList<>(Arrays.asList("Tomatoes", "Potatoes", "Cucumber", "Milk", "Cheese"));
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
                ArrayAdapter adapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1,
                        sampleItems);
                ListView listView = (ListView) findViewById(R.id.itemsList);
                listView.setAdapter(adapter);
            }
        });
    }

}
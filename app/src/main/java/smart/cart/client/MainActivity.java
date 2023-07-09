package smart.cart.client;

import static smart.cart.client.shared.Helpers.associateTitleToImage;
import static smart.cart.client.shared.Helpers.refreshList;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import smart.cart.client.shared.Item;
import smart.cart.client.shared.MyListAdapter;

public class MainActivity extends AppCompatActivity {

    private ListView list;
    private double totalPrice = 0;
    Item[] items = {
            new Item("000001", "Tomatoes", 1.99, 1),
            new Item("000002", "Potatoes", 2.99, 1),
            new Item("000003", "Cucumber", 0.99, 6),
            new Item("000004", "Milk", 3.99, 1),
            new Item("000005", "Cheese", 4.99, 1),
            new Item("000006", "Bread", 1.99, 1),
            new Item("000007", "Eggs", 2.99, 1),
            new Item("000008", "Butter", 0.99, 1),
            new Item("000009", "Ham", 3.99, 1),
            new Item("000010", "Sausage", 4.99, 1),
            new Item("000011", "Chicken", 1.99, 1),
            new Item("000012", "Beef", 2.99, 1),
            new Item("000013", "Fish", 3.99, 1),
            new Item("000014", "Rice", 4.99, 1),
    };
    List<Item> itemsList = new ArrayList<>(Arrays.asList(items));
    String[] mainTitle = new String[itemsList.size()];
    String[] subtitle = new String[itemsList.size()];
    Integer[] imgId = new Integer[itemsList.size()];


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
                setTitle("Cart ID: " + cartId);

                for (int i = 0; i < itemsList.size(); i++) {
                    mainTitle[i] = itemsList.get(i).getName();
                    subtitle[i] = itemsList.get(i).getQuantity() + " x " + itemsList.get(i).getPrice() + "€";
                    imgId[i] = associateTitleToImage(itemsList.get(i).getName());
                    if (itemsList.get(i).getPrice() instanceof Double d) {
                        if (itemsList.get(i).getQuantity() instanceof Integer q) {
                            totalPrice += d * q;
                        } else {
                            totalPrice += d;
                        }
                    } else {
                        if (itemsList.get(i).getQuantity() instanceof Integer q) {
                            totalPrice += (itemsList.get(i).getPrice()).doubleValue() * q;
                        } else {
                            totalPrice += (itemsList.get(i).getPrice()).doubleValue();
                        }
                    }
                }
                totalPrice = Math.round(totalPrice * 100.0) / 100.0;
                TextView textView = findViewById(R.id.totalPrice);
                String totalPriceString = "Total: " + totalPrice + "€";
                textView.setText(totalPriceString);
                textView.setTextAlignment(TextView.TEXT_ALIGNMENT_VIEW_END);
                MyListAdapter adapter = new MyListAdapter(this, mainTitle, subtitle, imgId);
                list = findViewById(R.id.itemsList);
                list.setAdapter(adapter);

                // add a refresh button
                final Button refreshButton = findViewById(R.id.refresh);

                refreshButton.setOnClickListener(v -> {
                    refreshList(this);
                });

//                SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swipeRefresh);
//                swipeRefreshLayout.setOnRefreshListener(() -> {
//                    refreshList(this);
//                });

            }
        });
    }

}
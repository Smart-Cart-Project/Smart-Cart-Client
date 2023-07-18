package smart.cart.client;

import static smart.cart.client.shared.Helpers.associateTitleToImage;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import smart.cart.client.shared.Helpers;
import smart.cart.client.shared.Item;
import smart.cart.client.shared.MyListAdapter;

public class MainActivity extends AppCompatActivity {

    private double totalPrice = 0;
    private ExecutorService executorService;

    private TextInputEditText textInputEditText;
    private TextView totalPriceTextView;
    private ListView itemsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textInputEditText = findViewById(R.id.cartIdInput);
        Button button = findViewById(R.id.mainButton);
        button.setOnClickListener(view -> {
            String cartId = Objects.requireNonNull(textInputEditText.getText()).toString().trim();
            if (!cartId.isEmpty()) {
                initializeItemListLayout(cartId);
                fetchItemsAndUpdateUI(cartId);
            }
        });

        executorService = Executors.newSingleThreadExecutor();
    }

    private void initializeItemListLayout(String cartId) {
        setContentView(R.layout.items_list);
        setTitle("Cart ID: " + cartId);

        Button homeButton = findViewById(R.id.home);
        homeButton.setOnClickListener(v -> returnToMainLayout());

        Button refreshButton = findViewById(R.id.refresh);
        refreshButton.setOnClickListener(v -> refreshItems(cartId));

        totalPriceTextView = findViewById(R.id.totalPrice);
        itemsListView = findViewById(R.id.itemsList);

        Button checkoutButton = findViewById(R.id.checkout);
        checkoutButton.setOnClickListener(v -> checkout(cartId, totalPrice));
    }

    private void returnToMainLayout() {
        setContentView(R.layout.activity_main);
        setTitle("Smart Cart Client");
        totalPrice = 0;

        textInputEditText = findViewById(R.id.cartIdInput);
        Button button = findViewById(R.id.mainButton);
        button.setOnClickListener(view -> {
            String cartId = Objects.requireNonNull(textInputEditText.getText()).toString().trim();
            if (!cartId.isEmpty()) {
                initializeItemListLayout(cartId);
                fetchItemsAndUpdateUI(cartId);
            }
        });
    }


    private void checkout(String cartId, double totalPrice) {
        setContentView(R.layout.checkout);
        setTitle("Checkout");

        Button cancelButton = findViewById(R.id.cancel);
        cancelButton.setOnClickListener(v -> refreshItems(cartId));

        TextView totalPriceTextView = findViewById(R.id.totalPrice);
        String totalPriceString = "Total: " + totalPrice + "€";
        totalPriceTextView.setText(totalPriceString);

    }

    private void fetchItemsAndUpdateUI(String cartId) {
        executorService.submit(() -> Helpers.getItems(cartId, receivedItems -> runOnUiThread(() -> {
            processItems(receivedItems);
            updateTotalPriceUI();
        })));
    }

    private void processItems(Item[] receivedItems) {
        List<Item> itemsList = new ArrayList<>(Arrays.asList(receivedItems));
        String[] mainTitle = new String[itemsList.size()];
        String[] subtitle = new String[itemsList.size()];
        Integer[] imgId = new Integer[itemsList.size()];

        for (int i = 0; i < itemsList.size(); i++) {
            Item item = itemsList.get(i);
            mainTitle[i] = item.getName();
            subtitle[i] = item.getQuantity() + " x " + item.getPrice() + "€";
            imgId[i] = associateTitleToImage(item.getName());
            updateTotalPrice(item);
        }

        MyListAdapter adapter = new MyListAdapter(this, mainTitle, subtitle, imgId);
        itemsListView.setAdapter(adapter);
    }

    private void updateTotalPrice(Item item) {
        double price = item.getPrice().doubleValue();
        int quantity = (int) item.getQuantity();

        totalPrice += price * quantity;
    }

    private void updateTotalPriceUI() {
        totalPrice = Math.round(totalPrice * 100.0) / 100.0;
        String totalPriceString = "Total: " + totalPrice + "€";
        totalPriceTextView.setText(totalPriceString);
        totalPriceTextView.setX(totalPriceTextView.getX() + totalPriceTextView.getWidth() - totalPriceTextView.getPaint().measureText(totalPriceString) - 20);
    }

    private void refreshItems(String cartId) {
        initializeItemListLayout(cartId);
        totalPrice = 0;
        fetchItemsAndUpdateUI(cartId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }
}

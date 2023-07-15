package smart.cart.client.shared;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import smart.cart.client.MainActivity;
import smart.cart.client.R;

public class Helpers {
    private static final String url = "https://smart-cart-service.onrender.com/items";

    public interface GetItemsCallback {
        void onItemsReceived(Item[] items);
    }

    public static void getItems(String cartId, GetItemsCallback callback) {

        try {
            String requestUrl = url + "?cartId=" + cartId;
            URL requestURL = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) requestURL.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder responseBody = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    responseBody.append(line);
                }
                reader.close();

                JSONArray jsonArray = new JSONArray(responseBody.toString());
                Item[] items = new Item[jsonArray.length()];
                for (int i = 0; i < jsonArray.length(); i++) {
                    String itemId = jsonArray.getJSONObject(i).getString("itemId");
                    String name = jsonArray.getJSONObject(i).getString("name");
                    Double price = jsonArray.getJSONObject(i).getDouble("price");
                    Integer quantity = jsonArray.getJSONObject(i).getInt("quantity");
                    items[i] = new Item(itemId, name, price, quantity);
                }
                callback.onItemsReceived(items);
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

    }

    public static Integer associateTitleToImage(String title) {

        return switch (title) {
            case "Tomatoes" -> R.drawable.tomato;
            case "Potatoes" -> R.drawable.potato;
            case "Cucumber" -> R.drawable.cucumber;
            case "Milk" -> R.drawable.milk;
            case "Cheese" -> R.drawable.cheese;
            case "Bread" -> R.drawable.bread;
            case "Eggs" -> R.drawable.eggs;
            case "Butter" -> R.drawable.butter;
            case "Ham" -> R.drawable.ham;
            case "Sausage" -> R.drawable.sausage;
            case "Chicken" -> R.drawable.chicken;
            case "Beef" -> R.drawable.beef;
            case "Fish" -> R.drawable.fish;
            case "Rice" -> R.drawable.rice;
            case "CocaCola" -> R.drawable.cocacola;
            case "Nutela" -> R.drawable.nutela;
            default -> R.drawable.ic_launcher_foreground;
        };

    }
}

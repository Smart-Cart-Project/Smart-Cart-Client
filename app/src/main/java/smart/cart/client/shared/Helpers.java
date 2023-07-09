package smart.cart.client.shared;

import android.app.Activity;

import smart.cart.client.R;

public class Helpers {


    public static void refreshList(Activity context) {
        context.setContentView(R.layout.items_list);
        context.setTitle("TODO");
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
            default -> R.drawable.ic_launcher_foreground;
        };

    }

}

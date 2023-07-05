package smart.cart.client;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextInputEditText textInputEditText = findViewById(R.id.cartIdInput);
        final Button button = findViewById(R.id.mainButton);
        button.setOnClickListener(view -> {
            if (textInputEditText.getText() != null && textInputEditText.getText().length() != 0) {
                // Switch to the items list layout
                setContentView(R.layout.items_list);
            }
        });
    }
}
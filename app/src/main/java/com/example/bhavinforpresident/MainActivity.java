package com.example.bhavinforpresident;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private Button button_start_reset;
    private TextView timer_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer_text = findViewById(R.id.time);


        button_start_reset = findViewById(R.id.button_start_reset);
        button_start_reset.setOnClickListener(view -> {
            if (button_start_reset.getText().equals(getResources().getString(R.string.work))){
                button_start_reset.setText(getResources().getString(R.string.stop));
                button_start_reset.setBackgroundColor(Color.RED);
            }else{
                button_start_reset.setText(getResources().getString(R.string.work));
                button_start_reset.setBackgroundColor(Color.GREEN);

            }
        });
    }
}
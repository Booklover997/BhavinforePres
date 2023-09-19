package com.example.bhavinforpresident;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button button_start_reset;
    private TextView timer_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_start_reset = findViewById(R.id.button_start_reset);
        button_start_reset.setOnClickListener(view -> {
            if (button_start_reset.getText().equals("Work")){
                button_start_reset.setText("Stop");
            }else{
                button_start_reset.setText("Work");
            }
        });
    }
}
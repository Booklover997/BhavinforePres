package com.example.bhavinforpresident;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Locale;

public class home extends Fragment {

    private int current_progress = 0;
    private ProgressBar progressBar;
    private Button button_start_reset;
    private TextView timer_text;
    private CountDownTimer Countdown_Timer;
    private long startTime = 600000;
    private long millis = startTime;
    private boolean running = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        progressBar = view.findViewById(R.id.progressBar);
        timer_text = view.findViewById(R.id.time);
        button_start_reset = view.findViewById(R.id.button_start_reset);

        updateText();

        button_start_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (button_start_reset.getText().equals(getResources().getString(R.string.work))) {
                    startTimer();
                } else {
                    resetTimer();
                }
            }
        });

        return view;
    }

    private void startTimer() {
        button_start_reset.setText(getResources().getString(R.string.stop));
        button_start_reset.setBackgroundColor(Color.RED);
        Countdown_Timer = new CountDownTimer(millis, 1000) {
            @Override
            public void onTick(long l) {
                millis = l;
                updateText();
                current_progress = current_progress + 10;
                progressBar.setProgress(current_progress);
                progressBar.setMax(100);
            }

            @Override
            public void onFinish() {

            }
        }.start();
        running = true;
    }

    private void resetTimer() {
        button_start_reset.setText(getResources().getString(R.string.work));
        button_start_reset.setBackgroundColor(Color.GREEN);
        Countdown_Timer.cancel();
        running = false;
        millis = startTime;
        updateText();
        progressBar.setProgress(100);
    }

    private void updateText() {
        int seconds = (int) millis / 1000;
        int min = (int) seconds / 60;
        seconds -= min * 60;
        timer_text.setText(String.format(Locale.getDefault(), "%02d:%02d", min, seconds));
    }
}

package com.example.bhavinforpresident;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;
import database.AppDatabase;
import database.Mats;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import database.AppDatabase;
import database.Mats;

public class home extends Fragment {

    private int current_progress = 0;
    private ProgressBar progressBar;
    private Button button_start_reset;
    private ImageButton increaseup;
    private ImageButton decreasedown;
    private TextView timer_text;
    private CountDownTimer Countdown_Timer;
    private long startTime = 60000*5;
    private long millis = startTime;
    private boolean running = false;
    private int update_counter;
    private TextView moneytext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        progressBar = view.findViewById(R.id.progressBar);
        timer_text = view.findViewById(R.id.time);
        button_start_reset = view.findViewById(R.id.button_start_reset);
        increaseup = view.findViewById(R.id.increaseup);
        decreasedown = view.findViewById(R.id.decreasedown);
        moneytext = view.findViewById(R.id.money_text);

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

        decreasedown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (millis>0) {
                    millis -= 300000;
                    startTime -= 300000;
                    updateText();
                }
            }
        });

        increaseup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    millis += 300000;
                    startTime += 300000;
                    updateText();
            }
        });


        new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase db = Room.databaseBuilder(requireContext(), AppDatabase.class, "Mats").build();
                Mats mats = db.MatsDao().getMatsByName("Money");


                // Update the UI elements on the main thread
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        moneytext.setText(""+mats.quantity);
                    }
                });
            }

        }).start();
        return view;
    }

    private void startTimer() {
        button_start_reset.setText(getResources().getString(R.string.stop));
        button_start_reset.setBackgroundColor(Color.RED);
        update_counter = 0;
        actions.completeAction(getActivity().getApplicationContext(),startTime);
        //TODO: Get this outa hear
        Countdown_Timer = new CountDownTimer(millis, 100) {
            @Override
            public void onTick(long l) {
                millis = l;
                update_counter+=1;
                if (update_counter == 10){
                    updateText();
                    update_counter=0;
                }
                current_progress = current_progress + 1;
                progressBar.setProgress(current_progress);
                //100 instead of 1000 to make it smoother
                progressBar.setMax((int)startTime/100);
            }

            @Override
            public void onFinish() {
                //Make sure its full when its finished
                    progressBar.setProgress((int)startTime/100);
                actions.completeAction(getActivity().getApplicationContext(),startTime);

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
        progressBar.setProgress(0);
        current_progress = 0;
    }

    private void updateText() {
        int seconds = (int) millis / 1000;
        int min = (int) seconds / 60;
        seconds -= min * 60;
        timer_text.setText(String.format(Locale.getDefault(), "%02d:%02d", min, seconds));
    }
}

package com.example.bhavinforpresident;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import androidx.cardview.widget.CardView;
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Map#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Map extends androidx.fragment.app.Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ImageButton arrow_woods;
    LinearLayout hiddenView_woods;
    CardView cardView_woods;

    ImageButton arrow_mountains;
    LinearLayout hiddenView_mountains;
    CardView cardView_mountains;


    ImageButton arrow_caves;
    LinearLayout hiddenView_caves;
    CardView cardView_caves;



    LinearLayout rock;

    LinearLayout iron;
    LinearLayout diamond;
    LinearLayout oak;
    LinearLayout birch;
    LinearLayout spruce;
    LinearLayout upper_level;
    LinearLayout mid_level;
    LinearLayout lower_level;
    LinearLayout gold;
    LinearLayout redwood;






    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Map() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Map.
     */
    // TODO: Rename and change types and number of parameters
    public static Map newInstance(String param1, String param2) {
        Map fragment = new Map();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map, container, false);

       arrow_mountains= view.findViewById(R.id.arrow_mountains);
        hiddenView_mountains = view.findViewById(R.id.hidden_view_mountains);
        cardView_mountains = view.findViewById(R.id.base_view_mountains);


        arrow_woods = view.findViewById(R.id.arrow_woods);
        hiddenView_woods = view.findViewById(R.id.hidden_view_woods);
        cardView_woods = view.findViewById(R.id.base_view_woods);





        rock =view.findViewById((R.id.rock));
        iron =view.findViewById((R.id.iron));
        diamond = view.findViewById(R.id.diamond);
        gold = view.findViewById(R.id.gold);

        oak =view.findViewById((R.id.oak));
        birch =view.findViewById((R.id.birch));
        spruce = view.findViewById(R.id.spruce);
        redwood = view.findViewById(R.id.redwood);


        rock.setOnClickListener(obj->{
            actions.setAction("Explore");
            actions.setDetails("Stone");
            resetBackgroundColor();
            rock.setBackgroundColor(Color.parseColor("#FF5733"));
        });
        iron.setOnClickListener(obj -> {
            actions.setAction("Explore");
            actions.setDetails("Iron");
            resetBackgroundColor();
            iron.setBackgroundColor(Color.parseColor("#FF5733")); // Set the background color
        });
        gold.setOnClickListener(obj->{
            actions.setAction("Explore");
            actions.setDetails("Gold");
            resetBackgroundColor();
            gold.setBackgroundColor(Color.parseColor("#FF5733"));
        });

        diamond.setOnClickListener(obj -> {
            actions.setAction("Explore");
            actions.setDetails("Diamond");
            resetBackgroundColor();
            diamond.setBackgroundColor(Color.parseColor("#FF5733")); // Set the background color
        });

        oak.setOnClickListener(obj -> {
            actions.setAction("Explore");
            actions.setDetails("Oak");
            resetBackgroundColor();
            oak.setBackgroundColor(Color.parseColor("#FF5733")); // Set the background color
        });

        birch.setOnClickListener(obj -> {
            actions.setAction("Explore");
            actions.setDetails("Birch");
            resetBackgroundColor();
            birch.setBackgroundColor(Color.parseColor("#FF5733")); // Set the background color
        });

        spruce.setOnClickListener(obj -> {
            actions.setAction("Explore");
            actions.setDetails("Spruce");
            resetBackgroundColor();
            spruce.setBackgroundColor(Color.parseColor("#FF5733")); // Set the background color
        });
        redwood.setOnClickListener(obj->{
        actions.setAction("Explore");
        actions.setDetails("Redwood");
        resetBackgroundColor();
        redwood.setBackgroundColor(Color.parseColor("#FF5733"));
    });


        arrow_mountains.setOnClickListener(kek ->
        {
            /*checks if it is alraedy expanded */
            if (hiddenView_mountains.getVisibility()  == View.VISIBLE){
                /*If the view is visible on click */
                AutoTransition trans = new AutoTransition();
                trans.setDuration(200);
                TransitionManager.beginDelayedTransition(cardView_mountains, trans.setDuration(75));
                hiddenView_mountains.setVisibility(View.GONE);
                arrow_mountains.setImageResource(R.drawable.down);
            }
            else{
                AutoTransition trans = new AutoTransition();
                trans.setDuration(200);
                TransitionManager.beginDelayedTransition(cardView_mountains, trans);

                hiddenView_mountains.setVisibility(View.VISIBLE);
                arrow_mountains.setImageResource(R.drawable.up);
            }
        });
        arrow_woods.setOnClickListener(hello ->
        {
            /*checks if it is alraedy expanded */
            if (hiddenView_woods.getVisibility()  == View.VISIBLE){
                /*If the view is visible on click */
                AutoTransition trans = new AutoTransition();
                trans.setDuration(200); //TODO: make sure that deez are good
                TransitionManager.beginDelayedTransition(cardView_woods, trans.setDuration(75));
                TransitionManager.beginDelayedTransition(cardView_mountains, trans.setDuration(300));

                hiddenView_woods.setVisibility(View.GONE);
                arrow_woods.setImageResource(R.drawable.down);
            }
            else{
                AutoTransition trans = new AutoTransition();
                trans.setDuration(200);
                TransitionManager.beginDelayedTransition(cardView_woods, trans);
                TransitionManager.beginDelayedTransition(cardView_mountains, trans);
                hiddenView_woods.setVisibility(View.VISIBLE);
                arrow_woods.setImageResource(R.drawable.up);
            }
        });









        /* the action on clics*/
        return view;    }

    private void resetBackgroundColor() {
        int defaultBackgroundColor = Color.TRANSPARENT; // Set this to the default background is transparent so that card background is seen

        // Reset the background color for each view
        rock.setBackgroundColor(defaultBackgroundColor);
        iron.setBackgroundColor(defaultBackgroundColor);
        gold.setBackgroundColor(defaultBackgroundColor);
        diamond.setBackgroundColor(defaultBackgroundColor);
        oak.setBackgroundColor(defaultBackgroundColor);
        birch.setBackgroundColor(defaultBackgroundColor);
        spruce.setBackgroundColor(defaultBackgroundColor);
        redwood.setBackgroundColor(defaultBackgroundColor);
    }




}
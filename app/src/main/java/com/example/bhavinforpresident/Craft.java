package com.example.bhavinforpresident;

import android.graphics.Color;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import database.AppDatabase;
import database.Mats;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Craft#newInstance} factory method to
 * create an instance of this fragment.
 */
public class  Craft extends androidx.fragment.app.Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextView iron_text;
    TextView rock_text;
    TextView diamonds_text;
    TextView oak_text;
    TextView birch_text;
    TextView spruce_text;
    TextView gold_text;
    TextView redwood_text;

    private CardView chair;
    private CardView door;
    private CardView table;
    private CardView shelf;
    private CardView stonesword;
    private CardView ironsword;
    private CardView goldsword;
    private CardView diamondsword;


    public Craft() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Craft.
     */
    // TODO: Rename and change types and number of parameters
    public static Craft newInstance(String param1, String param2) {
        Craft fragment = new Craft();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_craft, container, false);
        iron_text = view.findViewById(R.id.iron_text);
        rock_text = view.findViewById(R.id.stone_text);
        gold_text = view.findViewById(R.id.gold_text);
        oak_text = view.findViewById(R.id.oak_text) ;
        diamonds_text = view.findViewById(R.id.diamond_text);
        birch_text = view.findViewById(R.id.birch_text);
        spruce_text = view.findViewById(R.id.spruce_text);
        redwood_text = view.findViewById(R.id.redwood_text);
         chair = view.findViewById(R.id.chairtop);
         door = view.findViewById(R.id.doortop);
         table = view.findViewById(R.id.tabletop);
         shelf = view.findViewById(R.id.shelftop);
         stonesword = view.findViewById(R.id.stoneswordtop);
         ironsword = view.findViewById(R.id.ironswordtop);
         goldsword = view.findViewById(R.id.goldswordtop);
         diamondsword = view.findViewById(R.id.diamondswordtop);
        
        //make change color on click for a recipe and set action
        chair.setOnClickListener(l-> {
            resetColors(chair, door, table, shelf, stonesword, ironsword, goldsword, diamondsword);
            chair.setBackgroundColor(Color.RED);
            actions.setDetails("Chair");
            actions.setAction("Craft");
        });

        door.setOnClickListener(l-> {
            resetColors(chair, door, table, shelf, stonesword, ironsword, goldsword, diamondsword);
                door.setBackgroundColor(Color.RED);
            actions.setDetails("Door");
            actions.setAction("Craft");

        });

        table.setOnClickListener(l-> {
            resetColors(chair, door, table, shelf, stonesword, ironsword, goldsword, diamondsword);

                table.setBackgroundColor(Color.RED);
            actions.setDetails("Table");
            actions.setAction("Craft");

        });

        shelf.setOnClickListener(l-> {
            resetColors(chair, door, table, shelf, stonesword, ironsword, goldsword, diamondsword);

                shelf.setBackgroundColor(Color.RED);
                actions.setDetails("Shelf");
                actions.setAction("Craft");

        });

        stonesword.setOnClickListener(l-> {
            resetColors(chair, door, table, shelf, stonesword, ironsword, goldsword, diamondsword);

            chair.setBackgroundColor(Color.RED);
            actions.setDetails("Stone_Sword");
            actions.setAction("Craft");
            
        
        });
        ironsword.setOnClickListener(l-> {
            resetColors(chair, door, table, shelf, stonesword, ironsword, goldsword, diamondsword);

            chair.setBackgroundColor(Color.RED);
            actions.setDetails("Iron_Sword");
            actions.setAction("Craft");


        });
        goldsword.setOnClickListener(l-> {
            resetColors(chair, door, table, shelf, stonesword, ironsword, goldsword, diamondsword);

            chair.setBackgroundColor(Color.RED);
            actions.setDetails("Gold_Sword");
            actions.setAction("Craft");


        });
        diamondsword.setOnClickListener(l-> {
            resetColors(chair, door, table, shelf, stonesword, ironsword, goldsword, diamondsword);

            chair.setBackgroundColor(Color.RED);
            actions.setDetails("Diamond_Sword");
            actions.setAction("Craft");


        });
            

        // Start a new thread to perform database operations
        new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase db = Room.databaseBuilder(requireContext(), AppDatabase.class, "Mats").build();
                List<Mats> mats = db.MatsDao().getAll();
                Map<String, Integer> materialMap = new HashMap<>();
                Log.d("Hashmap", String.valueOf(materialMap.get("Iron")));

                // Iterate through the list and build the mapping
                for (Mats material : mats) {
                    String materialName = material.mat_name;
                    int materialQuantity = material.quantity;

                    // Add the material name and quantity to the map
                    materialMap.put(materialName, materialQuantity);
                }

                // Update the UI elements on the main thread
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateUI(materialMap);
                    }
                });
            }
        }).start();

        return view;
    }

    private void updateUI(Map<String, Integer> materialMap) {
//        // Access and set the text for each UI element using materialMap
        iron_text.setText("x" + String.valueOf(materialMap.get("Iron")));
        rock_text.setText("x" + String.valueOf(materialMap.get("Stone")));
        diamonds_text.setText("x" + String.valueOf(materialMap.get("Diamond")));
        oak_text.setText("x" + String.valueOf(materialMap.get("Oak")));
        birch_text.setText("x" + String.valueOf(materialMap.get("Birch")));
        spruce_text.setText("x" + String.valueOf(materialMap.get("Spruce")));
        redwood_text.setText("x" + String.valueOf(materialMap.get("Redwood")));
        gold_text.setText("x" + String.valueOf(materialMap.get("Gold")));

        Log.d("pass", "pass");
    }
    private void resetColors(CardView chair, CardView door, CardView table, CardView shelf, CardView stonesword, CardView ironsword, CardView goldsword, CardView diamondsword) {
        chair.setBackgroundColor(Color.WHITE);
        door.setBackgroundColor(Color.WHITE);
        table.setBackgroundColor(Color.WHITE);
        shelf.setBackgroundColor(Color.WHITE);
        stonesword.setBackgroundColor(Color.WHITE);
        goldsword.setBackgroundColor(Color.WHITE);
        ironsword.setBackgroundColor(Color.WHITE);
        diamondsword.setBackgroundColor(Color.WHITE);
    }


}








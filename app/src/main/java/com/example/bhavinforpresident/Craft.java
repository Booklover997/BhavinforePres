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

    private CardView table;
    private CardView wood_sword;
    private CardView iron_sword;
    private CardView diamond_sword;
    private CardView chair;


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
        diamonds_text = view.findViewById(R.id.diamond_text);
        birch_text = view.findViewById(R.id.birch_text);
        spruce_text = view.findViewById(R.id.spruce_text);
        CardView table = view.findViewById(R.id.table);
        CardView wood_sword = view.findViewById(R.id.wood_sword);
        CardView iron_sword = view.findViewById(R.id.steel_Sword);
        CardView diamond_sword = view.findViewById(R.id.diamond_sword);
        CardView chair = view.findViewById(R.id.chair);

        //make change color on click for a recipe and set action
        table.setOnClickListener(l-> {
            resetColors(table, wood_sword, iron_sword, diamond_sword, chair);
            table.setBackgroundColor(Color.RED);
            actions.setDetails("Table");
            actions.setAction("Craft");
        });

        wood_sword.setOnClickListener(l-> {
            resetColors(table, wood_sword, iron_sword, diamond_sword, chair);
                wood_sword.setBackgroundColor(Color.RED);
            actions.setDetails("Wood_Sword");
            actions.setAction("Craft");

        });

        iron_sword.setOnClickListener(l-> {
            resetColors(table, wood_sword, iron_sword, diamond_sword, chair);

                iron_sword.setBackgroundColor(Color.RED);
            actions.setDetails("Iron_Sword");
            actions.setAction("Craft");

        });

        diamond_sword.setOnClickListener(l-> {
            resetColors(table, wood_sword, iron_sword, diamond_sword, chair);

                diamond_sword.setBackgroundColor(Color.RED);
                actions.setDetails("Diamond_Sword");
                actions.setAction("Craft");

        });

        chair.setOnClickListener(l-> {
            resetColors(table, wood_sword, iron_sword, diamond_sword, chair);

                chair.setBackgroundColor(Color.RED);
            actions.setDetails("Chair");
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
        // Access and set the text for each UI element using materialMap
        iron_text.setText("x" + String.valueOf(materialMap.get("Iron")));
        rock_text.setText("x" + String.valueOf(materialMap.get("Stone")));
        diamonds_text.setText("x" + String.valueOf(materialMap.get("Diamond")));
        oak_text.setText("x" + String.valueOf(materialMap.get("Oak")));
        birch_text.setText("x" + String.valueOf(materialMap.get("Birch")));
        spruce_text.setText("x" + String.valueOf(materialMap.get("Spruce")));
    }
    private void resetColors(CardView table, CardView wood_sword, CardView iron_sword, CardView diamond_sword, CardView chair) {
        table.setBackgroundColor(Color.WHITE);
        wood_sword.setBackgroundColor(Color.WHITE);
        iron_sword.setBackgroundColor(Color.WHITE);
        diamond_sword.setBackgroundColor(Color.WHITE);
        chair.setBackgroundColor(Color.WHITE);
    }


}








package com.example.bhavinforpresident;
import android.util.Log;
import androidx.room.Room;
import database.*;
import android.content.Context;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class actions {
    private static Boolean set = false;
    //Can be set to craft, sell, or explore
    private static String action = "none";
    private static String details = "nada";
    public static void setAction(String action_to_set){
        action = action_to_set;
    }
    public static void setDetails(String details_to_set){
        details = details_to_set;  Log.d("details", details);
    }
    public static String getAction()
    {
        return action;
    }
    public static String getDetails(){
        return details;
    }

    public static Map<String, Integer> getMats(final Context context) {
        FutureTask<Map<String, Integer>> futureTask = new FutureTask<>(new Callable<Map<String, Integer>>() {
            @Override
            public Map<String, Integer> call() {
                MatsDao matsDao = idk(context);

                Map<String, Integer> materialMap = new HashMap<>();

                // Iterate through the list and build the mapping
                for (Mats material : matsDao.getAll()) {
                    String materialName = material.name;
                    int materialQuantity = material.quantity;

                    // Add the material name and quantity to the map
                    materialMap.put(materialName, materialQuantity);
                }

                // Return the materialMap
                return materialMap;
            }
        });

        // Start the thread
        new Thread(futureTask).start();

        try {
            // Wait for the thread to complete and return the result
            return futureTask.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null; // Handle exceptions as needed
        }
    }





    public static void completeAction(Context context, long time) {
        // Create a new thread to perform database operations
        Log.d("????", action);
        if (action.equals("Explore")) {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    MatsDao matsDao = idk(context);
                    Mats mat = matsDao.getMatsByName(details);
                    Log.d("Chosen Mat", mat.name);
                    mat.quantity += (int) time/300000;
                    matsDao.update(mat);
                    List<Mats> mats = matsDao.getAll();


                    for (Mats material : mats) {
                        String materialName = material.name;
                        Log.d("Material Name", materialName);
                        Log.d("Material Quanitity", " " + material.quantity);
                    }

                    // You can update UI or perform other actions with the retrieved data here.
                }
            }).start();
        }
    }


    public  static MatsDao idk(Context context ) {
        AppDatabase db = Room.databaseBuilder(context,
                AppDatabase.class, "Mats").build();

        return db.MatsDao();
    }
}

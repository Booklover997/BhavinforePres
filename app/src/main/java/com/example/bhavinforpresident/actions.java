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
    private static int tInterval = 60000*5;
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
                    String materialName = material.mat_name;
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
        Log.d("????", action);
        if (action.equals("Explore")) {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    MatsDao matsDao = idk(context);
                    Mats mat = matsDao.getMatsByName(details);
                    mat.quantity += (int) time/tInterval;
                    matsDao.update(mat);
                    List<Mats> mats = matsDao.getAll();


                    for (Mats material : mats) {
                        String materialName = material.mat_name;
                        Log.d("Material Name", materialName);
                        Log.d("Material Quanitity", " " + material.quantity);
                    }

            }
            }).start();

        }
        if (action.equals("Craft")) {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    MatsDao matsDao = idk(context);
                    Mats mat = matsDao.getMatsByName(details);
                    //Get the actual quantites of the resources that are required
                        Mats wood = matsDao.getMatsByName(mat.wood_type);
                        Log.d("wood", wood.mat_name);

                        Mats rock  = matsDao.getMatsByName(mat.wood_type);


                    for (int i=0; i< (int) time/tInterval; i++){
                        //Check to see fit the quantites match or exceed the resources required or are "none" short circuiting the boolean statement  cc
                        if ((mat.wood_type=="none" || wood.quantity>=mat.wood_quantity) && (mat.mineral_type == "none" || rock.quantity >= mat.mineral_quantity)) {
                            mat.quantity += 1;
                            wood.quantity-=mat.wood_quantity;
                            rock.quantity -= mat.mineral_quantity;

                        }
                        }
                    matsDao.update(rock);
                    matsDao.update(mat);
                    matsDao.update(wood);
                    List<Mats> mats = matsDao.getAll();


                    for (Mats material : mats) {
                        String materialName = material.mat_name;
                        Log.d("Material Name", materialName);
                        Log.d("Material Quanitity", " " + material.quantity);
                    }
              }
            }).start();
        }
        }

        public static Mats makeMats(String name, String wood_type, int wood_quantity, String rock_type, int rock_quantity ){
            Mats mat = new Mats();
            mat.mat_name = name;
            mat.wood_type = wood_type;
            mat.wood_quantity = wood_quantity;
            mat.mineral_type = rock_type;
            mat.mineral_quantity = rock_quantity;
            mat.quantity = 0;
            return mat;
        }



    public  static MatsDao idk(Context context ) {
        AppDatabase db = Room.databaseBuilder(context,
                AppDatabase.class, "Mats").build();

        return db.MatsDao();
    }
}

package com.example.bhavinforpresident;
import android.util.Log;
import androidx.room.Room;
import database.*;
import android.content.Context;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class actions {
    private static Boolean set = false;
    //Can be set to craft, sell, or explore
    private static String action = "Selling";
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





    public static void completeAction(Context context, final long time) {
        Log.d("????", action);
        if (action.equals("Explore")) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    long t = time;
                    MatsDao matsDao = idk(context);
                    Mats mat = matsDao.getMatsByName(details);
                    Mats Money = matsDao.getMatsByName("Money");
                    //should be going 12-> 13 instead is going 12->164 FIXED by changing time to t
                    while (t>=tInterval && Money.quantity>=mat.purchase_value){
                            mat.quantity+=1;
                            Money.quantity-=mat.purchase_value;
                            t-=tInterval;

                    }
                    matsDao.update(mat);
                    matsDao.update((Money));
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
        if (action.equals("Selling")) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    long t = time;
                    MatsDao matsDao = idk(context);
                    Mats Money = matsDao.getMatsByName("Money");
                    Mats Table = matsDao.getMatsByName("Table");

                    List<Mats> mats = matsDao.getAll();
                    Log.d("monehymonehymonehymononehymonehymonehymonehymonehymonehymonehymonehyv", ""+Table.quantity);

//                    actions.makeMats("Oak", "none", 0, "none", 0),
//                            actions.makeMats("Birch", "none", 0, "none", 0),
//                            actions.makeMats("Spruce", "none", 0, "none", 0),
//                            actions.makeMats("Redwood", "none", 0, "none", 0),
//                            actions.makeMats("Iron", "none", 0, "none", 0),
//                            actions.makeMats("Stone", "none", 0, "none", 0),
//                            actions.makeMats("Gold", "none", 0, "none", 0),
//                            actions.makeMats("Diamond", "none", 0, "none", 0),
//                            actions.makeMats("Wood_Sword", "Oak", 4, "none", 0),
//                            actions.makeMats("Stone_Sword", "Birch", 2, "Stone", 2),
//                            actions.makeMats("Iron_Sword", "Spruce", 2, "Iron", 2),
//                            actions.makeMats("Diamond_Sword", "Oak", 2, "Diamond", 2),
//                            actions.makeMats("Table", "Oak", 4, "none", 0),
//                            actions.makeMats("Chair", "Birch", 2, "stone", 2),
//                            actions.makeMats("Furnace", "Spruce", 2, "stone", 2),
//                            actions.makeMats("Door", "Spruce", 2, "Stone", 4),
//                            actions.makeMats("Luxury Table", "Oak", 4, "Diamond", 1),
//                            actions.makeMats("Luxury Chair", "Birch", 4, "iron", 2),
//                            actions.makeMats("Oven", "Spruce", 4, "stone", 4),
//                            actions.makeMats("Money", "none", 0, "none", 0)
                    List<String> items = Arrays.asList("Wood_Sword", "Stone_Sword", "Iron_Sword", "Diamond_Sword","Table", "Chair", "Furnace","Door", "Luxury Table", "Luxury Chair", "Oven");
                    for (Mats material : mats) {
                        if (items.contains(material.mat_name)){
                            while((material.quantity>0)&&(t>=60000)){
                                t-=60000;
                                material.quantity-=1;
                                Money.quantity+=material.sell_value;
                            }
                            matsDao.update(Money);
                            matsDao.update(material);
                        }
                    }

                }
            }).start();

        }
        }

        public static Mats makeMats(String name, String wood_type, int wood_quantity, String rock_type, int rock_quantity, int sell_value ){
            Mats mat = new Mats();
            mat.mat_name = name;
            mat.wood_type = wood_type;
            mat.wood_quantity = wood_quantity;
            mat.mineral_type = rock_type;
            mat.mineral_quantity = rock_quantity;
            mat.quantity = 0;
            mat.sell_value = sell_value;
            return mat;
        }
    public static Mats makeMats(String name, String wood_type, int wood_quantity, String rock_type, int rock_quantity, int sell_value, int purchase_value ){
        Mats mat = new Mats();
        mat.mat_name = name;
        mat.wood_type = wood_type;
        mat.wood_quantity = wood_quantity;
        mat.mineral_type = rock_type;
        mat.mineral_quantity = rock_quantity;
        mat.purchase_value = purchase_value;
        mat.quantity = 0;
        mat.sell_value = sell_value;
        return mat;
    }



    public  static MatsDao idk(Context context ) {
        AppDatabase db = Room.databaseBuilder(context,
                AppDatabase.class, "Mats").build();

        return db.MatsDao();
    }
}

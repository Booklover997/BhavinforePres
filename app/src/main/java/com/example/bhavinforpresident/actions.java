package com.example.bhavinforpresident;
import android.util.Log;
import androidx.room.Room;
import database.*;
import android.content.Context;

import java.util.List;

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

package com.example.bhavinforpresident;

import android.util.Log;

public class actions {
    private static Boolean set = false;
    //Can be set to craft, sell, or explore
    private static String action;
    private static String details;
    public static void setAction(String action_to_set){
        action = action_to_set;
    }
    public static void setDetails(String details_to_set){
        details = details_to_set;
    }
    public static String getAction()
    {
        return action;
    }
    public static String getDetails(){
        return details;
    }
}

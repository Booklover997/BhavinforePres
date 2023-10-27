package com.example.bhavinforpresident;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;

import com.google.android.material.navigation.NavigationView;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import database.AppDatabase;
import database.Mats;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;

    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);



            List<Mats> mats = Arrays.asList(
                    actions.makeMats("Oak", "none", 0, "none", 0,0),
                    actions.makeMats("Birch", "none", 0, "none", 0,0),
                    actions.makeMats("Spruce", "none", 0, "none", 0,0),
                    actions.makeMats("Redwood", "none", 0, "none", 0,0),
                    actions.makeMats("Iron", "none", 0, "none", 0,0),
                    actions.makeMats("Stone", "none", 0, "none", 0,0),
                    actions.makeMats("Gold", "none", 0, "none", 0,0),
                    actions.makeMats("Diamond", "none", 0, "none", 0,0),
                    actions.makeMats("Wood_Sword", "Oak", 4, "none", 0,4),
                    actions.makeMats("Stone_Sword", "Birch", 2, "Stone", 2,8),
                    actions.makeMats("Iron_Sword", "Spruce", 2, "Iron", 2,14),
                    actions.makeMats("Diamond_Sword", "Redwood", 2, "Diamond", 2,22),
                    actions.makeMats("Chair", "Oak", 4, "none", 2,4),
                    actions.makeMats("Door", "Redwood", 6, "none", 4,6),
                    actions.makeMats("Table", "Birch", 8, "none", 2,6),
                    actions.makeMats("Furnace", "Spruce", 4, "stone", 4,8),
                    actions.makeMats("Luxury Table", "Oak", 4, "none", 1,12),
                    actions.makeMats("Luxury Chair", "Birch", 4, "iron", 2,10),
                    actions.makeMats("Oven", "Spruce", 4, "stone", 4,8),
                    actions.makeMats("Luxury Door", "Redwood", 2, "Gold", 4,8),
                    actions.makeMats("Money", "none", 0, "none", 0,0)
            );

        new Thread(new Runnable() {
            @Override
            public void run() {
                //fallbackToDestructiveMigration destroys old database when changes made
                // Initialize the Room database
                AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "Mats").fallbackToDestructiveMigration()
                        .build();

                // Iterate through the list of material names
                for (Mats mat : mats) {
                    // Check if the material already exists in the database
                    Mats existingMaterial = db.MatsDao().getMatsByName(mat.mat_name);

                    if (existingMaterial == null) {
                        // Material does not exist, so insert it with quantity 0 which is set in the mats class
                        db.MatsDao().insert(mat);
                    }
                }

            }
        }).start();

        setContentView(R.layout.activity_main);

        // Set a Toolbar to replace the ActionBar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Find our drawer view
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        // Setup drawer view
        setupDrawerContent(nvDrawer);
        selectDrawerItem();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
     private void setupDrawerContent(NavigationView navigationView) {
         navigationView.setNavigationItemSelectedListener(
                 new NavigationView.OnNavigationItemSelectedListener() {
                     @Override
                     public boolean onNavigationItemSelected(MenuItem menuItem) {
                         selectDrawerItem(menuItem);
                         return true;
                     }
                 });
     }

     public void selectDrawerItem(MenuItem menuItem) {
         // Create a new fragment and specify the fragment to show based on nav item clicked
         Fragment fragment = null;
         Class fragmentClass = null;

         int itemId = menuItem.getItemId(); // Get the ID of the selected menu item

         if (itemId == R.id.nav_home) {
             fragmentClass = home.class;
         } else if (itemId == R.id.nav_map) {
             fragmentClass = Map.class;
         } else if (itemId == R.id.nav_craft) {
             fragmentClass = Craft.class;
         }

         try {
             fragment = (Fragment) fragmentClass.newInstance();
         } catch (Exception e) {
             e.printStackTrace();
         }

         // Insert the fragment by replacing any existing fragment
         FragmentManager fragmentManager = getSupportFragmentManager();
         fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

         // Highlight the selected item as done by NavigationView
         menuItem.setChecked(true);
         // Set action bar title
         setTitle(menuItem.getTitle());
         // Close the navigation drawer
         mDrawer.closeDrawers();
     }
     public void selectDrawerItem() {
         // Create a new fragment and specify the fragment to show based on nav item clicked
         Fragment fragment = null;
         Class fragmentClass = null;


         fragmentClass = home.class;


         try {
             fragment = (Fragment) fragmentClass.newInstance();
         } catch (Exception e) {
             e.printStackTrace();
         }

         // Insert the fragment by replacing any existing fragment
         FragmentManager fragmentManager = getSupportFragmentManager();
         fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

         // Highlight the selected item as done by NavigationView
         // Set action bar title
         setTitle("Home");
         // Close the navigation drawer
         mDrawer.closeDrawers();
     }

 }
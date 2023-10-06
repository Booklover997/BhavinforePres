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
import java.util.List;
import java.util.Locale;

import database.AppDatabase;
import database.Mats;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;

    // Make sure to be using androidx.appcompat.app.ActionBarDrawerToggle version.
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        // Create a list of material names
        List<String> materialNames = Arrays.asList("Oak", "Birch", "Spruce", "Iron", "Stone", "Diamond");

// Start a new thread to perform database operations
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Initialize the Room database
                AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "Mats").build();

                // Iterate through the list of material names
                for (String materialName : materialNames) {
                    // Check if the material already exists in the database
                    Mats existingMaterial = db.MatsDao().getMatsByName(materialName);

                    if (existingMaterial == null) {
                        // Material does not exist, so insert it with quantity 0
                        Mats newMaterial = new Mats();
                        newMaterial.quantity = 0;
                        newMaterial.name = materialName;
                        db.MatsDao().insert(newMaterial);
                    }
                }

            }
        }).start();

        setContentView(R.layout.activity_main);

        // Set a Toolbar to replace the ActionBar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // This will display an Up icon (<-), we will replace it with hamburger later
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
        // The action bar home/up action should open or close the drawer.
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
package com.example.smartspend;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.smartspend.R;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Find the DrawerLayout and NavigationView in the layout
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.naView);

        // Set up the ActionBarDrawerToggle for smooth drawer open/close animation
        setupActionBarDrawerToggle(drawerLayout);

        // Set up the NavigationView item click listener
        setupNavigationItemSelectedListener(navigationView);

        // Set up the button click listener to open the drawer
        setupButtonClickListener(drawerLayout, navigationView);
    }

    // Method to set up ActionBarDrawerToggle for smooth drawer open/close animation
    private void setupActionBarDrawerToggle(DrawerLayout drawerLayout) {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        // Add the toggle as a drawer listener to sync the state
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    // Method to set up NavigationView item click listener
    private void setupNavigationItemSelectedListener(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(item -> {
            // Handle item clicks here
            int id = item.getItemId();

            // Add your logic to handle different menu items if needed

            return true;
        });
    }

    // Method to set up button click listener to open the drawer
    private void setupButtonClickListener(DrawerLayout drawerLayout, NavigationView navigationView) {
        Button btnOpenDrawer = findViewById(R.id.btnOpenDrawer);
        btnOpenDrawer.setOnClickListener(v -> {
            // Open the drawer when the button is clicked
            drawerLayout.openDrawer(navigationView, true);
        });
    }
}
package com.example.smartspend;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.smartspend.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class NetBalanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_netbalance);

        // Find the DrawerLayout and NavigationView in the layout
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.naView);

        // Set up the ActionBarDrawerToggle for smooth drawer open/close animation
        setupActionBarDrawerToggle(drawerLayout);

        // Set up the NavigationView item click listener
        setupNavigationItemSelectedListener(navigationView);

        // Set up the button click listener to open the drawer
        setupDrawerButtonClickListener(drawerLayout, navigationView);

        // Set up the button click listener to start TransactionActivity
        setupTransactionButtonClickListener();

        // Set up the BottomNavigationView item click listener
        setupBottomNavigationViewListener();
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
    private void setupDrawerButtonClickListener(DrawerLayout drawerLayout, NavigationView navigationView) {
        Button btnOpenDrawer = findViewById(R.id.dashboard);
        btnOpenDrawer.setOnClickListener(v -> {
            // Open the drawer when the button is clicked
            drawerLayout.openDrawer(navigationView, true);
        });
    }

    // Method to set up button click listener to start TransactionActivity
    private void setupTransactionButtonClickListener() {
        Button transButton = findViewById(R.id.transactionBtnNet);
        transButton.setOnClickListener(v -> {
            Intent intent = new Intent(NetBalanceActivity.this, TransactionActivity.class);
            startActivity(intent);
        });
    }

    // Method to set up BottomNavigationView item click listener
    private void setupBottomNavigationViewListener() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.home) {
                // Already in NetBalanceActivity, do nothing or add additional logic
            } else if (itemId == R.id.chatbot) {
                navigateToActivity(ChatbotActivity.class);
            } else if (itemId == R.id.profile) {
                // Navigate to the ProfileActivity
                navigateToActivity(ProfileActivity.class);
            }
            return true;
        });
    }

    // Method to start a new activity based on the provided class
    private void navigateToActivity(Class<?> activityClass) {
        Intent intent = new Intent(NetBalanceActivity.this, activityClass);
        startActivity(intent);
    }
}

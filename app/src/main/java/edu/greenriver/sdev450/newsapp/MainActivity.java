package edu.greenriver.sdev450.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.Group;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ActionBar;
import android.app.UiModeManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Context mainContext;
    private ArrayList<String> categories;
    public static final String SHARED_PREF_ANDROID = "ANDROIDCLASS";

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    TextView textViewHello;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mainContext = this;

        loadPreferences();

        categories = new ArrayList<>(10);
        categories.add(getString(R.string.world));
        categories.add(getString(R.string.business));
        categories.add(getString(R.string.technology));
        categories.add(getString(R.string.sport));
        categories.add(getString(R.string.entertainment));

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);

        textViewHello = findViewById(R.id.textViewHello);


        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //makes the back button appear


        Menu slidingMenu = navigationView.getMenu();
        slidingMenu.add(Menu.FIRST, 1, Menu.FIRST, "Test Item");




        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int buttonID = item.getItemId();
                if(buttonID == R.id.home){
                    Toast.makeText(getApplicationContext(), "You are already home.", Toast.LENGTH_LONG).show();
                }else if(buttonID == R.id.slidingSettings){
                    Toast.makeText(getApplicationContext(), "Settings selected", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(mainContext, SettingsActivity.class);
                    intent.putExtra("categories", categories);
                    startActivity(intent);
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int itemID = item.getItemId();
        if(itemID == R.id.settings){
            //Toast.makeText(getApplicationContext(), "Settings button is clicked", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, SettingsActivity.class);
            intent.putExtra("categories", this.categories);
            startActivity(intent);
        }
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public List<String> getCategories(){
        return categories;
    }

    private void loadPreferences(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_ANDROID, MODE_PRIVATE);
        Boolean nightMode = sharedPreferences.getBoolean("nightMode", false);


        if (nightMode) {
            getApplicationContext();
            UiModeManager uiModeManager = (UiModeManager) getApplicationContext()
                    .getSystemService(UI_MODE_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                uiModeManager.setNightMode(UiModeManager.MODE_NIGHT_YES);
            }else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
        } else {
            //nothing
        }
    }

}
package edu.greenriver.sdev450.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.UiModeManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //public static String[] CATEGORIES = {"World", "Business", "Technology", "Sport", "Entertainment"};
    private ArrayList<String> categories;
    public static final String SHARED_PREF_ANDROID = "ANDROIDCLASS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadPreferences();

        categories = new ArrayList<>(10);
        categories.add(getString(R.string.world));
        categories.add(getString(R.string.business));
        categories.add(getString(R.string.technology));
        categories.add(getString(R.string.sport));
        categories.add(getString(R.string.entertainment));

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
//            Bundle bundle = intent.getExtras();
//            if (bundle != null) {
//                bundle.putStringArrayList("categories", this.categories);
//            } else {
//                //something else?
//            }
            intent.putExtra("categories", this.categories);
            startActivity(intent);
        }
        return true;
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
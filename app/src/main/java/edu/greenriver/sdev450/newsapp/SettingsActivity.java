package edu.greenriver.sdev450.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.UiModeManager;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {

    private ArrayList<String> categories;
    private ListView listView;
    private ToggleButton nightMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        categories = getIntent().getExtras().getStringArrayList("categories");
        listView = findViewById(R.id.settingsCategoryListView);
        nightMode = findViewById(R.id.toggleButtonNightMode);

        CustomAdapter customAdapter = new CustomAdapter(this, categories);
        listView.setAdapter(customAdapter);

        getApplicationContext();
        SharedPreferences sharedPreferences = getSharedPreferences(
                MainActivity.SHARED_PREF_ANDROID,
                MODE_PRIVATE);
        nightMode.setChecked(sharedPreferences.getBoolean("nightMode", false));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //
            }
        });

        nightMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //System.out.println("Checkbox clicked");
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("nightMode", isChecked);
                editor.apply();

                if (isChecked) {
                    UiModeManager uiModeManager = (UiModeManager) getSystemService(UI_MODE_SERVICE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                        uiModeManager.setNightMode(UiModeManager.MODE_NIGHT_YES);
                    }else {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    }
                } else {
                    UiModeManager uiModeManager = (UiModeManager) getSystemService(UI_MODE_SERVICE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                        uiModeManager.setNightMode(UiModeManager.MODE_NIGHT_NO);
                    }else {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    }
                }
            }
        });

    }
}
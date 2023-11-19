package edu.greenriver.sdev450.newsapp;

import android.app.UiModeManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

    //fields//attributes
    private Context myContext;
    private LayoutInflater inflater;
    private List<String> categories;



    //constructor
    public CustomAdapter(Context myContext, List<String> categories) {
        this.myContext = myContext;
        inflater = LayoutInflater.from(myContext);
        this.categories = categories;
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Object getItem(int position) {
        return categories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = inflater.inflate(R.layout.list_view_category_item, parent, false);
        }
        TextView textView = convertView.findViewById(R.id.textViewCategoryListView);
        textView.setText(categories.get(position));

        CheckBox checkBox = convertView.findViewById(R.id.checkBoxCategoryListView);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                System.out.println("Checkbox clicked");
                SharedPreferences sharedPreferences = myContext.getSharedPreferences(
                        MainActivity.SHARED_PREF_ANDROID,
                        myContext.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("nightMode", isChecked);

                if (isChecked) {
                    UiModeManager uiModeManager = (UiModeManager) myContext
                            .getSystemService(myContext.UI_MODE_SERVICE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                        uiModeManager.setNightMode(UiModeManager.MODE_NIGHT_YES);
                    }else {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    }
                } else {
                    UiModeManager uiModeManager = (UiModeManager) myContext
                            .getSystemService(myContext.UI_MODE_SERVICE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                        uiModeManager.setNightMode(UiModeManager.MODE_NIGHT_NO);
                    }else {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    }
                }
            }
        });


        return convertView;
    }
}

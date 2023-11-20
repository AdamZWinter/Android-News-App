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

import java.util.ArrayList;
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

        SharedPreferences sharedPreferences = myContext.getSharedPreferences(
                MainActivity.SHARED_PREF_ANDROID,
                Context.MODE_PRIVATE);

        CheckBox checkBox = convertView.findViewById(R.id.checkBoxCategoryListView);
        checkBox.setChecked(
                sharedPreferences.getBoolean(categories.get(position), true)
        );

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = checkBox.isChecked();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                if (isChecked) {
                    editor.putBoolean(categories.get(position), true);
                } else {
                    editor.putBoolean(categories.get(position), false);
                }
                editor.apply();

                ArrayList<Boolean> booleans = new ArrayList<>();
                for (int i = 0; i < categories.size(); i++) {
                    booleans.add(sharedPreferences.getBoolean(categories.get(i), false));
                }
                System.out.println(booleans);

            }
        });

        return convertView;
    }
}

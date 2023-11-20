package edu.greenriver.sdev450.newsapp;

import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainNewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainNewsFragment extends Fragment {

    View view;
    ListView listView;
    List<NewsStory> newsStories;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private ArrayList<String> mParam1;
    private int mParam2;

    public MainNewsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainNewsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainNewsFragment newInstance(ArrayList<String> param1, int param2) {
        MainNewsFragment fragment = new MainNewsFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getStringArrayList(ARG_PARAM1);
            mParam2 = getArguments().getInt(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main_news, container, false);
        listView = view.findViewById(R.id.listViewMainNewsFragment);

        ArrayList<NewsStory> newsStoryArrayList = new ArrayList<>();

        for (String category : mParam1) {
            for (int i = 0; i < mParam2; i++) {
                newsStoryArrayList.add(new NewsStory(category));
            }
        }

        CustomAdapterMainNews customAdapter = new CustomAdapterMainNews(this.getContext(), newsStoryArrayList);
        listView.setAdapter(customAdapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getActivity().getApplicationContext(), "Long Click", Toast.LENGTH_LONG).show();
                PopupMenu popupMenu = new PopupMenu(getActivity().getApplicationContext(), view);
                popupMenu.getMenuInflater().inflate(R.menu.context_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int itemID = item.getItemId();
                        if(itemID == R.id.bookmark){
                            Toast.makeText(getActivity().getApplicationContext(), "Bookmark selected", Toast.LENGTH_LONG).show();
                        }else if(itemID == R.id.share){
                            Toast.makeText(getActivity().getApplicationContext(), "Share selected", Toast.LENGTH_LONG).show();
                        }
                        return true;
                    }
                });
                popupMenu.show();
                return false;
            }
        });

        return view;
    }

    public void setNewsStories(List<NewsStory> newsStories){
        this.newsStories = newsStories;
    }
}
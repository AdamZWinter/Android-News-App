package edu.greenriver.sdev450.newsapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainNewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainNewsFragment extends Fragment {

    View view;
    ListView listView;

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public MainNewsFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment MainNewsFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static MainNewsFragment newInstance(String param1, String param2) {
//        MainNewsFragment fragment = new MainNewsFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main_news, container, false);
        listView = view.findViewById(R.id.listViewMainNewsFragment);
        ArrayList<NewsStory> testList = new ArrayList<>();
        testList.add(new NewsStory("Business"));
        testList.add(new NewsStory("Business"));
        testList.add(new NewsStory("Business"));
        testList.add(new NewsStory("Business"));
        testList.add(new NewsStory("Business"));
        testList.add(new NewsStory("Business"));
        CustomAdapterMainNews customAdapter = new CustomAdapterMainNews(this.getContext(), testList);
        listView.setAdapter(customAdapter);


        return view;
    }
}
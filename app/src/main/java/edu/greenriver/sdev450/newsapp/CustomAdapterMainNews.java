package edu.greenriver.sdev450.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapterMainNews extends BaseAdapter {

    //fields//attributes
    private Context myContext;
    private LayoutInflater inflater;
    private List<NewsStory> newsStories;


    //constructor
    public CustomAdapterMainNews(Context myContext, List<NewsStory> newsStories) {
        this.myContext = myContext;
        inflater = LayoutInflater.from(myContext);
        this.newsStories = newsStories;
    }
    @Override
    public int getCount() {
        return newsStories.size();
    }

    @Override
    public Object getItem(int position) {
        return newsStories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = inflater.inflate(R.layout.list_view_news_story_item, parent, false);
        }
        ImageView imageView = convertView.findViewById(R.id.imageViewNewsItem);
        TextView textView = convertView.findViewById(R.id.textViewNewsListHeadline);

        imageView.setImageResource(R.drawable.baseline_airplanemode_active_24);
        textView.setText(newsStories.get(position).getHeadline());

        return convertView;
    }
}

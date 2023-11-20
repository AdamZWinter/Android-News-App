package edu.greenriver.sdev450.newsapp;

import java.io.Serializable;
import java.util.Random;

public class NewsStory implements Serializable {
    private String headline;
    private String fullText;
    private String author;
    private String category;
    private int imageId;
    public static Random randomNews = new Random();
    public static int[] images = {R.drawable.baseline_back_hand_24,
                                    R.drawable.baseline_bathtub_24,
                                    R.drawable.baseline_blind_24,
                                    R.drawable.baseline_construction_24,
                                    R.drawable.baseline_departure_board_24,
                                    R.drawable.baseline_favorite_border_24
                                };

    public NewsStory(String headline, String fullText, String author, String category, int imageId) {
        this.headline = headline;
        this.fullText = fullText;
        this.author = author;
        this.category = category;
        this.imageId = imageId;
    }

    public NewsStory(String category) {
        this.category = category;
        this.headline = "" + category + " news story no. " + randomNews.nextInt(100);
        this.fullText = "This the full text of the story";
        this.author = "Author Name";
        this.imageId = images[randomNews.nextInt(6)];
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getFullText() {
        return fullText;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}

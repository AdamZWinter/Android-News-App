package edu.greenriver.sdev450.newsapp;

import java.util.Random;

public class NewsStory {
    private String headline;
    private String fullText;
    private String author;
    private String category;
    private int imageId;
    public static Random randomNews = new Random();

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
        this.imageId = randomNews.nextInt(5);
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
}

package com.example.sangnv.appnews.RssGet;

import android.util.Log;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ThinkPad on 5/6/2018.
 */

public class Item_RSS {

    private String title;
    private String description;
    private String date;
    private String link;
    private boolean isSaved;

    public Item_RSS() {
        this.isSaved = false;
    }

    public boolean isSaved(){
        return this.isSaved;
    }

    public void setSaved(boolean saved) {
        this.isSaved = saved;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        String pattern = new String("src=\"(\\S*?)\"");
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(description);
        if ( matcher.find()) {
            this.description = matcher.group(1);
        }else
            this.description = null;

        Log.d("description url", description);
        Log.d("description pattern", pattern);
        Log.d("description item match", this.description);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isNull(){
        if (this.title == null) return true;
        if (this.description == null) return true;
        if (this.date == null) return true;
        if (this.link == null) return true;
        return false;
    }
}

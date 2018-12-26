package me.delong.githubgraticule;

import android.appwidget.AppWidgetManager;
import android.graphics.Color;
import android.os.AsyncTask;
import android.widget.RemoteViews;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GraticuleScraper extends AsyncTask<String, Void, String> {
    RemoteViews views;
    AppWidgetManager appWidgetManager;
    int appWidgetId;

    public GraticuleScraper(RemoteViews views, AppWidgetManager appWidgetManager, int appWidgetId){
        this.views = views;
        this.appWidgetManager = appWidgetManager;
        this.appWidgetId = appWidgetId;
    }


    protected String doInBackground(String... urls) {
        //2018-12-24
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();

        Document doc = null;
        try {
            doc = Jsoup.connect("https://github.com/"+urls[0]+"/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements content = doc.getElementsByAttributeValue("data-date", dtf.format(now));

        return content.first().attr("fill");
    }

    protected void onPostExecute(String attr) {
        // TODO: check this.exception
        // TODO: do something with the feed
        System.err.println("ATTRIBUTE!!! "+attr);
        views.setInt(R.id.bg_layout, "setBackgroundColor", Color.parseColor("#FFFFFF"));

        if(attr == null){
            System.err.println("Updating Color Null");
            views.setInt(R.id.bg_layout, "setBackgroundColor", Color.parseColor("#FFFFFF"));
        }
        else{
            System.err.println("Updating Color Assigned");
            views.setInt(R.id.bg_layout, "setBackgroundColor", Color.parseColor(attr));
        }
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }
}
package me.delong.githubgraticule;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.graphics.Color;
import android.widget.RemoteViews;

import java.util.concurrent.ExecutionException;

/**
 * Implementation of App Widget functionality.
 */
public class GraticuleTracker extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        //CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.graticule_tracker);
        //views.setTextViewText(R.id.appwidget_text, widgetText);

        new GraticuleScraper(new RemoteViews(context.getPackageName(), R.layout.graticule_tracker), appWidgetManager, appWidgetId).execute("dangerousfood");
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
        //new GraticuleScraper(new RemoteViews(context.getPackageName(), R.layout.graticule_tracker)).execute("dangerousfood");
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}


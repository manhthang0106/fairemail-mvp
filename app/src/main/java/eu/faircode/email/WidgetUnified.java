package eu.faircode.email;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

public class WidgetUnified extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    private void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        new Thread(() -> {
            DB db = DB.getInstance(context);
            // Get unread count would be through LiveData, but for widget we need immediate value
            // This is simplified
            
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_unified);
            views.setTextViewText(R.id.tv_count, "0");
            
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }).start();
    }
}

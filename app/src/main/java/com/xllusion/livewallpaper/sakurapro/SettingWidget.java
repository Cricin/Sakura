package com.xllusion.livewallpaper.sakurapro;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class SettingWidget extends AppWidgetProvider {
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] iArr) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
        int i = iArr[0];
        Intent intent = new Intent(context, Settings.class);
        intent.putExtra("appWidgetId", i);
        remoteViews.setOnClickPendingIntent(R.id.imageButton, PendingIntent.getActivity(context, i, intent, 0));
        appWidgetManager.updateAppWidget(iArr, remoteViews);
        super.onUpdate(context, appWidgetManager, iArr);
    }
}

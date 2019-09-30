package com.example.moviecatalogue.services;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.example.moviecatalogue.R;
import com.example.moviecatalogue.activity.MainActivity;

import java.util.Calendar;

public class DailyReminder extends BroadcastReceiver {
    public static String CHANNEL_ID = "ch_01";
    public static String CHANNEL_NAME = "Catalogue Channel";
    public static final String Repeating = "RepeatAlarm";
    public static final String Message = "message";
    public static final String Type = "type";

    private final int Id_Onetime = 100;
    private final int Id_Repeating = 101;

    public DailyReminder() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String type = intent.getStringExtra(Type);
        String message = intent.getStringExtra(Message);

        String title = type.equalsIgnoreCase(Repeating) ? "One Time Alarm" : "Repeating Alarm";
        int Id = type.equalsIgnoreCase(Repeating) ? Id_Onetime : Id_Repeating;

        title = context.getResources().getString(R.string.app_name);

        showNotification(context, title, message, Id);
        Log.d("Bebas", "BEBAS");
    }

    public void showNotification(Context context, String title, String message, int Id) {
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pending = PendingIntent.getActivity(context, Id, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle(title)
                .setContentText(message)
                .setColor(ContextCompat.getColor(context, android.R.color.transparent))
                .setContentIntent(pending)
                .setAutoCancel(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            builder.setChannelId(CHANNEL_ID);
            if (mNotificationManager != null) {
                mNotificationManager.createNotificationChannel(channel);
            }
        }

        Notification notification = builder.build();

        if (mNotificationManager != null) {
            mNotificationManager.notify(Id, notification);
        }
    }

    public void setDailyReminder(Context context, String type, String time, String message) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context, DailyReminder.class);
        intent.putExtra(Message, message);
        intent.putExtra(Type, type);

        String timeArray[] = time.split(":");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]));
        calendar.set(Calendar.SECOND, 0);

        if (calendar.before(Calendar.getInstance())) calendar.add(Calendar.DATE, 1);

        int requestCode = Id_Repeating;
        PendingIntent pending = PendingIntent.getBroadcast(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pending);
    }

    public void cancelDailyReminder(Context context, String type) {
        AlarmManager alarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, DailyReminder.class);

        int requestCode = type.equalsIgnoreCase(Repeating) ? Id_Onetime : Id_Repeating;
        PendingIntent pending = PendingIntent.getBroadcast(context, requestCode, intent, 0);

        alarm.cancel(pending);
    }
}

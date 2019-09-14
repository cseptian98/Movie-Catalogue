package com.example.moviecatalogue.services;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import com.example.moviecatalogue.R;
import com.example.moviecatalogue.activity.MainActivity;

import java.util.Calendar;

public class DailyReminderService extends BroadcastReceiver {
    public static final String Type_Repeating = "RepeatAlarm";
    public static final String Extra_Msg = "message";
    public static final String Extra_Type = "type";

    private final int Notif_Id_Onetime = 100;
    private final int Notif_Id_Repeating = 101;

    public DailyReminderService(){

    }

    @Override
    public void onReceive(Context context, Intent intent){
        String type = intent.getStringExtra(Extra_Type);
        String message = intent.getStringExtra(Extra_Msg);

        String title = type.equalsIgnoreCase(Type_Repeating) ? "One Time Alarm" : "Repeating Alarm";
        int notifId = type.equalsIgnoreCase(Type_Repeating) ? Notif_Id_Onetime : Notif_Id_Repeating;

        title = context.getResources().getString(R.string.app_name);

        showNotification(context, title, message, notifId);
    }

    public void showNotification(Context context, String title, String message, int notifId){
        NotificationManager notificationManagerCompat = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, notifId, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle(title)
                .setContentText(message)
                .setColor(ContextCompat.getColor(context, android.R.color.transparent))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        notificationManagerCompat.notify(notifId, builder.build());
    }

    public void setDailyReminderNotif(Context context, String type, String time, String message){
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context, DailyReminderService.class);
        intent.putExtra(Extra_Msg, message);
        intent.putExtra(Extra_Type, type);

        String timeArray[] = time.split(":");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]));
        calendar.set(Calendar.SECOND, 0);

        if(calendar.before(Calendar.getInstance())) calendar.add(Calendar.DATE, 1);

        int requestCode = Notif_Id_Repeating;
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }

    public void cancelDailyReminder(Context context, String type){
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, DailyReminderService.class);

        int requestCode = type.equalsIgnoreCase(Type_Repeating) ? Notif_Id_Onetime : Notif_Id_Repeating;
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, 0);

        alarmManager.cancel(pendingIntent);
    }
}

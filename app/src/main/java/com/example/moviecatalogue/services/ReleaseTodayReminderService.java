package com.example.moviecatalogue.services;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.moviecatalogue.R;
import com.example.moviecatalogue.activity.MainActivity;
import com.example.moviecatalogue.movie.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ReleaseTodayReminderService extends BroadcastReceiver {

    public static final String Type_Repeating = "RepeatingAlarm";
    public static final String Extra_Msg = "message";
    public static final String Extra_Type = "type";
    private static final String API_KEY = "5f793d033ea33558e13b3664b3eadca9";

    private final int Notif_Id_Onetime = 100;
    private final int Notif_Id_Repeating = 101;

    public ReleaseTodayReminderService() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        getTodayMovies(context);
    }
    private void showNotification(Context context, String title, String message, int notifId) {
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

    public void setMovieReleaseNotif(Context context, String type, String time) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context, ReleaseTodayReminderService.class);
        intent.putExtra(Extra_Type, type);

        String timeArray[] = time.split(":");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]));
        calendar.set(Calendar.SECOND, 0);

        if (calendar.before(Calendar.getInstance())) calendar.add(Calendar.DATE, 1);

        int requestCode = Notif_Id_Repeating;
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }

    public void cancelMovieNotif(Context context, String type) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, ReleaseTodayReminderService.class);

        int requestCode = type.equalsIgnoreCase(Type_Repeating) ? Notif_Id_Onetime : Notif_Id_Repeating;
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, 0);

        alarmManager.cancel(pendingIntent);
    }

    public void getTodayMovies(final Context context) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        final String now = dateFormat.format(Calendar.getInstance().getTime());
        String url = "https://api.themoviedb.org/3/discover/movie?api_key="+API_KEY+"&primary_release_date.gte=" +
                ""+now+"&primary_release_date.lte="+now+"";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("results");
                    for (int i = 0; i < array.length(); i++){

                        JSONObject obj = array.getJSONObject(i);
                        Movie item = new Movie(obj);

                        if (item.getRelease_date().equals(now)) {
                            String msg = String.format(context.getResources().getString(R.string.notif_msg_release),item.getTitle());
                            showNotification(context, item.getTitle(), msg, Notif_Id_Repeating+i);
                        }
                    }
                } catch (JSONException e) {

                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}

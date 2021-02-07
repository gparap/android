package gparap.apps.alarm_clock;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

/**
 * Broadcast Receiver for Alarm Clock
 * Created by gparap on 2020-09-16
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String channelId = "channel id";
        NotificationChannel notificationChannel = null;

        //create a notification channel (needed for Android 8.0 or higher)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            CharSequence channelName = "channel name";
            String channelDescription = "channel description";
            int notificationImportance = NotificationManager.IMPORTANCE_DEFAULT;
            notificationChannel = new NotificationChannel(channelId, channelName, notificationImportance);
            notificationChannel.setDescription(channelDescription);
        }

        //create the notification
        Notification notification = new NotificationCompat.Builder(context, channelId)
                .setContentTitle("Alarm")
                .setContentText("Alarm is ON")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT).build();

        //post the notification
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            //create an existing notification channel to post notifications
            notificationManager.createNotificationChannel(notificationChannel);
        }
        notificationManager.notify(0, notification);
    }
}

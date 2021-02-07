package gparap.apps.countdown_timer;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;

/**
 * Created by gparap on 2020-09-18.
 */
public class CountdownReceiver extends BroadcastReceiver {
    @SuppressLint("ObsoleteSdkInt")
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        final String channelId = "channel id";

        //create a notification channel (needed for Android 8.0 or higher)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            CharSequence channelName = "channel name";
            String channelDescription = "channel description";
            int notificationImportance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, notificationImportance);
            notificationChannel.setDescription(channelDescription);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        //create the notification
        Notification notification = new NotificationCompat.Builder(context, channelId)
                .setContentTitle("Countdown Timer")
                .setContentText("Countdown is finished.")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT).build();

        //post the notification
        notificationManager.notify(0, notification);
    }
}

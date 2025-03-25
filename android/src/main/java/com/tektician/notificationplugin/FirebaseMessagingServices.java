package com.tektician.notificationplugin;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.tektician.notificationplugin.NotificationPluginPlugin;
import com.getcapacitor.Bridge;
import com.getcapacitor.PluginHandle;

public class FirebaseMessagingServices extends FirebaseMessagingService {

    private static final String CHANNEL_ID = "default_channel_id";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
      super.onMessageReceived(remoteMessage);

      if (AppForeground.isAppInForeground()) {
        String title = null;
        String body = null;

        // Handle notification messages (sent via Firebase Console)
        if (remoteMessage.getNotification() != null) {
          title = remoteMessage.getNotification().getTitle();
          body = remoteMessage.getNotification().getBody();
        }

        // Handle data messages (custom key-value pairs sent via backend)
        if (remoteMessage.getData().size() > 0) {
          if (title == null) title = remoteMessage.getData().get("title");
          if (body == null) body = remoteMessage.getData().get("body");
        }

        if (title != null && body != null) {

         triggerEvent(String.valueOf(true));
          showNotification(title, body);

        }

      }
    }

    private void showNotification(String title, String body) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // For devices running Android 8.0 (API 26) and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence channelName = "Default Notifications";
            String channelDescription = "Channel for default notifications";
            int importance = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, channelName, importance);
            channel.setDescription(channelDescription);

            // Register the channel with the system
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }

        // Build the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification) // Replace with your small icon
            .setContentTitle(title)
            .setContentText(body)
            .setPriority(NotificationCompat.PRIORITY_HIGH) // For older versions
            .setAutoCancel(true);

        // Show the notification
        if (notificationManager != null) {
            notificationManager.notify(0, builder.build());
        }
    }


    public void triggerEvent(String message) {
      Bridge bridge = MainActivity.getInstance().getBridge();
      if(bridge != null) {

        PluginHandle pluginHandle = bridge.getPlugin("NotificationPlugin");
        if (pluginHandle != null) {
          // Get the actual plugin instance
          NotificationPluginPlugin plugin = (NotificationPluginPlugin) pluginHandle.getInstance();
          if (plugin != null) {
            // Call the method on the plugin instance
            PluginCall call = new PluginCall(plugin, "sendCustomEvent");
            call.setData(new JSObject().put("message", message));

            // Resolve the call manually
            plugin.sendCustomEvent(call);
            Log.d("FirebaseMessaging", "Triggering custom event.");

          } else {
            Log.e("FirebaseMessaging", "CustomPlugin instance is null.");
          }
        } else {
          Log.e("FirebaseMessaging", "PluginHandle for CustomPlugin not found.");
        }
      } else {
        Log.e("FirebaseMessaging", "Bridge instance is null.");
      }
    }

}

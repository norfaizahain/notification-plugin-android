package com.tektician.notificationplugin;
import android.app.NotificationManager;
import android.util.Log;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.JSObject;
import android.content.Context;
import android.os.Build;

public class NotificationPlugin {

    private Context context;

    public NotificationPlugin(Context context) {
        this.context = context;
    }

      // Check Notification Permission
    public JSObject checkNotificationPermission() {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        JSObject result = new JSObject();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            result.put("status", "not_applicable"); // No permission required on Android 13+
        } else {
            boolean granted = notificationManager.areNotificationsEnabled();
            result.put("status", granted ? "granted" : "denied");
        }

        Log.d("NotificationPlugin", "Notification permission: " + result.getString("status"));
        return result;
    }

    // Send Custom Event
    // public JSObject sendCustomEvent(String message) {
    //     JSObject eventData = new JSObject();
    //     eventData.put("message", message);
    //     return eventData;
    // }


    // public String echo(String value) {
    //     Log.i("Echo", value);
    //     return value;
    // }



   
}

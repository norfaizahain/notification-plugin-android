package com.tektician.notificationplugin;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.Bridge;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.os.Build;
import android.util.Log;
// import android.content.pm.PackageManager;
// import androidx.annotation.NonNull;

@CapacitorPlugin(name = "NotificationPlugin")
public class NotificationPluginPlugin extends Plugin{
    private static final String TAG = "NotificationPermission";
    private NotificationPlugin implementation;


    private static Bridge staticBridge;
    private boolean isPermissionDialogVisible = false;

    @Override
    public void load() {
        super.load();
        
        staticBridge = this.getBridge(); // Store the Bridge instance
        implementation = new NotificationPlugin(getContext());
      
    }

    public static Bridge getStaticBridge() {
        return staticBridge;
    }

    @PluginMethod
    public void checkNotificationPermission(PluginCall call) {
        JSObject result = implementation.checkNotificationPermission();
        call.resolve(result);
    }
    public void triggerCustomEvent(String message) {
        JSObject eventData = new JSObject();
        eventData.put("message", message);
    
        notifyListeners("inAppNotificationTriggered", eventData);
        Log.d("NotificationPluginPlugin", "Custom event triggered: " + message);
    }

    // @PluginMethod
    // public void sendCustomEvent(PluginCall call) {
    //     String message = call.getString("message");
    //     if (message == null) {
    //         call.reject("Message cannot be null");
    //         return;
    //     }

    //     JSObject eventData = implementation.sendCustomEvent(message);
    //     notifyListeners("inAppNotificationTriggered", eventData);
    //     // call.resolve(eventData);
    // }
    // @Override
    // protected void handleOnResume() {
    //     super.handleOnResume();
    //     if (isPermissionDialogVisible) {
    //         isPermissionDialogVisible = false; // Reset the flag
    //     } else {
    //         if (staticBridge != null) {
    //             staticBridge.triggerWindowJSEvent("nativeAppResumed");
    //         }
    //     }
    // }

    // public void onPermissionResult(int requestCode, String[] permissions, int[] grantResults) {
    //     boolean allGranted = true;
    //     for (int result : grantResults) {
    //         if (result != PackageManager.PERMISSION_GRANTED) {
    //             allGranted = false;
    //             break;
    //         }
    //     }

    //     isPermissionDialogVisible = true;

    //     // Send event to the web app
    //     if (staticBridge != null) {
    //         JSObject result = new JSObject();
    //         result.put("permissionsGranted", allGranted);
    //         staticBridge.triggerWindowJSEvent("notificationPermissionResult", result.toString());
    //     }
    // }

    // @PluginMethod
    // public void echo(PluginCall call) {
    //     String value = call.getString("value");

    //     JSObject ret = new JSObject();
    //     ret.put("value", implementation.echo(value));
    //     call.resolve(ret);
    // }


   
}

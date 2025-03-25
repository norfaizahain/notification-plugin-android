package com.tektician.notificationplugin;
// package com.example.capacitorplugin;
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

@CapacitorPlugin(name = "NotificationPlugin")
public class NotificationPluginPlugin extends Plugin {
    private static final String TAG = "NotificationPermission";
    private NotificationPlugin implementation = new NotificationPlugin();

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

    @PluginMethod
    public void sendCustomEvent(PluginCall call) {
        String message = call.getString("message");
        if (message == null) {
            call.reject("Message cannot be null");
            return;
        }

        JSObject eventData = implementation.sendCustomEvent(message);
        notifyListeners("inAppNotificationTriggered", eventData);
        call.resolve(eventData);
    }
    @Override
    protected void handleOnResume() {
        super.handleOnResume();
        if (isPermissionDialogVisible) {
            isPermissionDialogVisible = false; // Reset the flag
        } else {
            if (staticBridge != null) {
                staticBridge.triggerWindowJSEvent("nativeAppResumed");
            }
        }
    }

    @Override
    protected void handleOnRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.handleOnRequestPermissionsResult(requestCode, permissions, grantResults);
        isPermissionDialogVisible = true;
    }

    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", implementation.echo(value));
        call.resolve(ret);
    }


   
}

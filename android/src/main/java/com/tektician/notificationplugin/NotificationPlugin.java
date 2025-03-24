package com.tektician.notificationplugin;

import android.util.Log;

public class NotificationPlugin {

    public String echo(String value) {
        Log.i("Echo", value);
        return value;
    }
}

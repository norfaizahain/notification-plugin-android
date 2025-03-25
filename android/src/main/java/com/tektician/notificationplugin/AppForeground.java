package com.tektician.notificationplugin;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ProcessLifecycleOwner;

public class AppForeground extends Application {

  private static boolean isAppInForeground = false;

  @Override
  public void onCreate() {
    super.onCreate();

    // Register lifecycle observer
    ProcessLifecycleOwner.get().getLifecycle().addObserver(new DefaultLifecycleObserver() {

      @Override
      public void onStart(LifecycleOwner owner) {
        Log.d("FirebaseMessaging", "onStart fu");
        isAppInForeground = true;  // App is in the foreground
      }

      @Override
      public void onStop(LifecycleOwner owner) {
        Log.d("FirebaseMessaging", "onStop");
        isAppInForeground = false;  // App is in the background
      }
    });
  }

  // Method to check if app is in the foreground
  public static boolean isAppInForeground() {
    return isAppInForeground;
  }
}

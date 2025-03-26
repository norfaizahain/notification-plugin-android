import { PluginListenerHandle, WebPlugin } from '@capacitor/core';

import type { NotificationPluginPlugin, notificationEvents } from './definitions';

export class NotificationPluginWeb
  extends WebPlugin
  implements NotificationPluginPlugin
{
  private eventListeners: { [event: string]: ((event: any) => void)[] } = {};
  // async echo(options: { value: string }): Promise<{ value: string }> {
  //   console.log('ECHO', options);
  //   return options;
  // }
  async checkNotificationPermission(): Promise<{ status: "granted" | "denied" | "not_applicable" }> {
    console.warn("checkNotificationPermission() is not available on the web.");
    return { status: "not_applicable" };
  }
  
  async addListener(eventName: notificationEvents, listener: (data: any) => void): Promise<PluginListenerHandle> {
    if (!this.eventListeners[eventName]) {
      this.eventListeners[eventName] = [];
    }
    this.eventListeners[eventName].push(listener);
    // Return an object with a `remove` method to unregister the listener
    return {
      remove: () => {
        this.eventListeners[eventName] = this.eventListeners[eventName].filter(l => l !== listener);
        return Promise.resolve();
      }
    };
  }
  // async sendCustomEvent(options: { message: string }): Promise<{ message: string }> {
  //   console.warn("sendCustomEvent() is not available on the web.");
  //   return options;
  // }
}

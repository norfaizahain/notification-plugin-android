import { PluginListenerHandle } from "@capacitor/core";
export declare type notificationEvents = 'inAppNotificationTriggered';
export declare type notificationMessage = {
  message: string;
};
export interface NotificationPluginPlugin {
  // echo(options: { value: string }): Promise<{ value: string }>;
  checkNotificationPermission(): Promise<{ status: "granted" | "denied" | "not_applicable" }>;
  addListener(eventName: notificationEvents, listener: (event: notificationMessage) => void): Promise<PluginListenerHandle>;
  // sendCustomEvent(options: { message: string }): Promise<{ message: string }>;
}

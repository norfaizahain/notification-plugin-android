export interface NotificationPluginPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  checkNotificationPermission(): Promise<{ status: "granted" | "denied" | "not_applicable" }>;

  sendCustomEvent(options: { message: string }): Promise<{ message: string }>;
}

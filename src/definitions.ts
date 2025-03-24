export interface NotificationPluginPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}

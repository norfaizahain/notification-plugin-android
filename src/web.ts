import { WebPlugin } from '@capacitor/core';

import type { NotificationPluginPlugin } from './definitions';

export class NotificationPluginWeb
  extends WebPlugin
  implements NotificationPluginPlugin
{
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
  async checkNotificationPermission(): Promise<{ status: "granted" | "denied" | "not_applicable" }> {
    console.warn("checkNotificationPermission() is not available on the web.");
    return { status: "not_applicable" };
  }

  async sendCustomEvent(options: { message: string }): Promise<{ message: string }> {
    console.warn("sendCustomEvent() is not available on the web.");
    return options;
  }
}

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
}

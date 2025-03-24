import { registerPlugin } from '@capacitor/core';

import type { NotificationPluginPlugin } from './definitions';

const NotificationPlugin = registerPlugin<NotificationPluginPlugin>(
  'NotificationPlugin',
  {
    web: () => import('./web').then(m => new m.NotificationPluginWeb()),
  },
);

export * from './definitions';
export { NotificationPlugin };

# notification-plugin-android

in app notification popup message

## Install

```bash
npm install notification-plugin-android
npx cap sync
```

## API

<docgen-index>

* [`checkNotificationPermission()`](#checknotificationpermission)
* [`addListener('inAppNotificationTriggered', ...)`](#addlistenerinappnotificationtriggered-)
* [Interfaces](#interfaces)
* [Type Aliases](#type-aliases)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### checkNotificationPermission()

```typescript
checkNotificationPermission() => Promise<{ status: "granted" | "denied" | "not_applicable"; }>
```

**Returns:** <code>Promise&lt;{ status: 'granted' | 'denied' | 'not_applicable'; }&gt;</code>

--------------------


### addListener('inAppNotificationTriggered', ...)

```typescript
addListener(eventName: notificationEvents, listener: (event: notificationMessage) => void) => Promise<PluginListenerHandle>
```

| Param           | Type                                                                                    |
| --------------- | --------------------------------------------------------------------------------------- |
| **`eventName`** | <code>'inAppNotificationTriggered'</code>                                               |
| **`listener`**  | <code>(event: <a href="#notificationmessage">notificationMessage</a>) =&gt; void</code> |

**Returns:** <code>Promise&lt;<a href="#pluginlistenerhandle">PluginListenerHandle</a>&gt;</code>

--------------------


### Interfaces


#### PluginListenerHandle

| Prop         | Type                                      |
| ------------ | ----------------------------------------- |
| **`remove`** | <code>() =&gt; Promise&lt;void&gt;</code> |


### Type Aliases


#### notificationEvents

<code>'inAppNotificationTriggered'</code>


#### notificationMessage

<code>{ message: string; }</code>

</docgen-api>

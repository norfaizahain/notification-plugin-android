# notification-plugin-android

in app notification popup message

## Install

```bash
npm install notification-plugin-android
npx cap sync
```

## API

<docgen-index>

* [`echo(...)`](#echo)
* [`checkNotificationPermission()`](#checknotificationpermission)
* [`sendCustomEvent(...)`](#sendcustomevent)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### echo(...)

```typescript
echo(options: { value: string; }) => Promise<{ value: string; }>
```

| Param         | Type                            |
| ------------- | ------------------------------- |
| **`options`** | <code>{ value: string; }</code> |

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### checkNotificationPermission()

```typescript
checkNotificationPermission() => Promise<{ status: "granted" | "denied" | "not_applicable"; }>
```

**Returns:** <code>Promise&lt;{ status: 'granted' | 'denied' | 'not_applicable'; }&gt;</code>

--------------------


### sendCustomEvent(...)

```typescript
sendCustomEvent(options: { message: string; }) => Promise<{ message: string; }>
```

| Param         | Type                              |
| ------------- | --------------------------------- |
| **`options`** | <code>{ message: string; }</code> |

**Returns:** <code>Promise&lt;{ message: string; }&gt;</code>

--------------------

</docgen-api>

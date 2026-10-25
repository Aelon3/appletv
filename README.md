# Apple TV launcher (Android Studio project)

Package: com.aelon.appletv  |  Display name: Apple TV  |  Unofficial, not affiliated with Apple Inc.

## Build the APK
1. Install Android Studio (Koala or newer). Open this folder (File > Open > AppleTV).
2. Let Gradle sync (needs internet the first time). If asked, use JDK 17 (bundled).
3. Build > Build Bundle(s) / APK(s) > Build APK(s).
4. APK: app/build/outputs/apk/debug/app-debug.apk

## Install on the TV
1. TV: Settings > Device Preferences > About > press Build 7 times. Then Developer options > USB/ADB debugging ON (and "Network debugging" if present).
2. Computer, same Wi-Fi: adb connect TV_IP:5555   then   adb install -r app-debug.apk
   (or copy the APK with a USB stick / "Send files to TV" app and open it)
3. Press Home on the remote > choose "Apple TV" > Always.
4. In the launcher: Settings > Notification Access > enable "Apple TV" to see real notifications.

## What is real on Android
- Tiles open the real installed apps (YouTube, Chrome, Google, Netflix, Play Store via the "App Store" tile, Photos, Maps, Files, ...). Not installed = a clear message.
- Safari tile = built-in WebView browser (opens google.com; typing on a TV remote is slow).
- Wi-Fi / Bluetooth tiles open the system Wi-Fi / Bluetooth screens (apps cannot toggle them silently).
- Screen Mirroring opens the system Cast settings.
- Volume changes the real media volume. Brightness dims the launcher screen (TV panels do not allow apps to change real backlight).
- RAM and storage are the real device values. Weather is live from open-meteo.com (location set in Bridge.kt: lat/lon).
- Notifications are real, shown in the Dynamic Island for 2 seconds.

## Still demo / limits
- Music tile plays a built-in demo tune, not your music library.
- Tiles without a mapped package (NBC, PBS, TBS, The CW, Media) show "not installed".
- Not compiled or tested by the author on a device: first build may need small fixes. Send the error text.
- Some TVs (Google TV) resist changing the default launcher; if "Always" is not offered, use Settings > Apps > Default apps > Home app.

# RELIC

RELIC is a native Android experiment: a retro-futuristic interface that will eventually awaken when a Raspberry Pi Pico is attached to a Galaxy S24 FE over USB-C.

**Current stage: 01 — Android foundation (source committed; build verification tracked by GitHub Actions).**

## What works in this slice
- A native Kotlin / Jetpack Compose activity, not a WebView.
- Offline-first, dark retro interface with an original geometric identity.
- Manual **Activate core / Enter standby** interaction with state that survives activity recreation.
- Honest hardware status: no Pico or USB connection is claimed.
- A small unit-tested session model.

**Not yet implemented:** USB detection, USB permissions, Pico firmware, hardware handshake, automatic launch, the cinematic startup sequence, games, companion, or authenticator. These are planned slices, not working features.

## Technology
- Android Gradle Plugin 9.3.1, Gradle 9.5.0
- Kotlin Compose compiler plugin 2.4.21; AGP 9 built-in Kotlin
- Compose BOM 2026.09.00; Activity Compose 1.13.0
- compileSdk 37, targetSdk 36, minSdk 28; Java 17

These versions were selected from current official Android/Kotlin documentation. See [docs/DECISIONS.md](docs/DECISIONS.md).

## Local setup (Windows)
1. Clone this repository into `D:\Programming\03_Projects\personal-projects\04_Miscellaneous\relic` or pull updates in that existing clone.
2. Open the folder in a recent Android Studio with Android SDK Platform 37 and JDK 17.
3. Let Gradle sync. This repository's CI generates the **official Gradle wrapper** via `gradle wrapper`; on successful bootstrap it commits `gradlew`, `gradlew.bat`, and the wrapper files back to `main`. Pull again if they have just appeared.
4. Run `./gradlew.bat :app:assembleDebug :app:testDebugUnitTest` in PowerShell once the wrapper is available. Otherwise use a system installation of Gradle 9.5.0.
5. Install `app\build\outputs\apk\debug\app-debug.apk` through Android Studio or `adb install -r app\build\outputs\apk\debug\app-debug.apk`.

The GitHub Actions [Android build](.github/workflows/android.yml) workflow also builds and uploads an APK artifact. The APK is **not** asserted to have been built until the workflow succeeds.

## Next slices
- **02 — USB link:** Android USB Host discovery, permissions, state machine, Pico CDC communication and minimal firmware echo/handshake.
- **03 — Activation:** custom scanline/crescent/emblem animation, haptics, sound, and supported Android USB attach launch.
- **04 — Interface:** navigation, actual device diagnostics, preferences.
- **05 — Play:** artifact puzzle, arcade, companion; each individually functional.
- **06 — Authenticator:** independently audited TOTP/backup architecture first, then experimental FIDO2 hardware with clear security limits.

One slice = implement, validate, commit. Development stays on `main`. No secrets, external backends, or speculative hardware claims.

## Security and licensing
No authentication credentials are stored in this release. No network or device permissions are requested. This repository currently has no explicit open-source license; public visibility alone does not grant redistribution rights.

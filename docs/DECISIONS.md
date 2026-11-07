# Technical decisions — 2026-11-07

RELIC starts with the smallest usable native Android app. No simulated hardware, account system, permissions, backend, or early speculative abstractions.

## Versions and rationale
- **AGP 9.3.1 + Gradle 9.5.0:** AGP 9.3 supports API 37, with minimum Gradle 9.5. Kotlin 2.4.21's published compatibility range includes AGP up to 9.3.1 and Gradle up to 9.7. Later AGP 9.5 exists, but matching the Kotlin toolchain's documented range takes priority.
- **Compose compiler plugin 2.4.21:** published by JetBrains on 2026-10-08; Kotlin/Compose compiler version matched.
- **Compose BOM 2026.09.00:** current stable BOM from Android documentation. Foundation and Material3 use aligned versions.
- **Activity Compose 1.13.0:** current stable AndroidX activity integration.
- **compileSdk 37 / targetSdk 36 / minSdk 28:** compile against current SDK needed for newer Compose, target the stable Android 16 behavior set and support more than the S24 FE.
- **Java 17:** supported minimum JDK for the selected AGP.
- **Built-in Kotlin:** AGP 9 includes Kotlin support; no separate kotlin-android plugin.
- **No wrapper jar committed yet:** GitHub CI generates the official Gradle Wrapper, runs a build and unit tests, then publishes the wrapper on successful verification.

## Official sources
- https://developer.android.com/build/releases/agp-9-3-0-release-notes
- https://developer.android.com/build/releases/about-agp
- https://developer.android.com/build/migrate-to-built-in-kotlin
- https://kotlinlang.org/docs/gradle-configure-project.html
- https://plugins.gradle.org/plugin/org.jetbrains.kotlin.plugin.compose/2.4.21
- https://developer.android.com/develop/ui/compose/setup-compose-dependencies-and-compiler
- https://developer.android.com/develop/ui/compose/bom
- https://developer.android.com/jetpack/androidx/releases/activity

## Status
Source created through the GitHub connector. No physical-device test or Android SDK build was possible in the ChatGPT execution environment. GitHub Actions results are the next validation gate. The GUI activation in this slice is manual and local, not a USB handshake.

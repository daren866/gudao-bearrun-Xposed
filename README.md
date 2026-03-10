# xposed-module-template

A modern Xposed/LSPosed module template with Kotlin support, featuring best practices for hook development.

## Features

- Ready-to-use LSPosed module structure
- Kotlin-first approach
- Scope configuration examples
- Hook examples for common Android APIs
- ProGuard rules included

## Requirements

- Android 8.0+
- LSPosed framework

## Getting Started

1. Clone this repository
2. Modify `app/src/main/assets/xposed_init` with your hook entry class
3. Update `AndroidManifest.xml` with your module metadata
4. Implement your hooks in `MainHook.kt`

## Module Structure

```
app/
├── src/main/
│   ├── assets/
│   │   └── xposed_init          # Entry point declaration
│   ├── java/
│   │   └── com/example/module/
│   │       ├── MainHook.kt      # Main hook implementation
│   │       └── HookUtils.kt     # Utility functions
│   └── AndroidManifest.xml
└── build.gradle
```

## License

Apache 2.0


# MediaFile Library

**MediaFile** is a powerful and lightweight library designed to help you handle media files (such as images, videos, and audio) in your Android applications. This library provides utilities for media file caching, manipulation, and management.

## Installation

You can include **MediaFile** in your project by adding the following dependency to your `build.gradle` file:

### Gradle
Add this to your **`build.gradle`** file:
```gradle
dependencies {
    implementation "com.github.arunsaini065:MediaFile:1.3.0"
}
```

### Maven
Alternatively, if you prefer to use **Maven**, you can add the following dependency:
```xml
<dependency>
    <groupId>com.github.arunsaini065</groupId>
    <artifactId>MediaFile</artifactId>
    <version>1.3.0</version>
</dependency>
```

Make sure to include the **JitPack repository** in your `build.gradle` file:
```gradle
repositories {
    mavenCentral()
    maven { url 'https://jitpack.io' }
}
```

## Features
- **File Caching**: Automatically cache media files to avoid repeated downloads.
- **Media Handling**: Simplifies the process of dealing with images, videos, and audio files.
- **Efficient Management**: Helps with organizing and retrieving files based on their type and size.
- **Offline Support**: Cache files for offline use in your application.

## Usage

### Caching Media Files



## Versioning

Current Version: **1.3.0**

## Contributing

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Commit your changes (`git commit -am 'Add feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Open a Pull Request.

## License

Distributed under the MIT License. See `LICENSE` for more information.

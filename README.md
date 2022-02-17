# Android Code Standard Core Library

## Core Features

* Analytics Repository interface to implemented in app project
* Commons utils and extention
* Support MVVM pattern with BaseViewModel
* Network Configuration
* WebViewActivity used/ call this directly to show webview 
* BaseActivity and BaseFragment

## How to use
Gradle
```
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}

dependencies {
  implementation 'com.github.mncinnovation:mnc-android-code-standard-core:0.1.3'
}
```
or Maven
```
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>

<dependency>
  <groupId>com.github.mncinnovation</groupId>
  <artifactId>mnc-android-code-standard-core</artifactId>
  <version>0.1.3</version>
</dependency>
```

## License

```
Copyright 2021 MNC Innovation Center

Proprietary license
```

# CHNEWS APP

## Overview

This is an android app built to used in Android TVs. This app displays the headlines
that is happening across all over the world. It uses an remote API to fetch all the news.
Headlines will be categorized based on Country and Category

## Architecture

The app is entirely built using Compose along with tv-material library for design. It uses an MVVM pattern
with Unidirectional data flow method for UI. (i.e UI emit events and viewModel updates the business logic and UI reacts to the state change)

## How to build & run the project?

### Prerequsites

1. Install & Setup Java
2. Android Studio
3. Download and Android TV emulator(Eg Android TV_1080P)

Once you have all the prerequisites then build the app using the following command

```gradle
./gradlew :app:build
```

Once the build is successful, you can run the application from the android studio and launch the app in the TV emulator

## Screenshots

### Home Page
<img src="src/screenshots/home_page.png">

### Fetching News from Network
<img src="src/screenshots/fetching_news.png">

### Refresh News while holding D-Pad DOWN
<img src="src/screenshots/news_refresh.png">


https://github.com/user-attachments/assets/9565e1f8-8fcd-48e5-b96a-fcfa94272adf

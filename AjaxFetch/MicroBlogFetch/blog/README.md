Instructions to run the Application through Android Studio: 
Pre Requisites: Android Studio, Android SDK and Internet connection.

Instructions to install the APP
1. Using Android Studio
    - Import the project to Android studio by using the option Open an existing Android Studio project.
    - After the project is imported, *Build* the project.
    - The application can be installed on emulator or any device (connected via USB) by selecting *Run* and then *Run ‘app’* option and by selecting the device on which it need to be installed; Or
    - An APK can be created to be directly used on any android mobile device, by clicking the *Build* option, then *Build Bundle(s)/ APK(s)* and then *Build APK(s)*. The generated APK can be located using the clickable link (*locate*) provided at the bottom right of the android studio.

2. Using command line
    Pre Requisites : Java 1.8+, Gradle : 4.4.1.
    You can install Gradle from [here](https://gradle.org/install/).
    - Go to your current project directory workspace
        Suppose you have your COMPASS folder on your Desktop.
        execute : $$ cd /Users/*yourUserName*/Desktop/COMPASS
    - Generate the necessary Wrapper files in the project directory by executing: $ gradle wrapper
    - Initiate a debug build, execute:  $ ./gradlew assembleDebug
    - Install the Debug variant of the application on the connected device, execute: $ ./gradlew installDebug
    - Run the COMPASS application on your device.


REFERENCES:

1. Eye tracking library used: Google vision
https://developers.google.com/vision/
Eye tracking code samples and examples referred:
https://github.com/googlesamples/android-vision
2. https://developer.android.com/studio/build/building-cmdline.html
3. https://docs.gradle.org/current/userguide/gradle_wrapper.html

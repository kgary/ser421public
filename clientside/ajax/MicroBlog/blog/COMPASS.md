Instructions to run the Application through Android Studio: 
Pre Requisites: Android Studio, Android SDK and Internet connection.

1. Import the project to Android studio by using the option Open an existing Android Studio project.
2. After the project is imported, build the project.
3. You can either run the project using the android emulator, connected Android device or by installing APK in an Android device. 
To run the project using the android emulator or connected mobile device:
1. Click on Run to run the Application
2. Click on either emulator or the mobile device.

The APK can be obtained by clicking the Build option and then Build APK. The APK will be downloaded and this should be installed in the Android mobile device and then it can be run in the Android mobile device.


Instructions to run the Application on your device through command line :   
Pre Requisites : Java 1.8+, Gradle : 4.4.1.   
You can install Gradle from [here](https://gradle.org/install/).

1. cd into your project directory workspace
    - Suppose you have your COMPASS folder on your Desktop.  
    - execute : $ cd /Users/*yourUserName*/Desktop/COMPASS

3. Generate the necessary Wrapper files in the project directory. 
    - execute: $ gradle wrapper

4. Initiate a debug build.
    - execute $ ./gradlew assembleDebug

5. Connect your external device to your laptop.

6. Install the Debug variant of the application. 
    - execute $ ./gradlew installDebug

7. Run the COMPASS application on your device.


REFERENCES:

1. Eye tracking library used: Google vision
https://developers.google.com/vision/
Eye tracking code samples and examples referred:
https://github.com/googlesamples/android-vision

2. https://developer.android.com/studio/build/building-cmdline.html

3. https://docs.gradle.org/current/userguide/gradle_wrapper.html

factual-android-sdk-demo
========================
A simple Android project demonstrating usage of the Factual Java driver.  The application prints the response of a Factual read request against the "places" table onto the main screen.

# Setup

1. Install the Android SDK.  
2. Modify com.factual.android.demo.MainActivity.java to replace "your_key" and "your_secret" with your Factual key and secret respectively.

## Maven users

Compile:

    mvn clean install
    
Deploy to a test device:

	mvn android:deploy
	    
## Non Maven users

You can download the individual driver jar, and view the pom.xml file, here:
[Android driver download folder](http://repo1.maven.org/maven2/com/factual/factual-java-driver/1.2.1-android/)

The pom.xml tells you what dependencies you'll need to plug into your project to get the driver to work.

Compile and deploy using your preferred environment.
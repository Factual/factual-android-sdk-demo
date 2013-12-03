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
[Android driver download folder](http://repo1.maven.org/maven2/com/factual/factual-java-driver/1.8.2-android/factual-java-driver-1.8.2-android.pom)

The pom.xml tells you what dependencies you'll need to plug into your project to get the driver to work.  You must use the versions specified for dependent libraries.

Compile and deploy using your preferred environment.

## FAQ

### How do I get all the dependencies required to run this project?

We strongly recommend using maven to get dependencies as described above. However, if you want to grab the dependencies manually, you can do this as described in "Non Maven users" above.  Note that you need to be sure to grab all dependencies and make sure they are the exact same version.  There is no guarantee the project will work with mismatched versions of dependency jars.

### I think I got the dependencies, but I get a java.lang.NoClassDefFoundError when running the application.  Why?

It's likely that the required jars are not being deployed on your test device or emulator.  This is a common issue for Android development, and is described here:

[http://stackoverflow.com/questions/9833607/android-eclipse-noclassdeffounderror-for-external-jar-files](http://stackoverflow.com/questions/9833607/android-eclipse-noclassdeffounderror-for-external-jar-files)

There are many other resources for resolving this, and these should be available online.

Note that if you use the instructions in "Maven users" above, you should not run into this issue.

### When using mvn install, I get an error…


	[ERROR] Failed to execute goal com.jayway.maven.plugins.android.generation2:android-maven-plugin:3.1.1:generate-sources (default-generate-sources) on project factual-android-demo: Execution default-generate-sources of goal com.jayway.maven.plugins.android.generation2:android-maven-plugin:3.1.1:generate-sources failed: Could not find tool 'aapt'. Please provide a proper Android SDK directory path as configuration parameter <sdk><path>...</path></sdk> in the plugin <configuration/>. As an alternative, you may add the parameter to commandline: -Dandroid.sdk.path=... or set environment variable ANDROID_HOME. -> [Help 1]
### …why?

When using android sdk version above 21, use a workaround described [here](http://stackoverflow.com/questions/16927306/could-not-find-tool-aapt-please-provide-proper-android-sdk-directory-path-as-co):


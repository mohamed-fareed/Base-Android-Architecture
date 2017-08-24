# Base-Android-Architecture
It's an Android module which contains base classes and some utils which are commonly used in all our apps.

To use it:
add this is project gradle:
    
    allprojects {
      repositories {
          ...
          maven { url 'https://jitpack.io' }
      }
    }
    
then add this line in app gradle file:
  
  compile 'com.github.mohamed-fareed:Base-Android-Architecture:1.0.0'

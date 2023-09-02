# A game written in java using maven

I use the lwjgl3-awt lib to render canvas in a Swing Frame. The canvas renders using OpenGl 1.x.

I started from ```https://github.com/LWJGLX/lwjgl3-awt/blob/main/test/org/lwjgl/opengl/awt/AWTTest.java``` example code.

The canvas shows a diamond that the user can move by pressing  W/A/S/D keys;

## problems

When i first tried to run the project lwjglx library loader threw:

```java.lang.UnsatisfiedLinkError: Failed to locate library: liblwjgl.so```

I circumvented this problem the following way:

I created the lwjgl-3.2.3  that contains the linux x64 shared libraries for lwjgl version 3.2.3(downloaded from "https://www.lwjgl.org/browse/release/3.2.3/linux/x64"). Now to run the project we have to tell java to use these libraries. This also means that the project only runs on linux x64 now.

## To build:

```mvn clean compile```

## To run the project:

```mvn exec:java -Dorg.lwjgl.librarypath=lwjgl-3.2.3 -Dexec.mainClass=group1.view.View```

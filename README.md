# NgdroidApp
I teach game programming here in Gamelab Istanbul (http://gamelab.istanbul) and in 2 universities. I created this simple Java framework to use in my classes. Thus, instead of spending time with configuring Android, the students can start to write their game codes asap.

The framework is an Android Studio project. You can fork it, and then, you can clone your fork into your computer via the Android Studio.

The framework has got;

- GameActivity (the entry point),
- AppManager (SurfaceView),
- Main loop (main thread),
- NgApp (the root class),
- CanvasManager and
- GameCanvas.

All you have to do is opening GameCanvas and start coding your game.

The framework is designed in the MVC pattern. If you need to create new classes for your menus and other views, you can simply extend BaseCanvas and set this new child canvas as the current canvas.

When you want to generate your own APK;

- Change the app name in the res/values/strings.xml,
- Change package com.mycompany.myngdroidapp to your own package name,
- Put your game icon into res/mipmap folders,
- Modify the app/build.graddle and
- Modify AndroidManifest.xml.

There are still a lot of rooms to develop. Contributions are really appreciated.

This framework is licenced with MIT licence.

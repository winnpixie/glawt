# glAWT

A *loose* implementation of OpenGL 1.x using Java AWT.

## How to use

Take a look at [ImageOutputExample.java](src/main/java/example/ImageOutputExample.java) for basic usage.

In reality, don't try this at home. Use a project with native bindings, such as LWJGL, or JOGL.

## // TODO:

- Proper implementation for matrices (**MODELVIEW**, **PROJECTION**, **TEXTURE**(?), **COLOR**(?))
- 3D to 2D projection (for vertices whose Z is not `0.0`)
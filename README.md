# glAWT

OpenGL (primarily 1.x / 2.x) loosely emulated using Java AWT.

## How to use

Take a look at [ImageOutputExample.java](src/main/java/example/ImageOutputExample.java) for basic implementation.

In reality, please don't use this. You should really be using a project with proper native bindings, like LWJGL or JOGL.

## // TODO:

- "Proper" matrices (MODELVIEW, PROJECTION, TEXTURE(?), COLOR(?))
- 3D to 2D projection (for vertices whose Z is not 0.0)
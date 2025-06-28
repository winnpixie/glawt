package io.github.winnpixie.glawt.commands;

import io.github.winnpixie.glawt.OpenGL;
import io.github.winnpixie.glawt.utilities.SynchronizedList;

import java.util.ArrayList;

public class DisplayList extends SynchronizedList<GLCommand> {
    private final DisplayListMode mode;

    public DisplayList(DisplayListMode mode) {
        super(new ArrayList<>());

        this.mode = mode;
    }

    @Override
    public boolean add(GLCommand glCommand) {
        if (mode == DisplayListMode.GL_COMPILE_AND_EXECUTE) glCommand.execute(OpenGL.getGLContext());

        return super.add(glCommand);
    }
}

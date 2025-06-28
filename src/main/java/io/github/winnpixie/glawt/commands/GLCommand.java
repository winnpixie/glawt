package io.github.winnpixie.glawt.commands;

import io.github.winnpixie.glawt.GLContext;

public interface GLCommand {
    boolean execute(GLContext context);
}

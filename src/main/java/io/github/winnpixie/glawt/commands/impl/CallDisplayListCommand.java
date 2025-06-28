package io.github.winnpixie.glawt.commands.impl;

import io.github.winnpixie.glawt.GLContext;
import io.github.winnpixie.glawt.commands.DisplayList;
import io.github.winnpixie.glawt.commands.GLCommand;

public record CallDisplayListCommand(long name) implements GLCommand {
    @Override
    public boolean execute(GLContext context) {
        DisplayList displayList = context.getDisplayListManager().getList(name);

        for (GLCommand glCommand : displayList) glCommand.execute(context);

        return true;
    }
}

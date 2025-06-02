package io.github.winnpixie.glawt.commands;

import io.github.winnpixie.glawt.*;

public class CallListCommand implements Command {
    private final int name;

    public CallListCommand(int name) {
        this.name = name;
    }

    @Override
    public boolean execute(GraphicsContext context) {
        CommandList list = GlobalContext.getList(name);
        for (Command command : list) command.execute(GL.getContext());

        return true;
    }
}

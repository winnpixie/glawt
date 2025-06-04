package io.github.winnpixie.glawt;

import java.util.ArrayList;

public class CommandList extends ArrayList<Command> {
    private final byte mode;

    public CommandList(byte mode) {
        super();

        this.mode = mode;
    }

    @Override
    public boolean add(Command command) {
        if (mode == ListMode.GL_COMPILE_AND_EXECUTE) command.execute(GLAWT.getContext());

        return super.add(command);
    }
}

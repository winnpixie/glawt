package io.github.winnpixie.glawt.commands;

import io.github.winnpixie.glawt.Command;
import io.github.winnpixie.glawt.GraphicsContext;

import java.awt.*;

public class SetColorCommand implements Command {
    private final int red;
    private final int green;
    private final int blue;
    private final int alpha;

    public SetColorCommand(int red, int green, int blue, int alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    @Override
    public boolean execute(GraphicsContext context) {
        context.getDriver().setColor(new Color(red, green, blue, alpha));

        return true;
    }
}

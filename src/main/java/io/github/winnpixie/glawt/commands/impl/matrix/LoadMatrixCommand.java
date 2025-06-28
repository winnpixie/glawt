package io.github.winnpixie.glawt.commands.impl.matrix;

import io.github.winnpixie.glawt.GLContext;
import io.github.winnpixie.glawt.commands.GLCommand;
import io.github.winnpixie.glawt.matrix.Matrix;

import java.util.Deque;

public record LoadMatrixCommand(Matrix matrix) implements GLCommand {
    @Override
    public boolean execute(GLContext context) {
        Deque<Matrix> matrixStack = context.getMatrixManager().getActiveMatrixStack();

        matrixStack.pop();
        matrixStack.push(matrix);

        return true;
    }
}

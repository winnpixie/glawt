package io.github.winnpixie.glawt;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Matrices {
    public static final Matrix IDENTITY = new Matrix(4, 4, new double[]{
            1, 0, 0, 0,
            0, 1, 0, 0,
            0, 0, 1, 0,
            0, 0, 0, 1
    });

    private static final Map<Byte, Stack<Matrix>> MATRIX_STACKS = new HashMap<>();

    private static byte activeMatrix = MatrixMode.GL_MODELVIEW;

    static {
        Stack<Matrix> modelViewStack = new Stack<>();
        modelViewStack.push(Matrix.copy(IDENTITY));
        MATRIX_STACKS.put(MatrixMode.GL_MODELVIEW, modelViewStack);

        Stack<Matrix> projectionStack = new Stack<>();
        projectionStack.push(Matrix.copy(IDENTITY));
        MATRIX_STACKS.put(MatrixMode.GL_MODELVIEW, projectionStack);

        Stack<Matrix> textureStack = new Stack<>();
        textureStack.push(Matrix.copy(IDENTITY));
        MATRIX_STACKS.put(MatrixMode.GL_MODELVIEW, textureStack);

        Stack<Matrix> colorStack = new Stack<>();
        colorStack.push(Matrix.copy(IDENTITY));
        MATRIX_STACKS.put(MatrixMode.GL_MODELVIEW, colorStack);
    }

    public static Stack<Matrix> getActiveMatrixStack() {
        return MATRIX_STACKS.get(activeMatrix);
    }

    public static Matrix getActiveMatrix() {
        return getActiveMatrixStack().peek();
    }

    public static void setActiveMatrix(byte mode) {
        activeMatrix = mode;
    }
}

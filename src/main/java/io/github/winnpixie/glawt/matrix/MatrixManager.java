package io.github.winnpixie.glawt.matrix;

import java.util.Deque;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class MatrixManager {
    private final Map<MatrixMode, Deque<Matrix>> matrices = new ConcurrentHashMap<>();

    private MatrixMode activeMode = MatrixMode.GL_MODELVIEW;

    public MatrixManager() {
        Deque<Matrix> modelViewStack = new ConcurrentLinkedDeque<>();
        modelViewStack.push(Matrix.IDENTITY.copy());
        matrices.put(MatrixMode.GL_MODELVIEW, modelViewStack);

        Deque<Matrix> projectionStack = new ConcurrentLinkedDeque<>();
        projectionStack.push(Matrix.IDENTITY.copy());
        matrices.put(MatrixMode.GL_MODELVIEW, projectionStack);

        Deque<Matrix> textureStack = new ConcurrentLinkedDeque<>();
        textureStack.push(Matrix.IDENTITY.copy());
        matrices.put(MatrixMode.GL_MODELVIEW, textureStack);

        Deque<Matrix> colorStack = new ConcurrentLinkedDeque<>();
        colorStack.push(Matrix.IDENTITY.copy());
        matrices.put(MatrixMode.GL_MODELVIEW, colorStack);
    }

    public Map<MatrixMode, Deque<Matrix>> getMatrices() {
        return matrices;
    }

    public MatrixMode getActiveMode() {
        return activeMode;
    }

    public void setActiveMode(MatrixMode activeMode) {
        this.activeMode = activeMode;
    }

    public Deque<Matrix> getActiveMatrixStack() {
        return matrices.get(activeMode);
    }

    public Matrix getActiveMatrix() {
        return getActiveMatrixStack().peek();
    }
}

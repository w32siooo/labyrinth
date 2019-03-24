package SnakeLogic;

import javafx.scene.paint.Color;

/**
 * Items in the "maze"
 */
public class Item {
    protected int x;
    protected int y;

    public Item(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Item(){};

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

package SnakeLogic;

import javafx.scene.paint.Color;

public class Wall extends Item {
    public Wall(int x, int y) {
        // set color and position
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}



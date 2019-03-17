package SnakeLogic;

import javafx.scene.Scene;

import java.util.Random;

public class RandomRambler implements GameObject {
    private Random random = new Random();
    boolean alreadyExecuted =false;
    private int X;
    private int Y;
    public int speedX = 1;
    public int speedY = 1;
    int delay;

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public void collision() {
        if(!alreadyExecuted) {
            speedY = speedY*-1;
            alreadyExecuted = true;
            delay=2000;
        }
    }

    public void findTheWay(){
    }

    public void update() {


        this.setX(X = X + random.nextInt(2) * speedX);
        this.setY(Y = Y + random.nextInt(2) * speedY);

        //left wall
        if (this.getX() < 0) {

            //System.out.println("out of X");
            speedX = speedX * -1;
            System.out.print("x is out " + X);
            this.setY(X = X + 1);

        }
        //right wall
        if (this.getX() > 19) {

            //System.out.println("out of X");
            speedX = speedX * -1;
            System.out.print("x is out " + X);
            this.setY(X = X - 1);
        }


        //upper wall
        if (this.getY() < 0) {

            //System.out.println("out of y");
            System.out.print(Y);
            this.setY(Y = Y + 1);
            speedY = speedY * -1;
            System.out.print("y is out " + Y);

        }
        //lower wall
        if (this.getY() > 19) {

            this.setY(Y = Y - 1);
            speedY = speedY * -1;
            System.out.print("y is out " + Y);

        }

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        if (delay==2000) {
                            alreadyExecuted = false;
                            delay=0;
                            System.out.println("delay");
                        }
                     }
                },
                delay
        );


    }

}
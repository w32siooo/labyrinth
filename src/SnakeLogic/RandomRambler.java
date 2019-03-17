package SnakeLogic;

import javafx.scene.Scene;

import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

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

    public void depthFirst(){


        int[][] wallsArray = new int[][]{
/*start*/       {       0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {
                        2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1},
                {
                        1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1},
                {
                        1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1},
                {
                        1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1},
                {
                        1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 1},
                {
                        1, 0, 0, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1},
                {
                        1, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1},
                {
                        1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1},
                {
                        1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1},
                {
                        1, 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1},
                {
                        1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1},
                {
                        1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 1, 1},
                {
                        1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1},
                {
                        1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1},
                {
                        1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 1, 1},
                {
                        1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1},
                {
                        1, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1},
                {
                        1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
                {
                        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}/*finish*/
        };

        //j = x
        //i = y

        Stack stack = new Stack<>();

        //move right
        if(wallsArray[this.getY()][this.getX()+1]==0){

            int[] tempArray = new int[]{this.getX(),this.getY()};

            stack.add(tempArray);



            this.setX(this.getX()+1);

        }
        else{
            //move down
            if(wallsArray[this.getY()+1][this.getX()]==0){
                int[] tempArray = new int[]{this.getX(),this.getY()};
                stack.add(tempArray);

                this.setY(this.getY()+1);
                //System.out.println("moving down");
            }
            else{
                //move up

                if(wallsArray[this.getY()-1][this.getX()]==0    ){
                    int[] tempArray = new int[]{this.getX(),this.getY()};
                    stack.add(tempArray);

                    this.setY(this.getY()-1);
                    //System.out.println("moving up");



                    int[] test2Array =(int[]) stack.peek();
                    int[] testArray = new int[]{this.getX(),this.getY()};

                    if (testArray[0]==test2Array[0]){
                        System.out.println("THIS IS A GREAT SUCESS");


                    }






                }


                }
            System.out.println(Arrays.toString((int[]) stack.peek()));

            int[] testArray = new int[]{0,1};
            int[] test2Array =(int[]) stack.peek();

            if (testArray[0]==test2Array[0]){
                System.out.println("THIS IS A GREAT SUCESS");

            }

        /*
         //if we cant move down we move to the left
                if(wallsArray[this.getY()+1][this.getX()-1]==0){

                    stack.add(1);
                    // this.setY(this.getY()+1);
                    this.setX(this.getX()-1);

                    System.out.println("moving left");
                }
         */






        }

        /*
        for (int i = 0; i < 20 ; i++) {

            for (int j = 0; j < 30; j++) {




                if(wallsArray[i][j]!=0){



                }

            }

        }
        */



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
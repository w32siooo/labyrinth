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
    boolean hasmoved=true;
    boolean hasmoved2=true;
    int[] test2Array = {5,5};
    int instance = 0;
    int[][] wallsArray = new int[][]{
/*start*/   {       0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1},
            {
                    1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1},
            {
                    1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1},
            {
                    1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1},
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
                    1, 0, 0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1},
            {
                    1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1},
            {
                    1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 1, 1},
            {
                    1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1},
            {
                    1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1},
            {
                    1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 1, 1},
            {
                    1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1},
            {
                    1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1},
            {
                    1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1},
            {
                    1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}/*finish*/
    };


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

        Stack stack = new Stack<>();

        movement(0);

        test(0);

    }

    private void movement(int type) {
        if(wallsArray[this.getY()][this.getX()+1]==type){


            //mark as visited
            wallsArray[this.getY()][this.getX()]=type+2;
            //move to the right 1 tile

            this.setX(this.getX()+1);

        }

        //check if we can move down
        if(wallsArray[this.getY()+1][this.getX()]==type){
            //mark as visited
            wallsArray[this.getY()][this.getX()]=type+2;

            //move to the down 1 tile
            this.setY(this.getY()+1);

        }

        //check if we can move left

        if(wallsArray[this.getY()][this.getX()-1]==type){
            //add current position to stack
            wallsArray[this.getY()][this.getX()]=type+2;

            //move to the right 1 tile
            this.setX(this.getX()-1);

        }

        if(wallsArray[this.getY()-1][this.getX()]==type){
            //add current position to stack
            wallsArray[this.getY()][this.getX()]=type+2;

            //move to the down 1 tile
            this.setY(this.getY()-1);
        }
    }

    private void test(int type) {
        if(wallsArray[this.getY()-1][this.getX()]!=type&&wallsArray[this.getY()][this.getX()-1]!=type&&wallsArray[this.getY()+1][this.getX()]!=type&&wallsArray[this.getY()][this.getX()+1]!=type){
           // System.out.println(this.getX()+this.getY());
            System.out.println("stuck");
            movement(2);
        }


    }

    public void update() {



    }

}
package SnakeLogic;

import javafx.scene.Scene;

import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

public class RandomRambler implements GameObject {
    private boolean alreadyExecuted =false;
    private int X;
    private int Y;
    private int speedY = 1;
    private int times=0;


    public int[][] getWallsArray() {
        return wallsArray;
    }

    private int[][] wallsArray = new int[][]{
/*start*/   {       0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1},
            {
                    1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1},
            {
                    1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1},
            {
                    1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1},
            {
                    1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 1},
            {
                    1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 1},
            {
                    1, 0, 0, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1},
            {
                    1, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1},
            {
                    1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1},
            {
                    1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1},
            {
                    1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1},
            {
                    1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1},
            {
                    1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 1, 1},
            {
                    1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1},
            {
                    1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1},
            {
                    1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 1, 1},
            {
                    1, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1},
            {
                    1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1},
            {
                    1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1},
            {
                    1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}/*finish*/
    };


    public int getX() {
        return X;
    }

    private void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    private void setY(int y) {
        Y = y;
    }



    public boolean depthFirst(){

        if(this.getX()==21&&this.getY()==0){
            //do nothing
            System.out.println("It took " + times + " recursions to get through this maze.");
            return true;


        }
            else{

            movement(0);

            unstuck(0);
            return false;

        }

    }

    public void greedyAlgo(){

    }

    private void movement(int type) {
        if(wallsArray[this.getY()][this.getX()+1]==type){


            //mark as visited
            wallsArray[this.getY()][this.getX()]=type+2;
            //move to the right 1 tile

            this.setX(this.getX()+1);

        }

        //check if we can move down
        else if(wallsArray[this.getY()+1][this.getX()]==type){
            //mark as visited
            wallsArray[this.getY()][this.getX()]=type+2;

            //move to the down 1 tile
            this.setY(this.getY()+1);

        }

        //check if we can move left

        else if(wallsArray[this.getY()][this.getX()-1]==type){
            //add current position to stack
            wallsArray[this.getY()][this.getX()]=type+2;

            //move to the right 1 tile
            this.setX(this.getX()-1);

        }

        else if(wallsArray[this.getY()-1][this.getX()]==type){
            //add current position to stack
            wallsArray[this.getY()][this.getX()]=type+2;

            //move to the down 1 tile
            this.setY(this.getY()-1);
        }
    }

    private void unstuck(int type) {

        if(this.getX()>0&&this.getY()>0)
        if(wallsArray[this.getY()-1][this.getX()]!=type&&wallsArray[this.getY()][this.getX()-1]!=type&&wallsArray[this.getY()+1][this.getX()]!=type&&wallsArray[this.getY()][this.getX()+1]!=type){
            times++;

            //recursion until unstuck...
            movement(2);
        }

    }

    public void update() {



    }

}
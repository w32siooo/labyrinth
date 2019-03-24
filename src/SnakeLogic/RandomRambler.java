package SnakeLogic;

import javafx.scene.Scene;

import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

public class RandomRambler implements GameObject {
    private int X=7;
    private int Y=17;

    public RandomRambler(int x, int y){
        this.X = x;
        this.Y = y;
    }

    public int[][] getWallsArray() {
        return wallsArray;
    }

    private int[][] wallsArray = new int[][]{
/*start*/   {       1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1},
            {
                    1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1},
            {
                    1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1},
            {
                    1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1},
            {
                    1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 1},
            {
                    1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 1},
            {
                    1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1},
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

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }



    public boolean depthFirst(){

        if(this.getX()==21&&this.getY()==0){
            //do nothing
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
        //check if we can move right
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
            //mark as visited
            wallsArray[this.getY()][this.getX()]=type+2;

            //move to the right 1 tile
            this.setX(this.getX()-1);

        }
        //check if we can move up

        else if(wallsArray[this.getY()-1][this.getX()]==type){
            //mark as visited
            wallsArray[this.getY()][this.getX()]=type+2;

            //move to the down 1 tile
            this.setY(this.getY()-1);
        }
    }

    private void unstuck(int type) {

        if(this.getX()>0&&this.getY()>0)
        if(wallsArray[this.getY()-1][this.getX()]!=type&&wallsArray[this.getY()][this.getX()-1]!=type&&wallsArray[this.getY()+1][this.getX()]!=type&&wallsArray[this.getY()][this.getX()+1]!=type){

            //recursion until unstuck...
            movement(2);
        }

    }

    public void update() {



    }

}
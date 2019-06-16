package SnakeLogic;

public class RandomRambler  {

    public int getDestX() {
        return destX;
    }

    public void setDestX(int destX) {
        this.destX = destX;
    }

    private int destX=22;

    public int getDestY() {
        return destY;
    }

    public void setDestY(int destY) {
        this.destY = destY;
    }

    private int destY=2;
    boolean moveRight =false;
    boolean moveLeft =false;
    boolean moveNorth =false;
    boolean moveSouth =false;

    private int X ;
    private int Y;

    private int[][] wallsArray = new int[][]{
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1},
            {1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1},
            {1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1, 1},
            {1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1},
            {1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1},
            {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1},
            {1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1},
            {1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1},
            {1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1, 1},
            {1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1},
            {1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1},
            {1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1},
            {1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1},
            {1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1},
            {1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1},
            {1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1},
            {1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1},
            {1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1, 1},
            {1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1},
            {1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1},
            {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1},
            {1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1},
            {1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1},
            {1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1, 1},
            {1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1},
            {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    public RandomRambler(int x, int y) {
        this.X = x;
        this.Y = y;
    }

    public int[][] getWallsArray() {
        return wallsArray;
    }

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


    //greedy first, search algoritm. Will use depth first if stuck.
    public boolean greedy(){

        if (this.getY() == destY&&this.getX()==destX) {
            //do nothing
            return true;
        }
            else{
                if (this.getX() <= this.destX) moveRight = true;
                else {
                    moveRight = false;
                }
                if (this.getX() > this.destX) moveLeft = true;
                else {
                    moveLeft = false;
                }
                if (this.getY() > this.destY) moveNorth = true;
                else {
                    moveNorth = false;
                }
                if (this.getY() < this.destY) moveSouth = true;
                else {
                    moveSouth = false;
                }


                //check if we can move right

                if (wallsArray[this.getY() - 1][this.getX()] == 0 && moveNorth) {
                //mark as visited
                wallsArray[this.getY()][this.getX()] = 0 + 2;

                //move to the down 1 tile
                this.setY(this.getY() - 1);
            }
                else if (wallsArray[this.getY()][this.getX() + 1] == 0 && moveRight) {

                    //mark as visited
                    wallsArray[this.getY()][this.getX()] = 0 + 2;
                    //move to the right 1 tile

                    this.setX(this.getX() + 1);

                }


                //check if we can move down
                else if (wallsArray[this.getY() + 1][this.getX()] == 0 && moveSouth) {
                    //mark as visited
                    wallsArray[this.getY()][this.getX()] = 0 + 2;

                    //move to the down 1 tile
                    this.setY(this.getY() + 1);

                }


                //check if we can move left

                else if (wallsArray[this.getY()][this.getX() - 1] == 0 && moveLeft) {
                    //mark as visited
                    wallsArray[this.getY()][this.getX()] = 0 + 2;

                    //move to the right 1 tile
                    this.setX(this.getX() - 1);

                }
                //check if we can move up

              else {
                    movement(0);

                }
            return false;

        }

    }


    //depth first search with backtracking, 2d array based.
    public boolean depthFirst() {


        if (this.getY() == destY&&this.getX()==destX) {
            //do nothing
            return true;

        } else {

            movement(0);

            return false;

        }

    }

    //takes a type of movement to move through the array with, if 0, moves through zeros.
    private void movement(int type) {
        //check if we can move right
        if (wallsArray[this.getY()][this.getX() + 1] == type) {

            //mark as visited
            wallsArray[this.getY()][this.getX()] = type + 2;
            //move to the right 1 tile

            this.setX(this.getX() + 1);

        }

        //check if we can move down
        else if (wallsArray[this.getY() + 1][this.getX()] == type) {
            //mark as visited
            wallsArray[this.getY()][this.getX()] = type + 2;

            //move to the down 1 tile
            this.setY(this.getY() + 1);

        }

        //check if we can move left

        else if (wallsArray[this.getY()][this.getX() - 1] == type) {
            //mark as visited
            wallsArray[this.getY()][this.getX()] = type + 2;

            //move to the right 1 tile
            this.setX(this.getX() - 1);

        }
        //check if we can move up

        else if (wallsArray[this.getY() - 1][this.getX()] == type) {
            //mark as visited
            wallsArray[this.getY()][this.getX()] = type + 2;

            //move to the down 1 tile
            this.setY(this.getY() - 1);
        }
        else {
            unstuck(0);

        }
    }

    //gets run if stuck, its runs movement with parameter 2, allowing it to move back through visited positions.
    private void unstuck(int type) {

        //this is to avoid array index out of bounds errors when we are 1 tile from the edge
        if (this.getX() > 0 && this.getY() > 0) {
            //this will only run if we have nowhere else to go. That is, if every position around us is either visited or a wall.
            if (wallsArray[this.getY() - 1][this.getX()] != type && wallsArray[this.getY()][this.getX() - 1] != type && wallsArray[this.getY() + 1][this.getX()] != type && wallsArray[this.getY()][this.getX() + 1] != type) {

                //so if we are stuck, we will keep moving back where we were before until an unvisited position is avaiable.
                movement(2);
            }
        }

    }
}
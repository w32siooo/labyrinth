package SnakeGUI;


import SnakeLogic.Item;
import SnakeLogic.RandomRambler;
import SnakeLogic.Wall;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.util.*;

public class Controller {

    @FXML
    Label labelStatus;
    @FXML
    Canvas canvas;

    private double fieldHeight;
    private double fieldWidth;
    private int width = 60;
    private int height = 40;
    private float refreshRate =10;
    private boolean actiones = false;
    int clicks = 0;


    private RandomRambler randomRambler = new RandomRambler(1, 1);

    ArrayList<Item> items = new ArrayList<>();
    private ArrayList<Item> walls = new ArrayList<>();
    private ArrayList<Item> beens = new ArrayList<>();

    @SuppressWarnings("StatementWithEmptyBody")
    public void btnStartAction()
    {
        //labelStatus.setText("test");
        while(!randomRambler.depthFirst());
        {
            System.out.println("cleared");
        }

    }

    public void actiones()
    {
        //labelStatus.setText("test");

        actiones = true;
    }

    public void switchPos()
    {
        switch (clicks)
        {
            case 0 :
                randomRambler.setX(16);
                randomRambler.setY(17);
                break;

            case 1:
                randomRambler.setX(3);
                randomRambler.setY(17);
                break;

            case 2:
                randomRambler.setX(29);
                randomRambler.setY(8);
                break;

            case 3:
                randomRambler.setX(21);
                randomRambler.setY(4);
                break;

            case 4:
                randomRambler.setX(52);
                randomRambler.setY(17);
                break;
            case 5:
                randomRambler.setX(1);
                randomRambler.setY(1);
                clicks = -1;
                break;
        }
        clicks++;



    }

    /**
     * Executed when JavaFX is initialized. Used to setup the Snake game
     */
    public void initialize()
    {
        AddItems();

        calculateFields();

        // this position is used by player
        //drawCanvas();

        // Start and control game loop
        new AnimationTimer(){
            long lastUpdate;
            public void handle (long now)
            {
                if (now > lastUpdate + refreshRate * 1000000)
                {
                    lastUpdate = now;
                    update(now);
                }             }
        }.start();
    }

    private void AddItems() {
        // add walls here?


        for (int i = 0; i < randomRambler.getWallsArray().length; i++) {

            for (int j = 0; j <58 ; j++) {

                if(randomRambler.getWallsArray()[i][j]!=0)
                walls.add(new Wall(j,i));
            }
        }

    }


    /**
     * Game loop - executed continously during the game
     * @param now game time in nano seconds
     */
    private void update(long now)
    {

        drawCanvas();

        if (actiones) randomRambler.depthFirst();
    }


    /**
     * Calculate height and width of each field
     */
    private void calculateFields() {
        this.fieldHeight = canvas.getHeight() / this.height;
        this.fieldWidth = canvas.getWidth() / this.width;
    }

    /**
     * Draw the canvas - used in the gameloop
     */
    private void drawCanvas() {
        GraphicsContext g = canvas.getGraphicsContext2D();

        // remove everything?
        g.clearRect(0,0,width*fieldWidth ,height*fieldHeight);

        //beens

        for (int i = 0; i < randomRambler.getWallsArray().length; i++) {

            for (int j = 0; j <58 ; j++) {

                if(randomRambler.getWallsArray()[i][j]==2)
                    beens.add(new Wall(j,i));
            }
        }


        for (Item wall : walls){

            g.setFill(Color.GREY);

                           g.fillRoundRect(wall.getX() * fieldWidth, wall.getY() * fieldHeight, fieldWidth, fieldHeight, 5, 5);
        }
        for (Item wall : beens){

            g.setFill(Color.INDIANRED);

            g.fillRoundRect(wall.getX() * fieldWidth, wall.getY() * fieldHeight, fieldWidth, fieldHeight, 5, 5);

        }

        //draw rambler
        g.setFill(Color.YELLOWGREEN);

        g.fillRoundRect(this.randomRambler.getX() * fieldWidth, this.randomRambler.getY() * fieldHeight, fieldWidth, fieldHeight, 3, 3);

        // draw walls

    }
}

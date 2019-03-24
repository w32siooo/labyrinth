package SnakeGUI;


import SnakeLogic.Item;
import SnakeLogic.Player;
import SnakeLogic.RandomRambler;
import SnakeLogic.Wall;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.util.*;

public class Controller {

    @FXML
    Label labelStatus;
    @FXML
    Canvas canvas;

    private double fieldHeight;
    private double fieldWidth;
    private int width = 30;
    private int height = 20;
    private Random random = new Random();
    private int gameLoopDelay = 50;
    private float refreshRate =10;
    private KeyCode keyPressed = KeyCode.BACK_SPACE;

    // my player instance
    private Player player = new Player();
    private RandomRambler randomRambler = new RandomRambler();


    ArrayList<Item> items = new ArrayList<>();
    private ArrayList<Item> walls = new ArrayList<>();
    private ArrayList<Item> beens = new ArrayList<>();



    public void btnStartAction(ActionEvent event)
    {
        //labelStatus.setText("test");
        while(!randomRambler.depthFirst());
        {
            //run
        }


    }

    /**
     * Executed when JavaFX is initialized. Used to setup the Snake game
     */
    public void initialize()
    {
        AddItems();

        calculateFields();

        // this position is used by player
        getRandomPosition();
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

            for (int j = 0; j <30 ; j++) {

                if(randomRambler.getWallsArray()[i][j]!=0)
                walls.add(new Wall(j,i));
            }
        }

    }

    private void keyPressed(KeyCode keyCode)
    {

        System.out.println("key pressed: " + keyCode);
        this.keyPressed = keyCode;
    }

    /**
     * Game loop - executed continously during the game
     * @param now game time in nano seconds
     */
    private void update(long now)
    {
        switch (keyPressed)
        {
            case S:
                this.player.setY(player.getY()+1);
                //this.Y++;
                break;
            case A:
                this.player.setX(player.getX()-1);
                break;
            case D:
                this.player.setX(player.getX()+1);
                break;
            case W:
                this.player.setY(player.getY()-1);
                break;
        }

        // random rambler update
        // probably looking for walls????
        // update RR position(x,y)

        drawCanvas();
        //getRandomPosition();

        //randomRambler.update();




       // randomRambler.depthFirst();


        //System.out.println(now);
// 0
    }

    /**
     * Get a random position
     */

    private void getRandomPosition() {
        player.setX(random.nextInt(width));
        player.setY(random.nextInt(height));
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

            for (int j = 0; j <30 ; j++) {

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

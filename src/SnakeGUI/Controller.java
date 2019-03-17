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
    private int gameLoopDelay = 500;
    private float refreshRate =300;
    private KeyCode keyPressed = KeyCode.BACK_SPACE;

    // my player instance
    private Player player = new Player();
    private RandomRambler randomRambler = new RandomRambler();


    ArrayList<Item> items = new ArrayList<Item>();
    ArrayList<Item> walls = new ArrayList<Item>();


    public void btnStartAction(ActionEvent event)
    {
        System.out.println("btn clicked");
        labelStatus.setText("test");
        getRandomPosition();
        drawCanvas();
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

        int[][] wop = new int[][]{{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
        {
            1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
        },
        {
            1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
        },
        {
            1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
        }
                ,
                {
                        1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1
                }
                ,
                {
                        1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1
                }
                ,
                {
                        1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1
                }
                ,
                {
                        1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1
                }
                ,
                {
                        1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1
                }
                ,
                {
                        1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1
                }
                ,
                {
                        1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1
                }
                ,
                {
                        1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
                }
                ,
                {
                        1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
                }
                ,
                {
                        1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
                }
                ,
                {
                        1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
                }
                ,
                {
                        1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
                }
                ,
                {
                        1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
                }
                ,
                {
                        1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
                }
                ,
                {
                        1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
                }
                ,
                {
                        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
                }
        };


        for (int i = 0; i < wop.length; i++) {

            for (int j = 0; j <30 ; j++) {

                if(wop[i][j]!=0)
                walls.add(new Wall(j,i));

            }
        }




//        for (int i = 10; i < 30; i++) {
//            walls.add(new Wall(i,10));
//        }
//
//        for (int i = 10; i < 30; i++) {
//            walls.add(new Wall(i,15));
//        }
//
//        for (int i = 0; i < 20; i++) {
//            walls.add(new Wall(5,i));
//        }
//
//        for (int i = 10; i < 20; i++) {
//            walls.add(new Wall(25,i));
//        }




//        items.add(new Item(Color.GREEN, 3,3));
//        items.add(new Item(Color.RED, 12,9));
//        items.add(new Item(Color.GREY, 0,7));
        //refactroring: would be nice to add player and walls here somehow!?

    }

    public void keyPressed(KeyCode keyCode)
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
/*
        // draw all fields
        g.setFill(Color.BLUE);
        for (int i = 0; i < width ; i++) {
            for (int j = 0; j < height ; j++) {
                g.fillRoundRect(i*fieldWidth, j*fieldHeight, fieldWidth,fieldHeight, 5, 5);
            }
        }


*/

        //draw rambler
        g.setFill(Color.YELLOWGREEN);

        g.fillRoundRect(this.randomRambler.getX() * fieldWidth, this.randomRambler.getY() * fieldHeight, fieldWidth, fieldHeight, 3, 3);

        // draw walls

        for (Item wall : walls){
            g.setFill(Color.GREY);

                           g.fillRoundRect(wall.getX() * fieldWidth, wall.getY() * fieldHeight, fieldWidth, fieldHeight, 5, 5);

        }

        // draw items
//        int i = 0;
//        for (Item item : items)
//        {
//
//                g.setFill(item.getColor());
//
//
//                g.fillRoundRect(item.getX() * fieldWidth, item.getY() * fieldHeight, fieldWidth, fieldHeight, 5, 5);
//                i++;
//        }

        // could be removed if player was in the list of items?
        // draw 'player'
//        g.setFill(Color.BLACK);
//        g.fillRoundRect(this.player.getX() * fieldWidth, this.player.getY() * fieldHeight, fieldWidth, fieldHeight, 3, 3);
    }
}

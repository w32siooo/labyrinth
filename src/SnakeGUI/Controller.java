package SnakeGUI;


import SnakeLogic.Item;
import SnakeLogic.RandomRambler;
import SnakeLogic.Wall;
import javafx.animation.AnimationTimer;
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
    private float refreshRate =40;
    private boolean action = false;
    private int clicks = 0;
    private boolean greedy = false;

    private RandomRambler randomRambler = new RandomRambler(2, 25);
    private RandomRambler randomRambler2 = new RandomRambler(2, 2);
    private RandomRambler randomRambler3 = new RandomRambler(15, 20);

    ArrayList<Item> items = new ArrayList<>();
    private ArrayList<Item> walls = new ArrayList<>();
    private ArrayList<Item> beens = new ArrayList<>();
    private ArrayList<Item> beens2 = new ArrayList<>();


    @SuppressWarnings("StatementWithEmptyBody")
    public void btnStartAction()
    {
        while(!randomRambler.depthFirst());
        {
            System.out.println("cleared");
        }

        while(!randomRambler2.greedy());
        {
            System.out.println("cleared");
        }

        while(!randomRambler3.greedy());
        {
            System.out.println("cleared");
        }

    }

    public void actiones()
    {

        action = true;
    }

    public void greedy()
    {

         greedy = true;

    }

    public void switchPos()
    {
        switch (clicks)
        {
            case 0 :
                randomRambler.setX(2);
                randomRambler.setY(2);
                break;

            case 1:
                randomRambler.setX(2);
                randomRambler.setY(31);
                break;

            case 2:
                randomRambler.setX(29);
                randomRambler.setY(25);
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

            for (int j = 0; j <33 ; j++) {

                if(randomRambler.getWallsArray()[i][j]!=0)
                walls.add(new Wall(j,i));
            }
        }

    }


    /**
     * Game loop - executed continously during the game
     * @param now game time in nano seconds
     */
    private void update(long now) {

        drawCanvas();

        if (action) {

        randomRambler.depthFirst();
        randomRambler2.greedy();
        randomRambler3.greedy();

        }

        if (greedy) randomRambler2.greedy();

        //where we have been, used to paint where we have been. Is only run in the end.


            RandomRambler RandomRambler[] = new RandomRambler[]{randomRambler,randomRambler2,randomRambler3};
            for (RandomRambler rambler : RandomRambler) {
                if (rambler.getY() == 2 && rambler.getX() == 22) {

                    for (int i = 0; i < rambler.getWallsArray().length; i++) {

                        for (int j = 0; j < 33; j++) {

                            if (rambler.getWallsArray()[i][j] == 2)
                                beens.add(new Wall(j, i));
                        }
                    }

                    for (int i = 0; i < rambler.getWallsArray().length; i++) {

                        for (int j = 0; j < 33; j++) {

                            if (rambler.getWallsArray()[i][j] == 4)
                                beens2.add(new Wall(j, i));
                        }
                    }

                }
            }

        //


        //
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


        for (Item wall : walls){

            g.setFill(Color.GREY);

                           g.fillRoundRect(wall.getX() * fieldWidth, wall.getY() * fieldHeight, fieldWidth, fieldHeight, 5, 5);
        }
        for (Item wall : beens){

            g.setFill(Color.INDIANRED);

            g.fillRoundRect(wall.getX() * fieldWidth, wall.getY() * fieldHeight, fieldWidth, fieldHeight, 5, 5);

        }

        for (Item wall : beens2){

            g.setFill(Color.CADETBLUE);

            g.fillRoundRect(wall.getX() * fieldWidth, wall.getY() * fieldHeight, fieldWidth, fieldHeight, 5, 5);

        }

        //draw "ghosts"
        g.setFill(Color.YELLOWGREEN);

        g.fillRoundRect(this.randomRambler.getX() * fieldWidth, this.randomRambler.getY() * fieldHeight, fieldWidth, fieldHeight, 3, 3);

        g.setFill(Color.RED);

        g.fillRoundRect(this.randomRambler2.getX() * fieldWidth, this.randomRambler2.getY() * fieldHeight, fieldWidth, fieldHeight, 3, 3);

        g.setFill(Color.PURPLE);

        g.fillRoundRect(this.randomRambler3.getX() * fieldWidth, this.randomRambler3.getY() * fieldHeight, fieldWidth, fieldHeight, 3, 3);

        // draw "pacman"

        g.setFill(Color.YELLOW);

        g.fillOval(22 * fieldWidth, 2 * fieldHeight, fieldWidth, fieldHeight);

    }
}

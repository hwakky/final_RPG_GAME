package controller;

import game.Game;
import object.GameObject;
import object.ID;

public class Camera {

    private float VIEWPORT_X;
    private float VIEWPORT_Y;

   // camera in map
    private final float WORLD_SIZE_X = Game.BOX_SIZE * (105);   
    private final float WORLD_SIZE_Y = Game.BOX_SIZE * (100);     

    private float offsetMaxX;
    private float offsetMaxY;
    private float offsetMinX;
    private float offsetMinY;

    private float camX;
    private float camY;

    private float x, y;

    public Camera(Game game) {

        VIEWPORT_X = game.getWidth();
        VIEWPORT_Y = game.getHeight();

        offsetMaxX = WORLD_SIZE_X - VIEWPORT_X;
        offsetMaxY = WORLD_SIZE_Y - VIEWPORT_Y;
        offsetMinX = 0;
        offsetMinY = 0;
    }

    public void tick(GameObject Robot) {

        if (Robot.getId() == ID.Robot) {

            camX = Robot.getX() - VIEWPORT_X / 2;
            camY = Robot.getY() - VIEWPORT_Y / 2;

            if (camX > offsetMaxX) camX = offsetMaxX + 32;
            else if (camX < offsetMinX) camX = offsetMinX;

            if (camY > offsetMaxY) camY = offsetMaxY - 12;
            else if (camY < offsetMinY) camY = offsetMinY;

        }

    }

    public float getCamX() {
        return camX;
    }

    public void setCamX(float camX) {
        this.camX = camX;
    }

    public float getCamY() {
        return camY;
    }

    public void setCamY(float camY) {
        this.camY = camY;
    }

}



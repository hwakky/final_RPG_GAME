package object;

import entity.ElementPosition;
import game.Game;
import game.Handler;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
public class Bomb extends GameObject{

    Handler handler;
    ArrayList<ElementPosition> ABombP;
    BufferedWriter bufferedWriter;
    BufferedImage image;

    public Bomb(int x, int y, ID id, Handler handler, ArrayList<ElementPosition> ABombP, BufferedWriter bufferedWriter) {
        super(x, y, id);
        this.handler = handler;
        this.ABombP = ABombP;
        this.bufferedWriter = bufferedWriter;

        try {                
            image = ImageIO.read(new File("../res/object/bomb.png"));
         } catch (IOException ex) {
              
         }
    }

    @Override
    synchronized public void tick() {

        for (int i = 0; i < handler.object.size(); i++) {

            GameObject tempObject = handler.object.get(i);

            if (getBounds().intersects(tempObject.getBounds())) {
                if (tempObject.getId() == ID.Robot) {
                    Robot.hp -= 5;

                    sendMSG(Game.username + " decrease HP :" + Robot.hp);
                }
            }
        }

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.drawOval(x, y, 32, 32);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }

    public void sendMSG(String msg) {
        try {
            bufferedWriter.write(String.valueOf(msg));
            bufferedWriter.newLine();
            bufferedWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

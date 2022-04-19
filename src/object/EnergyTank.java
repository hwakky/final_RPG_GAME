package object;

import game.Handler;

import java.awt.*;
import java.util.Random;

public class EnergyTank extends GameObject {

    private final int[] randomET = {50, 55,60, 65, 70, 75,80 ,85, 90, 95};
    private int random;
    private Handler handler;
    public static int destroy;

    public EnergyTank(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        destroy = 0;
        random = getRandom(randomET);
    }

    public EnergyTank(int x, int y, ID id, Handler handler, int random) {
        super(x, y, id);
        this.handler = handler;
        destroy = 0;
        this.random = random;
    }

    @Override
    public void tick() {

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (getBounds().intersects(tempObject.getBounds())) {
                if (tempObject.getId() == ID.BulletRobot) {
                    handler.removeObject(tempObject);
                }

                if ((tempObject.getId() == ID.Robot)) {
                    if (Robot.hp < 100) {
                        random -= 5;
                        Robot.hp += 5;
                    }
                    if (random <= 0) {
                        handler.removeObject(this);
                        destroy++;
                    }
                } else if (tempObject.getId() == ID.Enemy) {
                    if (Enemy.hp < 100) {
                        random -= 5;
                        Enemy.hp += 5;
                    }
                    if (random <= 0) {
                        handler.removeObject(this);
                        destroy++;
                    }

                }
            }
        }

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillRect(x, y, 32, 32);

        g.setColor(Color.white);
        g.drawString(String.valueOf(random), x + 10, y + 20);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }
    //random fuel
    public int getRandom(int[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }

    public int getRandom() {
        return random;
    }
}

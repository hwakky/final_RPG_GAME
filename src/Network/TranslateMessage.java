package Network;

import controller.BufferImagesLoader;
import game.Game;
import game.Handler;
import object.*;

import java.io.BufferedWriter;
import java.util.ArrayList;

public class TranslateMessage {

    private Handler handler;

    public TranslateMessage(Handler handler) {
        this.handler = handler;
    }

    public ArrayList<Integer> msgAreaBomb(String msgFromGroupChat, ArrayList<Integer> element) {


        if (element.equals(Game.bombXP1)) {

            int indexColon = msgFromGroupChat.indexOf(":");
            int indexET = msgFromGroupChat.indexOf(",ET");

            indexColon += 1;
            String newMsgFromGroupChat = msgFromGroupChat.substring(indexColon, indexET);
            String x[] = newMsgFromGroupChat.split(",");

            for (int i = 0; i < x.length; i++) {
                element.add(Integer.parseInt(x[i]));
            }

            return element;
        }

        else if (element.equals(Game.bombYP1)) {

            int indexColon = msgFromGroupChat.indexOf(":");
            int indexET = msgFromGroupChat.indexOf(",ET");
            indexColon += 1;
            String newMsgFromGroupChat = msgFromGroupChat.substring(indexColon, indexET);

            String x[] = newMsgFromGroupChat.split(",");

            for (int i = 0; i < x.length; i++) {
                element.add(Integer.parseInt(x[i]));
            }

            return element;
        }

        else if (element.equals(Game.ETXP1)) {

            int indexET = msgFromGroupChat.indexOf(",ETX :");
            indexET += 6;
            String newMsgFromGroupChat = msgFromGroupChat.substring(indexET);

            String x[] = newMsgFromGroupChat.split(",");

            for (int i = 0; i < x.length; i++) {
                element.add(Integer.parseInt(x[i]));
            }

            return element;
        }

        else if (element.equals(Game.ETYP1)) {

            int indexET = msgFromGroupChat.indexOf(",ETY :");
            indexET += 6;
            String newMsgFromGroupChat = msgFromGroupChat.substring(indexET);
            String x[] = newMsgFromGroupChat.split(",");

            for (int i = 0; i < x.length; i++) {
                element.add(Integer.parseInt(x[i]));
            }

            return element;
        }

        else {
            return null;
        }

    }

    public ArrayList<Integer> msgAreaET(String msgFromGroupChat, ArrayList<Integer> element) {

        if (element.equals(Game.ETXP1)) {

            int indexET = msgFromGroupChat.indexOf(",ETX :");
            indexET += 6;

            String newMsgFromGroupChat = msgFromGroupChat.substring(indexET);


            String x[] = newMsgFromGroupChat.split(",");

            for (int i = 0; i < x.length; i++) {
                element.add(Integer.parseInt(x[i]));
            }

            return element;

        }

        else if (element.equals(Game.ETYP1)) {

            int indexET = msgFromGroupChat.indexOf(",ETY :");
            indexET += 6;

            String newMsgFromGroupChat = msgFromGroupChat.substring(indexET);


            String x[] = newMsgFromGroupChat.split(",");

            for (int i = 0; i < x.length; i++) {
                element.add(Integer.parseInt(x[i]));
            }

            return element;
        }

        else {
            return null;
        }

    }

    public void msgEnterNewClient(String msgFromGroupChat, BufferImagesLoader loader) {
        String position[] = subMessage(msgFromGroupChat, 2).split(" ");

        // create enemy
        handler.addObject(new Enemy(
                Game.BOX_SIZE * Integer.parseInt(position[0]),
                Game.BOX_SIZE * Integer.parseInt(position[1]),
                ID.Enemy, handler,
                "enemy",
                loader));
    }

    public void msgEnemyMove(String msgFromGroupChat) {
        String position[] = subMessage(msgFromGroupChat, 1).split(" ");

        for (int i = 0; i < handler.object.size(); i++) {
            if (handler.object.get(i).getId() == ID.Enemy) {
                handler.object.get(i).setX(Integer.parseInt(position[0]));
                handler.object.get(i).setY(Integer.parseInt(position[1]));
            }
        }

    }

    public void msgHPPlayer1(String msgFromGroupChat) {
        String hpPlayer1 = subMessage(msgFromGroupChat, 2);

        Enemy.hp = Integer.parseInt(hpPlayer1);

    }

    public void msgEnemyDirect(String msgFromGroupChat) {
        String newMsgFromGroupChat = subMessage(msgFromGroupChat, 1);

        for (int i = 0; i < handler.object.size(); i++) {
            if (handler.object.get(i).getId() == ID.Enemy) {
                Enemy.direct = newMsgFromGroupChat;
            }
        }
    }

    public void msgEnemyShoot(String msgFromGroupChat, BufferedWriter bufferedWriter) {

        String positionBullet[] = subMessage(msgFromGroupChat, 1).split(" ");

        handler.object.add(new Bullet(
                Integer.parseInt(positionBullet[0]), Integer.parseInt(positionBullet[1]), ID.BulletEnemy, handler));
    }

    public void msgCreateLoadPlayer(String msgFromGroupChat, String username, BufferImagesLoader loader) {

        if (msgFromGroupChat.contains("Loading player 1")) {

            Robot.robotUP = loader.loadImage("/res/player/Robot_up.png");
            Robot.robotDown = loader.loadImage("/res/player/Robot_down.png");
            Robot.robotLeft = loader.loadImage("/res/player/Robot_left.png");
            Robot.robotRight = loader.loadImage("/res/player/Robot_right.png");
            Robot.image = Robot.robotDown;

            Enemy.robotUP = loader.loadImage("/res/player/RobotG_up.png");
            Enemy.robotDown = loader.loadImage("/res/player/RobotG_down.png");
            Enemy.robotLeft = loader.loadImage("/res/player/RobotG_left.png");
            Enemy.robotRight = loader.loadImage("/res/player/RobotG_right.png");

            Enemy.image = Enemy.robotUP;

        } else if (msgFromGroupChat.contains("Loading player 2") && !msgFromGroupChat.contains(username)) {

            Robot.robotUP = loader.loadImage("/res/player/RobotG_up.png");
            Robot.robotDown = loader.loadImage("/res/player/RobotG_down.png");
            Robot.robotLeft = loader.loadImage("/res/player/RobotG_left.png");
            Robot.robotRight = loader.loadImage("/res/player/RobotG_right.png");

            Robot.image = Robot.robotDown;

            Enemy.robotUP = loader.loadImage("/res/player/Robot_up.png");
            Enemy.robotDown = loader.loadImage("/res/player/Robot_down.png");
            Enemy.robotLeft = loader.loadImage("/res/player/Robot_left.png");
            Enemy.robotRight = loader.loadImage("/res/player/Robot_right.png");

            Enemy.image = Enemy.robotUP;
        }

    }

    public void msgPlayerEnd(String msgFromGroupChat) {

        // destroy enemy
        for (int i = 0; i < handler.object.size(); i++) {
            if (handler.object.get(i).getId() == ID.Enemy) {
                handler.removeObject(handler.object.get(i));
            }
        }

    }

    public void msgDecreaseHP(String msgFromGroupChat) {

        String hpPlayer1 = subMessage(msgFromGroupChat, 2);
        Enemy.hp = Integer.parseInt(hpPlayer1);

    }

    public ArrayList<Integer> msgCreateBombXP2(String msgFromGroupChat, ArrayList<Integer> bombP2) {

        int indexColon = msgFromGroupChat.indexOf(":");
        indexColon += 2;
        int indexET = msgFromGroupChat.indexOf("areaPlayer2 BombY : ");
        String newMsgFromGroupChat = msgFromGroupChat.substring(indexColon, indexET);

        String x[] = newMsgFromGroupChat.split(",");

        for (int i = 0; i < x.length; i++) {
            bombP2.add(Integer.parseInt(x[i]));
        }

        return bombP2;
    }

    public ArrayList<Integer> msgCreateBombYP2(String msgFromGroupChat, ArrayList<Integer> bombP2) {

        int indexColon = msgFromGroupChat.indexOf(":");
        indexColon += 2;

        int indexET = msgFromGroupChat.indexOf("BombY : ");

        String newMsgFromGroupChat = msgFromGroupChat.substring(indexET+8);

        String x[] = newMsgFromGroupChat.split(",");

        for (int i = 0; i < x.length; i++) {
            bombP2.add(Integer.parseInt(x[i]));
        }

        return bombP2;
    }

    public ArrayList<Integer> msgCreateETXP2(String msgFromGroupChat, ArrayList<Integer> etxp2) {

        int msgEnd = msgFromGroupChat.indexOf("areaPlayer2 ETY :");


        String newMsgFromGroupChat = subMessage(msgFromGroupChat, 2, msgEnd);
        String x[] = newMsgFromGroupChat.split(",");

        for (int i = 0; i < x.length; i++) {
            etxp2.add(Integer.parseInt(x[i]));
        }

        return etxp2;
    }

    public ArrayList<Integer> msgCreateETYP2(String msgFromGroupChat, ArrayList<Integer> etyp2) {
        int msgStart = msgFromGroupChat.indexOf("ETY :");
        int msgEnd = msgFromGroupChat.indexOf("randET :");
        String newMsgFromGroupChat = msgFromGroupChat.substring(msgStart + 6, msgEnd);

        String x[] = newMsgFromGroupChat.split(",");

        for (int i = 0; i < x.length; i++) {
            etyp2.add(Integer.parseInt(x[i]));
        }

        return etyp2;
    }


    public ArrayList<Integer> msgCreateRandET(String msgFromGroupChat, ArrayList<Integer> randETP2) {
        int msgEnd = msgFromGroupChat.indexOf("randET :");
        String newMsgFromGroupChat = msgFromGroupChat.substring(msgEnd + 9);

        String x[] = newMsgFromGroupChat.split(",");

        for (int i = 0; i < x.length; i++) {
            randETP2.add(Integer.parseInt(x[i]));
        }

        return randETP2;
    }

    private String subMessage(String msgFromGroupChat, int indexCount) {

        int indexColon = msgFromGroupChat.indexOf(":");
        indexColon += indexCount;

        return msgFromGroupChat.substring(indexColon);

    }

    private String subMessage(String msgFromGroupChat, int indexCount, int endIndex) {

        int indexColon = msgFromGroupChat.indexOf(":");
        indexColon += indexCount;

        return msgFromGroupChat.substring(indexColon, endIndex);

    }

}

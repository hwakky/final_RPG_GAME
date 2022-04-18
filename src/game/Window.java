package game;

import javax.swing.*;
import java.awt.*;
class Window {

    private JFrame frame;
    private int width, height;

    public Window(int width, int height, String name, Game game) {
        
        this.frame = new JFrame(name);
        this.width = width;
        this.height = height;

        frame.setPreferredSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));

        frame.add(game);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public void closeWindow(){
        frame.dispose();
        frame.setVisible(false);
    }
}



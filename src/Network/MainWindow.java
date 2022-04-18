package Network;

import game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

public class MainWindow {

    private final JFrame frame;
    private Game client;



    public MainWindow(String name, int width, int height) {
         // set screen default
        this.frame = new JFrame(name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().setBackground(Color.BLACK);
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(4,1));
        frame.pack();
        frame.setVisible(true);

        // text tanky
        JLabel label = new JLabel("Tanky", SwingConstants.CENTER);
        label.setForeground(Color.YELLOW);
        label.setBounds(300, 100, 0, 0);
        label.setFont(new Font("Serif", Font.BOLD,50));
        frame.add(label);
        label.setVisible(true);
        // button to start game
        JButton button = ButtonStart();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Socket socket = null;
                try {
                    socket = new Socket("localhost", 9999);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                try {
                    client = new Game(socket);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                client.listenForMessage();

                frame.setVisible(false);
                frame.dispose();

            }
        });


    }

    private JButton ButtonStart() {
        // button
        JButton button = new JButton("Start game");
        button.setBounds(300, 300, 200, 50);
        frame.add(button);
        button.setVisible(true);
        return button;
    }

    public static void main(String[] args) {
        new MainWindow("Main window", 800, 650);
    }
}
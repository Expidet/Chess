package Board;

import javax.swing.*;
import java.awt.*;


public class Board implements Runnable{

    private JPanel BoardScreen;

    public static final int WIDTH = 1200;
    public static final int HEIGHT = 1200;

    public JPanel generateScreen(){
        JPanel panel = new JPanel(new GridLayout(8, 8));

        for(int i = 0; i < 64; i++){
            Field f = new Field(i);
            panel.add(f);
        }
        return panel;
    }

    @Override
    public void run() {
        showGUI();
    }

    private void showGUI(){
        JFrame frame = new JFrame("Board");
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setLayeredPane(new PiecePanel());
        frame.setContentPane(generateScreen());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Board());
    }
}

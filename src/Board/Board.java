package Board;

import javax.swing.*;
import java.awt.*;


public class Board implements Runnable{

    private JPanel BoardScreen;

    public JPanel generateScreen(){
        JPanel panel = new JPanel(new GridLayout(8, 8));
        Color darkBrown = new Color(185, 139, 98);
        Color lightBrown = new Color(242, 218, 179);
        for(int i = 0; i < 64; i++){
            JButton btn = new JButton();
            btn.setBackground(((i + Math.floor(i / 8)) % 2 == 0) ? lightBrown : darkBrown);
            btn.addActionListener(new FieldActionListener(btn));
            btn.setBorderPainted(false);
            panel.add(btn);
        }
        return panel;
    }

    @Override
    public void run() {
        showGUI();
    }

    private void showGUI(){
        JFrame frame = new JFrame("Board");
        frame.setPreferredSize(new Dimension(1200, 1200));
        frame.setContentPane(generateScreen());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Board());
    }
}

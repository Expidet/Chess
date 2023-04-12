package Board;

import javax.swing.*;
import java.awt.*;


public class Board implements Runnable{

    private JPanel BoardScreen;

    public static final int WIDTH = 1200;
    public static final int HEIGHT = 1200;
    public static final Field[] fields = new Field[64];
    private static Field selectedField;

    public JPanel generateScreen(){
        JPanel panel = new JPanel(new GridLayout(8, 8));

        for(int y = 0; y < 8; y++){
            for(int x = 0; x < 8; x++){
                Field f = new Field(x, y);
                fields[8 * y + x] = f;
                panel.add(f);
            }
        }
        return panel;
    }

    @Override
    public void run() {
        showGUI();
    }

    public static void setSelectedField(Field selectedField) {
        Board.selectedField = selectedField;
    }

    public static Field getSelectedField() {
        return selectedField;
    }

    private void showGUI(){
        JFrame frame = new JFrame("Board");
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        JPanel cp = generateScreen();
        PiecePanel pp = new PiecePanel();
        frame.setLayeredPane(pp);
        frame.setContentPane(cp);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        pp.repaint();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Board());
    }
}

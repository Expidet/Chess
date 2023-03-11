package Board;

import GameManaging.GameManager;
import Pieces.PiecePanel;

import javax.swing.*;
import java.awt.*;


public class Board implements Runnable{

    public static final int WIDTH = 700;
    public static final int HEIGHT = 700;
    public static final Field[] fields = new Field[64];
    private static PiecePanel piecePanel;
    private static Field selectedField;

    public JPanel generateScreen(){
        JPanel panel = new JPanel(new GridLayout(8, 8));
        for(int y = 0; y < 8; y++){
            for(int x = 0; x < 8; x++){
                Field f = new Field(y, x);

                fields[8 * y + x] = f;
                panel.add(f);
            }
        }
        return panel;
    }

    public static void setSelectedField(Field f){
        Board.selectedField = f;
    }

    public static Field getSelectedField(){
        return Board.selectedField;
    }

    @Override
    public void run() {
        showGUI();
    }

    private void showGUI(){
        JFrame frame = new JFrame("Board");
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        JPanel cp = generateScreen();
        piecePanel = new PiecePanel();
        GameManager.setPieces(piecePanel.getPieces());
        GameManager.calcValidMoves();
        frame.setLayeredPane(piecePanel);
        frame.setContentPane(cp);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        piecePanel.repaint();
        frame.setVisible(true);
    }

    public static PiecePanel getPiecePanel() {
        return piecePanel;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Board());
    }
}

package Board;

import javax.swing.*;
import java.awt.*;

import Pieces.*;

public class PiecePanel extends JLayeredPane {
    King k;
    public PiecePanel(){
        this.setPreferredSize(new Dimension(Board.WIDTH, Board.HEIGHT));
        initChessBoard();
    }

    private void initChessBoard(){
        k = new King(Teams.WHITE);
        k.setBounds(0, 0, Board.WIDTH / 8, Board.HEIGHT / 8);
        this.setLayer(k, 1);
        this.add(k);
    }

    @Override
    public void repaint() {
        super.repaint();
        k.setBounds(0, 0, this.getWidth() / 8, this.getHeight() / 8);
    }
}

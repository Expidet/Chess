package Promotion;

import Board.Field;
import Pieces.*;

import javax.swing.*;
import java.awt.*;

public class PromotionFrame extends JFrame {
    public PromotionFrame(Pawn pawnToPromote){
        this.setPreferredSize(new Dimension(250, 250));
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        Teams team = pawnToPromote.getTeam();
        Field startField = pawnToPromote.getCurrentField();
        int pieceId = pawnToPromote.getPieceId();
        Piece[] pieces = {new Rook(team, startField, pieceId), new Knight(team, startField, pieceId), new Bishop(team, startField, pieceId), new Queen(team, startField, pieceId)};
        this.setLayeredPane(new PromotionLayeredPane(pieces));
        this.setContentPane(new PromotionPanel(pawnToPromote, pieces, this));
    }
}

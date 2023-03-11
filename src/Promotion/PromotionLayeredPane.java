package Promotion;

import Pieces.Piece;

import javax.swing.*;
import java.awt.*;

public class PromotionLayeredPane extends JLayeredPane {

    public PromotionLayeredPane(Piece[] pieces){
        for(int y = 0; y < 2; y++){
            for(int x = 0; x < 2 ; x++){
                Piece piece = pieces[2*y+x];
                this.setLayer(piece, 2);
                piece.setBounds(x * 240 / 2,y * 215 / 2, 240 / 2, 215 / 2);
                this.add(piece);
            }
        }
    }
}

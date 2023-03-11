package Promotion;

import Pieces.*;

import javax.swing.*;
import java.awt.*;

public class PromotionPanel extends JPanel {
    public PromotionPanel(Pawn pawnToReplace, Piece[] pieces, PromotionFrame frame){
        this.setLayout(new GridLayout(2,2));
        for(int i = 0; i < 4; i++){
            JButton btn = new PromotionButton(pawnToReplace, pieces[i], frame);
            this.add(btn);
        }
    }
}


package Board;

import GameManaging.GameManager;
import Pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FieldActionListener implements ActionListener {

    final Field self;

    public FieldActionListener(Field self) {
        this.self = self;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(self.isMovable()){
            Field startField = Board.getSelectedField();
            movePieceToSelf(startField);
            Board.getPiecePanel().repaint();
        }

        if (self.getPieceOnField() == null && Board.getSelectedField() != null) {
            Board.getSelectedField().setSelected(false);
        }
        if (self.getPieceOnField() != null && GameManager.getCurrentTurn() == self.getPieceOnField().getTeam()) {
            if (self.getPieceOnField() != null && Board.getSelectedField() != null && !self.isSelected()) {
                Board.getSelectedField().setSelected(false);
                self.setSelected(true);
            } else if (self.getPieceOnField() != null) {
                self.setSelected(!self.isSelected());
            }
        }
    }

    public void movePieceToSelf(Field startField){
        Piece pieceToMove = Board.getSelectedField().getPieceOnField();
        startField.setSelected(false);
        pieceToMove.moveToNewField(self);
    }
}

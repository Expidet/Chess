package Board;

import GameManaging.CastleMove;
import GameManaging.GameManager;
import Pieces.King;
import Pieces.Piece;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FieldActionListener implements ActionListener {

    final Field self;
    public FieldActionListener(Field self){
        this.self = self;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (self.isMovable()) {
            Field startField = Board.getSelectedField();
            movePieceToSelf(startField);
            checkEnPassant();
            checkCastles();
            Board.getPiecePanel().repaint();
            GameManager.progressTurn();
        }
        if(self.getPieceOnField() == null && Board.getSelectedField() != null){
            Board.getSelectedField().setSelected(false);
        }
        if(self.getPieceOnField() != null && GameManager.getCurrentTurn() == self.getPieceOnField().getTeam()){
            if(self.getPieceOnField() != null && Board.getSelectedField() != null && !self.isSelected()){
                Board.getSelectedField().setSelected(false);
                self.setSelected(true);
            } else if(self.getPieceOnField() != null){
                self.setSelected(!self.isSelected());
            }
        }
    }

    public void movePieceToSelf(Field startField){
        Piece pieceToMove = Board.getSelectedField().getPieceOnField();
        //If a piece was captured
        self.removePieceFromSelf();

        startField.setSelected(false);
        pieceToMove.moveToNewField(self);
        pieceToMove.moved();
    }

    private void checkEnPassant(){
        Field enPassant = GameManager.getEnPassant(self);
        if(enPassant != null){
            enPassant.removePieceFromSelf();
            enPassant.setPieceOnField(null);
        }
    }

    private void checkCastles(){
        CastleMove castleMove = GameManager.getCastle(self);
        if(castleMove != null){
            castleMove.rookToCastle.moveToNewField(castleMove.newRookField);
            GameManager.clearCastleMoves();
        }
    }
}

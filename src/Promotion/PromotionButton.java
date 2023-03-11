package Promotion;

import Board.Board;
import GameManaging.GameManager;
import Pieces.Piece;

import javax.swing.*;
public class PromotionButton extends JButton {
    public PromotionButton(Piece pieceToRemove, Piece pieceToAdd, PromotionFrame promotionFrame){
        this.addActionListener(e -> {
            Board.getPiecePanel().removePieceFromBoard(pieceToRemove);
            Board.getPiecePanel().addPieceToBoard(pieceToAdd);
            GameManager.setChoosingPiece(false);
            GameManager.progressTurn();
            promotionFrame.setVisible(false);
            promotionFrame.dispose();
        });
    }
}

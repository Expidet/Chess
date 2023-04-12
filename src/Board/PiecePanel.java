package Board;

import javax.swing.*;
import java.util.ArrayList;

import Pieces.*;

public class PiecePanel extends JLayeredPane {
    ArrayList<Piece> pieces = new ArrayList<>();
    public PiecePanel(){
        this.generateBoard("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
        //this.generateBoard("pppppppp/p6p/p6p/p2Q3p/p6p/p6p/p6p/pppppppp w KQkq - 0 1");
        //this.generateBoard("8/8/8/3B4/8/8/8/8 w KQkq - 0 1");
    }

    private void generateBoard(String FEN){
        String[] FENGroups = FEN.split(" ");
        int currentIndex = 0;
        char[] piecePositions = FENGroups[0].toCharArray();
        for (char c : piecePositions) {
            if (c == '/') continue;
            if (Character.isDigit(c)) {
                currentIndex += Character.getNumericValue(c);
                continue;
            }
            Teams team = (Character.isLowerCase(c)) ? Teams.BLACK : Teams.WHITE;
            Piece currentPiece = null;
            switch (Character.toLowerCase(c)) {
                case 'r' -> {
                    currentPiece = new Rook(team, Board.fields[currentIndex]);
                }
                case 'n' -> {
                    currentPiece = new Knight(team, Board.fields[currentIndex]);
                }
                case 'b' -> {
                    currentPiece = new Bishop(team, Board.fields[currentIndex]);
                }
                case 'q' -> {
                    currentPiece = new Queen(team, Board.fields[currentIndex]);
                }
                case 'k' -> {
                    currentPiece = new King(team, Board.fields[currentIndex]);
                }
                case 'p' -> {
                    currentPiece = new Pawn(team, Board.fields[currentIndex]);
                }
            }
            pieces.add(currentPiece);
            Board.fields[currentIndex].setPieceOnField(currentPiece);
            currentIndex++;
        }

        for(Piece p : pieces){
            this.setLayer(p, 2);
            p.setBounds(p.getCurrentField().getXPos(Board.WIDTH / 8), p.getCurrentField().getYPos(Board.HEIGHT / 8), Board.WIDTH / 8, Board.HEIGHT / 8);
            this.add(p);
        }

    }

    @Override
    public void repaint() {
        super.repaint();
        for(Piece p : pieces){
            p.setBounds(p.getCurrentField().getXPos(this.getWidth() / 8), p.getCurrentField().getYPos(this.getHeight() / 8), this.getWidth() / 8, this.getHeight() / 8);
        }
    }
}

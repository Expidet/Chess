package Pieces;

import Board.*;
import GameManaging.GameManager;
import jdk.jfr.Description;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Objects;

public class PiecePanel extends JLayeredPane {

    ArrayList<Piece> pieces = new ArrayList<>();
    public PiecePanel(){
        //this.generateBoard("pppppppp/p6p/p6p/p2Q3p/p6p/p6p/p6p/pppppppp w KQkq - 0 1");
        //this.generateBoard("r3k2r/8/8/8/8/8/RQ6/8 w KQkq - 0 1");
        //this.generateBoard("");
        //this.generateBoard("r2qkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
        //this.generateBoard("4k3/5p2/4Q3/8/8/8/8/8 b KQkq - 0 1");
        this.generateBoard("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 1 2");
        //this.generateBoard("QK6/8/8/k7/8/8/8/8 w KQkq - 0 1");
        //this.generateBoard("8/P7/8/p7/8/8/2R5/8 w KQkq - 0");
    }

    @Description("Generates a list of pieces based on the FEN string and puts them at their required location on the board" +
                 "Sets the current turn in the Game Manager by calling GameManager.setCurrentTurn() based on the FEN String as well" +
                 "Handles castle rights and En passant")
    public void generateBoard(String FEN){
        String[] FENGroups = FEN.split(" ");
        int currentIndex = 0;
        char[] piecePositions = FENGroups[0].toCharArray();
        for(int i = 0; i < piecePositions.length; i++){
            char c = piecePositions[i];
            if(c == '/') continue;
            if(Character.isDigit(c)){
                currentIndex += Character.getNumericValue(c);
                continue;
            }
            Teams team = (Character.isLowerCase(c)) ? Teams.BLACK : Teams.WHITE;
            Piece currentPiece = null;
            switch (Character.toLowerCase(c)) {
                case 'r' -> currentPiece = new Rook(team, Board.fields[currentIndex], i);
                case 'n' -> currentPiece = new Knight(team, Board.fields[currentIndex], i);
                case 'b' -> currentPiece = new Bishop(team, Board.fields[currentIndex], i);
                case 'q' -> currentPiece = new Queen(team, Board.fields[currentIndex], i);
                case 'k' -> currentPiece = new King(team, Board.fields[currentIndex], i);
                case 'p' -> currentPiece = new Pawn(team, Board.fields[currentIndex], i);
            }
            pieces.add(currentPiece);
            Board.fields[currentIndex].setPieceOnField(currentPiece);
            currentIndex++;
        }
        //TODO: Analyze En Passants out of FEN
        //TODO: Analyze castles out of FEN
        GameManager.setCurrentTurn((Objects.equals(FENGroups[1], "w")) ? Teams.WHITE : Teams.BLACK);
        for(Field f : Board.fields){
            this.setLayer(f.getFieldStateVisualizer(), 1);
            f.getFieldStateVisualizer().setBounds(f.getXPos(Board.WIDTH / 8), f.getYPos(Board.HEIGHT / 8), Board.WIDTH / 8, Board.HEIGHT / 8);
            this.add(f.getFieldStateVisualizer());
        }

        for(Piece p : pieces){
            this.setLayer(p, 2);
            p.setBounds(p.getCurrentField().getXPos(Board.WIDTH / 8), p.getCurrentField().getYPos(Board.HEIGHT / 8), Board.WIDTH / 8, Board.HEIGHT / 8);
            this.add(p);
        }
    }

    public void removePieceFromBoard(Piece piece){
        pieces.remove(piece);
        this.remove(piece);
        this.repaint();
    }

    public void addPieceToBoard(Piece piece){
        pieces.add(piece);
        this.setLayer(piece, 2);
        this.add(piece);
        this.repaint();
    }

    @Override
    public void repaint() {
        super.repaint();
        for(Field f : Board.fields){
            f.getFieldStateVisualizer().setBounds(f.getXPos(this.getWidth() / 8), f.getYPos(this.getHeight() / 8), this.getWidth() / 8, this.getHeight() / 8);
        }

        for(Piece p : pieces){
            p.setBounds(p.getCurrentField().getXPos(this.getWidth() / 8), p.getCurrentField().getYPos(this.getHeight() / 8), this.getWidth() / 8, this.getHeight() / 8);
        }
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }
}

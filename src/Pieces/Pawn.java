package Pieces;

import Board.*;
import GameManaging.EnPassantMove;
import GameManaging.GameManager;

import java.util.ArrayList;

public class Pawn extends Piece{

    private int timesMoved = 0;
    private boolean canBeEnPassantInFuture = true;
    public Pawn(Teams team, Field startField) {
        super("chessPieces/" + team.name().toLowerCase() + "_pawn.svg", team, startField);
    }

    @Override
    public ArrayList<Field> getValidMoves() {
        ArrayList<Field> validMoves = new ArrayList<>();

        Integer[] moveVectors = getMoveVectors();
        int currentIndex = this.getCurrentField().getFieldId();
        for(int moveVector: moveVectors){
            int newPos = currentIndex + moveVector;
            if(newPos < 0 || newPos > 63) break;
            if (Board.fields[newPos].getPieceOnField() != null)
                break;
            validMoves.add(Board.fields[newPos]);
        }
        validMoves.addAll(getTakes());
        return validMoves;
    }

    public ArrayList<Field> getTakes(){
        ArrayList<Field> takes = new ArrayList<>();
        Field currentField = this.getCurrentField();
        int teamPref = (this.getTeam() == Teams.WHITE) ? -1 : 1;

        //Normal takes
        if(currentField.getFieldId() + 7 * teamPref >= 0 && currentField.getFieldId() + 7 * teamPref <= 63){
            if(Board.fields[currentField.getFieldId() + 7 * teamPref].getPieceOnField() != null && Board.fields[currentField.getFieldId() + 7 * teamPref].getPieceOnField().getTeam() != this.getTeam()){
                if(currentField.getColumn() != ((this.getTeam() == Teams.BLACK) ? 0 : 7)){
                    takes.add(Board.fields[currentField.getFieldId() + 7 * teamPref]);
                }
            }
        }
        if(currentField.getFieldId() + 9 * teamPref >= 0 && currentField.getFieldId() + 9 * teamPref <= 63){
            if(Board.fields[currentField.getFieldId() + 9 * teamPref].getPieceOnField() != null && Board.fields[currentField.getFieldId() + 9 * teamPref].getPieceOnField().getTeam() != this.getTeam()) {
                if(currentField.getColumn() != ((this.getTeam() == Teams.BLACK) ? 7 : 0)){
                    takes.add(Board.fields[currentField.getFieldId() + 9 * teamPref]);
                }
            }
        }


        //En passant
        if(currentField.getRow() == ((this.getTeam() == Teams.WHITE) ? 3 : 4)){
            if(Board.fields[currentField.getFieldId() + 1].getPieceOnField() instanceof Pawn && ((Pawn) Board.fields[currentField.getFieldId() + 1].getPieceOnField()).canBeEnPassant()){
                takes.add(Board.fields[currentField.getFieldId() + ((this.getTeam() == Teams.BLACK) ? 9 : -7)]);
                EnPassantMove enPassantMove = new EnPassantMove(Board.fields[currentField.getFieldId() + 1], Board.fields[currentField.getFieldId() + ((this.getTeam() == Teams.BLACK) ? 9 : -7)]);
                GameManager.addEnPassantMove(enPassantMove);
                System.out.println("New en passant move added");
            }
            if(Board.fields[currentField.getFieldId() - 1].getPieceOnField() instanceof Pawn && ((Pawn) Board.fields[currentField.getFieldId() - 1].getPieceOnField()).canBeEnPassant()){
                takes.add(Board.fields[currentField.getFieldId() + ((this.getTeam() == Teams.BLACK) ? 7 : -9)]);
                EnPassantMove enPassantMove = new EnPassantMove(Board.fields[currentField.getFieldId() - 1], Board.fields[currentField.getFieldId() + ((this.getTeam() == Teams.BLACK) ? 7 : -9)]);
                GameManager.addEnPassantMove(enPassantMove);
                System.out.println("New en passant move added");
            }
        }
        return takes;
    }

    private Integer[] getMoveVectors(){
        boolean isFirstMove = this.getCurrentField().getRow() == ((this.getTeam() == Teams.WHITE) ? 6 : 1);
        int team = (this.getTeam() == Teams.WHITE) ? -1 : 1;
        if(isFirstMove){
            return new Integer[]{8 * team, 16 * team};
        }
        return new Integer[]{8 * team};
    }

    @Override
    public void moved() {
        timesMoved += 1;
    }

    public void setCanBeEnPassantInFuture(boolean canBeEnPassantInFuture) {
        this.canBeEnPassantInFuture = canBeEnPassantInFuture;
    }
    public boolean canBeEnPassant(){
        return timesMoved == 1 && this.getCurrentField().getRow() == ((this.getTeam() == Teams.BLACK) ? 3 : 4) && canBeEnPassantInFuture;
    }
}

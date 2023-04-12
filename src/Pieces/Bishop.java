package Pieces;

import Board.Board;
import Board.Field;

import java.util.ArrayList;

public class Bishop extends Piece{

    public Bishop(Teams team, Field startField) {
        super("chessPieces/" + team.name().toLowerCase() + "_bishop.svg", team, startField);
    }

    @Override
    public ArrayList<Field> getValidMoves() {
        Integer[] moveVectors = getMoveVectors();
        ArrayList<Field> validMoves = new ArrayList<>();

        int currentFieldIndex = this.getCurrentField().getFieldId();
        int startFieldIndex = currentFieldIndex;

        for(Integer moveVector : moveVectors){
            while (true){
                currentFieldIndex += moveVector;
                if(currentFieldIndex < 0 || currentFieldIndex > 63) break;
                if(Board.fields[currentFieldIndex].getPieceOnField() != null && Board.fields[currentFieldIndex].getPieceOnField().getTeam() == this.getTeam()) break;
                if(Board.fields[currentFieldIndex].getPieceOnField() != null && Board.fields[currentFieldIndex].getPieceOnField().getTeam() != this.getTeam() || ((currentFieldIndex) % 8 == 0) || (currentFieldIndex + 1) % 8 == 0){
                    validMoves.add(Board.fields[currentFieldIndex]);
                    break;
                }
                validMoves.add(Board.fields[currentFieldIndex]);
            }
            currentFieldIndex = startFieldIndex;
        }
        for(Field f : validMoves){
            System.out.println(f.getFieldId());
        }
        return validMoves;
    }

    private Integer[] getMoveVectors(){
        Field currentField =this.getCurrentField();
        if(currentField.getColumn() == 0){
            return new Integer[]{9, -7};
        } else if (currentField.getColumn() == 7) {
            return new Integer[]{-9, 7};
        }
        return new Integer[]{9, 7, -7, -9};
    }
}

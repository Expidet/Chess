package Pieces;

import Board.Board;
import Board.Field;

import java.util.ArrayList;

public class Knight extends Piece{

    public Knight(Teams team, Field startField) {
        super("chessPieces/" + team.name().toLowerCase() + "_knight.svg", team, startField);
    }

    @Override
    public ArrayList<Field> getValidMoves() {
        Integer[] moveVectors = getMoveVectors();
        ArrayList<Field> validMoves = new ArrayList<>();
        int currentFieldIndex = this.getCurrentField().getFieldId();
        for (Integer moveVector : moveVectors) {
            int newFieldIndex = currentFieldIndex + moveVector;
            if (newFieldIndex < 0 || newFieldIndex > 63) continue;
            if (Board.fields[newFieldIndex].getPieceOnField() != null && Board.fields[newFieldIndex].getPieceOnField().getTeam() == this.getTeam())
                continue;
            if ((Board.fields[newFieldIndex].getPieceOnField() != null && Board.fields[newFieldIndex].getPieceOnField().getTeam() != this.getTeam())) {
                validMoves.add(Board.fields[newFieldIndex]);
                continue;
            }
            validMoves.add(Board.fields[newFieldIndex]);
        }
        for(Field f : validMoves){
            System.out.println(f.getFieldId());
        }
        return validMoves;
    }


    private Integer[] getMoveVectors(){
        Field currentField =this.getCurrentField();
        if(currentField.getColumn() == 0){
            return new Integer[]{-15, -6, 17, 10};
        }else if(currentField.getColumn() == 1){
            return new Integer[]{-15, -6, 17, 10, 15, -17};
        }else if(currentField.getColumn() == 6){
            return new Integer[]{15, 6, -17, -10, -15, 17};
        }else if (currentField.getColumn() == 7) {
            return new Integer[]{15, 6, -17, -10};
        }

        return new Integer[]{-15, -17, 15, 17, -6, 10, 6, -10};
    }
}

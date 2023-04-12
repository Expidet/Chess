package Pieces;

import Board.*;

import java.util.ArrayList;

public class King extends Piece {


    public King(Teams team, Field startField) {
        super("chessPieces/" + team.name().toLowerCase() + "_king.svg", team, startField);
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
            validMoves.add(Board.fields[newFieldIndex]);
        }
        for(Field f : validMoves){
            System.out.println(f.getFieldId());
        }
        return validMoves;
    }
    private Integer[] getMoveVectors() {
        Field currentField = this.getCurrentField();
        if (currentField.getColumn() == 7) {
            return new Integer[]{-1, -8, 8, -9, 7};
        }else if (currentField.getColumn() == 0) {
            return new Integer[]{1, -8, 8, -7, 9};
        }
        return new Integer[]{-1, 1, -8, 8, -9, -7, 9, 7};
    }

}

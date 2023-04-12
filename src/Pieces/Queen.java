package Pieces;

import Board.Board;
import Board.Field;

import java.util.ArrayList;

public class Queen extends Piece{

    public Queen(Teams team, Field startField) {
        super("chessPieces/" + team.name().toLowerCase() + "_queen.svg", team, startField);
    }

    @Override
    public ArrayList<Field> getValidMoves() {
        Integer[] moveVectors = getMoveVectors();
        int currentFieldIndex = this.getCurrentField().getFieldId();
        int startFieldIndex = currentFieldIndex;
        ArrayList<Field> validMoves = new ArrayList<>();

        for (Integer moveVector : moveVectors) {
            while (true) {
                currentFieldIndex += moveVector;
                if (currentFieldIndex < 0 || currentFieldIndex > 63) break;
                if (Board.fields[currentFieldIndex].getPieceOnField() != null && Board.fields[currentFieldIndex].getPieceOnField().getTeam() == this.getTeam())
                    break;
                if (Board.fields[currentFieldIndex].getPieceOnField() != null && Board.fields[currentFieldIndex].getPieceOnField().getTeam() != this.getTeam()) {
                    validMoves.add(Board.fields[currentFieldIndex]);
                    break;
                }
                //Restricting diagonal movement
                if(((currentFieldIndex) % 8 == 0 || (currentFieldIndex + 1) % 8 == 0) && (Math.abs(moveVector) == 7 || Math.abs(moveVector) == 9)){
                    validMoves.add(Board.fields[currentFieldIndex]);
                    break;
                }
                //Restricting horizontal movement
                if((((currentFieldIndex) % 8 == 0) || (currentFieldIndex + 1) % 8 == 0) && Math.abs(moveVector) == 1){
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

    private Integer[] getMoveVectors() {
        Field currentField = this.getCurrentField();
        if (currentField.getColumn() == 0) {
            return new Integer[]{9, -7, 8, -8, 1};
        } else if (currentField.getColumn() == 7) {
            return new Integer[]{-9, 7, 8, -8, -1};
        }
        return new Integer[]{8, -8, 1, -1, 9, 7, -7, -9};
    }
}

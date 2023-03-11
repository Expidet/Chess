package Pieces;

import Board.*;
import GameManaging.CastleMove;
import GameManaging.GameManager;

import java.util.ArrayList;

public class King extends Piece {

    private boolean moved = false;
    public King(Teams team, Field startField, int pieceId) {
        super("chessPieces/" + team.name().toLowerCase() + "_king.svg", team, startField, pieceId);
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
            if (Board.fields[newFieldIndex].getPieceOnField() != null && Board.fields[newFieldIndex].getPieceOnField().getTeam() != this.getTeam()) {
                validMoves.add(Board.fields[newFieldIndex]);
                continue;
            }
            validMoves.add(Board.fields[newFieldIndex]);
        }
        validMoves.addAll(getCastleMoves());
        return validMoves;
    }

    private ArrayList<Field> getCastleMoves(){
        ArrayList<Field> castleMoves = new ArrayList<>();
        if(this.moved) return castleMoves;
        if(GameManager.isCheck()) return castleMoves;
        //short castle
        try {
            Rook r = ((Rook) Board.fields[this.getCurrentField().getFieldId() + 3].getPieceOnField());
            if(r.getMoved()) throw new Exception();
            if(Board.fields[this.getCurrentField().getFieldId() + 1].getPieceOnField() != null || Board.fields[this.getCurrentField().getFieldId() + 2].getPieceOnField() != null) throw new Exception();
            if(Board.fields[this.getCurrentField().getFieldId() + 1].isAttacked(this.getTeam()) || Board.fields[this.getCurrentField().getFieldId() + 2].isAttacked(this.getTeam())) throw new Exception();
            Field newKingPos = Board.fields[this.getCurrentField().getFieldId() + 2];
            Field newRookPos = Board.fields[newKingPos.getFieldId() -1];
            castleMoves.add(newKingPos);
            GameManager.addCastleMove(new CastleMove(newKingPos, newRookPos, r));
        }catch (Exception ignored){}

        //long castle
        try {
            Rook r = ((Rook) Board.fields[this.getCurrentField().getFieldId() - 4].getPieceOnField());
            if(r.getMoved()) throw new Exception();
            if(Board.fields[this.getCurrentField().getFieldId() - 1].getPieceOnField() != null || Board.fields[this.getCurrentField().getFieldId() - 2].getPieceOnField() != null || Board.fields[this.getCurrentField().getFieldId() - 3].getPieceOnField() != null) throw new Exception();
            if(Board.fields[this.getCurrentField().getFieldId() - 1].isAttacked(this.getTeam()) || Board.fields[this.getCurrentField().getFieldId() - 2].isAttacked(this.getTeam()) || Board.fields[this.getCurrentField().getFieldId() - 3].isAttacked(this.getTeam())) throw new Exception();
            Field newKingPos = Board.fields[this.getCurrentField().getFieldId() - 2];
            Field newRookPos = Board.fields[newKingPos.getFieldId() + 1];
            castleMoves.add(newKingPos);
            GameManager.addCastleMove(new CastleMove(newKingPos, newRookPos, r));
        }catch (Exception ignored){}

        return castleMoves;
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

    @Override
    public void moved() {
        moved = true;
    }

}

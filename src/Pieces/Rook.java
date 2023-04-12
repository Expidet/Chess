package Pieces;

import Board.Field;

public class Rook extends Piece{

    public Rook(Teams team, Field startField) {
        super("chessPieces/" + team.name().toLowerCase() + "_rook.svg", team, startField);
    }
}

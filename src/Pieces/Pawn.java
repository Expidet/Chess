package Pieces;

import Board.Field;

public class Pawn extends Piece{

    public Pawn(Teams team, Field startField) {
        super("chessPieces/" + team.name().toLowerCase() + "_pawn.svg", team, startField);
    }
}

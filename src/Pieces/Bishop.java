package Pieces;

import Board.Field;

public class Bishop extends Piece{

    public Bishop(Teams team, Field startField) {
        super("chessPieces/" + team.name().toLowerCase() + "_bishop.svg", team, startField);
    }
}

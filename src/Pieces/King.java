package Pieces;

import Board.Field;

public class King extends Piece{

    public King(Teams team, Field startField) {
        super("chessPieces/" + team.name().toLowerCase() + "_king.svg", team, startField);
    }
}

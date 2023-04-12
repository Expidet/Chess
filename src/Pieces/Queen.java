package Pieces;

import Board.Field;

public class Queen extends Piece{

    public Queen(Teams team, Field startField) {
        super("chessPieces/" + team.name().toLowerCase() + "_queen.svg", team, startField);
    }
}

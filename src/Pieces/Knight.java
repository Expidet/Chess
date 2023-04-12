package Pieces;

import Board.Field;

public class Knight extends Piece{

    public Knight(Teams team, Field startField) {
        super("chessPieces/" + team.name().toLowerCase() + "_knight.svg", team, startField);
    }
}

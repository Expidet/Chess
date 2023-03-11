package Pieces;

public class King extends Piece{

    public King(Teams team) {
        super("chessPieces/" + team.name().toLowerCase() + "_king.svg", team);
    }
}

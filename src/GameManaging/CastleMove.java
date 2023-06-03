package GameManaging;

import Board.Field;
import Pieces.Rook;

public class CastleMove {
    public Field newKingField;
    public Field newRookField;
    public Rook rookToCastle;

    public CastleMove(Field newKingField, Field newRookField, Rook rookToCastle){
        this.newKingField = newKingField;
        this.newRookField = newRookField;
        this.rookToCastle = rookToCastle;
    }
}

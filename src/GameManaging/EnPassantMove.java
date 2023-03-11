package GameManaging;

import Board.Field;

public class EnPassantMove {
    public Field removeField;
    public Field targetField;

    public EnPassantMove(Field removeField, Field targetField){
        this.removeField = removeField;
        this.targetField = targetField;
    }
}

package Board;

import Pieces.Piece;

import javax.swing.*;
import java.awt.*;

public class Field extends JButton {

    private final int row;
    private final int column;
    private final int fieldId;
    private boolean selected;
    private Piece pieceOnField;

    private final Color origColor;
    private final Color selectedColor;

    public Field(int column, int row){
        this.row = row;
        this.column = column;
        int index = row * 8 + column;
        Color darkBrown = new Color(185, 139, 98);
        Color lightBrown = new Color(242, 218, 179);
        this.selectedColor = new Color(218, 196, 49);
        this.setBackground(((index + Math.floor(index / 8)) % 2 == 0) ? lightBrown : darkBrown);
        this.origColor = this.getBackground();
        this.addActionListener(new FieldActionListener(this));
        this.setBorderPainted(false);

        this.fieldId = index;
        this.add(new JLabel(String.valueOf(fieldId)));
    }

    public Piece getPieceOnField() {
        return pieceOnField;
    }

    public void setPieceOnField(Piece pieceOnField) {
        this.pieceOnField = pieceOnField;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        Board.setSelectedField(((selected) ? this : null));
        setBackground((selected) ? this.selectedColor : this.origColor);
        if(selected){
            this.pieceOnField.getValidMoves();
        }
    }
    public boolean isSelected(){
        return this.selected;
    }

    public int getXPos(int widthPerCell) {
        return column * widthPerCell;
    }

    public int getYPos(int heightPerCell) {
        return row * heightPerCell;
    }

    public int getColumn() {
        return this.column;
    }

    public int getRow(){
        return this.row;
    }

    public int getFieldId() {
        return this.fieldId;
    }
}

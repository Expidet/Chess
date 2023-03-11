package Board;

import GameManaging.CastleMove;
import GameManaging.GameManager;
import Pieces.King;
import Pieces.Piece;
import Pieces.Teams;

import javax.swing.*;
import java.awt.*;

public class Field extends JButton {

    private final int fieldId;
    private final int column;
    private final int row;
    private final FieldStateVisualizer fieldStateVisualizer;
    private final Color origColor;
    private final Color selectedColor;

    private Piece pieceOnField;
    private Teams[] attackers = new Teams[2];
    private boolean isSelected;
    private boolean isMovable;

    public Field(int row, int column) {
        int index = row * 8 + column;

        Color darkBrown = new Color(185, 139, 98);
        Color lightBrown = new Color(242, 218, 179);
        this.selectedColor = new Color(218, 196, 49);

        this.setBackground(((index + Math.floorDiv(index, 8)) % 2 == 0) ? lightBrown : darkBrown);
        this.origColor = this.getBackground();
        this.addActionListener(new FieldActionListener(this));
        this.setBorderPainted(false);

        this.fieldId = index;
        this.fieldStateVisualizer = new FieldStateVisualizer(this);
        this.fieldStateVisualizer.setVisible(false);
        this.row = row;
        this.column = column;
        //this.add(new JLabel(String.valueOf(this.fieldId)));
    }

    public boolean setAttackerWhite(){
        //returns if black king is under check
        this.attackers[Teams.WHITE.ordinal()] = Teams.WHITE;
        try {
            King kingOnField = (King) this.getPieceOnField();
            return kingOnField.getTeam() == Teams.BLACK;
        }catch (Exception e){
            return false;
        }
    }

    public boolean setAttackerBlack(){
        //returns true if white king is under check
        this.attackers[Teams.BLACK.ordinal()] = Teams.BLACK;
        try {
            King kingOnField = (King) this.getPieceOnField();
            return kingOnField.getTeam() == Teams.WHITE;
        }catch (Exception e){
            return false;
        }
    }
    public boolean isAttacked(Teams ownTeam){
        return this.attackers[(ownTeam.ordinal() + 1) % 2] != null;
    }

    public void clearAttackers(){this.attackers = new Teams[2];}
    public int getXPos(int widthPerCell) {
        return column * widthPerCell;
    }

    public int getYPos(int heightPerCell) {
        return row * heightPerCell;
    }

    public int getFieldId() {
        return fieldId;
    }

    public void setPieceOnField(Piece pieceOnField) {
        this.pieceOnField = pieceOnField;
    }

    public Piece getPieceOnField() {return this.pieceOnField;}

    public boolean isSelected() {
        return isSelected;
    }

    public int getRow() {return this.row;}

    public int getColumn() {return this.column;}

    public boolean isMovable() {
        return isMovable;
    }

    public FieldStateVisualizer getFieldStateVisualizer() {
        return fieldStateVisualizer;
    }

    public void setSelected(boolean selected) {
        if(GameManager.isChoosingPiece()){
            return;
        }
        isSelected = selected;
        Board.setSelectedField((selected) ? this : null);
        this.setBackground((selected) ? this.selectedColor : this.origColor);
        if (selected) {
            for (Field validMove : GameManager.currentValidMoves.get(this.pieceOnField.getPieceId())) {
                validMove.makeFieldMovable();
            }
        } else {
            for (Field validMove : GameManager.currentValidMoves.get(this.pieceOnField.getPieceId())) {
                validMove.makeFieldUnmovable();
            }
            for(CastleMove castleMove : GameManager.getCastleMoves()){
                castleMove.newKingField.makeFieldUnmovable();
            }
        }
    }

    public void removePieceFromSelf(){
        if(this.getPieceOnField() != null){
            Board.getPiecePanel().removePieceFromBoard(this.getPieceOnField());
        }
    }

    public void makeFieldMovable() {
        this.fieldStateVisualizer.updateFile();
        this.fieldStateVisualizer.setVisible(true);
        isMovable = true;
    }

    public void makeFieldUnmovable() {
        this.fieldStateVisualizer.setVisible(false);
        isMovable = false;
    }


}

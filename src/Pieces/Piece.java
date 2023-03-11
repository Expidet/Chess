package Pieces;

import Board.Field;
import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGUniverse;
import jdk.jfr.Description;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.util.ArrayList;

public abstract class Piece extends JLabel {

    private final File f;
    private final Teams team;

    private Field currentField;
    private final int pieceId;

    public Piece(String pathToImgFile, Teams team, Field startField, int pieceId){
        f = new File(pathToImgFile);
        this.team = team;
        this.currentField = startField;
        this.pieceId = pieceId;
    }

    @Override
    @Description("Draws the SVG of the piece by using its file defined in the piece itself")
    protected void paintComponent(Graphics g) {
        SVGUniverse svgUniverse = new SVGUniverse();
        try {
            SVGDiagram diagram = svgUniverse.getDiagram(svgUniverse.loadSVG(f.toURI().toURL()));
            try {
                AffineTransform at = new AffineTransform();
                at.setToScale(this.getWidth()/diagram.getWidth(), this.getHeight()/diagram.getWidth());
                Graphics2D graphics2D = (Graphics2D) g;
                graphics2D.transform(at);
                diagram.render(graphics2D);
            }
            catch(Exception ex) {System.out.println(ex);}}
        catch (Exception ex2) {System.out.println(ex2);}
    }

    @Description("Returns a reference to the field the piece is currently on")
    public Field getCurrentField(){
        return this.currentField;
    }
    @Description("Moves the piece to a new Field in code => does not update Grahics")
    public void moveToNewField(Field newField){
        this.getCurrentField().setPieceOnField(null);
        this.currentField = newField;
        newField.setPieceOnField(this);
    }
    @Description("Moves the piece to a new Field in code => does not update Graphics. Returns the piece that was on the param new Field" +
                 "used by the game Manager to check if a move produces a check")
    public Piece moveToNewFieldUndoable(Field newField){
        this.getCurrentField().setPieceOnField(null);
        this.currentField = newField;
        Piece oldPiece = newField.getPieceOnField();
        newField.setPieceOnField(this);
        return oldPiece;
    }
    @Description("Used by the Game Manager to undo the Overloaded 'Piece moveToNewFieldUndoable(Field newField)' function by moving itself back to the start Field and sets the " +
                 "Piece on the Field this piece is moving away from. => essentially resetting the board to the state if was before 'Piece moveToNewFieldUndoable(Field newField)' got called")
    public void moveToNewFieldUndoable(Field newField, Piece oldPiece){
        this.getCurrentField().setPieceOnField(oldPiece);
        this.currentField = newField;
        newField.setPieceOnField(this);
    }
    @Description("returns the team the piece is from")
    public Teams getTeam(){
        return this.team;
    }

    @Description("returns the pieceId the piece is from")
    public int getPieceId() {
        return pieceId;
    }

    @Description("Overridden by every Piece instance to generate valid moves WITHOUT considering possible checks. (Checks is the task of the GameManager)")
    public abstract ArrayList<Field> getValidMoves();
    @Description("Overridden by Rook, King, Pawn as it matters for the rules when they move")
    public void moved(){}
}

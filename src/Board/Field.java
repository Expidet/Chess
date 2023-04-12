package Board;

import javax.swing.*;
import java.awt.*;

public class Field extends JButton {

    private final int row;
    private final int column;

    public Field(int column, int row){
        this.row = row;
        this.column = column;
        int index = row * 8 + column;
        Color darkBrown = new Color(185, 139, 98);
        Color lightBrown = new Color(242, 218, 179);
        this.setBackground(((index + Math.floor(index / 8)) % 2 == 0) ? lightBrown : darkBrown);
        this.addActionListener(new FieldActionListener(this));
        this.setBorderPainted(false);
    }

    public int getXPos(int widthPerCell) {
        return column * widthPerCell;
    }

    public int getYPos(int heightPerCell) {
        return row * heightPerCell;
    }
}

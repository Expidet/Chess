package Board;

import javax.swing.*;
import java.awt.*;

public class Field extends JButton {
    public Field(int index){
        Color darkBrown = new Color(185, 139, 98);
        Color lightBrown = new Color(242, 218, 179);
        this.setBackground(((index + Math.floor(index / 8)) % 2 == 0) ? lightBrown : darkBrown);
        this.addActionListener(new FieldActionListener(this));
        this.setBorderPainted(false);
    }
}

package Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FieldActionListener implements ActionListener {

    final JButton self;
    final Color origColor;
    final Color selectedColor;

    private int clickCount = 0;
    public FieldActionListener(JButton self){
        this.self = self;
        this.origColor = self.getBackground();
        this.selectedColor = new Color(218, 196, 49);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(clickCount % 2 == 0){
            self.setBackground(this.selectedColor);
        }else {
            self.setBackground(this.origColor);
        }

        clickCount++;
    }
}

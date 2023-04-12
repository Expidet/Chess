package Board;

import GameManaging.GameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FieldActionListener implements ActionListener {

    final Field self;


    private int clickCount = 0;
    public FieldActionListener(Field self){
        this.self = self;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(self.getPieceOnField() == null && Board.getSelectedField() != null){
            Board.getSelectedField().setSelected(false);
        }
        if(self.getPieceOnField() != null && GameManager.getCurrentTurn() == self.getPieceOnField().getTeam()){
            if(self.getPieceOnField() != null && Board.getSelectedField() != null && !self.isSelected()){
                Board.getSelectedField().setSelected(false);
                self.setSelected(true);
            } else if(self.getPieceOnField() != null){
                self.setSelected(!self.isSelected());
            }
        }
    }
}

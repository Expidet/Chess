package Pieces;

import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGUniverse;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.File;

public abstract class Piece extends JLabel {

    private final File f;
    private final Teams team;

    public Piece(String pathToImgFile, Teams team){
        f = new File(pathToImgFile);
        this.team = team;
    }

    @Override
    protected void paintComponent(Graphics g) {
        SVGUniverse svgUniverse = new SVGUniverse();
        try {
            SVGDiagram diagram = svgUniverse.getDiagram(svgUniverse.loadSVG((f.toURI().toURL())));
            try {
                AffineTransform at = new AffineTransform();
                at.setToScale(this.getWidth() / diagram.getWidth(), this.getHeight() / diagram.getHeight());
                Graphics2D graphics2D = (Graphics2D) g;
                graphics2D.transform(at);
                diagram.render(graphics2D);
            }catch (Exception ex1){System.out.println(ex1);}
        }catch (Exception ex2){System.out.println(ex2);}
    }
}

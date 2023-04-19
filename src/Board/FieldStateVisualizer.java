package Board;

import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGUniverse;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.File;

public class FieldStateVisualizer extends JLabel {

    private File file;
    private final Field self;
    public FieldStateVisualizer(Field self){
        this.self = self;
        updateFile();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        SVGUniverse svgUniverse = new SVGUniverse();
        try {
            SVGDiagram diagram = svgUniverse.getDiagram(svgUniverse.loadSVG(file.toURI().toURL()));
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

    public void updateFile(){
        this.file = new File((self.getPieceOnField() != null) ? "chessPieces/edge_capture.svg" : "chessPieces/circle_move.svg");
    }
}

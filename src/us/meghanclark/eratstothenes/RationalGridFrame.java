package us.meghanclark.eratstothenes;

import javax.swing.*;
import java.awt.*;

/**
 * A frame which displays a rectangular grid corresponding to some matrix of rational numbers.
 *
 * @author Meghan Clark
 */
public class RationalGridFrame extends JFrame {

    protected static final int DUPLICATE = 1;

    private final RationalGridContext context;

    public RationalGridFrame(RationalGridContext rationalGridContext) {
        this.context = rationalGridContext;

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(this.context.getAreaWidth(), this.context.getAreaHeight()));
    }

    @Override
    public void paint(Graphics g) {
        int width = this.context.getCellWidth();
        int height = this.context.getCellHeight();

        for (int i = 0; i < this.context.getRows(); i++) {
            for (int j = 0; j < this.context.getColumns(); j++) {
                if (this.context.getMatrix()[i][j] == DUPLICATE) {
                    g.setColor(this.context.getDuplicateColor());
                    g.drawRect(j * width, i * height, width, height);
                    g.fillRect(j * width, i * height, width, height);
                }
                else {
                    g.setColor(this.context.getEquivalenceColor());
                    g.fillRect(j * width, i * height, width, height);

                    g.setColor(this.context.getDuplicateColor());
                    g.drawRect(j * width, i * height, width, height);
                }
            }
        }
    }

    public RationalGridContext getRationalGridContext() {
        return this.context;
    }
}

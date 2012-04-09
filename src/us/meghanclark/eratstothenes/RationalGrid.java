package us.meghanclark.eratstothenes;

import java.awt.*;
import java.lang.reflect.Field;

import static java.lang.StrictMath.max;

/**
 * Entry point for commencement of the prettiness.
 *
 * @author Meghan Clark
 * @author Josh Essex
 */
public class RationalGrid {

    private static final String ROW_FLAG = "-r";
    private static final String COLUMN_FLAG = "-c";
    private static final String CELL_WIDTH_FLAG = "-w";
    private static final String CELL_HEIGHT_FLAG = "-h";
    private static final String EQUIVALENCE_COLOR_FLAG = "-e";
    private static final String DUPLICATE_COLOR_FLAG = "-d";

    public static void main(String[] args) {
        RationalGridContext rationalGridContext = parseArguments(args);

        RationalGridFrame rationalGridFrame = new RationalGridFrame(rationalGridContext);
        rationalGridFrame.pack();
        rationalGridFrame.setVisible(true);
    }

    /**
     * Parses the command line arguments and attempts to build a {@link RationalGridContext} from the arguments. Will exit the
     * program with an abnormal termination status if there is an issue parsing the arguments.
     *
     * @param args the command line arguments to parse
     * @return a context object built from the arguments
     */
    private static RationalGridContext parseArguments(String[] args) {
        if (args.length == 0) {
            int[][] matrix = buildMatrix(RationalGridContext.DEFAULT_ROWS, RationalGridContext.DEFAULT_COLUMNS);
            return new RationalGridContext(matrix);
        }

        int rows = RationalGridContext.DEFAULT_ROWS;
        int columns = RationalGridContext.DEFAULT_COLUMNS;
        int cellHeight = RationalGridContext.DEFAULT_CELL_HEIGHT;
        int cellWidth = RationalGridContext.DEFAULT_CELL_WIDTH;
        Color equivalenceColor = RationalGridContext.DEFAULT_EQUIVALENCE_COLOR;
        Color duplicateColor = RationalGridContext.DEFAULT_DUPLICATE_COLOR;

        try {
            for (int i = 0; i < args.length - 1; i++) {
                String arg = args[i];
                String nextArg = args[i + 1];

                if (arg.equals(ROW_FLAG)) {
                    rows = Integer.parseInt(nextArg);
                }
                else if (arg.equals(COLUMN_FLAG)) {
                    columns = Integer.parseInt(nextArg);
                }
                else if (arg.equals(CELL_HEIGHT_FLAG)) {
                    cellHeight = Integer.parseInt(nextArg);
                }
                else if (arg.equals(CELL_WIDTH_FLAG)) {
                    cellWidth = Integer.parseInt(nextArg);
                }
                else if (arg.equals(EQUIVALENCE_COLOR_FLAG)) {
                    final Field equivField = Color.class.getField(nextArg.toUpperCase());
                    equivalenceColor = (Color) equivField.get(null);
                }
                else if (arg.equals(DUPLICATE_COLOR_FLAG)) {
                    final Field duplicateField = Color.class.getField(nextArg.toUpperCase());
                    duplicateColor = (Color) duplicateField.get(null);
                }
            }
        }
        catch (Exception e) {
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append("Usage: java RationalGrid [options]\n");
            stringBuilder.append("\t-r \tAmount of rows\n");
            stringBuilder.append("\t-c \tAmount of columns\n");
            stringBuilder.append("\t-w \tWidth of each cell (in pixels)\n");
            stringBuilder.append("\t-h \tHeight of each cell (in pixels)\n");
            stringBuilder.append("\t-e \tColor of equivalence class cells (must be a java.awt.Color)\n");
            stringBuilder.append("\t-d \tColor of duplicate class cells (must be a java.awt.Color)\n");

            System.err.println(stringBuilder.toString());
            System.exit(1);
        }

        int[][] matrix = buildMatrix(rows, columns);
        return new RationalGridContext(matrix, columns, rows, cellWidth, cellHeight, equivalenceColor, duplicateColor);
    }

    /**
     * Builds and returns the matrix.
     *
     * @param rows the amount of rows in the matrix
     * @param columns the amount of columns in the matrix
     * @return the matrix
     */
    private static int[][] buildMatrix(int rows, int columns) {
        int[][] matrix = new int[rows][columns];

        // Flag duplicates
        for (int z = 2; z <= max(rows, columns); z++) {
            for (int i = 1; z * i <= rows; i++) {
                for (int j = 1; z * j <= columns; j++) {
                    matrix[z * i - 1][z * j - 1] = RationalGridFrame.DUPLICATE;
                }
            }
        }

        return matrix;
    }
}

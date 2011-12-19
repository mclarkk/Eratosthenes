/* RationalGrid.java
Author: Meghan
Description: This makes and displays a grid of all the separate equivalence classes (in blue) and the duplicates (in black) as a subset of an MxN grid. You can change the values of M and N, as well as the size of the sqaures...the "resolution" if you will.

Redo as a BufferedImage, with the option of saving?
*/

import javax.swing.*;
import java.awt.*;

public class RationalGridRedux extends JFrame
{
	private int[][] matrix;
	private static int M = 225; //rows
	private static int N = 360; //cols
	private static int sHeight = 4; //square height in pixels
	private static int sWidth = 4; //square width in pixels
	
	public static void main(String[] args)
	{
		RationalGridRedux r = new RationalGridRedux(M, N);
	}

	public RationalGridRedux(int M, int N)
	{
		matrix = makeMatrix(M, N);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(1440, 900));
		drawOnComponent(matrix, M, N);
		pack();
		setVisible(true);
	}
	
	//each matrix coordinates is a 2x2 square.
	public void drawOnComponent(int[][] matrix, int rowsize, int colsize)
	{
		JPanel panel = (JPanel) getContentPane();

		JPanel p = new JPanel();
		panel.add(p);
	}

	public int[][] makeMatrix(int rowsize, int colsize)
	{
		//init with all zeros
		int[][] newMatrix = new int[rowsize][colsize];
		for (int i = 0; i<rowsize; i++)
		{
			for (int j = 0; j<colsize; j++)
			{
				newMatrix[i][j] = 0;
			}
		}
	
		//darken multiples
		for (int z = 2; z<=StrictMath.max(rowsize, colsize); z++)
		{
			for (int i = 1; z*i<=rowsize; i++)
			{
				for (int j = 1; z*j<=colsize; j++)
				{
					newMatrix[z*i-1][z*j-1] = 1;
				}
			}
		}

		return newMatrix;
	}

	public void paint(Graphics g)
	{
		for (int i = 0; i<M; i++)
		{
			for (int j = 0; j<N; j++)
			{
				if (matrix[i][j] == 1)
				{
					g.setColor(Color.BLACK);
					g.drawRect(j*sWidth, i*sHeight, sWidth, sHeight);
					g.fillRect(j*sWidth, i*sHeight, sWidth, sHeight);
				}
				else
				{
					g.setColor(Color.BLUE);
					g.fillRect(j*sWidth, i*sHeight, sWidth, sHeight);
					g.setColor(Color.BLACK);
					g.drawRect(j*sWidth, i*sHeight, sWidth, sHeight);
				}
			}
		}
	}
}

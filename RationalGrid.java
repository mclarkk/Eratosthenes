/* RationalGrid.java
Author: Meghan
Description: This makes and displays a grid of all the separate equivalence classes (in white) and the duplicates (in black) as a subset of an NxN grid, where each row n starts at 1 and goes until n-1.
*/

import javax.swing.*;
import java.awt.*;

public class RationalGrid extends JFrame
{
	public static void main(String[] args)
	{
		int size = 100;
		RationalGrid r = new RationalGrid(size);
	}

	public RationalGrid(int size)
	{
		int[][] matrix = makeMatrix(size);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(800, 900));
		createGUI(matrix, size);
		pack();
		setVisible(true);
	}
	
	public void createGUI(int[][] matrix, int size)
	{
		GridLayout layout = new GridLayout(size, size, 0, 0);
		JPanel panel = new JPanel();
		panel.setLayout(layout);
		add(panel);
		
				int count = 1; //TEST
		Color color = Color.WHITE;
		for (int i = 0; i<size; i++)
		{
			for (int j = 0; j<size; j++)
			{
				if (matrix[i][j] == 1)
					color = Color.BLACK;
				else
					color = Color.BLUE;
				JPanel p = new JPanel();
				p.setBackground(color);
				p.setBorder(BorderFactory.createLineBorder(Color.black));
				panel.add(p);
			}
		}
	}

	public int[][] makeMatrix(int size)
	{
		int[][] matrix = new int[size][size];
		for (int i = 0; i<size; i++)
		{
			for (int j = 0; j<size; j++)
			{
				matrix[i][j] = 0;
			}
		}
	
		for (int z = 2; z<=size; z++)
		{
			for (int i = 1; z*i<=size; i++)
			{
				for (int j = 1; z*j<=size; j++)
				{
					matrix[z*i-1][z*j-1] = 1;
				}
			}
		}

		return matrix;
	}
}

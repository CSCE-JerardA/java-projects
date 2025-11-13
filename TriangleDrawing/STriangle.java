/*
 * Written by Jerard Austin with help from Youtube and GeeksforGeeks
 */


import javax.swing.*;
import java.awt.*;


//I learned about implementing the JPanel through youtube
public class STriangle extends JPanel{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Variables along with setting up the frame
		JFrame frame = new JFrame("Sierpinski Triangle");
        STriangle panel = new STriangle();
        frame.add(panel);
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
	
	//A method set the points and draw the triangle
    protected void paintComponent(Graphics g) {
    	
    	//I know what super does in Python but I had to look it up on geeksforgeeks about what it could mean or do in Java.
        super.paintComponent(g);
        // Sets the initial triangle points
        int[] xPoints = {400, 100, 700};
        int[] yPoints = {100, 700, 700};
        // Draws the initial triangle
        drawST(g, xPoints, yPoints, 4);
    }

    
    private void drawST(Graphics g, int[] xPoints, int[] yPoints, int limit) {
        // Stops the recursion if the triangle side length is less than the limit
        if (Math.abs(xPoints[1] - xPoints[0]) <= limit) {
            return;
        }

        
        g.setColor(Color.BLUE);
        g.fillPolygon(xPoints, yPoints, 3);

        // Calculate midpoints and indexes of each side
        int midX1 = (xPoints[0] + xPoints[1]) / 2;
        int midY1 = (yPoints[0] + yPoints[1]) / 2;

        int midX2 = (xPoints[1] + xPoints[2]) / 2;
        int midY2 = (yPoints[1] + yPoints[2]) / 2;

        int midX3 = (xPoints[2] + xPoints[0]) / 2;
        int midY3 = (yPoints[2] + yPoints[0]) / 2;

        // Creates an upside down version of this
        g.setColor(Color.GREEN);
        int[] innerX = {midX1, midX2, midX3};
        int[] innerY = {midY1, midY2, midY3};
        g.fillPolygon(innerX, innerY, 3);

        // Draws the smaller triangles
        drawST(g, new int[]{xPoints[0], midX1, midX3}, new int[]{yPoints[0], midY1, midY3}, limit);
        drawST(g, new int[]{midX1, xPoints[1], midX2}, new int[]{midY1, yPoints[1], midY2}, limit);
        drawST(g, new int[]{midX3, midX2, xPoints[2]}, new int[]{midY3, midY2, yPoints[2]}, limit);
    }
		
		
	

}

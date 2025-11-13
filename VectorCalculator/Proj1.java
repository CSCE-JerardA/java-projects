import javax.swing.JOptionPane;



/*
 * Jerard Austin
 */

//Single comments


public class Proj1 {

	//Class Body
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Main Body Method
		
		//Main Variables
		String input;
		int inums;
		double[] vect1 = {};
		double[] vect2 = {};
		
		//Opening message
		JOptionPane.showConfirmDialog(null,"Hello! Welcome to " + "a Vector Operations" + " System. ");
		
		//Entering a number in order to use an operator
		input = JOptionPane.showInputDialog("Enter 0 to" + " add two vectors, " + "Enter 1 to" + " subtract two vectors," + " Enter 2 to find" + "the magnitude of the vector, " + "Enter 3 to" + " quit. : ");
		inums = Integer.parseInt(input);
		
		//If statements leading to different methods
		if(inums == 0) {
			// Writes first vector in the getVect method
			vect1 = getVec("first vect");
			// Writes second vector in the getVect method
			vect2 = getVec("second vect");
			// Sends these vectors to the method addVect
			addVect(vect1, vect2);
		}
		else if(inums == 1) {
			// Writes first vector in the getVect method
			vect1 = getVec("first vect");
			// Writes second vector in the getVect method
			vect2 = getVec("second vect");
			// Sends these vectors to the method subVect
			subVect(vect1,vect2);
		}
		else if (inums == 2) {
			// Writes the vector in the getVect method
			vect1 = getVec("vect");
			// Sends vector to the method magVect
			magVect(vect1);
		}
		else if (inums == 3)
			// Sends user to the quitVect method
			quitVect();
		else
			// Sends user to the error method
			error();
			
		
		
	}

	

	private static double[] getVec(String orderofVec) {
		// TODO Auto-generated method stub
		
		// Variable
		String input;
		
		// Input message
		input = JOptionPane.showInputDialog("Enter two numbers for " + orderofVec );
		// This command below splits the input of the users number by using "\\s+"
		String[] inputs = input.split("\\s+");
		// Creates an array from the input the user sends and makes the input a double variable
		double[] vector = new double[inputs.length];
		for(int i = 0; i<inputs.length;i++) {
			vector[i] = Double.parseDouble(inputs[i]);
		}
		// Returns this vector
		return vector;
	}



	private static void error() {
		// TODO Auto-generated method stub
		// Displays a message of an error for not entering a number leading to an operator
		JOptionPane.showConfirmDialog(null, "Error! Enter 0" + ", 1, 2, or" + " 3 to operate!");	
	}

	private static void quitVect() {
		// TODO Auto-generated method stub
		//Displays a message saying goodbye for quitting
		JOptionPane.showConfirmDialog(null,"Goodbye!");
	}

	private static void magVect(double[] vect1) {
		// TODO Auto-generated method stub
		
		// Makes the sum variable zero to start
		double sum = 0.0;
		
		// Creates a some of the two numbers within the vector
		for(double num:vect1) {
			sum += num *num;
		}
		// Squares the sum and displays the answer in a message
		double magnitude = Math.sqrt(sum);
		JOptionPane.showConfirmDialog(null, "The magnitude of this vector is " + magnitude);
	}

	private static void subVect(double[] vect1, double[] vect2) {
		// TODO Auto-generated method stub
		
		// Statement saying if the length of either vector aren't the same then display an error message
		if (vect1.length != vect2.length || vect2.length != vect1.length) {
            JOptionPane.showMessageDialog(null, "Error! Vectors must be of the same size.");
            return;
        }
		
		//Creates an answer from subtracting vector 1 and vector 2
        double[] result = new double[vect1.length];
        for (int i = 0; i < vect1.length; i++) {
            result[i] = vect1[i] - vect2[i];
        }
        // Displays the answer
        JOptionPane.showMessageDialog(null, "The result of this vector is: " + arrayToString(result));
    
	}

	private static void addVect(double[]vect1,double[]vect2) {
		// TODO Auto-generated method stub
		
		
		// Statement saying if the length of either vector aren't the same then display an error message
		if (vect1.length != vect2.length || vect2.length != vect1.length) {
		    JOptionPane.showMessageDialog(null, "Error! Vectors must be of the same size.");
		    return;
		}
		
		//Creates an answer from adding vector 1 and vector 2
        double[] result = new double[vect1.length];
        for (int i = 0; i < vect1.length; i++) {
            result[i] = vect1[i] + vect2[i];
        }
        // Displays the answer
        JOptionPane.showMessageDialog(null, "The result of this vector is: " + arrayToString(result));
		
			
	}



	private static String arrayToString(double[] Varray) {
		// TODO Auto-generated method stub
		
		// Creates a new 
		StringBuilder sb = new StringBuilder();
		for(double num:Varray) {
			sb.append(num).append(" ");
		}
		// Returns the double variables in the array into string variables
		return sb.toString();
			
			
		}
}


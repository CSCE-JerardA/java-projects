/*
 * Written by Jerard Austin with help from GeeksforGeeks
 * 
 * I'm rambling but I always use the Javax dialogue box because I learned how to use it at my previous university. I just prefer it over the regular console
 * 
 */

// Hello vro

import javax.swing.JOptionPane;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

// Main class
public class TOrg {
	
	private static String[][] tLists = new String[5][100]; 
    private static int[] taskCounts = new int[5]; 
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Variables
		String input;
		int inums;
		String tFile;
		
		// Opening Message
		JOptionPane.showConfirmDialog(null, "Welcome to JC's Task Organizer! ");
		
		while(true) {
			// Statement asking for the user's input to move to a specific task
			input = JOptionPane.showInputDialog("Enter 0 to add a task:" + "\nEnter 1 to delete a task:" + "\nEnter 2 to display the tasks:" + "\nEnter 3 to read a task:" + "\nEnter 4 to write a new task:" + "\nEnter 7 to quit: ");
			inums = Integer.parseInt(input);
			
			// Cases that switch you to a method depending on the input
			switch(inums) {
			case 0:
				//Method that adds a task
				addT();
			case 1:
				//Method that deletes a task
				delT();
				break;
			case 2:
				//Method that displays a task
				dispT();
				break;
			case 3:
				//Method that reads tasks from a file
				readT();
				break;
			case 4:
				// Method that writes a new task in the file
				writeT();
				break;
			case 7:
				//Method that quits running the program
				quit();
				return;
			default:
				//Method stating that 
				error();
				break;
		}
			
			
		}
		
		
		
		
		
	}

	private static void error() {
		// TODO Auto-generated method stub
		JOptionPane.showConfirmDialog(null, "Error! Enter a number listed");
	}

	private static void quit() {
		// TODO Auto-generated method stub
		JOptionPane.showConfirmDialog(null, "Goobye!");
	}

	private static void writeT() {
		// TODO Auto-generated method stub
		String fileName = JOptionPane.showInputDialog("Enter the file name to write tasks to:");

		// It's supposed to create a new file but it didn't work when I attempted to create a new one
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (int i = 0; i < tLists.length; i++) {
                for (String task : tLists[i]) {
                    writer.println(i + "\t" + task);
                }
            }
            JOptionPane.showMessageDialog(null, "Tasks written successfully to file.");
        } catch (IOException e) {
        	//File error message!
            JOptionPane.showMessageDialog(null, "Error writing tasks to file.");
        }
    }
	

	private static void readT() {
		// TODO Auto-generated method stub
		String inFile;
		String input;
		inFile = JOptionPane.showInputDialog("Enter a name for the task file: ");
           try (Scanner fileScanner = new Scanner(new File(inFile))) {
                // Clears current task lists
                for (int i = 0; i < taskCounts.length ; i++) {
                    taskCounts[i] = 0;
                }

                // Read tasks from file
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    String[] parts = line.split("\t");
                    if (parts.length == 2) {
                        int priority = Integer.parseInt(parts[0]);
                        if (priority >= 0 && priority <= 4) {
                        	tLists[priority][taskCounts[priority]++] = parts[1];
                        }
                    }
                }

                JOptionPane.showMessageDialog(null, "Tasks read successfully from file.");
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "File not found.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error reading tasks from file.");
            }
        }
            
	

	private static void dispT() {
		// TODO Auto-generated method stub
		StringBuilder output = new StringBuilder();
		for (int i = 0; i < tLists.length; i++) {
            output.append("Priority ").append(i).append(":\n");
            if (taskCounts[i] == 0) {
                output.append("No tasks\n");
            } else {
                for (String task : tLists[i]) {
                    output.append(" - ").append(task).append("\n");
                }
            }
        }
        JOptionPane.showMessageDialog(null, output.toString());
	}

	private static void delT() {
		// TODO Auto-generated method stub
		String pInput = JOptionPane.showInputDialog("Enter the priority list to delete a task: ");

        int p;
        try {
            p = Integer.parseInt(pInput);
            if (p < 0 || p > 4) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid. Please enter a number between 0 and 4.");
            return;
        }

        if (taskCounts[p] == 0) {
            JOptionPane.showMessageDialog(null, "Empty List!");
            return;
        }

        StringBuilder taskDisplay = new StringBuilder("Tasks in priority list " + p + ":\n");
        for (int i = 0; i < taskCounts[p] ; i++) {
        	taskDisplay.append(i + 1).append(". ").append(tLists[p][i]).append("\n");
        }

        String taskIndexInput = JOptionPane.showInputDialog(taskDisplay.toString() + "Enter the number of the task to delete:");

        try {
            int tIndex = Integer.parseInt(taskIndexInput) - 1;
            if (tIndex < 0 || tIndex >= taskCounts[p]) {
                throw new IndexOutOfBoundsException();
            }
            for (int i = tIndex; i < taskCounts[p] - 1; i++) {
                tLists[p][i] = tLists[p][i + 1];
            }
            taskCounts[p]--;
            
            JOptionPane.showMessageDialog(null, "Task deleted.");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "Invalid task number.");
        }
    }

	

	private static void addT() {
		// TODO Auto-generated method stub
		String t;
		String pInput; 
		
		t = JOptionPane.showInputDialog("Enter the description of the task: ");
		pInput = JOptionPane.showInputDialog("Enter the priorty! 0 being the highest and 4 being the lowest!: ");
		
		int p;
		try {
			p = Integer.parseInt(pInput);
			if(p < 0 || p > 4) {
				throw new NumberFormatException();
		}
		}
		catch (NumberFormatException e) {
	          JOptionPane.showMessageDialog(null, "Invalid priority. Please enter a number between 0 and 4.");
	           return;
		}
		
		
		if (taskCounts[p] >= tLists[p].length) {
            JOptionPane.showMessageDialog(null, "Task list is full for priority " + p + ".");
            return;
        }

        tLists[p][taskCounts[p]++] = t;
		
		
		
		
		JOptionPane.showMessageDialog(null, "Task added successfully with priority " + p + ".");
		}
		
	}





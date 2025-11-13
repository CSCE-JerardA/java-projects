/*
 * Written by Jerard Austin with help from geeksforgeeks
 */

import java.io.*;
import java.util.Scanner;
import javax.swing.JOptionPane;


public class VGDatabase {
	
	//Creates a new linked list from my generic linked list
	private static LList<VG> gList = new LList<>();
	private static LList<VG> searchResults = new LList<>();

	// Main method
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Opening message
		JOptionPane.showConfirmDialog(null,"Hello! Welcome to " + "a Video Game" + " Database! ");
		
		//Variables
		String input;
		int inums;
		String vidFile = null;
		
		
		while(true) {input = JOptionPane.showInputDialog("Enter 1 to load the database. " + "Enter 2 to search the database. " + "Enter 3 to print the current results to the console. " + "Enter 4 to print results to file. " + "Enter 0 to quit!");
		inums = Integer.parseInt(input);
		
		
		// Depending on the user's input this will determine which method/step they want to jump to in the database
		switch(inums) {
			case 0:
				quit();
				return;
		
			case 1:
				loadMethod();
				break;
			case 2:
				searchDB();
				break;
			case 3:
				displayResults();
				break;
			case 4:
				displayinFile();
				break;
			default:
				error();
				break;
		}
		
		}
		
	}

	// Method that displays an error
	private static void error() {
		// TODO Auto-generated method stub
		JOptionPane.showConfirmDialog(null, "Error! Enter a number 0-4.");
	}

	// Method displaying the results within the file
	private static void displayinFile() {
		// TODO Auto-generated method stub
		try {
            String fileName = JOptionPane.showInputDialog("Enter the file name to save the results: ");
            FileWriter w = new FileWriter(fileName, true);  // Appends to the file
            for (int i = 0; i < searchResults.size(); i++) {
                VG game = searchResults.getData(i);
                w.write(game.vTitle + "\t" + game.vConsole + "\n");
            }
            w.close();
            JOptionPane.showMessageDialog(null, "Results saved to " + fileName);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An error occurred while writing to the file.");
        }
    }
	
	// Method displaying the results in console or in my case the Dialogue box
	private static void displayResults() {
		// TODO Auto-generated method stub
		if (searchResults.size() == 0) {
            JOptionPane.showMessageDialog(null, "There's nothing here!");
            return;
        }
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < searchResults.size(); i++) {
            VG game = searchResults.getData(i);
            output.append(game.vTitle).append("\t").append(game.vConsole).append("\n");
        }
        JOptionPane.showMessageDialog(null, output.toString());
	}
	// Method searching the database 
	private static void searchDB() {
		// TODO Auto-generated method stub
		searchResults.clear();
        String titleSearch = JOptionPane.showInputDialog("Enter the game title or '*' for all games: ");
        String consoleSearch = JOptionPane.showInputDialog("Enter the console name or '*' for all consoles: ");

        for (int i = 0; i < gList.size(); i++) {
            VG game = gList.getData(i);
            if ((titleSearch.equals("*") || game.vTitle.toLowerCase().contains(titleSearch.toLowerCase())) &&
                (consoleSearch.equals("*") || game.vConsole.toLowerCase().contains(consoleSearch.toLowerCase()))) {
                searchResults.addData(game);
            }
        }
        if (searchResults.size() == 0) {
            JOptionPane.showMessageDialog(null, "There's nothing here!");
        }
	}
	// Method loading the data into the file
	private static void loadMethod() {
		// TODO Auto-generated method stub
		String fileIn;
		fileIn = JOptionPane.showInputDialog("Enter a name for the file: ");
		
		try {
            Scanner fileScanner = new Scanner(new File(fileIn));
            while (fileScanner.hasNextLine()) {
                String[] data = fileScanner.nextLine().split("\t");
                if (data.length == 2) {
                    gList.addData(new VG(data[0], data[1]));
                }
            }
            fileScanner.close();
            JOptionPane.showMessageDialog(null, "the file is loaded :D !");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error: Could not load the file.");
        }
	}

	// Method quitting the program
	private static void quit() {
		// TODO Auto-generated method stub
		JOptionPane.showConfirmDialog(null, "Goodbye!!");
	}
}



// Creates a generic Linked List. I used my base from geeksforgeeks and just changed it a bit
class LList<T>{
	
	node<T> head;
	private int size = 0;
	
	public void addData(T data) {
		node<T> nNode = new node<>(data);
		if(head == null) {
			head = nNode;
		}
		
		else {
			node<T> temp = head;
			while(temp.next != null) {
				temp = temp.next;
			}
			temp.next = nNode;
		}
	}
	
	public T getData(int in) {
		if(in >= size || in < 0) {
			JOptionPane.showConfirmDialog(null, "Index out of bounds!");
		}
		
		node<T> temp = head;
		for(int i = 0; i < in; i++) {
			temp = temp.next;
		}
		return temp.data;
	}
	
	public void clear() {
		head = null;
		size = 0;
	}
	
	public int size() {
		return size;
	}
	
	private static class node<T>{
		T data;
		node<T> next;
		
		node(T data){
		this.data = data;
		this.next = null;
		
		}
	}
}







// Video Game constructor class
class VG{
	String vTitle;
	String vConsole;
	
	VG(String vTitle, String vConsole){
		this.vTitle = vTitle;
		this.vConsole = vConsole;
	}
	
	// Converts back to a string
	public String convertString() {
		return vTitle + "\t" + vConsole;
	}
	
	
}

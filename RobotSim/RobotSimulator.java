/*
 * Written by JC Austin with help from Professor JJ and geeksforgeeks
 * 
 */


import java.io.File;
import java.util.Scanner;
import java.io.*;
import javax.swing.JOptionPane;


class CmndQ<T> {
	
	private class Node{
		
		T data;
		Node nxt;
		
		Node(T data){
			this.data = data;
			this.nxt = null;
			
		}
	}
	
	private Node frnt;
	private Node rear;
	
	public CmndQ() {
		
		this.frnt = this.rear = null;
		
	}
	
	
	public void addElmnt(T data) {
		Node newN = new Node(data);
        if (rear == null) {
            frnt = rear = newN;
        } else {
            rear.nxt = newN;
            rear = newN;
        }
	}
	
	public T removeElmnt() {
		if(frnt == null)
			return null;
		T data = frnt.data;
		frnt = frnt.nxt;
		
		if(frnt == null)
			rear = null;
		return data;
	}
	
	
	public boolean Empty() {
        return frnt == null;
    }
	
	
	
}



public class RobotSimulator {


	
	public static void main(String []args) {
		//Main method
		String bfile;
		String cfile;
		
		JOptionPane.showMessageDialog(null, "Welcome to JC's Robot Simulator!");
		bfile = JOptionPane.showInputDialog("Enter a file to the board: ");
		
		cfile = JOptionPane.showInputDialog("Enter a file for the robot commands: ");
		
		char[][] board = loadB(bfile);
		if(board == null) {
			JOptionPane.showMessageDialog(null, "Failed to load the board");
			return;
		}
		
		CmndQ<String> commands = loadC(cfile);
		if(commands == null) {
			JOptionPane.showMessageDialog(null, "Failed to load the commands");
			return;
		}
		
		simRobot(board, commands);
		
		
	}

	private static void simRobot(char[][] board, CmndQ<String> commands) {
		// TODO Auto-generated method stub
		int x = 0, y = 0; //The starting position
        board[y][x] = 'R';

        while (!commands.Empty()) {
            String command = commands.removeElmnt();
            board[y][x] = '_'; // Clear previous position

            // Move based on the command
            switch (command) {
                case "Move Up": y--; break;
                case "Move Down": y++; break;
                case "Move Left": x--; break;
                case "Move Right": x++; break;
            }

            // Check for out of bounds or obstacles
            if (y < 0 || y >= board.length || x < 0 || x >= board[0].length || board[y][x] == 'X') {
                JOptionPane.showMessageDialog(null,"Crash");
                break;
            }

            // Update the robot's position on the board
            board[y][x] = 'R';
            display(board);
        }
	}

	private static void display(char[][] board) {
		// TODO Auto-generated method stub
		for (char[] row : board) {
            for (char cell : row) {
                JOptionPane.showConfirmDialog(null, cell + " ");
            }
            JOptionPane.showConfirmDialog(null,"");
        }
        JOptionPane.showConfirmDialog(null, "");
    
	}

	private static CmndQ<String> loadC(String cfile) {
		// TODO Auto-generated method stub
		CmndQ<String> commands = new CmndQ<>();
        try (BufferedReader br = new BufferedReader(new FileReader(cfile))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (Valid(line)) {
                    commands.addElmnt(line);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Error reading command file: " + e.getMessage());
            return null;
        }
		return commands;
	}

	private static boolean Valid(String commands) {
		// TODO Auto-generated method stub
		return commands.equals("Move Up") || commands.equals("Move Down") || commands.equals("Move Left") || commands.equals("Move Right");
	}

	private static char[][] loadB(String bfile) {
		// TODO Auto-generated method stub
		char[][] board = new char[10][10];
        try (BufferedReader br = new BufferedReader(new FileReader(bfile))) {
            for (int i = 0; i < 10; i++) {
                String line = br.readLine();
                for (int j = 0; j < 10; j++) {
                    board[i][j] = line.charAt(j);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading board file: " + " "+  e.getMessage());
            return null;
        }
		return board;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}

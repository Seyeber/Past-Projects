import java.util.*;
import java.io.*;

public class Main{
  
  public static void printGrid(char[][] grid){  //Function to Print 2D Array

      System.out.print("x "); for (int top_row = 0; top_row < grid[0].length - 1; top_row++) {System.out.print(top_row + " ");} System.out.print("9"); System.out.println(); //Makes top row of print out
        for (int i = 0; i < grid.length; i++){
          System.out.print(i + " "); //Makes side portion of print out
          for (int j = 0; j < grid[i].length - 1; j++){
            System.out.print(grid[i][j] + " "); //Might need to change " " to "" depending on formatting
          }
          System.out.print(grid[i][grid.length - 1]);
          System.out.println();
        }
        System.out.println();
      }
  
  public static void replaceAll(char[][] grid, char oldChar, char newChar){ //Function to replace all the characters that match
    for (int i = 0; i < grid.length; i++){
      for (int j = 0; j < grid[i].length; j++){
        if (grid[i][j] == oldChar){
          grid[i][j] = newChar;
        }
      }
    }
  }

  public static void expandChar(char[][] grid, char character){ //Function to expand (1 Up, 1 Down, 1 Left, 1 Right)
    
    ArrayList<String> coordinates = new ArrayList<String>(); //ArrayList To Store Coordinates
    for (int i = 0; i < grid.length; i++){  //For Loop accessing each element
      for (int j = 0; j < grid[i].length; j++){
        
        if (grid[i][j] == character){ //Adds the coordinates of the char to the ArrayList
          String testing = Integer.toString(i) + Integer.toString(j);
          coordinates.add(testing);
        }
      }
    }
    
    for (int a = 0; a < coordinates.size(); a++){ //Going through it and adding in the right places
      int i = Character.getNumericValue(coordinates.get(a).charAt(0)); //Row / horiz
      int j = Character.getNumericValue(coordinates.get(a).charAt(1)); //Column / vert

      if (i != 0){ //Checking if its on the top row
        grid[i - 1][j] = character;
      }
      if (i != grid.length - 1){ //Checking if its on the bottom row
        grid[i + 1][j] = character;
      }
      if (j != 0){ //Checking if its on left most wall
        grid[i][j - 1] = character;
      }
      if (j != grid[i].length - 1){ //Checking if its on right most wall
        grid[i][j + 1] = character;
      }
    }
  }
  
  public static void contractChar(char [][] grid, char character){ //Function to contract character (delete anything has one either under or up top, left or right)
    
    ArrayList<String> coordinates = new ArrayList<String>(); //ArrayList To Store Coordinates
    String adding;
    for (int i = 0; i < grid.length; i++){  //For Loop accessing each element
      for (int j = 0; j < grid[i].length; j++){
        
        if (grid[i][j] == character){ //Checking if it's on the character
          if (j != 0 && grid[i][j - 1] == '.'){ //Char to right and null to left
            adding = Integer.toString(i) + Integer.toString(j);
            coordinates.add(adding);
          }
          else if (j != grid[i].length - 1 && grid[i][j + 1] == '.'){ //Char to left, null on right
            adding = Integer.toString(i) + Integer.toString(j);
            coordinates.add(adding);
          }
          else if (i != 0 && grid[i - 1][j] == '.'){ //Char on bottom, null on top
            adding = Integer.toString(i) + Integer.toString(j);
            coordinates.add(adding);
          }
          else if (i != grid.length - 1 && grid[i + 1][j] == '.'){ //Char on top, null on bottom
            adding = Integer.toString(i) + Integer.toString(j);
            coordinates.add(adding);
          }
        }

      }
    }

    for (int a = 0; a < coordinates.size(); a++){ //Going through it and adding in the right places
      int i = Character.getNumericValue(coordinates.get(a).charAt(0)); //Row
      int j = Character.getNumericValue(coordinates.get(a).charAt(1)); //Column

      grid[i][j] = '.'; //Changes the values to 'null'
    }
  }

  public static void floodFill(char [][] grid, char oldChar, char newChar, int startX, int startY){ //Recursive function
    if (startX < 0 || startX >= grid.length || startY < 0 || startY >= grid[startX].length){
      return;
    }
    if (grid[startX][startY] == oldChar){ //If it equals old char
      grid[startX][startY] = newChar;

      floodFill(grid, oldChar, newChar, startX + 1, startY); //Up
      floodFill(grid, oldChar, newChar, startX - 1, startY); //Down
      floodFill(grid, oldChar, newChar, startX, startY + 1); //Left
      floodFill(grid, oldChar, newChar, startX, startY - 1); //Right
    }
  }
  public static void main(String args[]){
      
      BufferedReader br; //BufferedReader declaration
      String line; //String to Capture line from file
      char[][] grid = new char[10][10]; //Grid that image will be on
      Scanner scan = new Scanner(System.in); //Scanner
      String ans = ""; //Keeps the users input on what action they would like to do

      System.out.println("What file input would you like?"); //Might need to change later due to test cases
      
      String input = scan.nextLine(); //Taking Input for file

      try{ //Try Statement to add the Characters into Grid 2D Array
          br = new BufferedReader(new FileReader(new File(input)));
          int count = 0;

          while ((line = br.readLine()) != null){
              for (int i = 0; i < line.length(); i++){
                  grid[count][i] = line.charAt(i);
              }
              count++;
          }
      }
      catch(Exception e) {System.out.println(e.getMessage());} //Might need changing, idk

      while (!(ans.equals("quit"))){ //Keeps going until "quit" is typed
        printGrid(grid);  //Prints out grid
      
        System.out.println("What action would you like to do?");
        ans = scan.nextLine();

        //Beginning of If Statements
        
        if (ans.equals("replace")){ //If Answer is Replace
          char old, newChar; //Initialzing char variables
          
          System.out.println("What character would you like to replace?"); //Old Char thats being replaced
          old = scan.nextLine().charAt(0);

          System.out.println("What character would you like to replace it with?"); //New Char replacing Old Char
          newChar = scan.nextLine().charAt(0);

          replaceAll(grid, old, newChar); //Replacing them
        }

        if (ans.equals("expand")){ //If answer is Expand
          char character; //The character going to be expanded

          System.out.println("What character would you like to expands?"); //May need to change later to match test cases
          character = scan.nextLine().charAt(0);

          expandChar(grid, character);
        }

        if (ans.equals("contract")){
          char character; //The character going to be expanded

          System.out.println("What character would you like to contract?"); //May need to change later to match test cases
          character = scan.nextLine().charAt(0);

          contractChar(grid, character);
        }

        if (ans.equals("floodfill")){
          int vert, horiz; //Horizontal and Vertical Variables
          char oldChar, newChar; //Old character and new character

          System.out.println("What character would you like to floodfill?");
          oldChar = scan.nextLine().charAt(0);

          System.out.println("What character would you like to replace it with?");
          newChar = scan.nextLine().charAt(0);

          System.out.println("What is the vertical position?");
          vert = scan.nextInt();

          System.out.println("What is the horizontal position?");
          horiz = scan.nextInt();

          scan.nextLine();

          if (vert < 0 || vert > grid.length || horiz < 0 || horiz > grid[0].length || grid[vert][horiz] != oldChar){
            System.out.println("Those coordinates are out of bounds.");
            System.exit(0);
          }
          else{
            floodFill(grid, oldChar, newChar, vert, horiz);
          }
        }
      }
      
      scan.close(); //Closes scanner
  }
}
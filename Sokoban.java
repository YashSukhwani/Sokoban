//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Text-based Sokoban game.
// Files: Sokoban.java TestSokoban.java MyLevels.java
// Course: CS 200, Fall 2018
//
// Author: Yash Sukhwani
// Email: sukhwani@wisc.edu
// Lecturer's Name: Marc Renault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Abhinav Kaushik
// Partner Email: akaushik4@wisc.edu
// Lecturer's Name: Marc Renault
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates
// strangers, etc do. If you received no outside help from either type of
// source, then please explicitly indicate NONE.
//
// Persons: NONE
// Online Sources: NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.Scanner;
import java.util.Random;

public class Sokoban {

    public static final char EMPTY_CHAR = ' '; // Empty character
    public static final char BOX_CHAR = '='; // Box character
    public static final char WALL_CHAR = '#'; // Wall character
    public static final char WORKER_CHAR = '@'; // Worker character
    public static final char GOAL_CHAR = '.'; // Goal character
    public static final char BOX_GOAL_CHAR = '*'; // Box on a goal character
    public static final char WORK_GOAL_CHAR = '+'; // Worker on a goal character



    /**
     * Prompts the user for a value by displaying prompt. Note: This method should not add a new
     * line to the output of prompt.
     *
     * After prompting the user, the method will consume an entire line of input while reading an
     * int. If the value read is between min and max (inclusive), that value is returned. Otherwise,
     * "Invalid value." terminated by a new line is output to the console and the user is prompted
     * again.
     *
     * @param sc The Scanner instance to read from System.in.
     * @param prompt The name of the value for which the user is prompted.
     * @param min The minimum acceptable int value (inclusive).
     * @param max The maximum acceptable int value (inclusive).
     * @return Returns the value read from the user.
     */

    /*
     * This method first displays a prompt to the user asking which level they'd like to play. The
     * method evaluates the input first before returning it. It takes into account whether the input
     * either a level number that does'nt exist or is a character other than numbers, then it will
     * notify the user that the input is invalid and loops back, and asks the user again for the
     * input. This will continue indefinitely until the user enters a valid input.
     *
     * A special case is when the user enters '-1', then the method will return -1 as this is the
     * value which will make the main method select a random level
     */
    public static int promptInt(Scanner sc, String prompt, int min, int max) {

        int input; // This string will contain the user input and will be evaluated

        do { 
            System.out.print(prompt);
            if (sc.hasNextInt()) {
                input = sc.nextInt();
                sc.nextLine();
                if ((input >= min) && (input <= max)) {
                    return input;
                }
                else {
                    System.out.println("Invalid value.");
                }
                
            }

            else {
                System.out.println("Invalid value.");
                sc.nextLine();

            }
        } while (true); // loop executes indefinitely till return statement reached (valid input).
    }



    /**
     * Prompts the user for a char value by displaying prompt. Note: This method should not be a new
     * line to the output of prompt.
     *
     * After prompting the user, the method will read an entire line of input and return the first
     * non-whitespace character converted to lower case.
     *
     * @param sc The Scanner instance to read from System.in
     * @param prompt The user prompt.
     * @return Returns the first non-whitespace character (in lower case) read from the user. If
     *         there are no non-whitespace characters read, the null character is returned.
     */


    /*
     * This method helps the program to decide whether the user wants to play again. The method
     * first displays the prompt, and expects user input. This method then reads through the first
     * character of the input and returns that input value turned into lower case.
     * 
     * However, if the user inputs a blank line, then the method returns a null char.
     */
    public static char promptChar(Scanner sc, String prompt) {
        System.out.print(prompt); // prints the prompt parameter passed to this method
        String input = sc.nextLine(); // input is the user input that will be evaluated
        char inputChar = input.charAt(0);
        if (Character.isWhitespace(inputChar)) // If input is a blank line
        {
            return '\0'; // returns a null char
        } else {
            return Character.toLowerCase(inputChar);
        }
    }

    /**
     * Prompts the user for a string value by displaying prompt. Note: This method should not be a
     * new line to the output of prompt.
     *
     * After prompting the user, the method will read an entire line of input, remove any leading
     * and trailing whitespace, and return the input converted to lower case.
     *
     * @param sc The Scanner instance to read from System.in
     * @param prompt The user prompt.
     * @return Returns the string entered by the user, converted to lower case with leading and
     *         trailing whitespace removed.
     */


    /*
     * This method asks the user to enter a string by first displaying the prompt Then this method
     * removes leading and trailing whitespace and then converts the string into lower-case and then
     * returns the string.
     * 
     * This method is useful as it is the way the game will take input from the user about the moves
     * they want to do.
     */

    public static String promptString(Scanner sc, String prompt) {

        System.out.print(prompt); // prints the prompt parameter passed to this method
        String input = sc.nextLine();

        String userStr = input.trim().toLowerCase();
        return userStr;

    }

    /**
     * Initializes the game board to a given level. You can assume that the level at lvl has been
     * successfully verified by the checkLevel method and that pos is an array of length 2.
     *
     * 1 - The game board should be created row-by-row. a - For each row, copy the values from the
     * corresponding row in the 2-d array contained at index lvl in levels. b - When the worker is
     * located, it's position should be recorded in the pos parameter. 2 - For each goal described
     * in the array at index lvl of goals, convert the character at the goal coordinate to: -
     * Config.WORK_GOAL_CHAR if it contains the worker - Config.BOX_GOAL_CHAR if it contains a box -
     * Config.GOAL_CHAR otherwise
     * 
     * @param lvl The index of the level to load.
     * @param levels The array containing the levels.
     * @param goals The parallel array to levels, containing the goals for the levels.
     * @param pos The starting pos of the worker. A length 2 array, where index 0 is the row and
     *        index 1 is the column.
     * @return A two dimension array representing the initial configuration for the given level.
     */

    /*
     * This method initializes the game-board by combining all the different arrays. To do this, a
     * new array is created which is identical in dimensions to the levels array. The array is
     * copied and created cell by cell. Wherever there was a worker character located in the levels
     * array, its coordinates are recorded in the pos array.
     * 
     * The goals array is a parallel array which contains the coordinates of the goals. This method
     * also checks whether any given goal char lies in the same position as a worker or box char, if
     * so - it initializes them to a either worker goal char or a box goal char.
     */


    public static char[][] initBoard(int lvl, char[][][] levels, int[][] goals, int[] pos) {

        /*
         * gives the new array the same number rows as the original levels array Since the original
         * array is ragged(each row contains different number of columns) the column length will be
         * defined using another for loop specific to that row
         */
        char[][] arrayModded = new char[levels[lvl].length][]; // defines total rows in arrayModed

        for (int i = 0; i < levels[lvl].length; ++i) { // loop goes through all rows of levels[]

            arrayModded[i] = new char[levels[lvl][i].length]; // defines total columns in arrayModed

            for (int j = 0; j < levels[lvl][i].length; ++j) { // goes through each column in the row

                if (levels[lvl][i][j] == Config.WORKER_CHAR) {
                    pos[0] = i;
                    pos[1] = j;
                } // recording the pos of worker in the pos array


                arrayModded[i][j] = levels[lvl][i][j]; // copies each levels element into arrayModed

                /*
                 * Going through each cell using two for loops and then checking for the goal char
                 * having the same location as a worker or box char
                 */
                for (int k = 0; k < goals[lvl].length; k += 2) { // goes through goals array

                    if (goals[lvl][k] == i && goals[lvl][k + 1] == j) { 

                        if (levels[lvl][i][j] == Config.WORKER_CHAR) {
                            arrayModded[i][j] = Config.WORK_GOAL_CHAR;
                        } else if (levels[lvl][i][j] == Config.BOX_CHAR) {
                            arrayModded[i][j] = Config.BOX_GOAL_CHAR;
                        } else {
                            arrayModded[i][j] = Config.GOAL_CHAR;
                        } // above nested if toggled the goal char using worker and box positions

                    } // this if checked whether current cell in levels contained a goal char
                }

            }
        }

        return arrayModded;

    }

    /**
     * Prints out the game board.
     * 
     * 1 - Since the game board does not contain the outer walls, print out a sequence of
     * Config.WALL_CHAR with a length equal to that of the first row of board, plus the outer wall
     * to the left and the right. 2 - For each row in board, print out a Config.WALL_CHAR, followed
     * by the contents of the row, followed by a Config.WALL_CHAR. 3 - Finally, print out a sequence
     * of Config.WALL_CHAR with a length equal to that of the last row of board, plus the outer wall
     * to the left and the right.
     *
     * Note: each row printed out should be terminated by a new line.
     *
     * @param board The board to print.
     */

    /*
     * This method prints the board once it has been initialized This method also prints a series a
     * wall char surrounding the game board
     * 
     */
    public static void printBoard(char[][] board) {

        int i; // will be used as a counter
        int j; // will be used as a counter


        System.out.print(Config.WALL_CHAR); // prints the leftmost wall of the board


        for (i = 0; i <= board[0].length; ++i) {
            System.out.print(Config.WALL_CHAR); // prints the topmost wall of the board
        }
        System.out.println();


        
        for (i = 0; i < board.length; ++i) {

            System.out.print(Config.WALL_CHAR); // 

            for (j = 0; j < board[i].length; ++j) {
                System.out.print(board[i][j]); // prints every char from board row as it is
            }
            System.out.println(Config.WALL_CHAR); // prints the rightmost wall of the board
        }

        
        System.out.print(Config.WALL_CHAR);

        for (i = 0; i <= board[board.length - 1].length; ++i) {
            System.out.print(Config.WALL_CHAR); // prints the bottom wall of the board 
        }
        System.out.println();


    }


    /**
     * Runs a given level through some basic sanity checks.
     *
     * This method performs the following tests (in order): 1 - lvl >= 0 2 - lvl is a valid index in
     * levels, that the 2-d array at index lvl exists and that it contains at least 1 row. 3 - lvl
     * is a valid index in goals, the 1-d array at index lvl exists and that it contains an even
     * number of cells. 4 - the number of boxes is more than 0. 5 - the number of boxes equals the
     * number of goals. 6 - the coordinate of each goal is valid for the given lvl and does not
     * correspond to a wall cell. 7 - the number of workers is exactly 1. 8 - check for duplicate
     * goals.
     *
     * @param lvl The index of the level to load.
     * @param levels The array containing the levels.
     * @param goals The parallel array to levels, containing the goals for the levels.
     * @return 1 if all tests pass. Otherwise if test: - Test 1 fails: 0 - Test 2 fails: -1 - Test 3
     *         fails: -2 - Test 4 fails: -3 - Test 5 fails: -4 - Test 6 fails: -5 - Test 7 fails: -6
     *         - Test 8 fails: -7
     * 
     */
    /*
     * This level checks whether the level chosen by the user to play can be won and don't contain
     * any errors It examines different aspects of the board that should be in order for the game
     * board to pass this check.
     */
    public static int checkLevel(int lvl, char[][][] levels, int[][] goals) {

        // Test 1
        if (lvl < 0) {
            return 0; // returns 0 if level entered is less than zero
        }

     // Test 2
        if (!(lvl < levels.length) && !(levels[lvl].length > 0)) {
            return -1; // method returns -1 if level is defined and contains at least one row
        }

     // Test 3
        if (!(lvl < goals.length) || !(goals[lvl].length % 2 == 0)) {
            return -2; // returns -2 if goals has odd number of cells or levels in invalid in goal
        }
        
     // Test 4
        int count = 0; // to count number of boxes in the levels array
        
        for (int i = 0; i < levels[lvl].length; i++) {
            for (int j = 0; j < levels[lvl][i].length; j++) { // goes through all elements of levels
                if (levels[lvl][i][j] == Config.BOX_CHAR) {
                    count++; 
                }
            }
        }

        if (count == 0) {
            return -3; // returns -3 if no box characters present in the level
        }
        
     // Test 5
        if (!(goals[lvl].length / 2 == count)) {
            return -4; // returns -4 if different number of goals and boxes in the level
        }

     // Test 6
        for (int i = 0; i < goals[lvl].length; i++) {
            if (goals[lvl][i] < 0) {
                return -5; // returns -5 if goal coordinates in goals array are invalid
            }
        }

        for (int i = 0; i < goals[lvl].length; i += 2) {
            if (levels[lvl][goals[lvl][i]][goals[lvl][i + 1]] == Config.WALL_CHAR) {
                return -5; // returns -5 if a goal position overlaps with a wall character in levels
            }
        }

     // Test 7
        int countWorker = 0;

        for (int i = 0; i < levels[lvl].length; i++) {
            for (int j = 0; j < levels[lvl][i].length; j++) {
                if (levels[lvl][i][j] == Config.WORKER_CHAR) {
                    countWorker++;
                }
            }
        }

        if (countWorker != 1) {
            return -6;
        }

        // Test 8 
        for (int i = 0; i < goals[lvl].length - 1; i += 2) { // first scan through lvl in goals 
            for (int j = i + 2; j < goals[lvl].length - 1; j += 2) { // second scan through goals
                if (goals[lvl][i] == goals[lvl][j] && goals[lvl][i + 1] == goals[lvl][j + 1]) {
                    return -7; // returns -7 if both scans yield the same goal position at same time
                }
            }
        }

        return 1;
    }



    /**
     * This method builds an int array with 2 cells, representing a movement vector, based on the
     * String parameter.
     *
     * The rules to create the length 2 int array are as follows: - The 1st character of the String
     * represents the direction. - The remaining characters (if there are any) are interpreted as
     * integer and represent the magnitude or the number of steps to take.
     *
     * The cell at index 0 represents movement in the rows. Hence, a negative value represents
     * moving up the rows and a positive value represents moving down the rows.
     *
     * The cell at index 1 represents movement in the columns. Hence, a negative value represents
     * moving left in the columns and a positive value represents moving right in the columns.
     *
     * If the first character of moveStr does not match on of Config.UP_CHAR, Config.DOWN_CHAR,
     * Config.LEFT_CHAR, or Config.RIGHT_CHAR, then return an array with 0 in both cells.
     *
     * If there are no characters after the first character of moveStr or the characters cannot be
     * interpreted as an int, set the magnitude of the movement to 1.
     *
     * Hint: Use Scanner to parse the magnitude.
     *
     * Some examples: - If the parameter moveStr is "81": An array {-1, 0} would represent moving up
     * by one character. - If the parameter moveStr is "65": An array {0, 5} would represent moving
     * right by 5 characters.
     *
     * @param moveStr The string to parse.
     * @return The calculated movement vector as a 2 cell int array.
     */
    public static int[] calcDelta(String moveStr) {
        int[] moveVector = new int[2];
        int magnitude;
        Scanner sc = new Scanner(moveStr.substring(1));// taking the first val of the string moveStr

        if (moveStr.charAt(0) != Config.UP_CHAR && moveStr.charAt(0) != Config.RIGHT_CHAR 
            && moveStr.charAt(0) != Config.LEFT_CHAR && moveStr.charAt(0) != Config.DOWN_CHAR) {
            moveVector[0] = 0;
            moveVector[1] = 0;
            return moveVector; // returns [0, 0] if first character of moveStr is an invalid direction
        }

        if (sc.hasNextInt()) { // checking whether the value entered is an integer
            magnitude = sc.nextInt(); 

        } else {
            magnitude = 1; // magnitude set to 1 if magnitude cannot be interpreted from user input
        }
        if (moveStr.charAt(0) == Config.DOWN_CHAR) {
            moveVector[0] = 1 * magnitude;
            moveVector[1] = 0;

        }
        if (moveStr.charAt(0) == Config.UP_CHAR) {
            moveVector[0] = -1 * magnitude;
            moveVector[1] = 0;

        }
        if (moveStr.charAt(0) == Config.RIGHT_CHAR) {
            moveVector[1] = 1 * magnitude;
            moveVector[0] = 0;

        }
        if (moveStr.charAt(0) == Config.LEFT_CHAR) {
            moveVector[1] = -1 * magnitude;
            moveVector[0] = 0;

        }
        sc.close(); // close Scanner

        return moveVector; // Return the calculated value of moveVector
    }

    /**
     * This method checks that moving from one position to another position is a valid move.
     *
     * To validate the move, the method should (in order) check: 1 - that pos is valid. 2 - that the
     * character at pos in board is in the valid array. 3 - that the delta is valid. 4 - that the
     * new position is valid and not a wall character. 5 - that the new position is not a box
     * character For what makes each test invalid, see the return details below.
     *
     * @param board The current board.
     * @param pos The position to move from. A length 2 array, where index 0 is the row and index 1
     *        is the column.
     * @param delta The move distance. A length 2 array, where index 0 is the change in row and
     *        index 1 is the change in column.
     * @param valid A character array containing the valid characters for the cell at pos.
     * @return 1 if the move is valid. Otherwise: -1 : if pos is null, not length 2, or not on the
     *         board. -2 : if the character at pos is not valid (not in the valid array). -3 : if
     *         delta is null or not length 2. -4 : if the new position is off the board or a wall
     *         character -5 : if the new position is a box character
     */
    public static int checkDelta(char[][] board, int[] pos, int[] delta, char[] valid) {
        if (pos == null || pos.length != 2) { 
                                              
            return -1; // returns -1 if position is null or not length 2
        }
        if (board.length - 1 < pos[0] || pos[0] < 0 || pos[1] < 0) {
            return -1;
        }
        
        int i = 0; // i will be used as a counter variable
        
        for (int k = 0; k < valid.length; ++k) {
            if (board[pos[0]][pos[1]] == valid[k]) {
                i = i + 1; // i increments by 1 for every valid position on the board
            }

        }
        if (i == 0) { 
            return -2; // method returns -2 if no board had no valid position
        }
        
        i = 0;
        
        if (delta == null || delta.length != 2) {
            return -3; // returns -3 if delta is null or not length 2
        }
        
        int[] newPos = new int[2];
        
        for (int j = 0; j < pos.length; ++j) {
            newPos[j] = pos[j] + delta[j]; // initializes each newPos element using pos and delta
        }
        
        if (board.length - 1 < newPos[0] || newPos[0] < 0 || newPos[1] < 0) {
            return -4;
        } else if (board[newPos[0]].length - 1 < newPos[1]
            || board[newPos[0]][newPos[1]] == Config.WALL_CHAR) {
            return -4; // -4 is returned if the new position calculated is not on board or a wall
        }

        if (board[newPos[0]][newPos[1]] == Config.BOX_CHAR
            || board[newPos[0]][newPos[1]] == Config.BOX_GOAL_CHAR) {
            return -5; // returns -5 if the new position is a form of a box character
        }
        return 1; // returns -1 if none of the above conditions were true
    }

    /**
     * Changes a character on the board to one of two characters (opt1 or opt2), depending on the
     * value of the cell.
     *
     * Check the cell at position pos. If the character is val, change it to opt1. Otherwise, change
     * it to opt2.
     *
     * @param board The current board.
     * @param pos The position to change. A length 2 array, where index 0 is the row and index 1 is
     *        the column.
     * @param val The value to check for in the board.
     * @param opt1 The character to change to if the value is val.
     * @param opt2 The character to change to if the value is not val.
     */

    public static void togglePos(char[][] board, int[] pos, char val, char opt1, char opt2) {

        if (board[pos[0]][pos[1]] == val) {
            board[pos[0]][pos[1]] = opt1;
        } else {
            board[pos[0]][pos[1]] = opt2;
        }

    }

    /**
     * Moves a box on the board.
     *
     * Step 1: Use your checkDelta method to check that the move is valid. Recall that there are 2
     * characters that can represent a box. Step 2: Use your togglePos method to correctly change
     * the character at the new position to the appropriate box character. Step 3: Again use your
     * togglePos method to correctly change the character at pos to the the appropriate character
     * without a box.
     *
     * @param board The current board.
     * @param pos The position to change. A length 2 array, where index 0 is the row and index 1 is
     *        the column.
     * @param delta The move distance. A length 2 array, where index 0 is the change in row and
     *        index 1 is the change in column.
     * @return The return value of checkDelta if less than 1. Otherwise 1.
     */
    public static int shiftBox(char[][] board, int[] pos, int[] delta) {
        char[] valid = {Config.BOX_CHAR, Config.BOX_GOAL_CHAR};

        if (Sokoban.checkDelta(board, pos, delta, valid) < 0) {
            return Sokoban.checkDelta(board, pos, delta, valid); 
        }

        int[] newPos = new int[2];

        for (int i = 0; i < pos.length; ++i) {
            newPos[i] = pos[i] + delta[i];
        }
        
        // toggling the new position of the box
        Sokoban.togglePos(board, newPos, Config.GOAL_CHAR, Config.BOX_GOAL_CHAR, Config.BOX_CHAR);

        // toggling the previous position of the box
        Sokoban.togglePos(board, pos, Config.BOX_GOAL_CHAR, Config.GOAL_CHAR, Config.EMPTY_CHAR);
        return 1;
    }

    /**
     * Processes a move of the worker step-by-step.
     *
     * Go through the delta step-by-step, calling doMove for each step. That is, if the delta is {0,
     * -3}, your method should call doMove three times with an argument of {0, -1} for the delta
     * parameter of doMove. Or, if the delta is {6, 0}, it would call the doMove six times with an
     * argument of {1, 0} for the delta parameter of the doMove method.
     *
     * During the processing of the move, if ever a call to doMove returns a value less than 1, your
     * method should stop processing and return that value.
     *
     * Note: You can assume that one of the cells of delta will be 0.
     *
     * @param board The current board.
     * @param pos The position to change. A length 2 array, where index 0 is the row and index 1 is
     *        the column.
     * @param delta The move distance. A length 2 array, where index 0 is the change in row and
     *        index 1 is the change in column.
     * @return If both of the cells of delta are 0, return 0. If the call to doMove returns a value
     *         less than 1, return that value. Otherwise, return 1.
     */
    public static int processMove(char[][] board, int[] pos, int[] delta) {
        int resDoMove = 0;
        int newMove[] = new int[2];
        int steps = 0; // this will decide how many times doMove will be executed

        if (delta[0] == 0 && delta[1] != 0) {
            steps = Math.abs(delta[1]); // steps initialized to the left/right magnitude
            
            if (delta[1] < 0) {
                newMove = new int[] {0, -1};
            } else {
                newMove = new int[] {0, 1};
            }
            
            for (int j = 0; j < steps; j++) {
                resDoMove = Sokoban.doMove(board, pos, newMove);
                if (resDoMove < 0) {
                    return resDoMove;
                }
            }
        } else if (delta[0] != 0 && delta[1] == 0) {
            steps = Math.abs(delta[0]); // steps initialized to the up/down magnitude
            if (delta[0] < 0) {
                newMove = new int[] {-1, 0};
            } else {
                newMove = new int[] {1, 0};
            }
            for (int i = 0; i < steps; i++) { // executes doMove step number of times
                resDoMove = Sokoban.doMove(board, pos, newMove);
                if (resDoMove < 0) {
                    return resDoMove;
                }
            }
        }
        
        if (delta[0] == 0 && delta[1] == 0) {
            return 0;
        }

        return 1;
    }

    /**
     * Moves the worker on the board.
     *
     * Step 1: Use your checkDelta method to check that the move is valid. Recall that there are 2
     * characters that can represent the worker. Step 2: If checkDelta returns -5, use your shiftBox
     * method to move the box by delta before moving the worker. Step 3: Use your togglePos method
     * to correctly change the character at the new position to the appropriate worker character.
     * Step 4: Again use your togglePos method to correctly change the character at pos to the the
     * appropriate character without a worker. Step 5: Update the position of the worker in pos.
     *
     * @param board The current board.
     * @param pos The position to change. A length 2 array, where index 0 is the row and index 1 is
     *        the column.
     * @param delta The move distance. A length 2 array, where index 0 is the change in row and
     *        index 1 is the change in column.
     * @return If checkDelta returns a value less than 1 that is not -5, return that value. If
     *         checkDelta returns -5 and shiftBox returns a value less than 0, return 0. Otherwise,
     *         return 1.
     */
    public static int doMove(char[][] board, int[] pos, int[] delta) {

        char[] valid = {Config.WORKER_CHAR, Config.WORK_GOAL_CHAR};
        int[] newPos = new int[2];

        for (int i = 0; i < pos.length; ++i) {
            newPos[i] = pos[i] + delta[i]; // each element in newPos initialized using delta and pos
        }

        if (Sokoban.checkDelta(board, pos, delta, valid) == -5) {
            if (Sokoban.shiftBox(board, newPos, delta) < 0) {
                return 0;
            }
        } else if (checkDelta(board, pos, delta, valid) < 0) {
            return checkDelta(board, pos, delta, valid);
        }

        Sokoban.togglePos(board, newPos, Config.EMPTY_CHAR, Config.WORKER_CHAR,
            Config.WORK_GOAL_CHAR);
        Sokoban.togglePos(board, pos, Config.WORK_GOAL_CHAR, Config.GOAL_CHAR, Config.EMPTY_CHAR);

        pos[0] = newPos[0];
        pos[1] = newPos[1];

        return 1;
    }

    /**
     * Checks all the cells in board and ensures that there are no goals that are not covered by
     * boxes.
     *
     * @param board The current board.
     * @return true if all the goals are covered by boxes. Otherwise, false.
     */
    public static boolean checkWin(char[][] board) {
        int i = 0;

        for (i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == Config.GOAL_CHAR) {
                    return false; // false returned if a goal character exists on the board
                }
            }
        }

        return true;
    }

    /**
     * This is the main method for the Sokoban game. It consists of the main game and play again
     * loops with calls to the various supporting methods. The details of the main method for each
     * milestone can be found in the BP1 - Sokoban write-up on the CS 200 webpage:
     * https://cs200-www.cs.wisc.edu/wp/programs/
     *
     * For all milestones, you will need to create a Scanner object to read from System.in that you
     * will pass to the helper methods.
     *
     * For milestone 3, you will need to create a Random object using Config.SEED as the seed. This
     * object should be create at the beginning of the method, outside of any loops.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {

        Random rand = new Random(Config.SEED); // Sets the specific seed for the random object

        int[] pos = new int[2];
        int maxLvl = Config.LEVELS.length - 1;

        System.out.println("Welcome to Sokoban!");
        Scanner sc = new Scanner(System.in);

        do {
            int lvl = promptInt(sc, "Choose a level between 0 and " + maxLvl + ": ", -1, maxLvl);

            if (lvl == -1) {
                lvl = rand.nextInt(maxLvl + 1);
            }

            int resCheckLevel = checkLevel(lvl, Config.LEVELS, Config.GOALS); 

            if (resCheckLevel == 1) {
                System.out.println("Sokoban Level " + lvl);

                char board[][] = initBoard(lvl, Config.LEVELS, Config.GOALS, pos);
                boolean loopControl = true; // Stays true until checkWin is satisfied
                int numMoves = 1; // Number of moves executed, incremented later
                String resPromptStr; // Stores moves input by user

                do {

                    printBoard(board);
                    numMoves = numMoves + 1;
                    resPromptStr = Sokoban.promptString(sc, ": ");

                    if (resPromptStr.length() == 0) {
                        loopControl = true;
                    } else if (resPromptStr.charAt(0) == Config.QUIT_CHAR) {
                        loopControl = false;
                    } else {
                        Sokoban.processMove(board, pos, Sokoban.calcDelta(resPromptStr));
                        if (Sokoban.checkWin(board)) {

                            System.out
                                .println("Congratulations! You won in " + numMoves + " moves!");
                            Sokoban.printBoard(board);

                            loopControl = false;

                        }
                    }
                } while (loopControl);
                Sokoban.calcDelta(resPromptStr);

            }
            
            else if (resCheckLevel == 0) {
                System.out.println("Level lvl must be 0 or greater!");
            } else if (resCheckLevel == -1) {
                System.out.println("Error with Config.LEVELS");
            } else if (resCheckLevel == -2) {
                System.out.println("Error with Config.GOALS");
            } else if (resCheckLevel == -3) {
                System.out.println("Level lvl does not contain any boxes.");
            } else if (resCheckLevel == -4) {
                System.out.println("Level lvl does not have the same number of boxes as goals.");
            } else if (resCheckLevel == -5) {
                System.out.println("Level lvl has a goal location that is a wall.");
            } else if (resCheckLevel == -6) {
                System.out.println("Level lvl has 0 or more than 1 worker(s).");
            } else if (resCheckLevel == -7) {
                System.out.println("Level contains duplicate goals.");
            } else {
                System.out.print("Unknown Error");
            }

        } while (promptChar(sc, "Play again? (y/n) ") == 'y');
        System.out.println("Thanks for playing!");
        sc.close();
    }
}

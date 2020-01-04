//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Text-based Sokoban game.
// Files:           Sokoban.java  TestSokoban.java  MyLevels.java
// Course:          CS 200, Fall 2018
//
// Author:          Yash Sukhwani
// Email:           sukhwani@wisc.edu
// Lecturer's Name: Marc Renault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    Abhinav Kaushik
// Partner Email:   akaushik4@wisc.edu
// Lecturer's Name: Marc Renault
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates 
// strangers, etc do.  If you received no outside help from either type of 
// source, then please explicitly indicate NONE.
//
// Persons:         NONE
// Online Sources:  NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////


/**
 * This file contains testing methods for the Sokoban project. These methods are intended to 
 * provide an example of a way to incrementally test your code, and to provide example method calls
 * for the Sokoban methods
 *
 * Toward these objectives, the expectation is that part of the grade for the Sokoban project is 
 * to write some tests and write header comments summarizing the tests that have been written. 
 * Specific places are noted with FIXME but add any other comments you feel would be useful.
 */

import java.util.Arrays;

/**
 * This class contains a few methods for testing methods in the Sokoban
 * class as they are developed. These methods are all private as they are only
 * intended for use within this class.
 * 
 * @author Marc Renault
 * @author FIXME add your name here when you add test
 *
 */
public class TestSokoban {
    
    public static final char EMPTY_CHAR = ' '; // Empty character
    public static final char BOX_CHAR = '='; // Box character
    public static final char WALL_CHAR = '#'; // Wall character
    public static final char WORKER_CHAR = '@'; // Worker character
    public static final char GOAL_CHAR = '.'; // Goal character
    public static final char BOX_GOAL_CHAR = '*'; // Box on a goal character
    public static final char WORK_GOAL_CHAR = '+'; // Worker on a goal character
    
    
    

    /**
     * This is the main method that runs the various tests. Uncomment the tests when
     * you are ready for them to run.
     * 
     * @param args (unused)
     */
    public static void main(String[] args) {
        // Milestone 1
        testCheckLevel();
        // Milestone 2
        testInitBoard();
        testCheckWin();
        testCalcDelta();
        testCheckDelta();
        // Milestone 3
        //testTogglePos();
        //testShiftBox();
        //testDoMove();
        //testProcessMove();
    }

        private static void testCheckLevel() {

            int numTests = 5;
            int passed = numTests;
            int res;

            // Test 1

            if ((res = Sokoban.checkLevel(0, Config.LEVELS, Config.GOALS)) == 0) {

            System.out.println(

            "FAILED: Sokoban.checkLevel Test 1. Expected 1, but value returned " + res);

            passed--;

            }


            // Test 2

            char[][][] lvl = new char[2][][];

            if ((res = Sokoban.checkLevel(1, Config.LEVELS, Config.GOALS)) == -1) {

            System.out.println(

            "FAILED: Sokoban.checkLevel Test 2. Expected 1, but value returned " + res);

            passed--;

            }


            // Test 3

            if ((res = Sokoban.checkLevel(1, Config.LEVELS, Config.GOALS)) == -2) {

            System.out.println(

            "FAILED: Sokoban.checkLevel Test 3. Expected 1, but value returned " + res);

            passed--;

            }


            // Test 4

            if ((res = Sokoban.checkLevel(1, Config.LEVELS, Config.GOALS)) == -3) {

            System.out.println(

            "FAILED: Sokoban.checkLevel Test 3. Expected 1, but value returned " + res);

            passed--;

            }

             

            //test 6

            if ((res = Sokoban.checkLevel(1, Config.LEVELS, Config.GOALS)) == -5) {

            System.out.println(

            "FAILED: Sokoban.checkLevel Test 6. Expected 1, but value returned " + res);

            passed--;

            }
        

        //FIXME Add more tests
        
        System.out.println("testCheckLevel: Passed " + passed + " of " + numTests + " tests.");
    }

    /**
     * Returns true if the arrays are the same size and have the same contents.
     */
    private static boolean compBoards(char[][] a, char[][] b) {
        if(a == null || b == null)
            return false;
        if(a.length != b.length)
            return false;
        for(int i = 0; i < a.length; i++) {
            if(!Arrays.equals(a[i], b[i])) {
                return false;
            }
        }
        return true;
    }
    
    private static void testInitBoard() {
        int numTests = 3;
        int passed = numTests;

        //Test 1
        int[] pTest1 = new int[2];
        char[][] bTest1 = Sokoban.initBoard(0, Config.LEVELS, Config.GOALS, pTest1);
        if(!Arrays.equals(pTest1, new int[]{4, 4})) {
            System.out.println("FAILED: Sokoban.initBoard Test 1. Expected initial position: {4, 4} , but value after call " + Arrays.toString(pTest1));
            passed--;
        }
        char[][] bCompTest1 = new char[][]{{Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
                                           {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
                                           {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR},
                                           {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
                                           {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WORKER_CHAR}};
        if(!compBoards(bTest1, bCompTest1)){
            System.out.println("FAILED: Sokoban.initBoard Test 1. Board not as expected!");
            System.out.println("Generated:");
            Sokoban.printBoard(bTest1);
            System.out.println("Expected:");
            Sokoban.printBoard(bCompTest1);            
            //passed--;
        }
        //End of Test 1
        
        //Test 2
        int[] pTest2 = new int[2];
        char[][] bTest2 = Sokoban.initBoard(0, Config.LEVELS, Config.GOALS, pTest2);
        if(!Arrays.equals(pTest2, new int[]{4, 4})) {
            System.out.println("FAILED: Sokoban.initBoard Test 2. Expected initial position: {3, 4} , but value after call " + Arrays.toString(pTest2));
            passed--;
        }
        char[][] bCompTest2 = new char[][]{{Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
                                           {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
                                           {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR},
                                           {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
                                           {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WORKER_CHAR}};
        if(!compBoards(bTest2, bCompTest2)){
            System.out.println("FAILED: Sokoban.initBoard Test 2. Board not as expected!");
            System.out.println("Generated:");
            Sokoban.printBoard(bTest2);
            System.out.println("Expected:");
            Sokoban.printBoard(bCompTest2);            
            //passed--;
        }
        //End of Test 2
        
        //Test 3
        int[] pTest3 = new int[2];
        char[][] bTest3 = Sokoban.initBoard(0, Config.LEVELS, Config.GOALS, pTest3);
        if(!Arrays.equals(pTest1, new int[]{4, 4})) {
            System.out.println("FAILED: Sokoban.initBoard Test 3. Expected initial position: {2, 1} , but value after call " + Arrays.toString(pTest3));
            passed--;
        }
        char[][] bCompTest3 = new char[][]{{Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
                                           {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
                                           {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR},
                                           {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
                                           {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WORKER_CHAR}};
        if(!compBoards(bTest3, bCompTest3)){
            System.out.println("FAILED: Sokoban.initBoard Test 3. Board not as expected!");
            System.out.println("Generated:");
            Sokoban.printBoard(bTest3);
            System.out.println("Expected:");
            Sokoban.printBoard(bCompTest3);            
            //passed--;
        }
        //End of Test 3
     
        
        
        
        //FIXME Add more tests
        
        System.out.println("testInitBoard: Passed " + passed + " of " + numTests + " tests.");
    }
    
    private static void testCheckWin() {
        //FIXME
        
        int numTests = 1;
        int passed = numTests;
        
        boolean res;
        
        int i = 0;
        int j = 0;
        
        int x = Config.GOALS[0][0];
        int y = Config.GOALS[0][1];
        
        if (Config.LEVELS[0][x][y] == Config.BOX_GOAL_CHAR)
        {
            res = true;
        }
        else
        {
            res = false;
        }
        
        if (res != Sokoban.checkWin(Config.LEVELS[0]))
        {
            System.out.println("FAILED: Sokoban.checkWin Test 1. Expected true, but value returned false.");
            passed--;
        }
        
        System.out.println("testCheckWin: Passed " + passed + " of " + numTests + " tests.");
        
        
        
}
        
    
    
    private static void testCheckDelta() {
        //FIXME
        
        int numTests = 1;
        int passed = numTests;
        
        int res;
        
        int [] pos = {0, 0, 0};
        char [] valid = {Config.EMPTY_CHAR, Config.WORKER_CHAR, Config.WORK_GOAL_CHAR, Config.GOAL_CHAR};
        res = Sokoban.checkDelta(Sokoban.initBoard(0, Config.LEVELS, Config.GOALS, pos), pos, Sokoban.calcDelta("81"), valid);
        
        if (res != -1)
        {
            System.out.println("FAILED: Sokoban.checkDelta Test 1. Expected -1, but value returned " + res);
            passed--;
        }
        
        
        
        System.out.println("testCheckDelta: Passed " + passed + " of " + numTests + " tests.");
    }
    
    private static void testCalcDelta() {
        //FIXME
        int numTests = 1;
        int passed = numTests;
        
        int [] res = new int [2];
        int [] expectedRes = {0, 0};
        
        int i = 0;

        
        res = Sokoban.calcDelta("abcd");
        
        if (!(Arrays.equals(res, expectedRes)) )
        {
            System.out.println("FAILED: Sokoban.calcDelta Test 1. Expected [0, 0], but value returned [");
            for (i = 0; i < res.length; i++)
            {
                if (i != res.length - 1)
                {
                    System.out.print(res[i] + ", ");
                }
                else
                {
                    System.out.print(res[i] + "]");
                }
            }
            passed--;
        }
        
        System.out.println("testcalcDelta: Passed " + passed + " of " + numTests + " tests.");
        
        
    }
    
    private static void testTogglePos() {
        //FIXME
    }

    private static void testShiftBox() {
        //FIXME
    }

    private static void testDoMove() {
        //FIXME
    }

    private static void testProcessMove() {
        //FIXME
    }    

}
package com.duckrace.app;

import com.duckrace.Board;
import com.duckrace.Reward;

import java.util.Locale;
import java.util.Scanner;

/*
 * Application "controller"
 * Sets up the system classes, orchestrates the overall flow of the application
 * prompts user for inputs and "forwards" those inputs into the system (back end)
 */
public class DuckRaceApp {
    private final Scanner scanner = new Scanner(System.in); // read inputs from console
    private final Board board = new Board();

    public void execute() {
        welcome();
        showBoard();
        int id = promptForId();
        Reward reward = promptForReward();
        updateBoard(id, reward);  // OR you could just say board.update(id, reward)
        showBoard();
    }

    private void updateBoard(int id, Reward reward) {
        board.update(id, reward);
    }

    private Reward promptForReward() {
        Reward reward = null;

        boolean validInput = false;
        while(!validInput){
            System.out.print("Please enter [D]ebit card or [P]rizes: ");
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.matches("D|P")) {
                validInput = true;
                reward = ("D".equals(input)) ? Reward.DEBIT_CARD : Reward.PRIZES;

                /*
                validInput = true;
                if("D".equals(input)) {     // OR use a ternary here (more later)
                    reward = Reward.DEBIT_CARD;
                }
                else {
                    reward = Reward.PRIZES;
                }
                 */
            }
        }
        return reward;
    }

    private int promptForId() {
        int id = 0;

        boolean validInput = false;
        while(!validInput) {
            System.out.print("Please enter id of the winner [1-13]:  ");  // TODO: don't hardcode 13
            String input = scanner.nextLine().trim();   // removes leading/trailing whitespace chars
            if (input.matches("\\d{1,2}")) {      // any digit, one or two occurrences
                id = Integer.parseInt(input);           // safe to convert to int at this point
                if (id>=1 && id<= 13) {                 // valid id, TODO: don't hardcode the 13
                    validInput = true;
                }
            }
        }

        return id;
    }

    private void showBoard() {
        board.show();
    }

    private void welcome() {
        System.out.println();
        System.out.println("-------------------------------------------------------------------");
        System.out.println("W E L C O M E  T O  T H E  D U C K  R A C E   A P P L I C A T I O N");
        System.out.println("-------------------------------------------------------------------");
        System.out.println();
    }
}
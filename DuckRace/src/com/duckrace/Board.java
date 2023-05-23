package com.duckrace;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/*
 * This is a lookup table of ids to student names.
 * When a duck wins for the first time, we need to find out who that is.
 * This lookup table could be hardcoded with the data, or we could read the data
 * in from a file, so that no code changes would need to be made for each cohort.
 *
 * Map<Integer,String> studentIdMap;
 *
 * Integer    String
 * =======    ======
 *    1       John
 *    2       Jane
 *    3       Danny
 *    4       Armando
 *    5       Sheila
 *    6       Tess
 *
 *
 * We also need a data structure to hold the results of all winners.
 * This data structure should facilitate easy lookup, retrieval, and storage.
 *
 * Map<Integer,DuckRacer> racerMap;
 *
 * Integer    DuckRacer
 * =======    =========
 *            id    name     wins   rewards
 *            --    ----     ----   -------
 *    5        5    Sheila     2    PRIZES, PRIZES
 *    6        6    Tess       1    PRIZES
 *   13       13    Zed        3    PRIZES, DEBIT_CARD, DEBIT_CARD
 *   17       17    Dom        1    DEBIT_CARD
 */

public class Board implements Serializable {
    // Class-level common area (static)
    private static final String dataFilePath = "data/board.dat";
    private static final String studentIdFilePath = "conf/student-ids.csv";

    /*
     * Static factory method for a Bard object.
     * If data/board.dat exists, read the Board object from that file,
     * otherwise create new and return it.
     * This uses Java's built-in Object Serialization facility.
     */
    public static Board getInstance() {
    Board board = null;
    if(Files.exists(Path.of(dataFilePath))){
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(dataFilePath))) {
            board = (Board) in.readObject();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    else{
        board = new Board();
    }
    return board;
    }

    // instance variables, present in each Board object
    private final Map<Integer, String> studentIdMap = loadStudentIdMap();
    private final Map<Integer, DuckRacer> racerMap = new TreeMap<>();

    // constructors - this one is private, to prevent outside instantiation
    Board(){

    }




    /*
     * Updates the board (racerMap) by making a DuckRacer "win."
     * This could mean fetching an existing DuckRacer from racerMap
     * or we might need to create a new DuckRacer and put it in the map.
     * Either way, we need to make it "win."
     */
    public void update(int id, Reward reward) {
        DuckRacer racer = null;
        if (racerMap.containsKey(id)) { // id exists in racerMap, so get DuckRacer next to it.
            racer = racerMap.get(id);

        }
        else {                          // id not present, create new DuckRacer, put it in the map
            racer = new DuckRacer(id, studentIdMap.get(id));
            racerMap.put(id, racer);

        }
        racer.win(reward);
        save();
    }

    // show the DuckRacers (only), i.e., the right side of the map
    //TODO render ths data Pretty, for display to the end user
    // see Java Pt. 1, Session 5:  Formatted Output
    public void show() {
        // if racerMap is empty, print "currently no results to show"
        // otherwise, show the data (as we are doing below)
        if (racerMap.isEmpty()) {
            System.out.println("There are currently no results to show.");
            System.out.println();
        }
        else {
            Collection<DuckRacer> racers = racerMap.values();

            System.out.println("Duck Race Results");
            System.out.println("=================");
            System.out.println();
            System.out.println("id     name     wins   rewards");
            System.out.println("__     ____     ____   _______");
            for(DuckRacer racer : racers){
                System.out.printf("%s     %s     %s   %s\n", racer.getId(), racer.getName(), racer.getWins(), racer.getRewards());
            }
        }
    }
    /*
     * Save the state of this Board object to binary file "data/board.dat"
     */
    private void save() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(dataFilePath))) {
            out.writeObject(this);  // write "me" (a Board object) to the binary file
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Map<Integer, String> loadStudentIdMap() {
        Map<Integer, String> idMap = new HashMap<>();

        // read all lines from comf/student-ids.csv
        try {
            List<String> lines = Files.readAllLines(Path.of(studentIdFilePath));

            // for each line (String) in the file, split it into "tokens"
            for (String line : lines) {
                String[] tokens = line.split(","); // returns an Array of String ["1", "Bullen"]
                idMap.put(Integer.valueOf(tokens[0]), tokens[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return idMap;
    }


}
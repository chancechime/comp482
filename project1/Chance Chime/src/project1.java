/*
Stable Marriage Problem: There are n men and n women, where each person has ranked all members
of the opposite sex in order of preference. You want to pair them up to create a stable matching that
there are no two people of opposite sex who would both rather have each other than their current
partners.

Write a Java program using Gale–Shapley algorithm which will receive an instance of the marriage
problem and return a stable matching when:
a) men propose to women
b) women propose to men

Input Format: Create input1.txt file in the same directory as the java and class files.
“input1.txt” will be entered as a command line argument. The first line will be n(the number of
women or men). wThe next n lines will be the preference lists of the men and the fallowing n
lines will be the preference lists of the women with a whitespace separated list from best to
worst.

Output Format: Your program will output a stable matching for when men propose to women and
another stable matching for when women propose to men.
*/

/* 
 1. Read the input from the file
    1.1. Read the first line to get the number
    1.2. Read the next start - n lines to get the preference list for men
    1.3. Read the next n - end lines to get the preference list for women
 2. Apply the Gale-Shapley algorithm to get the stable matching

 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class project1 {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java project1 input.txt");
            return;
        }

        String filename = args[0];
        try {
            Scanner scanner = new Scanner(new File(filename));
            int n = scanner.nextInt();
            scanner.nextLine(); // consume newline

            ArrayList<ArrayList<Integer>> menPreferences = new ArrayList<>();
            ArrayList<ArrayList<Integer>> womenPreferences = new ArrayList<>();

            // Reading men's preferences
            for (int i = 0; i < n; i++) {
                ArrayList<Integer> preferences = new ArrayList<>();
                String[] tokens = scanner.nextLine().split(" ");
                for (String token : tokens) {
                    preferences.add(Integer.parseInt(token));
                }
                menPreferences.add(preferences);
            }

            // Reading women's preferences
            for (int i = 0; i < n; i++) {
                ArrayList<Integer> preferences = new ArrayList<>();
                String[] tokens = scanner.nextLine().split(" ");
                for (String token : tokens) {
                    preferences.add(Integer.parseInt(token));
                }
                womenPreferences.add(preferences);
            }

            scanner.close();

            // Call the Gale–Shapley algorithm based on user input
            int choice = 1; // default: men propose
            switch (choice) {
                case 1:
                    solveStableMarriage(n, menPreferences, womenPreferences, true); // men propose
                    break;
                case 2:
                    solveStableMarriage(n, womenPreferences, menPreferences, false); // women propose
                    break;
                default:
                    System.out.println("Invalid choice");
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
    }

    private static void solveStableMarriage(int n, ArrayList<ArrayList<Integer>> proposerPrefs,
                                            ArrayList<ArrayList<Integer>> receiverPrefs, boolean menPropose) {
        // Initialize arrays to track matching and proposals
        int[] match = new int[n]; // match[i] stores the partner of person i
        boolean[] engaged = new boolean[n]; // engaged[i] indicates if person i is already engaged
        int[] next = new int[n]; // next[i] stores the next woman to propose to for man i

        // Initialize match and engaged arrays
        for (int i = 0; i < n; i++) {
            match[i] = -1; // initially, no one is matched
            engaged[i] = false; // initially, no one is engaged
            next[i] = 0; // initially, each man will propose to his first choice
        }

        while (true) {
            // Iterate over all men
            boolean proposalMade = false;
            for (int man = 0; man < n; man++) {
                if (!engaged[man]) {
                    int woman = proposerPrefs.get(man).get(next[man]); // Get the woman to propose to
                    next[man]++; // Move to the next woman for the next iteration

                    if (match[woman] == -1) { // If the woman is free
                        match[woman] = man; // Engage the man and the woman
                        engaged[man] = true;
                        proposalMade = true;
                    } else {
                        int currentPartner = match[woman];
                        if (isPreferred(woman, man, receiverPrefs)) { // If the woman prefers the new proposal
                            match[woman] = man; // Engage the man and the woman
                            engaged[man] = true;
                            engaged[currentPartner] = false; // Break the current engagement
                            proposalMade = true;
                        }
                    }
                }
            }

            if (!proposalMade) // If no proposals were made, the matching is stable
                break;
        }

        // Output the stable matching
        if (menPropose) {
            System.out.println("Stable matching when men propose:");
        } else {
            System.out.println("Stable matching when women propose:");
        }
        for (int i = 0; i < n; i++) {
            System.out.println(i + " " + match[i]);
        }
    }

    private static boolean isPreferred(int woman, int newMan, ArrayList<ArrayList<Integer>> preferences) {
        int currentMan = preferences.get(woman).indexOf(newMan);
        for (int i = 0; i < currentMan; i++) {
            int otherMan = preferences.get(woman).get(i);
            if (otherMan != newMan) {
                return false;
            }
        }
        return true;
    }
}

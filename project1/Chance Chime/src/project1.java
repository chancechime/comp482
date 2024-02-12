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


public class project1 {
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}
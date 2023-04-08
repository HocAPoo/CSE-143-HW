//Alexander Vuu
//TA: Nathan
//CSE143 Section A
//10/3/22
//class LetterInventory is used to keep track of an inventory of letters.
//The client is able to manipulate the counts using method calls.

import java.util.*;

public class LetterInventory {
    private int size; // total sum of indexes added up
    private int[] letterArray; // keeps track of charcters in string
    public static final int LETTER_ARRAY_LENGTH = 26; // incase you want to
    // account for letters beyond regular alphabet

    /*
     * Constructs an letterInventory object to keep track of charcters
     * provided in the parameter string.
     * ingnoring non alphabetic letters and case of charcters. (all lower case)
     * 
     * conditions
     * pre: Must have constructed an Inventory object with a 
     * param string using alphabetic letters,
     * ingnoring non alphabetic letters and case of charcters. (all lower case)
     * private variables:
     * int size variable is used to calculate the size of inventory object.
     * letterArray int[]
     * is used to keep track of letters recorded in inventory object.
     * int LETTER_ARRAY_LENGTH: array length of characters accounted for
     * param (parameter): data (String)
     * post: letterArray array coresponds to data string
     * size coresponds to amount of letters in invetory object.
     * ignores non-alphbetic charcters
     * Special Cases: If an empty String is passed the letterArray
     * will remain empty
     */
    public LetterInventory(String data) {

        size = 0;
        letterArray = new int[LETTER_ARRAY_LENGTH];

        data = data.toLowerCase();
        // makes an array recording each time a number appears in the alphabet
        for (int i = 0; i < data.length(); i++) {
            char ch = data.charAt(i);
            if ((ch >= 'a') && (ch <= 'z')) {
                letterArray[ch - 'a']++;
                size++;
            }
        }
    }

    /*
     * This method returns a count of how many times the
     * charcter in the parameter apears in the letterInventory.
     * throws an exception if charcter isnt a-z, (IllegalArgumentException).
     * 
     * conditions
     * pre: letter has to be a charcter a-z (uppercase letters will not throw an
     * exception).
     * If letter is a non-alphabetic charcter throws exception
     * (IllegalArgumentException)
     * param: letter (charcter)
     * returns: int (numberOfLetters)
     * post: returns an integer, a count based on number
     * of that letter in the inventory
     * Special Case: user request to get a charcter that is 
     * not in the inventory method returns zero
     */
    public int get(char letter) {
        if (!Character.isLetter(letter)) {
            throw new IllegalArgumentException("*Character is nonalphabetic*");
        }
        letter = Character.toLowerCase(letter);
        return letterArray[letter - 'a'];
    }

    /*
     * Sets the count for the given letter to the value of count.
     * throws an exception if charcter isnt a-z or positive number.
     * (IllegalArgumentException)
     * 
     * conditions
     * pre: letter has to be a charcter a-z
     * (uppercase letters will not throw an exception) or
     * value is a positive number. If eiter one is false
     * will throw an exception (IllegalArgumentException)
     * param: letter (charcter) letter that you want to set inventory to
     * value (int) amount of times you want to set letter to.
     * return: none (void)
     * post: returns nothing. Sets the letter's amount
     * to be the value declared in the parameter (value).
     * changes size value to fit with set changes.
     */
    public void set(char letter, int value) {
        if (!Character.isLetter(letter) || value < 0) {
            throw new IllegalArgumentException("*Character is nonalphabetic/value is negative*");
        }
        letter = Character.toLowerCase(letter);
        size += (value - letterArray[letter - 'a']);
        letterArray[letter - 'a'] = value;

    }

    /*
     * method returns the value of all counts in the inventory size.
     * 
     * conditions
     * pre: created an Inventory Object prior to running method
     * returns: size (int)
     * post: returns an int (size) based on size of inventory letterArray.
     */
    public int size() {
        return size;
    }

    /*
     * checks too see if the inventory count is empty.
     * true for empty, false for not empty.
     * 
     * conditions
     * pre: created an Inventory Object prior to running method
     * returns: boolean
     * post: returns a boolean value based on if all inventory counts
     * if all letterArray indexes are 0.
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /*
     * conditions
     * This method returns a string represetation of letterInventory.
     * All letters are alphabatized, alphabetic, lowercase, and string is surrounded
     * by []
     * 
     * pre: created an Inventory Object prior to running method
     * returns: mainString (String)
     * post: returns a string (mainString) surrounded by square brackets
     * Takes inventory's letter count
     * (letterOccurance) and turns into a string format
     * Special Cases: If the letterOccuranceArray is empty mainString = [].
     */
    public String toString() {
        String mainString = "[";

        for (int i = 0; i < letterArray.length; i++) {
            if (letterArray[i] > 0) {
                for (int j = 0; j < letterArray[i]; j++) {
                    mainString += (char) ('a' + i);
                }
            }
        }
        return mainString += ']';
    }

    /*
     * Constructs and returns a new LetterInventory with each index being the
     * sum of the current letterInventory and the other letterInvetory
     * declared in the param. both letterInvetory's keep thier current data
     *
     * conditions
     * Pre: requires two LetterInventory objects,
     * Param: other (LetterInventory object)
     * Return: sumArray (LetterInventory object)
     *
     * Post: constructs and returns a LetterInventory object (sumArray)
     * which is calculated by adding the first LetterInventory object
     * plus the second LetterInventory object.
     */
    public LetterInventory add(LetterInventory other) {
        LetterInventory sumArray = new LetterInventory("");
        for (int i = 0; i < sumArray.letterArray.length; i++) {
            sumArray.letterArray[i] = letterArray[i] + other.secretArray()[i];
        }
        sumArray.size = size + other.size;
        return sumArray;
    }

    /*
     * Constructs and returns a new LetterInventory with each index being the
     * differance of the current letterInventory minus the other letterInvetory
     * declared in the param. both letterInvetory's keep thier current data
     * if any of the counts are negtive the method returns NULL.
     *
     * conditions
     * Pre: requires two LetterInventory objects,
     * When subtracting the second inventory from the first inventory,
     * Param: other (LetterInventory object)
     * Return: differanceArray (LetterInventory object)
     *
     * Post: constructs and returns a LetterInventory object (differanceArray)
     * which is calculated by subtracting the first LetterInventory object
     * minus the second LetterInventory object at each index.
     */
    public LetterInventory subtract(LetterInventory other) {
        LetterInventory differanceArray = new LetterInventory("");
        for (int i = 0; i < differanceArray.letterArray.length; i++) {
            differanceArray.letterArray[i] = letterArray[i] - other.secretArray()[i];
            if (differanceArray.letterArray[i] < 0) {
                return null;
            }
        }
        differanceArray.size = size - other.size;
        return differanceArray;
    }

    /*
     * This helper method returns an object's letterArray
     * 
     * conditions
     * pre: created an Inventory Object prior to running method
     * post: returns an integer array (helper method for add and subtract methods)
     */
    private int[] secretArray() {
        return letterArray;
    }
}
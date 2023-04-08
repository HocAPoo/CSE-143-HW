//Alexander Vuu
//TA: Nathan
//CSE143 Section A
//10/23/22
//manages a game of hangman where the computer is cheating by not 
//picking a work until the user guesses a letter, everytime the user
//guesses a letter the computer will take all the words out of the dictionary 
//that contain that letter. Game ends when computer is backed into a corner 
//where it has to give the word or the user runs out of guesses

import java.util.*;

public class HangmanManager {

    private int numOfGuess; // amount of guesses user has left
    private Set<String> newDictionary; // the words being considered
    private Set<Character> guessedChars; // characters that have already been guessed
    private String lastString; // holds the progress of the user's corret letters

    /*
     * Constructs a blank space for each letter in the word.
     * sets the amout of guesses that each player is allowed to have.
     * Adds all words to a new dictionary with all words that have
     * the requested length of letters.
     * 
     * Pre: Given a dictionary, length of the word that user wants to guess,
     * and a limit to the amout of guesses a player is allowed to have
     * Post: new dictionary eliminates duplicates and contains words of
     * a passed length, sets the "secret" word to have a passed length of
     * letters, and limit of guesses is initialized. Assumes all letters are
     * lower cased.
     * 
     * Post: Sets up the game for the player.
     * 
     * @Param dictionary | type Collection<String> | A passed dictionary of words
     * client can pass in either a set or a list
     * 
     * @Param length | type int | All the words in dictionary are at
     * the passed length and creates lenth amount of "-" for displayed string
     * 
     * @Param max | type int | the number of guesses you get
     * 
     * @Param Requirements: Assumed passed dictionary is legal and has at least one
     * word
     * Assumes all words are lower cased
     * length must be more than one, max must be greader than zero
     * 
     * @exceptions: length must be greader than one, or max is greader than zero
     * throws IllegalArgumentException
     */
    public HangmanManager(Collection<String> dictionary, int length, int max) {
        if ((length < 1) || (max < 0)) {
            throw new IllegalArgumentException();
        }
        numOfGuess = max;

        newDictionary = new TreeSet<>();
        guessedChars = new TreeSet<>();
        lastString = "-";

        for (int i = 1; i < length; i++) {
            lastString += " -";
        }

        for (String word : dictionary) {
            if (word.length() == length) {
                newDictionary.add(word);
            }
        }
    }

    /*
     * post: returns set of words being considered in the new dictionary
     * 
     * @returns an integer based on the number of words left in the new dictionary.
     */
    public Set<String> words() {
        return newDictionary;
    }

    /*
     * post: returns number of guesses user has left
     * 
     * @returns an integer of the amount of guesses user has left
     */
    public int guessesLeft() {
        return numOfGuess;
    }

    /*
     * post: returns the set of characters that the user has already guessed
     * 
     * @returns a set of characters consisting of charcters that the user has
     * guessed
     */
    public Set<Character> guesses() {
        return guessedChars;
    }

    /*
     * post: returns a pattern of dashs and letters, dash is a unknown
     * character and letter is a correct character
     * each character can be a letter only
     * if the computer decides that it chooses to reveal a letter, and
     * a blank for a wrong letter (computer doesn't choose to
     * reveal a letter) based on the letters that have been guessed
     * there are no leading spaces and trailing spaces, but a space
     * in between each character/dash.
     *
     * 
     * @returns a string, the current pattern of letters and
     * blank spaces
     * 
     * @excpetion throws an IllegalStateExcpetion if the size of the words
     * being considered is less than zero
     */
    public String pattern() {
        if (newDictionary.isEmpty()) {
            throw new IllegalStateException();
        }
        return lastString;
    }

    /*
     * records the next charcter that the client passed in
     * (the letter user guessed), using that guess it decides
     * which words to consider and which words to not consider, when
     * choosing a word.
     * 
     * Pre: Client passes the user's guess, their guess determies how many words to
     * keep on considering
     * Post: returns the number of times user's guess apears in the "secret word"
     * 
     * @param guess | type char | the character the client passed
     * that the user has guessed
     * 
     * @param requirements: Guess, Assumes passed charcter is in
     * the alphabet, (if its not, special characters are counted as a wrong guess)
     * Assumes all guessed letters passed in are lower cased.
     * 
     * @returns the number of guesses the user has left
     * 
     * @exception if number of guesses is less than one or no
     * words in the set of words throws IllegalStateException
     * 
     * @exception if passed character is seen again
     * throws new IllegalArgumentException
     * 
     */
    public int record(char guess) {
        if ((numOfGuess < 1) || (newDictionary.isEmpty())) {
            throw new IllegalStateException();
        } else if (guessedChars.contains(guess)) {
            throw new IllegalArgumentException();
        }
        Set<String> hasNoLetter = new TreeSet<>();
        Set<String> containsLetter = new TreeSet<>();
        Map<String, Set<String>> pattern = new TreeMap<>();
        String biggestKey = "";

        guessedChars.add(guess);
        for (String dicWord : newDictionary) {
            if (dicWord.indexOf(guess) > -1) {
                containsLetter.add(dicWord);
            } else {
                hasNoLetter.add(dicWord);
            }
        }

        if (!containsLetter.isEmpty()) {
            biggestGuessedSet(containsLetter, pattern);
            biggestKey = biggestSet(biggestKey, pattern);
        }

        return amountCorrectChar(biggestKey, guess, hasNoLetter, containsLetter, pattern);
    }

    /*
     * helper method:
     * looks through the set of words and assigns each word to a pattern
     * of where that letter appears in the word.
     * (e.g if the set of words contains cool and good, the string would be -oo- )
     * if that pattern doesn't exist yet it makes a new one, otherwise it puts
     * it into a set that already exist
     * 
     * @param words | type set | one set of strings the that computer uses to place
     * words into multiple sets. (containsLetter but different variable name)
     * 
     * @param pattern | type Map | uses to make a new key if the key doesnt exist
     * otherwise it places the new string into that set
     * 
     */
    private void biggestGuessedSet(Set<String> words, Map<String, Set<String>> pattern) {
        // iterates through to make the key strings and adds words to the set values
        for (String eachWord : words) {
            String wordPattern = "";
            // makes the keys
            if (guessedChars.contains(eachWord.charAt(0))) {
                wordPattern += eachWord.charAt(0);
            } else {
                wordPattern += "-";
            }
            for (int i = 1; i < eachWord.length(); i++) {
                if (guessedChars.contains(eachWord.charAt(i))) {
                    wordPattern += " " + eachWord.charAt(i);
                } else {
                    wordPattern += " -";
                }
            }
            // creates each value in the map
            if (!pattern.containsKey(wordPattern)) {
                pattern.put(wordPattern, new TreeSet<>());
            }
            pattern.get(wordPattern).add(eachWord);
        }
    }

    /*
     * helper method:
     * returns the pattern with the larget set of strings
     * 
     * @param biggestKey | type String | a string representation of
     * the key, with the largest set of strings
     * 
     * @param pattern | type Map <String , Set<String>> | used to compare
     * the sizes of each set
     * 
     * @returns a key in the form of a string, key unlocks the largest set value
     * 
     */
    private String biggestSet(String biggestKey, Map<String, Set<String>> pattern) {
        int max = 0;
        for (String patternSets : pattern.keySet()) {
            if (pattern.get(patternSets).size() > max) {
                max = pattern.get(patternSets).size();
                biggestKey = patternSets;
            }
        }
        return biggestKey;
    }

    /*
     * helper method:
     * adds to the count for each letter the user got right in the word
     * and decreases the number of guesses left if the user didnt get a
     * character in the letter. Also sets the biggest set as the new dictionary
     * 
     * @param biggestKey | type String| used to find out what the count is
     * based on number of times the guess apears in the string
     * 
     * @param guess| type Char| checks the biggest key for the amount
     * of times the passed appears in the biggest key
     * 
     * @param hasNoLetter | type Set| becomes the new dictionary if its set
     * is larger than any other considered set
     * 
     * @param containsLetter | type Set | used to see if the set is empty
     * if it is defualts to the else statement
     * 
     * @param patternKey | type Map | used to get the size of each set
     * 
     * @returns count | the number of times the guess apears in the biggest key
     */
    private int amountCorrectChar(String biggestKey, char guess, Set<String> hasNoLetter,
            Set<String> containsLetter, Map<String, Set<String>> patternKey) {

        int count = 0;
        if ((!containsLetter.isEmpty()) &&
                (patternKey.get(biggestKey).size() > hasNoLetter.size())) {
            for (int i = 0; i < biggestKey.length(); i++) {
                if (biggestKey.charAt(i) == (guess)) {
                    count++;
                }
            }
            newDictionary = patternKey.get(biggestKey);
            lastString = biggestKey;
        } else {
            count = 0;
            newDictionary = hasNoLetter;
            numOfGuess--;
        }
        return count;
    }

}

//Alexander Vuu
//TA: Nathan
//CSE143 Section A
//10/3/22
 
//program preforms a search to find all work combination as if
//the passed word was an anagram. Very similar to scrable, in
//a way that finds all the combination of words that can be made up of
//the user imputed letters
//(all you need to do is equate the letters to points, and try to find
//the best combination of words that have the highest point combination.
// then you got an upgraded project: SCRABBLE SOLVER)

import java.util.*;
public class AnagramSolver {

    private List<String> maintainOrder;//maintains order of the dictionary
    private Map<String,LetterInventory> stringToInventory;//keeps note of each order
    // in the dictionary
    
    /*
    * Pre: constructer is passed a list of words in a dictionary.
    * Post: stores each word for easy access later on. Doesn't change the state
    * of the dictionary.
    *
    * @Param list | type: List<String> | holds all the words in the dictionary
    * assumes the dictionary is a non empty collection of nonempty sequences,
    * of letters, and that has no duplicates.
    *
    */
    public AnagramSolver(List<String> list){
        maintainOrder = list;
        stringToInventory = new HashMap<>();
        for(String dicWord : list){
            LetterInventory invetoryWord = new LetterInventory(dicWord);
            stringToInventory.put(dicWord,invetoryWord);
        }  
    }

    /*
    * Pre: takes in a string which we will get anagrams from,
    * and an integer which is the amount of anagram words we want.
    * Post: prints out all of the word conbos in the dictionary that use
    * up all of the letters in the passed string, and at most have the
    * passed integer max words
    *
    * @exception: if max is less than zero throw IllegalArgumentException
    *
    * @Param s | type string | A string passed to dirivate anagrams from
    * @Param max | type max | An integer passed to limit the amount of
    * anagrams combinations
    */
    public void print(String s, int max){
        if(max < 0){
            throw new IllegalArgumentException();
        }
        
        LetterInventory anagramWord = new LetterInventory(s);
        List<String> soFar = new ArrayList<>();
        List<String> temp = new ArrayList<>();;

        
        
        for(String maintainString : maintainOrder){
            if(anagramWord.subtract(stringToInventory.get(maintainString))!=null){
                temp.add(maintainString);
            }
        }
        

        helpPrint(anagramWord, max, soFar, temp);

    }
    
    /*
    * Pre: passed a collection of letters as the letters we can use to make the anagrams,
    * a maximum amount of anagrams that can be printed, and a place to keep track of words.
    * Post: prints all possible combinations that can be made from the passed string whos
    * number of printed words are under that passed integer max.
    *
    * @Param s | type string | A string passed to dirivate anagrams from
    * @Param soFar | type List<String> | a list used to storage a combination
    * of letters that can be formed from the passed string.
    * @Param max | type max | An integer passed to limit the amount of
    * anagrams combinations
    * @Param temp | type List<String> | keeps track a list of strings that 
    * could be possible combinations.
    *
    */
    private void helpPrint(LetterInventory anagramWord, int max, 
                                        List<String> soFar, List<String> temp){ 
        if(anagramWord.isEmpty() && ((soFar.size() <= max) || max == 0)){
            System.out.println(soFar);
        }
        else if(!anagramWord.isEmpty()){
            for(String eachDicWord : temp){
                LetterInventory diffInventory = 
                                anagramWord.subtract(stringToInventory.get(eachDicWord));
                if(diffInventory != null){
                    soFar.add(eachDicWord);
                    helpPrint(diffInventory, max, soFar, temp);
                    soFar.remove(soFar.size()-1);
                }
            }
            
        }
    }
}
 
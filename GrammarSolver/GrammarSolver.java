//Alexander Vuu
//TA: Nathan
//CSE143 Section A
//10/28/22

//Grammer Solver grenerates randomized symbols a number of times until our 
//symbols reach a nonterminal and form a coherant statement
import java.util.*;

public class GrammarSolver {
    
    Random rand;//used to generate a random number 
    Map<String, String[]> map;// used to map out all of the terminals and non terminals.

    /*
     * Pre: constructor is passed is in a list of strings, 
     * reads in a grammar in Backus-Naur Form (BNF)
     * Post: stores each string for easy access.
     * 
     * @param grammer | type List<String> | a list of strings,
     * used to generate grammer sentences. 
     * 
     * @exception. Throws new IllegalArgumentException if grammer list is empty
     * or there are two or more entries for the same non-terminal
     * 
     * @paramater Reqs: grammer cannot be not be empty, 
     * and cant have two of the same entries for the same nonterminal.
     * 
     * pre: 
     * nonterminals must have at least one terminal
     * list of strings are not changed
     */
    public GrammarSolver(List<String> grammar){
        if(grammar.isEmpty()){
            throw new IllegalArgumentException();
        }
        rand = new Random();
        map = new TreeMap<>();
        for(String nonTerminal : grammar){
            
            String[] keySplit = nonTerminal.split("::=");
            if(map.containsKey(keySplit[0])){
                throw new IllegalArgumentException("two of the same non terminal");
            }
            
            String[] arrOfStr = keySplit[1].split("[|]");
            map.put(keySplit[0], arrOfStr);
            
        }
    }

    /*
     * Pre takes in a symbol that is of type string
     * Post: method determines if that symbol exist as a non-terminal statement.
     * 
     * @Param symbol | type String | method checks to see if the string 
     * is a terminal or non-terminal
     * 
     * @returns boolean: method returns a true if the string appears as a non-terminal
     * 
     */
    public boolean grammarContains(String symbol){
        return (map.containsKey(symbol));
    }       
    
    /*
     * Pre: map must be instantiatied 
     * Post: returns the nonterminals that the computer is able to be generated.
     * nonterminals can be read as text and sent to console to print
     * 
     * @returns a string that is a represetation of all the nonterminals with commas
     * and square brackets
     */
    public String getSymbols(){
        String symbols = "[";   
        Iterator<String> itr = (map.keySet()).iterator();
        symbols += itr.next();
        while(itr.hasNext()){
            symbols += ", " + itr.next();
        }
        return symbols + "]";
    }
    
    /*
     * Pre: client passes the symbol that the computer generates 
     * and the amout of sentences client wants to generate. 
     * Post: returns an collection with the size of the passed integer of randonly generated symbols 
     * that lead to a nonterminal.
     * @Param symbol | type String | used to generated the random occurances of that symbol
     * @Param times | type Int | collection is the size of times. Fills each 
     * randomly generated symbol in the collection equal to the amout of times
     * @exceptions throws IllegalArgumentException if grammer doesn't contain the nonterminal 
     * symbol, or times is less than zero 
     * @returns an collection of randomly generated symbols. collection is size of times, 
     * each symbol generated is randomized.
     * 
     */
    public String[] generate(String symbol, int times){
        if (!grammarContains(symbol) || times < 0){
            throw new IllegalArgumentException("doesn't contain symbol");
        }
        String[] sentenceArr = new String[times];
        
        for(int i = 0; i < times; i++){
            sentenceArr[i] = (findSet(symbol).trim());
            
        }
        return sentenceArr;
    }

    /*
     * Pre: gets passed a symbol that is a nonterminal 
     * Post: returns a string that is the sentence 
     * @param Symbol | type String | symbol is a terminal or 
     * non terminal passed in
     * @returns a terminal
     * 
     * assumes symbol is a non terminal from main method
     */
    private String findSet(String symbol){
        if(!map.containsKey(symbol)){
            return symbol;
        }
        String word = "";
        int random = rand.nextInt(map.get(symbol).length);

        String[] rules = map.get(symbol);

        String[] subStringArr = rules[random].trim().split("[ \t]+");
        for(int i = 0; i < subStringArr.length; i++){
            word += " " + findSet(subStringArr[i]);
        }
    
        return word.trim();
    }
    

}
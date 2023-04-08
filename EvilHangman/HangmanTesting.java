
import java.util.*;
import java.io.*;
public class HangmanTesting {
    public static final String DICTIONARY_FILE = "/Users/HocVuu/Cse143/EvilHangman/dictionary2.txt";
    public static final int length = 4;
    public static final int max = 12;
    public static void main(String[] args) throws FileNotFoundException{
        Scanner input = new Scanner(new File(DICTIONARY_FILE));
        List<String> dictionary = new ArrayList<>();
        Scanner console = new Scanner(System.in);
        
        while (input.hasNext()) {
            dictionary.add(input.next().toLowerCase());
        }
        //System.out.println(dictionary);

        
        HangmanManager hangman = new HangmanManager(dictionary, length, max);
        char ch = ';';
        
        while(ch != '!'){
            System.out.print("Next Choosen Letter: ");
            ch = console.next().toLowerCase().charAt(0);
            System.out.println();
            int count = hangman.record(ch);
            System.out.println("The character " + ch + " appeared " + count + " times");
            System.out.println("Already Selected Characters: " + hangman.guesses()+ "\n--------------------------------------------------");
        }
        

        
    }
}

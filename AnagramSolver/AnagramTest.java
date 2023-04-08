import java.io.*;
import java.util.*;
public class AnagramTest{
    public static void main(String[] agrs) throws FileNotFoundException {
        /*
        LetterInventory l1 = new LetterInventory("bob bush");
        LetterInventory l2 = new LetterInventory("bush");
        LetterInventory l3 = l1.subtract(l2);
        if(l3 == null){
            System.out.println("\n\nl3.toString()");
        }
        System.out.println(l3.toString());
         */
        
        //System.out.print("What is the name of the dictionary file? ");

        
        Scanner input = new Scanner(new File("/Users/HocVuu/Cse143/AnagramSolver/dict1.txt"));

        // read dictionary into an ArrayList
        List<String> dictionary = new ArrayList<>();
        while (input.hasNextLine()) {
            dictionary.add(input.nextLine());
        }

        // solve anagrams
        List<String> dictionary2 = Collections.unmodifiableList(dictionary);
        AnagramSolver solver = new AnagramSolver(dictionary2);
        System.out.println("\n\ngeorge bush");
        solver.print("george BUSH", 0);
        System.out.println("\n\nbarbara bush");
        solver.print("barbara BUSH", 0);
    }
}
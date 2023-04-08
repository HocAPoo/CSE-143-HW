import java.util.*;
import java.io.*;
/*

/Users/HocVuu/Cse143/GrammarSolver/sentence.txt

 */



public class GrammarTesting {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner console = new Scanner(System.in);


        // open grammar file
        
        
        Scanner input = new Scanner(new File("/Users/HocVuu/Cse143/GrammarSolver/sentence2.txt"));

        // read the grammar file and construct the grammar solver
        List<String> grammar = new ArrayList<>();
        while (input.hasNextLine()) {
            String next = input.nextLine().trim();
            if (next.length() > 0) {
                grammar.add(next);
            }
        }
        /*
        String ad = "asd";
        String[] arrOfStr = ad.split("[|]");
        System.out.println(Arrays.toString(arrOfStr));
         */
        GrammarSolver solver = new GrammarSolver(Collections.unmodifiableList(grammar));
        System.out.println("\n\n");
        System.out.println(solver.getSymbols());
        
        String str = "E";
        int times = 10;
        String[] strArr = solver.generate(str, times);
        
        System.out.println("\n\nThe Array printed out in a list: ");
        for (int i = 0; i < times; i++) {
            System.out.println(i + ") " + strArr[i]);
        }
        
    }
}

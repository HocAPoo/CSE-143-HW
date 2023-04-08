import java.util.*;
import java.io.*;
public class QuestionTest {
    
    public static void main(String[] args)throws Exception{
        String QUESTION_FILE = "/Users/HocVuu/CSE143/20Questions/question.txt";
        QuestionTree bruh = new QuestionTree();
        
        bruh.read(new Scanner(new File(QUESTION_FILE)));
        bruh.askQuestions();
        //bruh.printPreorder();       
        bruh.write(new PrintStream(new File("/Users/HocVuu/CSE143/20Questions/savedTree.txt")));


    }
}

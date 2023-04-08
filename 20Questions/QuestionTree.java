//Alexander Vuu
//TA: Nathan
//CSE143 Section A
//10/25/22
//plays a game of 20 questions but computer gets smarter each time it loses
import java.util.*;
import java.io.*;
public class QuestionTree {
    private Scanner console; // reads user input
    private QuestionNode questionTree; // keeps track of question and answers
    
    //Post: Constructs a decision algorithum with a single answer
    public QuestionTree(){
        console = new Scanner(System.in);
        questionTree = new QuestionNode("computer");
    }

    /*
    * Pre: Assumes client inputs a valid txt file in the
    * correct format
    * Post: Stores the file tokens for further uses,
    * this file passed will replace the current decision algorithum
    * with the file it reads in
    *
    * @Param input | type Scanner | reads file that will
    * replace the current decision algorithum
    */
    public void read(Scanner input){
        questionTree = helperRead(input);
    }

    /*
    * Post: will set our decision algorithum, each question as a branch
    * and each answer as a leaf
    *
    * @Param input | type Scanner | reads file that will
    * replace the current decision algorithum
    *
    * @Return type: QuestionNode | Returns our modified Question node that
    *  we set as our decision algorithum
    *
    */
    private QuestionNode helperRead(Scanner input){
        String nodeType = input.nextLine();
        String answerQuestionLine = input.nextLine();
        QuestionNode questionTree = new QuestionNode(answerQuestionLine);
        if(nodeType.equals("Q:")){
            questionTree.leftNode = helperRead(input);
            questionTree.rightNode = helperRead(input);
        }
        return questionTree;
    }

    /*
    * Pre: client passes a file they want the output to print to
    * (if it doesn't exist it creates one, otherwise it will
    * override the current one)
    * Post: writes our decision algorithum to the output file
    *
    * @Param output | type: PrintStream | adds functionality to
    * print to the output file
    *
    */
    public void write(PrintStream output){
        writeToFile(questionTree, output);
    }

    /*
    * Post: Writes the current decision algorithum into the output file,
    * output is in legal and in standared format
    *
    * @Param output | Type: PrintStream | adds functionality
    * to print to the output file
    */
    private void writeToFile(QuestionNode root, PrintStream output){
        if (root != null) {
            if(root.rightNode == null && root.leftNode == null){
                output.println("A:");
            }
            else{
                output.println("Q:");
            }
            output.println(root.nodeValue);
            writeToFile(root.leftNode, output);
            writeToFile(root.rightNode, output);
        }
    }

    /*
    * Pre: A must've been created decision algorithum
    * Post: tries to guess what the user is thinking using the current decision algorithum
    * if the computer doesn't get the object right code expands the decision algorithum to
    * add user's object, and a question to distinguish what the user was thinking
    * from the failed computer guess.
    *
    */
    public void askQuestions(){
        questionTree = askQuestionsHelper(questionTree);
    }

    /*
    * Post: asks yes or no questions and based on the decision algorithum,
    * computer makes its best guess as to what the user is thinking, if
    * the computer is wrong it adds to its decision algorithum the user's
    * object and a question to distinguish what the user was thinking
    * from the failed computer guess.
    *
    * @Param root | type QuestionNode | used to keeps
    * track of questions and answers
    * @Return type: QuestionNode | returns a new improved
    * QuestionNode that inclues the user's added responses
    *
    */
    private QuestionNode askQuestionsHelper(QuestionNode root){
        if(root == null){
            return null;
        }

        if(root.rightNode == null && root.leftNode == null){//its one of the leaf nodes
            if(yesTo("Would your object happen to be " + root.nodeValue + "?")){
                System.out.println("Great, I got it right!");
            }
            else{
                System.out.print("What is the name of your object? ");
                String addObject = console.nextLine();
                System.out.println("Please give me a yes/no question that");
                System.out.println("distinguishes between your object");
                System.out.print("and mine--> ");
                String addQuestion = console.nextLine();
                QuestionNode replacementNode;
                if(yesTo("And what is the answer for your object?")){
                    replacementNode = new QuestionNode(addQuestion,
                    new QuestionNode(addObject), root);
                }
                else{
                    replacementNode = new QuestionNode(addQuestion, root,
                    new QuestionNode(addObject));
                }
                root = replacementNode;
            }
        }
        else{// branch node
            if(yesTo(root.nodeValue)){
                root.leftNode = askQuestionsHelper(root.leftNode);
            }
            else{
                root.rightNode = askQuestionsHelper(root.rightNode);
            }
        }
        return root;
    }
    
    /*
    * *GIVEN*
    * Pre: passes in a string prompt to ask y or n to
    * Post: will ask the given question until user inputs
    * either a y for yes or an n for no
    *
    * @Param prompt | type prompt | a propt that will
    * ask to user to gain feedback
    * @returns Boolean true for yes and false for no
    *
    */
    public boolean yesTo(String prompt) {
        System.out.print(prompt + " (y/n)? ");
        String response = console.nextLine().trim().toLowerCase();
        while (!response.equals("y") && !response.equals("n")) {
            System.out.println("Please answer y or n.");
            System.out.print(prompt + " (y/n)? ");
            response = console.nextLine().trim().toLowerCase();
        }
        return response.equals("y");
    }
}
//Alexander Vuu
//TA: Nathan
//CSE143 Section A
//10/3/22
//20 Questions uses this node to construct a tree 

public class QuestionNode {
    public String nodeValue; //keeps track of string value assigned
    public QuestionNode leftNode; //keeps track of the left node
    public QuestionNode rightNode; //keeps track of the right node
    
    // constructs a leaf node with string as data
    // @Param data | type String | either the Question or answer phrase
    public QuestionNode(String data) {
        this(data, null, null);
    }
    
    // constructs a branch node with data, left subtree right subtree
    // @Param data | type String | either the Question or answer phrase
    // @Param leftValue | type QuestionNode | leftNode becomes leftValue
    // @Param rightValue | type QuestionNode | rightNode becomes rightValue
    public QuestionNode(String data, QuestionNode leftValue, 
            QuestionNode rightValue) {
        this.nodeValue = data;
        this.leftNode = leftValue;
        this.rightNode = rightValue;
    } 
}

//Alexander Vuu
//TA: Nathan
//CSE143 Section A
//10/29/22


public class HuffmanNode implements Comparable<HuffmanNode>{
    public int nodeValue; //keeps track of frequentcy
    public int charValue; // keeps track of acsii char value
    public HuffmanNode leftNode; //keeps track of the left node
    public HuffmanNode rightNode; //keeps track of the right node

    // constructs a leaf node with integer as frequentcy 
    // and an character
    // @Param frequentcy | type integer | freq of the character
    // @Param character | type char | charcter for the node
    public HuffmanNode(int frequentcy, int character) {
        this(frequentcy, character, null, null);
    }

    /*
     * constructs a branch/leaf node with no frequentcey a binary
     * path, and a integer character value
     * @Param character | type int | integer representation of character
     */
    public HuffmanNode(int character) {
        this(-1, character, null, null);
    }

    // constructs a branch node with integer as frequentcy,
    // integer as a character, with node on
    // the left and a node on the right
    // @Param frequentcy | type integer | freq of the character
    // @Param character | type char | charcter for the node
    // @Param leftValue | type HuffmanNode | leftNode becomes leftValue
    // @Param rightValue | type HuffmanNode | rightNode becomes rightValue
    public HuffmanNode(int frequentcy, int character, HuffmanNode leftValue, 
    HuffmanNode rightValue) {
        this.nodeValue = frequentcy;
        this.charValue = character;
        this.leftNode = leftValue;
        this.rightNode = rightValue;
    } 

    /*
     * Pre: Must've implemented Comparable
     * Post: Compares two node's frequency 
     * to figure out which is larger 
     * @Param otherNode | type HuffmanNode | object who's 
     * freqcency thats being compared
     * @Returns returns a positive negative or zero integer
     * depending on which object's frequency is larger
     */
    public int compareTo(HuffmanNode otherNode){
        if(this.nodeValue < otherNode.nodeValue){
            return-1;
        }
        else if(this.nodeValue == otherNode.nodeValue){
            return 0;
        }
        else{
            return 1;
        }
    }
}
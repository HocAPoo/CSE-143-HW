//Alexander Vuu
//TA: Nathan
//CSE143 Section A
//10/29/22
/*
 * Huffman Coding algorithum: compresses a text file maximizing
 * the use of each byte of memory used, common characters have 
 * the shortest encoding.
 * Also is able to return the file to its original state
 */

import java.util.*;
import java.io.*;

public class HuffmanTree {

    private HuffmanNode huffTree;
    
    /*
     * Pre: musti've passed a an int array which is a count of the character
     * frequencies and a character with an integer value 
     * Post: constructs a huffman algorithum to compress characters
     * @Param count | type: integer array | uses the integer array to 
     * format the algorithum specific to the file that needs to be 
     * compressed 
     */
    public HuffmanTree(int[] count){
        Queue<HuffmanNode> lowFreqFirst = new PriorityQueue<>();
        for(int i = 0; i < count.length;i++){
            int freq = count[i];
            if(freq>0){
                lowFreqFirst.add(new HuffmanNode(freq, i));
            }
        }
        lowFreqFirst.add(new HuffmanNode(1, count.length));//adds end of file character
        while(lowFreqFirst.size() >= 2){
            HuffmanNode lowestFreq = lowFreqFirst.remove();
            HuffmanNode secLowestFreq = lowFreqFirst.remove();
            lowFreqFirst.add(new HuffmanNode((lowestFreq.nodeValue + 
            secLowestFreq.nodeValue), -1, lowestFreq, secLowestFreq));
        }
        huffTree = lowFreqFirst.remove();
    }

    /*
     * Pre: client passes a file they want the output to print to
     * (if it doesn't exist it creates one, otherwise it will
     * override the current one)
     * Post: writes our decision algorithum to the output file
     * our first line is the character as an integer value, and 
     * the second line is how to get to the character using our
     * decision algorithum. This will compress our file 
     * @Param output | type: PrintStream | uses the PrintStream
     * to print to the output file
     */
    public void write(PrintStream output){
        String pathway = "";
        HuffmanNode temp = huffTree;
        helperWrite(temp, output, pathway);
    }

    /*
     * Post: Prints to the output file in preorder, 
     * @Param temp | type HuffmanNode | a referance to the decision 
     * algorithum, used to naviagte through the algorithum
     * @Param output | type PrintStream | used to print to the output file
     * @Param pathway | type String | used to print to the output file
     * 
     */
    private void helperWrite(HuffmanNode temp, PrintStream output, String pathway){//can the pathway variable be done with an int?
        if(temp != null){
            if(temp.leftNode == null && temp.rightNode == null){
                output.println(temp.charValue);
                output.println(pathway);
            }
            else{
                helperWrite(temp.leftNode, output, pathway + "0");
                helperWrite(temp.rightNode, output, pathway + "1");
            }
        }
    }

    /*
     * Pre: must've passed an .code file assumes scanner 
     * contains a broken decoding algorithum in standred format.
     * Post: reconstructs the decoding algorithum from the file 
     * in standred format
     * @Param input | type: Scanner | used to collect input from a file
     */
    public HuffmanTree(Scanner input){
        while(input.hasNextLine()){
            int intCharVal = Integer.parseInt(input.nextLine());
            String binary = input.nextLine();
            int index = 0;
            huffTree = HuffmanTreeHelper(huffTree, binary, index, intCharVal);
        }
    }

    /*
     * Post: reconstructs a decoding algorithum based on the 
     * information on the file assumes the file is in standred format
     * @Param huffTree | type: HuffmanNode | used to help construct the coding algorithum
     * @Param binary | type: String | used to figure out exactly where to place each leaf
     * @Param index | type: int | used as a pointer for binary
     * @Param intCharValue | type int | integer representation of a character
     * @Returns HuffmanNode | returns a reconstructed decoding algorithum 
     */
    private HuffmanNode HuffmanTreeHelper(HuffmanNode huffTree, String binary,
            int index, int intCharVal){

        String binaryCurrent = binary.substring(0,index);
        if(huffTree == null){
            huffTree = new HuffmanNode(-1);
        }
        if(binary.equals(binaryCurrent)){
            huffTree.charValue = intCharVal;
        }
        else{
            index++;
            if(index > binary.length()){
                return null;
            }
            if(binary.charAt(index-1) == '0'){
                huffTree.leftNode = HuffmanTreeHelper(huffTree.leftNode, 
                binary, index, intCharVal);
            }
            else{
                huffTree.rightNode = HuffmanTreeHelper(huffTree.rightNode, 
                binary, index, intCharVal);
            }
        }
        return huffTree;
    }

    /*
     * Pre: must have access to BitInputStream class, 
     * Post: prints out a uncompressed file to the output file
     * @Param input | BitInputStream input | used in conjuction 
     * with the decision algorithum to determine the output uncompressed
     * version of the original file and prints the the output file
     * @Param input | type BitInputStream | provides a number of bits 
     * to determine the next character in the original file 
     * @Param output | type: PrintStream | used to print to the output file 
     * @Param eof | type : eof | used to determine when to stop the outputing proccess
     * 
     */
    public void decode(BitInputStream input, PrintStream output, int eof){
        HuffmanNode temp = huffTree;
        while(temp.charValue != eof){
            temp = huffTree;
            while(temp.charValue == -1){
                if(input.readBit()==0){
                    temp = temp.leftNode;
                }
                else{
                    temp = temp.rightNode;
                }
            }
            if(temp.charValue != eof){//try to change bcuz its same statement as while loop, and does a lot of checks depending on the size of the actual file
                output.write(temp.charValue);
            }
            
        }
    }
}

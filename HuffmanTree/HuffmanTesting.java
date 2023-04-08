/*
            /Users/HocVuu/Cse143/HuffmanTree/secretmessage.txt/
            /Users/HocVuu/Cse143/HuffmanTree/secretmessage.code/
            /Users/HocVuu/Cse143/HuffmanTree/secretmessage.short/
 */

import java.io.*;
import java.util.*;

public class HuffmanTesting {
    public static final int CHAR_MAX = 256;  // max char value to be encoded

    public static void main(String[] args) throws IOException {
       
        Scanner console = new Scanner(System.in);


        

        makeCodeTest();
        encode();
        decodeTest();
        
        

    }

    public static void makeCodeTest() throws IOException{
        FileInputStream input = new FileInputStream("/Users/HocVuu/Cse143/HuffmanTree/hamlet.txt/");
        int[] count = new int[CHAR_MAX];
        int n = input.read();
        while (n != -1) {
            count[n]++;
            n = input.read();
        }

        HuffmanTree t = new HuffmanTree(count);
        PrintStream output = new PrintStream(new File("/Users/HocVuu/Cse143/HuffmanTree/hamlet.code/"));
        t.write(output);

        
    }
    
    public static void decodeTest() throws IOException{

        

        Scanner codeInput = new Scanner(new File("/Users/HocVuu/Cse143/HuffmanTree/hamlet.code/"));
        HuffmanTree t = new HuffmanTree(codeInput);

        BitInputStream input = new BitInputStream("/Users/HocVuu/Cse143/HuffmanTree/hamlet.short/");
        PrintStream output = new PrintStream(new File("/Users/HocVuu/Cse143/HuffmanTree/hamlet.new/"));
        t.decode(input, output, CHAR_MAX);
        output.close();
        
    }

    public static void encode() throws IOException{
        
        String inFile = "/Users/HocVuu/Cse143/HuffmanTree/hamlet.txt/";
        
        String codeFile = "/Users/HocVuu/Cse143/HuffmanTree/hamlet.code/";
        
        String outputFile = "/Users/HocVuu/Cse143/HuffmanTree/hamlet.short/";
        
        // open code file and record codes
        String[] codes = new String[CHAR_MAX + 1];
        Scanner codeInput = new Scanner(new File(codeFile));
        while (codeInput.hasNextLine()) {
            int n = Integer.parseInt(codeInput.nextLine());
            codes[n] = codeInput.nextLine();
        }
            
        // open source file, open output, encode
        FileInputStream input = new FileInputStream(inFile);
        BitOutputStream output = new BitOutputStream(outputFile);
        boolean done = false;
        int n = input.read();
        while (n != -1) {
            if (codes[n] == null) {
                System.out.println("Your code file has no code for " + n +
                                   " (the character '" + (char)n + "')");
                System.out.println("exiting...");
                System.exit(1);
            }
            writeString(codes[n], output);
            n = input.read();
        }
        writeString(codes[CHAR_MAX], output);
        output.close();
    }

    public static void writeString(String s, BitOutputStream output) {
        for (int i = 0; i < s.length(); i++) {
            output.writeBit(s.charAt(i) - '0');
        }
    }
}

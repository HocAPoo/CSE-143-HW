import java.util.*;    

public class LetterCount {
    public static void main(String[] args){
        LetterInventory inventory1 = new LetterInventory("zcvbnm,{____}lkjhgfdertyu[]");
        System.out.println();
        
        LetterInventory inventory2 = new LetterInventory("aaaz[]zz");
        
        LetterInventory inventory3 = new LetterInventory("");
        
        System.out.println("size method:\n" + inventory3.size()+ "\n");//size
        inventory3.set('g', 3);//.set
        System.out.println("size method:\n" + inventory3.size()+ "\n");//size
        inventory3.set('a', 2);//.set
        System.out.println("size method:\n" + inventory3.size()+ "\n");//size
        inventory3.set('G', 0);//.set
        System.out.println("size method:\n" + inventory3.size()+ "\n");//size
        
        LetterInventory inventory4 = new LetterInventory("");
        System.out.println();
        System.out.println("get method:\n" + inventory1.get('E'));//.get
        System.out.println("\nset method:");
        
        
        
        
        System.out.println("\ntoString method:\n" + inventory1.toString());//toString
        System.out.println(inventory2.toString());//toString

        if(inventory4.isEmpty()){//isEmpty
            System.out.println("\nisEmpty method: \narray is empty\n");
        }
        
        
        System.out.println(inventory1.toString());
        System.out.println(inventory2.toString());
        //LetterInventory sum = (inventory1.add(inventory1));
        //System.out.println(sum.toString());

        
        //System.out.println("size method:\n" + sum.size()+ "\n--------------------\n");//size
        
        //LetterInventory differance = (inventory1.subtract(inventory1));
        //System.out.println(differance.toString());
        //System.out.println("size method:\n" + differance.size());//size
         
    }
}

import java.io.*;
import java.util.*;

public class AssassinTesting{
    public static void main(String[] agrs){
        List<String> names = new ArrayList<>();
        names.add("Athos");
        names.add("Porthos");
        names.add("Aramis");
        
        
    
        //System.out.println(names);

        AssassinManager manager = new AssassinManager(names);
        
        System.out.println("current kill ring is: ");
        manager.printKillRing();

        
        
        manager.kill("Athos");
        System.out.println("\nAthos Dies\n");
        System.out.println("current kill ring is: ");
        manager.printKillRing();
        System.out.println("current graveyard is: ");
        manager.printGraveyard();

 //       System.out.println(manager.winner());
        manager.kill("Aramis");
        System.out.println("\naramis Dies\n");
        System.out.println("current kill ring is: ");
        manager.printKillRing();
        System.out.println("current graveyard is: ");
        manager.printGraveyard();

        System.out.println(manager.winner());
        System.out.println(manager.gameOver());


        
        /*
        System.out.println("current kill ring is: ");
        manager.printKillRing();
        //System.out.println(manager.killRingContains("joe"));
        
        System.out.println("\njoe dies\n");
        manager.kill("joe");
        System.out.println("current kill ring is: ");
        manager.printKillRing();
        System.out.println("current graveyard is: ");
        manager.printGraveyard();
        System.out.println(manager.graveyardContains("joe"));
        

        System.out.println("\nbob dies\n");
        manager.kill("bob");
        System.out.println("current kill ring is: ");
        manager.printKillRing();
        System.out.println("current graveyard is: ");
        manager.printGraveyard();
        System.out.println(manager.graveyardContains("bob"));
         */
    }
}
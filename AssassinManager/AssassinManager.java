
//Alexander Vuu
//TA: Nathan
//CSE143 Section A
//10/9/22
//
//This class manages a killing game where people kill each other.
import java.util.*;

public class AssassinManager {

    // global variables:
    private AssassinNode killNode; // declares a graveyard for all dead people.
    private AssassinNode graveNode; // declare a battlefield for contestants.

    /*
     * Constructs a kill ring with each players names. each player is linked to the
     * person in front of them.
     * With the last person in the kill ring following the first person.
     * if no names are on the passed list for the players throws an exception
     * initilizes global nodes.
     * 
     * Pre: Assume names are non empty strings. no duplicate name.
     * Post: gives each player a person to stalk, this order is determined on the
     * order which the names are passed.
     * 
     * @Param names | type List<String> | a list of names of the players passed in a
     * String<List>
     * 
     * @Param Requirements: names List<String> must have at least one name.
     * 
     * @Exception: if names List<String> has no names of players, throws new
     * IllegalArguemntException
     */
    public AssassinManager(List<String> names) {
        if (names.isEmpty()) {
            throw new IllegalArgumentException("Passed List String is empty");
        }

        killNode = null;
        graveNode = null;

        for (int i = names.size() - 1; i > 0; i--) {
            killNode = new AssassinNode(names.get(i), killNode);
        }
        killNode = new AssassinNode(names.get(0), killNode);
    }

    /*
     * prints a list for all of the players, in order of who was stalking who,
     * (one line 4 spaces) if only one person is in kill ring output is
     * *name* is stalking *name*
     * post: prints the current kill ring, a list of who is stalking who.
     */
    public void printKillRing() {
        AssassinNode current = killNode;
        while (current.next != null) {
            System.out.println("    " + current.name + " is stalking " + current.next.name);
            current = current.next;
        }
        System.out.println("    " + current.name + " is stalking " + killNode.name);
    }

    /*
     * prints the kill ring a list of all the dead players in order of the
     * latest person killed first is on top of the list and least recent
     * person on bottom. Prints names in reverse order. (Most recent kill on near
     * top)
     * post: prints a list of the graveyard with who died and who killed them
     */
    public void printGraveyard() {

        AssassinNode current = graveNode;
        if (current != null) {
            while (current != null) {
                System.out.println("    " + current.name + " was killed by " + current.killer);
                current = current.next;
            }
        }
    }

    /*
     * checks to see if passed parameter's name is
     * still in the kill ring. If he is return true.
     * pre: Ignores cases when comparing, sees if name is in kill ring
     * post: returns true if the person is still on the kill ring
     * 
     * @methods: runs a private method to check if the person is in the kill ring.
     * 
     * @param name | type: String | a name the client passes to check if that person
     * is still in play
     * 
     * @returns: returns true if checked name is still in the list,
     * false if we can't find
     * the person in the list
     * 
     */
    public boolean killRingContains(String name) {
        return graveKillContains(name, killNode);
    }

    /*
     * checks to see if passed parameter's name
     * is in graveyard. If he is return true
     * pre: Ignores cases when comparing, sees in name is in graveyard
     * post: returns true if the person is in the graeyard
     * 
     * @methods: runs a private method to check if the person is in the graveyard.
     * 
     * @param name | type: String | a name the client passes to check if that person
     * is still in play
     * 
     * @returns: returns true if checked name is still in the list, false if we
     * can't find
     * the person on the list
     * 
     */
    public boolean graveyardContains(String name) {
        return graveKillContains(name, graveNode);
    }

    /*
     * checks current to see if if it can find name somewhere
     * in its connected list
     * pre: helper method called by killRingContains and graveyardContains, must've
     * been passed in a name of the person its trying to find and a list that its
     * trying to find it on
     * 
     * post: returns true if it finds the person on the list, false if it
     * cannot find that person.
     * 
     * @param name | type: String | a name that is passed from killRingContains
     * or graveyardContains, tries to find name in current.
     * 
     * @param current | type: AssassinNode | a node that is passed from
     * killRingContains or graveyardContains its a list of all the names
     * currently dead or still in the kill ring
     * 
     * @returns true if name appears false if it doesn't
     */
    private boolean graveKillContains(String name, AssassinNode current) {
        while (current != null) {
            // if (current.name.equals(name.toLowerCase())) {
            if (current.name.toLowerCase().equals(name.toLowerCase())) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /*
     * Accessor Method: cheecks to see if the game is over
     * (kill ring has one man standing)
     * 
     * @returns true if the game is over
     * false if the game is not over
     * (there are still more than one person playing)
     */
    public boolean gameOver() {
        return killNode.next == null;
    }

    /*
     * Accessor Method:
     * pre: there should be one person left in the game then asks
     * for the name of the winner
     * post: returns the name of the winner, if the game is not over,
     * returns null
     * 
     * @returns the name for the winner, returns null if the game is not over.
     */
    public String winner() {
        if (!gameOver()) {
            return null;
        }
        return killNode.name;
    }

    /*
     * Mutator Method:
     * Removes the name of the person the client passes from the kill ring and
     * into the graveyard. Records the name of dead person's killer,
     * doesn't change order (except for the dead person, killer gets linked to next
     * person)
     * 
     * pre: must've passed a name of the person client wants to kill.
     * post: removes the person from the kill ring and drags them to the graveyard
     * 
     * Local Variable | deadGuy | the person that the client is going to kill
     * Local Variable | prev | the person the would've killed the person the
     * client wants to kill
     * Local Variable | temp | holds the graveyard, so most recent dead person is
     * in the front of the graveyard list
     * 
     * @param name | type String | the name of the person the client is trying to
     * remove
     * from the kill ring and place into the graveyard;
     * 
     * @Exception throws IllegalArgumentException if name is not part of the current
     * kill ring
     * 
     * @Exception throws an IllegalStateException if the game is over
     */
    public void kill(String name) {
        if (!killRingContains(name)) {
            throw new IllegalArgumentException("entered name is not in the killRing");
        } else if (gameOver()) {
            throw new IllegalStateException("game is already over");
        }
        AssassinNode deadGuy = killNode.next;
        AssassinNode prev = killNode;
        AssassinNode temp;
        while ((deadGuy != null) && (!deadGuy.name.equalsIgnoreCase(name))) {
            prev = prev.next;
            deadGuy = deadGuy.next;
        }

        if (killNode.name.equalsIgnoreCase(name)) {
            deadGuy = killNode;
            killNode = killNode.next;
        } else { // everyone else case
            prev.next = deadGuy.next;
        }
        temp = graveNode;
        deadGuy.killer = prev.name;
        graveNode = deadGuy;
        deadGuy.next = temp;
    }
}

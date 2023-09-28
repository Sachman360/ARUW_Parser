import java.util.*;

public class Parser {
    /** Declaration of all class variables **/

    // data stores all words inputted to the Parser. It is not strictly
    // necessary for the rules of this project, but rather a helpful addition.
    ArrayList<Integer> data;
    // packets is a list of lists. Each inner list is a packet.
    ArrayList<ArrayList<Integer>> packets;
    // The following two boolean variables operate in the same way. isVelocity
    // is set to true if an incoming word (at the beginning of a packet) is a 1,
    // and set to false when a packet ends. Same for isVision (with an incoming 2).
    boolean isVelocity;
    boolean isVision;
    // numValues keeps track of the number of values in a packet, and is checked
    // against a max value that changes based on the particular packet, to see
    // when the packet should end.
    int numValues;
    int max;

    public Parser() {
        data = new ArrayList<Integer>();
        packets = new ArrayList<ArrayList<Integer>>();
        isVelocity = false;
        isVision = false;
        numValues = 0;
        max = 0;
    }

    public int getNumPackets() {
        return packets.size();
    }

    /** addWord is the main method used in this program. It receives and interprets
     * data in the form of int words and puts them into packets. **/
    public void addWord(int n) {
        data.add(n);
        numValues++;
        // If a word is the first word in a packet, the following code is run:
        if(numValues == 1) {
            // A new list is added to packets (representing a new packet).
            packets.add(new ArrayList<Integer>());
            // The word n is added to this new list (even though this action occurs
            // in both parts of the if-else, it must be completed in here since the
            // list is created in here).
            packets.get(packets.size() - 1).add(n);
            // Determines which type of packet it is, and sets the corresponding
            // boolean to true.
            if(n == 1) {
                isVelocity = true;
                // For a wheelVelocity packet, we know the packet size of 3 right away.
                // For a visionTarget packet, we must wait until we have the next word
                // to figure out the packet size.
                max = 3;
            } else if(n == 2) {
                isVision = true;
            }
        // If a word is not the first in a packet, the following code is run:
        } else {
            // New word is added to the latest packet.
            packets.get(packets.size() - 1).add(n);
            // If the packet is a visionTarget packet, the second word must be used to
            // determine the packet size, represented by the variable max.
            if(numValues == 2 && isVision) {
                max = (n * 2) + 2;
            }
            // If the packet has reached capacity, boolean variables are reset to false,
            // and numValues to 0 so that the next word that comes in will be perceived
            // as the first in a packet.
            if(numValues == max) {
                numValues = 0;
                isVelocity = false;
                isVision = false;
            }
        }
    }

    // toString returns a list of the packets written in array notation.
    public String toString() {
        String p = "";
        for(int i = 0; i < packets.size(); i++) {
            p += arrayListToString(packets.get(i)) + "\n";
        }
        return p;
    }

    public String dataToString() {
        return arrayListToString(data);
    }

    // Helpful method to print arrayLists.
    public String arrayListToString(ArrayList<Integer> arr) {
        String list = "{";
        for(int i = 0; i < arr.size() - 1; i++) {
            list += arr.get(i) + ", ";
        }
        list += arr.get(arr.size() - 1) + "}";
        return list;
    }
}

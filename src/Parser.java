// Sachal Shaikh
// ARUW Software Technical Question

import java.util.*;

public class Parser {
    /** Declaration of all class variables **/

    // Note: "data" is not necessary for the parser to run properly.
    ArrayList<Integer> data;
    ArrayList<ArrayList<Integer>> packets;
    boolean isVelocity;
    boolean isVision;
    int numValues;
    int max;

    public Parser() {
        data = new ArrayList<>();
        packets = new ArrayList<>();
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
            packets.add(new ArrayList<Integer>());
            packets.get(packets.size() - 1).add(n);
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
            p += packets.get(i) + "\n";
        }
        return p;
    }

    // returns data in string form
    public String getData() {
        return data + "";
    }

}

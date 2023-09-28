import java.util.*;

public class Parser {
    ArrayList<Integer> data = new ArrayList<Integer>();
    ArrayList<ArrayList<Integer>> packets = new ArrayList<ArrayList<Integer>>();
    int numPackets;
    boolean isVelocity;
    boolean isVision;
    int numValues;
    int max;

    public Parser() {
        numPackets = 0;
        isVelocity = false;
        isVision = false;
        numValues = 0;
        max = 0;
    }

    public void addWord(int n) {
        data.add(n);
        numValues++;
        if(numValues == 1) {
            packets.add(new ArrayList<Integer>());
            packets.get(numPackets).add(n);
            if(n == 1) {
                isVelocity = true;
            } else if(n == 2) {
                isVision = true;
            }
        } else if(isVelocity) {
            packets.get(numPackets).add(n);
            if(numValues == 3) {
                finishPacket();
            }
        } else if(isVision) {
            if(numValues == 2) {
                max = n * 2;
                packets.get(numPackets).add(n);
            } else {
                packets.get(numPackets).add(n);
                if(numValues == max + 2) {
                    finishPacket();
                }
            }
        }
    }

    public void finishPacket() {
        numPackets++;
        numValues = 0;
        isVelocity = false;
        isVision = false;
    }

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

    public String arrayListToString(ArrayList<Integer> arr) {
        String list = "{";
        for(int i = 0; i < arr.size() - 1; i++) {
            list += arr.get(i) + ", ";
        }
        list += arr.get(arr.size() - 1) + "}";
        return list;
    }
}

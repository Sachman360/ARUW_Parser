import java.util.*;

public class Parser {
    ArrayList<Integer> data = new ArrayList<Integer>();
    ArrayList<int[]> packets = new ArrayList<int[]>();
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
            if(n == 1) {
                packets.add(new int[3]);
                packets.get(numPackets)[numValues - 1] = n;
                isVelocity = true;
            } else if(n == 2) {
                isVision = true;
            }
        } else if(isVelocity) {
            packets.get(numPackets)[numValues - 1] = n;
            if(numValues == 3) {
                finishPacket();
            }
        } else if(isVision) {
            if(numValues == 2) {
                max = n * 2;
                packets.add(new int[max + 2]);
                packets.get(numPackets)[0] = 2;
                packets.get(numPackets)[1] = n;
            } else {
                packets.get(numPackets)[numValues - 1] = n;
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

    public void printPackets() {
        for(int i = 0; i < packets.size(); i++) {
            System.out.print("{");
            for(int j = 0; j < packets.get(i).length - 1; j++) {
                System.out.print(packets.get(i)[j] + ", ");
            }
            System.out.println(packets.get(i)[packets.get(i).length - 1] + "}");
        }
    }
    public void printData() {
        System.out.print("{");
        for(int i = 0; i < data.size() - 1; i++) {
            System.out.print(data.get(i) + ", ");
        }
        System.out.println(data.get(data.size() - 1) + "}");
    }
}

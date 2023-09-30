public class Main {
    public static void main(String[] args) {
        Parser p = new Parser();

        // In this test case, the data is pre-written, but is still fed one at a time
        // into the Parser.
        int[] data = {2, 1, 2, 1, 1, 2, 3, 1, 4, 5, 2, 2, 1, 4, 5, 9, 1, 7, 8, 2, 3, 4, 5, 6, 7, 8, 9, 1};
        feedData(p, data);

        System.out.println(p.dataToString());
        System.out.println(p);
    }

    public static void feedData(Parser p, int[] data) {
        for(int i = 0; i < data.length; i++) {
            p.addWord(data[i]);
        }
    }
}
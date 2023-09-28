// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Parser p = new Parser();
        int[] data = {2, 1, 2, 1, 1, 2, 3, 1, 4, 5, 2, 2, 1, 4, 5, 9, 1, 7, 8, 2, 3, 4, 5, 6, 7, 8, 9};
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
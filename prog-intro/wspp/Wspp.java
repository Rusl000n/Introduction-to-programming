package wspp;

import java.util.*;
import java.io.*;

public class Wspp {
    public static void main(String[] args) {
        Scanner in;
        try {
            in = new Scanner(new File(args[0]), "UTF-8");
        } catch (FileNotFoundException | ArrayIndexOutOfBoundsException e) {
            in = new Scanner(System.in);
            System.err.println("Error: " + e.toString());
        }
        Map<String, ArrayList<Integer>> words = new LinkedHashMap<String, ArrayList<Integer>>();
        int sch = 1;
        while (in.hasNextLine()) {
            char[] newLine = in.nextLine().toCharArray();
            int j = 0, i;
            for (i = 0; i < newLine.length; i++) {
                if (!(Character.isLetter(newLine[i]) || Character.getType(newLine[i]) == Character.DASH_PUNCTUATION || newLine[i] == '\'')) {
                    if (i != j) addToMap(words, new String(newLine, j, i - j).toLowerCase(), sch++);
                    j = i + 1;
                }
            }
            i--;
            if (i > 0 && (Character.isLetter(newLine[i]) || Character.getType(newLine[i]) == Character.DASH_PUNCTUATION || newLine[i] == '\''))
                if (i != j) addToMap(words, new String(newLine, j, i - j + 1).toLowerCase(), sch++);
        }
        PrintWriter out;
        try {
            out = new PrintWriter(new File(args[1]));
        } catch (FileNotFoundException | ArrayIndexOutOfBoundsException | SecurityException e) {
            out = new PrintWriter(System.out);
            System.err.println("Error: " + e.toString());
        }

        for (String key : words.keySet()) {
            out.print(key);
            for (int i : words.get(key))
                out.print(" " + i);
            out.println();
        }
        out.close();
        in.close();
    }

    public static void addToMap(Map<String, ArrayList<Integer>> map, String key, int j) {
        if (map.containsKey(key)) {
            ArrayList<Integer> values = map.get(key);
            values.add(j);
            int amount = values.get(0);
            values.set(0, ++amount);
            map.replace(key, values);
        } else {
            ArrayList<Integer> values = new ArrayList<Integer>();
            values.add(1);
            values.add(j);
            map.put(key, values);
        }
    }
}
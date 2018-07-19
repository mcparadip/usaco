/*
ID: oliver.6
LANG: JAVA
TASK: namenum
*/

// USACO Training, Chapter 1.3
// Problem 4. Name that Number
// **********

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class namenum {

    private static String S;
    private static String[] keypad;

    public static void main(String[] args) throws IOException {

        keypad = new String[] {
                "",
                "",
                "ABC",
                "DEF",
                "GHI",
                "JKL",
                "MNO",
                "PRS",
                "TUV",
                "WXY"
        };

        BufferedReader in = new BufferedReader(new FileReader("namenum.in"));
        S = in.readLine();
        in.close();

        BufferedReader dict = new BufferedReader(new FileReader("dict.txt"));
        Stream<String> lines = dict.lines();

        lines = lines.filter(line -> line.length() == S.length());

        for (int i = 0; i < S.length(); i++) {

            final int ii = i;
            int c = Character.getNumericValue(S.charAt(i));

            lines = lines.filter(line -> keypad[c].indexOf(line.charAt(ii)) > -1);

        }

        PrintWriter out = new PrintWriter("namenum.out");

        List<String> names = lines.collect(Collectors.toList());

        if (names.size() == 0) {
            out.println("NONE");
        }

        for (String name: names) {
            out.println(name);
        }

        dict.close();
        out.close();

    }

}

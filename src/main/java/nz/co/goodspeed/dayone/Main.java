package nz.co.goodspeed.dayone;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        int result = 0;
        try(
                InputStreamReader reader = new InputStreamReader(Main.class.getClassLoader().getResourceAsStream("./dayone/dayone.txt"));
                BufferedReader bufferedReader = new BufferedReader(reader)
                ) {
            String line = null;
            while((line = bufferedReader.readLine()) != null) {
                IntegerFinder finder = new IntegerFinder(line);
                result += finder.getValue();
            }
        }
        System.out.println(result);
    }
}
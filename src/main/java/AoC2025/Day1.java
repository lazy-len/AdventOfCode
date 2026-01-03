package AoC2025;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class day1 {


    static void main() {
        
        // Day 1 part 1 :
        // Load the rotations from the file into a List
        // Loop through the List and check each rotation
        // - if you have to rotate left (L = subtract) or right (R = add)
        // - the amount you have to subtract/add
        // - calculate the new dial position
        //    - subtract / add new dialpostion
        //    - think of something that keeps the new dial position between 0 and 99. 
        //    - count the amount of times the dial ends on 0.
        
        // Good to know:
        // Dial goes from 0 - 99
        // 0 - 1 = 99
        // 99 + 1 = 0
        // Dial starts at 50
        
        String filepath = "src/main/resources/2025/day1.txt";
        List<String> dialRotations = readFileWithStreams(filepath);
        
        int dial = 50;
        int answer = 0;

        for (String rotationStr : dialRotations) {
            // convert rotation string to integer by removing the first letter -> i.e. R48 = 48.
            int rotation = Integer.parseInt( rotationStr.substring(1) );

            if ( rotationStr.charAt(0) == 'L' ) {
                // L -> rotate left -> subtract
                dial = rotateLeft( dial, rotation );
            }

            if ( rotationStr.charAt(0) == 'R' ) {
                // R -> rotate right -> add
                dial = rotateRight( dial, rotation );
            }

            if (dial == 0) {
                // if the dial hits 0 then you have to count that.
                answer ++;
            }

            // print progress to check.
//            System.out.printf("The dial is rotated %s to point at %d \n", rotationStr, dial);

        }

        System.out.println("Dial ends on 0 : " + answer);
    }

    static int rotateLeft(int dial, int rotation){
        // calculate the new dial position
        int newDial = dial - rotation;

        // baseCase : stop when dial is between 0 and 99.
        if (newDial >= 0 && newDial <= 99) {
            return newDial;
        }

        // if dial is not between 0 and 99. increase it with 100 and try again!
        return rotateLeft( newDial + 100, 0 );

    }

    static int rotateRight( int dial, int rotation ) {
        int newDial = dial + rotation;
        if (newDial >= 0 && newDial <= 99) {
            return newDial;
        }
        return rotateRight(newDial - 100, 0);
    }


    static List<String> readFileWithScanner(String filepath){
        List<String> stringList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(filepath));
            while (scanner.hasNextLine()) {
                stringList.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return stringList;
    }

    static List<String> readFileWithStreams(String filepath) {
        List<String> stringList = new ArrayList<>();
        File file = new File(filepath);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null ) {
                stringList.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return stringList;
    }
}

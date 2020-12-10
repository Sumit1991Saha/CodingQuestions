package codechef.may;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class CoronavirusSpread {

    public static void main(String[] args) throws IOException {

        int maxDist = 2;

        Scanner in = new Scanner(System.in);
        if (in.hasNextLine()) {
            int noOfTestCases = Integer.parseInt(in.nextLine());
            for (int i = 0; i < noOfTestCases; ++i) {
                int noOfPeople = Integer.parseInt(in.nextLine());
                String data = in.nextLine();
                List<Integer> group = new LinkedList<>();
                int[] pos = Arrays.stream(data.split(" ")).mapToInt(Integer::parseInt).toArray();
                int count = 1;
                for (int j = 1; j < noOfPeople; ++j) {
                    if (pos[j] - pos[j - 1] <= maxDist) {
                        count++;
                    } else {
                        group.add(count);
                        count = 1;
                    }
                }
                // To handle last group
                group.add(count);

                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;
                Iterator<Integer> it = group.iterator();
                while (it.hasNext()) {
                    int val = it.next();
                    if (val < min) {
                        min = val;
                    }

                    if (val > max) {
                        max = val;
                    }
                }
                System.out.println(min + " " + max);


            }
        }
    }
}

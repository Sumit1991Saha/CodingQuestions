package codechef.may;

import sun.jvm.hotspot.runtime.linux.LinuxSignals;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChefBitwiseProduct {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        if (in.hasNextLine()) {
            int noOfTestCases = Integer.parseInt(in.nextLine());
            for (int i = 0; i < noOfTestCases; ++i) {
                String[] data = in.nextLine().split(" ");
                int X = Integer.parseInt(data[0]);
                int Y = Integer.parseInt(data[1]);
                int L = Integer.parseInt(data[2]);
                int R = Integer.parseInt(data[3]);

                List<Integer> possibleOptions = new ArrayList<>();
                double val = 1;
                int count = 1;
                while (val < L) {
                    count++;
                    val = Math.pow(2, count) - 1;
                }

                while (val < R) {
                    possibleOptions.add((int)val);
                    count++;
                    val = Math.pow(2, count) - 1;
                }

                int answer = possibleOptions.get(0);
                int max = (X & answer)* (Y & answer);
                for (int j = 1; j < possibleOptions.size(); ++j) {
                    int currentAnswer = possibleOptions.get(j);
                    int current = (X & currentAnswer)* (Y & currentAnswer);
                    if (current > max) {
                        max = current;
                        answer = currentAnswer;
                    }
                }
                System.out.println(answer);
            }
        }
    }
}

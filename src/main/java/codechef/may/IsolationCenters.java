package codechef.may;

import java.util.Scanner;

public class IsolationCenters {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        if (in.hasNextLine()) {
            int noOfTestCases = Integer.parseInt(in.nextLine());
            for (int i = 0; i < noOfTestCases; ++i) {
                String[] data = in.nextLine().split(" ");
                int N = Integer.parseInt(data[0]);
                int noOfQueries = Integer.parseInt(data[1]);
                String content = in.nextLine();
                int[] charCount = computeCharCount(content);
                for (int j = 0; j < noOfQueries; ++j) {
                    int noOfIsolationCenter = Integer.parseInt(in.nextLine());
                    int pendingQueueSize = 0;
                    for (int k = 0; k < charCount.length; ++k) {
                        if (charCount[k] > noOfIsolationCenter) {
                            pendingQueueSize += charCount[k] - noOfIsolationCenter;
                        }
                    }
                    System.out.println(pendingQueueSize);
                }
            }
        }
    }

    static int[] computeCharCount(String content) {
        int[] count = new int[26];
        for (int i = 0; i < content.length(); ++i) {
            count[content.charAt(i) - 'a']++;
        }
        return count;
    }
}

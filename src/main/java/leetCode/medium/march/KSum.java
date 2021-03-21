package leetCode.medium.march;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KSum {

    public static void main(String[] args) {
        System.out.println(kSum(new int[]{-1, 0, 1, 2, -1, -4, -2, -3, 3, 0, 4}, 0, 3));
        System.out.println(kSum(new int[]{-2, 0, 0, 2, 2}, 0, 3));
        System.out.println(kSum(new int[]{1,0,-1,0,-2,2}, 0, 4));
        System.out.println(kSum(new int[]{-1, 0, 1, 2, -1, -4, -2, -3, 3, 0, 4}, 0, 5));
    }

    public static List<List<Integer>> kSum(int[] numbers, int target, int noOfElementsInTheCombination) {
        if (noOfElementsInTheCombination == 2) {
            return twoSum(numbers, target);
        } else {
            Arrays.sort(numbers);
            Set<List<Integer>> values = new HashSet<>();
            for (int i = 0; i < numbers.length; ++i) {
                int firstNumber = numbers[i];
                int[] subNumbersArray = Arrays.copyOfRange(numbers, i + 1, numbers.length);
                List<List<Integer>> possibleCombinations = kSum(subNumbersArray, target - firstNumber,
                        noOfElementsInTheCombination - 1);
                if (!possibleCombinations.isEmpty()) {
                    for (List<Integer> combination : possibleCombinations) {
                        combination.add(firstNumber);
                    }
                    values.addAll(possibleCombinations);
                }
            }
            return new ArrayList<>(values);
        }
    }

    public static List<List<Integer>> twoSum(int[] numbers, int target) {
        List<List<Integer>> possibleCombinations = new ArrayList<>();
        int startIndex = 0;
        int endIndex = numbers.length - 1;
        int sum;
        while (startIndex < endIndex) {
            sum = numbers[startIndex] + numbers[endIndex];
            if (sum == target) {
                int finalStartIndex = startIndex;
                int finalEndIndex = endIndex;
                possibleCombinations.add(new ArrayList<Integer>() {{
                    add(numbers[finalStartIndex]);
                    add(numbers[finalEndIndex]);
                }});
                startIndex++;
                endIndex--;
            } else if (sum < target) {
                startIndex++;
            } else {
                endIndex--;
            }
        }
        return possibleCombinations;
    }
}

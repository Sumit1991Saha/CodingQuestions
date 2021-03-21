package leetCode.medium.march;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/4sum/
 */
public class FourSum {
    public static void main(String[] args) {
        System.out.println(fourSum(new int[]{1,0,-1,0,-2,2}, 0));
    }
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        Set<List<Integer>> possibleSolutions = new HashSet<>();
        for (int i = 0; i < nums.length; ++i) {
            int[] subNumbersArray = Arrays.copyOfRange(nums, i + 1, nums.length);
            List<List<Integer>> possibleCombinations = threeSum(subNumbersArray, target-nums[i]);
            if (!possibleCombinations.isEmpty()) {
                for (List<Integer> combination : possibleCombinations) {
                    combination.add(nums[i]);
                    Collections.sort(combination);
                    possibleSolutions.add(combination);
                }
            }
        }
        return new ArrayList<>(possibleSolutions);

    }

    public static List<List<Integer>> threeSum(int[] nums, int target) {
        List<List<Integer>> possibleSolutions = new ArrayList<>();
        for (int i = 0; i < nums.length; ++i) {
            int[] subNumbersArray = Arrays.copyOfRange(nums, i + 1, nums.length);
            List<List<Integer>> possibleCombinations = twoSum(subNumbersArray, target - nums[i]);
            if (!possibleCombinations.isEmpty()) {
                for (List<Integer> combination : possibleCombinations) {
                    combination.add(nums[i]);
                }
                possibleSolutions.addAll(possibleCombinations);
            }
        }
        return possibleSolutions;
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

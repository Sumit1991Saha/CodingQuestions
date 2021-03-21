package leetCode.medium.march;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/3sum/
 */
public class ThreeSum {
    public static void main(String[] args) {
        System.out.println(threeSum(new int[]{-1, 0, 1, 2, -1, -4, -2, -3, 3, 0, 4}));
        System.out.println(threeSum(new int[]{-2, 0, 0, 2, 2}));


    }

    public static List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> possibleSolutions = new HashSet<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] > 0 || (i != 0 && nums[i] == nums[i - 1])) {
                continue;
            }
            int target = -nums[i];
            int[] subNumbersArray = Arrays.copyOfRange(nums, i + 1, nums.length);
            List<List<Integer>> possibleCombinations = twoSum(subNumbersArray, target);
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

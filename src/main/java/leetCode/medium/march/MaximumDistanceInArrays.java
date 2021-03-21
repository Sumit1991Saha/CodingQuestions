package leetCode.medium.march;

import java.util.ArrayList;
import java.util.List;

public class MaximumDistanceInArrays {

    public static void main(String[] args) {
        List<List<Integer>> data = new ArrayList<List<Integer>>() {{
            add(new ArrayList<Integer>() {{
                add(1);
                add(5);
            }});
            add(new ArrayList<Integer>() {{
                add(3);
                add(4);
            }});
        }};
        System.out.println(maxDistanceUsingSinglePass(data));
    }

    public static int maxDistanceUsingBruteForce(List<List<Integer>> arrays) {
        int numOfArrays = arrays.size();
        int maxSum = 0;
        List<Integer> a;
        List<Integer> b;
        for (int i = 0; i < numOfArrays; ++i) {
            for (int j = 0; j < numOfArrays; ++j) {
                if (i != j) {
                    a = arrays.get(i);
                    b = arrays.get(j);
                    maxSum = Math.max(maxSum, Math.abs(a.get(0) - b.get(b.size() - 1)));
                }
            }
        }
        return maxSum;
    }

    public static int maxDistanceUsingSinglePass(List<List<Integer>> arrays) {
        int numOfArrays = arrays.size();
        int firstMinima = Integer.MAX_VALUE;
        int secondMinima = Integer.MAX_VALUE;
        int firstMaxima = Integer.MIN_VALUE;
        int secondMaxima = Integer.MIN_VALUE;
        int arrayIndexOfFirstMinima = 0;
        int arrayIndexOfFirstMaxima = 0;
        for (int i = 0; i < numOfArrays; ++i) {
            List<Integer> array = arrays.get(i);
            int arraySize = array.size();
            if (arraySize != 0) {
                int firstValue = array.get(0);
                if (firstValue <= firstMinima) {
                    secondMinima = firstMinima;
                    firstMinima = firstValue;
                    arrayIndexOfFirstMinima = i;
                } else if (firstValue < secondMinima) {
                    secondMinima = firstValue;
                }
                int lastValue = array.get(arraySize - 1);
                if (lastValue >= firstMaxima) {
                    secondMaxima = firstMaxima;
                    firstMaxima = lastValue;
                    arrayIndexOfFirstMaxima = i;
                } else if (lastValue > secondMaxima) {
                    secondMaxima = lastValue;
                }
            }
        }

        return arrayIndexOfFirstMaxima == arrayIndexOfFirstMinima ?
                Math.max(Math.abs(firstMaxima - secondMinima), Math.abs(secondMaxima - firstMinima)) :
                Math.abs(firstMaxima - firstMinima);
    }
}

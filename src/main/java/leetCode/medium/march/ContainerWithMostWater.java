package leetCode.medium.march;

public class ContainerWithMostWater {
    public static void main(String[] args) {
        System.out.println(maxArea(new int[] {1,8,6,2,51,50,8,3,7}));
    }

    public static int maxArea(int[] height) {
        int maxArea = 0;
        int startIndex = 0;
        int endIndex = height.length - 1;
        int distance = endIndex - startIndex;
        int currentArea = Math.min(height[startIndex], height[endIndex]) * distance;
        maxArea = currentArea;
        while (startIndex < endIndex) {
            if (height[startIndex] < height[endIndex]) {
                startIndex++;
            } else {
                endIndex--;
            }
            distance = endIndex - startIndex;
            currentArea = Math.min(height[startIndex], height[endIndex]) * distance;
            maxArea = Math.max(maxArea, currentArea);
        }

        return maxArea;
    }
}

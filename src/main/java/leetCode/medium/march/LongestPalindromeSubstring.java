package leetCode.medium.march;

public class LongestPalindromeSubstring {

    public static void main(String[] args) {
        System.out.println(longestPalindrome("cbb"));

        System.out.println(longestPalindrome("abcbadbabd"));    }

    public static String longestPalindrome(String data) {
        String longestPalindrome = data.substring(0, 1); //base case first character is the longest palindrome in the string
        String oddLengthPalindrome = longestPalindromeUsingOddStringlength(data);
        if (longestPalindrome.length() < oddLengthPalindrome.length()) {
            longestPalindrome = oddLengthPalindrome;
        }
        String evenLengthPalindrome = longestPalindromeUsingEvenStringlength(data);
        if (longestPalindrome.length() < evenLengthPalindrome.length()) {
            longestPalindrome = evenLengthPalindrome;
        }
        return longestPalindrome;
    }

    private static String longestPalindromeUsingOddStringlength(String data) {
        String longestPalindrome = "";
        int dataLength = data.length();
        for (int i = 1; i < dataLength; ++i) {

            // Logic for odd length palindrome
            int prevIndex = i - 1;
            int nextIndex = i + 1;
            String currentPalindrome= "";
            while (prevIndex >= 0 && nextIndex < dataLength) {
                if (data.charAt(prevIndex)==data.charAt(nextIndex)) {
                    currentPalindrome = data.substring(prevIndex, nextIndex+1);
                    prevIndex--;
                    nextIndex++;
                } else {
                    break;
                }
            }
            if (currentPalindrome.length() > longestPalindrome.length()) {
                longestPalindrome = currentPalindrome;
            }
        }
        return longestPalindrome;
    }

    private static String longestPalindromeUsingEvenStringlength(String data) {
        String longestPalindrome = "";
        int dataLength = data.length();
        for (int i = 1; i < dataLength; ++i) {

            // Logic for odd length palindrome
            int prevIndex = i - 1;
            int currentIndex = i;
            String currentPalindrome= "";
            while (prevIndex >= 0 && currentIndex < dataLength) {
                if (data.charAt(prevIndex)==data.charAt(currentIndex)) {
                    currentPalindrome = data.substring(prevIndex, currentIndex+1);
                    prevIndex--;
                    currentIndex++;
                } else {
                    break;
                }
            }
            if (currentPalindrome.length() > longestPalindrome.length()) {
                longestPalindrome = currentPalindrome;
            }
        }
        return longestPalindrome;
    }
}

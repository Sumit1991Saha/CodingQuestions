package CodingInterviews.O9Solutions;

/**
 * convert string s1 into palindrome such that it contains string S2, minimum no. of character change needs to be returned.
 */
public class PalindromicSubstrings {
    public static void main(String[] args) {
        System.out.println(palindromeStrings("archit", "ar"));
        //System.out.println(palindromeStrings("aaaaa", "bbb"));
    }

    private static int palindromeStrings(String S1, String S2) {
        int l1 = S1.length(), l2 = S2.length();
        int ans = Integer.MAX_VALUE;
        if (l2 > l1) {
            ans = -1;
        } else {
            for (int i = 0; i < l1 - l2 + 1; i++) {
                String temp = S1.substring(0, i) + S2 + S1.substring(i + l2); // place S2 in all possible positions in S1
                int cost = 0;
                // calculate cost to place S2
                for (int j = i; j < i + l2; j++) {
                    if (S1.charAt(j) != temp.charAt(j)) {
                        cost++;
                    }
                }
                int z = 0;
                // find the cost to convert new string to palindrome
                for (int j = 0; j < Math.ceil(l1 / 2.0); j++) {
                    if ((j < i || j >= i + l2) && temp.charAt(j) != temp.charAt(l1 - j - 1)) {// if S2 is in the first half of new string
                        cost++;
                    } else if (temp.charAt(j) != temp.charAt(l1 - j - 1) && (l1 - j - 1 < i || l1 - j - 1 >= i + l2)) { // if S2 is in the second half of new string
                        cost++;
                    } else if (temp.charAt(j) != temp.charAt(l1 - j - 1)) { // if S2 is in both halves
                        z = 1;
                        break;
                    }
                }
                if (z == 0) {
                    ans = Math.min(ans, cost);
                }
            }
            if (ans == Integer.MAX_VALUE) {
                ans = -1;
            }
        }
        return ans;
    }
}

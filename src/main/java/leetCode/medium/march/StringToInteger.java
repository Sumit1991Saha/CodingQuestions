package leetCode.medium.march;

public class StringToInteger {

    public static void main(String[] args) {
        System.out.println(myAtoi(""));
        System.out.println(myAtoi("+1"));
        System.out.println(myAtoi("42"));
        System.out.println(myAtoi("   -42"));
        System.out.println(myAtoi("4193 with words"));
        System.out.println(myAtoi("words and 987"));
        System.out.println(myAtoi("-91283472332"));
    }

    public static int myAtoi(String s) {
        s = s.trim();
        boolean isNegative = false;

        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i == 0) {
                if (s.charAt(0) == '-') {
                    isNegative = true;
                    continue;
                } else if (s.charAt(0) == '+') {
                    continue;
                }
            }
            char charValue = s.charAt(i);
            int num = charValue - '0';
            if (num >= 0 && num <=9) {
                if (result > Integer.MAX_VALUE / 10 ||
                        (result == Integer.MAX_VALUE / 10 && num > Integer.MAX_VALUE % 10)) {
                    return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                }
                result = result * 10 + num;
            } else {
                break;
            }
        }

        if (isNegative) {
            result = -1 * result;
        }
        return result;
    }
}

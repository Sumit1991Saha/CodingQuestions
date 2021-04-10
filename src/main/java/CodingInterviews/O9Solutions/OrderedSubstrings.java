package CodingInterviews.O9Solutions;

import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Find the rank of the given string in a sortedSet, made from all the substring of the given string.
 */
public class OrderedSubstrings {

    public static void main(String[] args) {
        SortedSet<String> sortedSet = new TreeSet<>();
        String data = "acb";
        printAllCombinations(sortedSet, "", data);
        System.out.println(sortedSet);
        Iterator<String> iter = sortedSet.iterator();
        int rank = 0;
        while (iter.hasNext()) {
            String subString = iter.next();
            if (subString.equals(data)) {
                break;
            }
            if(data.contains(subString)) {
                rank++;
            }
        }
        System.out.println(rank);
    }

    public static void printAllCombinations(SortedSet<String> orderStringSet, String soFar, String rest) {
        if(rest.isEmpty()) {
            orderStringSet.add(soFar);
        } else {
            printAllCombinations(orderStringSet,soFar + rest.substring(0,1), rest.substring(1));
            printAllCombinations(orderStringSet, soFar , rest.substring(1));
        }
    }
}

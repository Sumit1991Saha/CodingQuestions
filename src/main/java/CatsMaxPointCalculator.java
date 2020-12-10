import java.util.Arrays;
import java.util.Collections;

public class CatsMaxPointCalculator {

    public static void main(String[] args) {

        int currentCTScore = 10296;
        int currentOpponentScore = 4620;
        int currentCTMultiplier = 102;
        int currentOpponentMultiplier = 42;
        int laterCTMultiplier = 60;
        int laterOpponentMultiplier = 84;
        int remainingMinutes = 1320;

        int i = 0;
        int currentDifferenceOfScore = (currentCTScore + (remainingMinutes * currentCTMultiplier))
                - (currentOpponentScore + (remainingMinutes * currentOpponentMultiplier));
        int newDifferenceOFScore = 0;
        while (i < remainingMinutes) {
            i++;
            int ctScoreAtIthMin = (currentCTScore + (remainingMinutes - i) * currentCTMultiplier + i * laterCTMultiplier);
            int opponentScoreAtIthMin = (currentOpponentScore + (remainingMinutes - i) * currentOpponentMultiplier + i * laterOpponentMultiplier);
            System.out.println("Minutes remaining on the clock :- " + i +
                    ", CT score :- " + ctScoreAtIthMin +
                    ", Opponent score :- " + opponentScoreAtIthMin);
            newDifferenceOFScore = ctScoreAtIthMin - opponentScoreAtIthMin;
            if (newDifferenceOFScore > currentDifferenceOfScore) {
                break;
            } else {
                currentDifferenceOfScore = newDifferenceOFScore;
            }
        }

    }
}

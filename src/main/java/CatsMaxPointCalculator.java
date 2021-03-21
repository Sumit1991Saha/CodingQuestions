import java.util.concurrent.TimeUnit;

public class CatsMaxPointCalculator {

    public static void main(String[] args) {

        int currentAVScore = 1806;
        int currentOpponentScore = 2466;
        int currentAVMultiplier = 63;
        int currentOpponentMultiplier = 81;
        int laterAVMultiplier = 84;
        int laterOpponentMultiplier = 60;
        int remainingMinutes = (int)TimeUnit.HOURS.toMinutes(23) + 14; // If you want the match to last full 24 hours enter the complete time.
        int maxScorePossible = 140000;
        //weScoreHighInitiallyThenGiveBuildingsLater(currentAVScore, currentOpponentScore,
          //    currentAVMultiplier, currentOpponentMultiplier, laterAVMultiplier, laterOpponentMultiplier, remainingMinutes);
        weScoreLowInitiallyThenTakeBuildingsLater(currentAVScore, currentOpponentScore,
                currentAVMultiplier, currentOpponentMultiplier, laterAVMultiplier, laterOpponentMultiplier,
                remainingMinutes, maxScorePossible);

    }

    private static void weScoreHighInitiallyThenGiveBuildingsLater(int currentAVScore, int currentOpponentScore,
                                                            int currentAVMultiplier, int currentOpponentMultiplier,
                                                            int laterAVMultiplier, int laterOpponentMultiplier,
                                                            int remainingMinutes) {
        int i = 0;
        int currentDifferenceOfScore = Math.abs((currentAVScore + (remainingMinutes * currentAVMultiplier))
                - (currentOpponentScore + (remainingMinutes * currentOpponentMultiplier)));
        int newDifferenceOFScore = 0;
        while (i < remainingMinutes) {
            i++;
            int ctScoreAtIthMin = (currentAVScore + (remainingMinutes - i) * currentAVMultiplier + i * laterAVMultiplier);
            int opponentScoreAtIthMin = (currentOpponentScore + (remainingMinutes - i) * currentOpponentMultiplier + i * laterOpponentMultiplier);
            System.out.println("Time remaining on the clock :- " + formatTime(i) +
                    ", AV score :- " + ctScoreAtIthMin +
                    ", Opponent score :- " + opponentScoreAtIthMin);
            newDifferenceOFScore = Math.abs(ctScoreAtIthMin - opponentScoreAtIthMin);
            if (newDifferenceOFScore > currentDifferenceOfScore) {
                break;
            } else {
                currentDifferenceOfScore = newDifferenceOFScore;
            }
        }
    }

    private static void weScoreLowInitiallyThenTakeBuildingsLater(int currentAVScore, int currentOpponentScore,
                                                           int currentAVMultiplier, int currentOpponentMultiplier,
                                                           int laterAVMultiplier, int laterOpponentMultiplier,
                                                           int remainingMinutes, int maxScorePossible) {
        int i = 0;
        int newMaxScoreForAV = 0;
        while (i < remainingMinutes) {
            i++;
            int ctScoreAtIthMin = (currentAVScore + (remainingMinutes - i) * currentAVMultiplier + i * laterAVMultiplier);
            int opponentScoreAtIthMin = (currentOpponentScore + (remainingMinutes - i) * currentOpponentMultiplier + i * laterOpponentMultiplier);
            System.out.println("Time remaining on the clock :- " + formatTime(i) +
                    ", AV score :- " + ctScoreAtIthMin +
                    ", Opponent score :- " + opponentScoreAtIthMin);
            newMaxScoreForAV  = ctScoreAtIthMin;
        }

        if (newMaxScoreForAV < maxScorePossible) {
            System.out.println("Instant not possible with new multiplier");
        }
    }

    private static String formatTime(int i) {
        int hours = i/60;
        int minutes = i % 60;
        return String.format("%dh %dm", hours, minutes);
    }
}

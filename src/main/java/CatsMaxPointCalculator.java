public class CatsMaxPointCalculator {

    public static void main(String[] args) {

        int currentCTScore = 2199;
        int currentOpponentScore = 2226;
        int currentCTMultiplier = 81;
        int currentOpponentMultiplier = 63;
        int laterCTMultiplier = 102;
        int laterOpponentMultiplier = 42;
        int remainingMinutes = 1365; // If you want the match to last full 24 hours enter the complete time.
        int maxScorePossible = 138000;
        //weScoreHighInitiallyThenGiveBuildingsLater(currentCTScore, currentOpponentScore,
        //      currentCTMultiplier, currentOpponentMultiplier, laterCTMultiplier, laterOpponentMultiplier, remainingMinutes);
        weScoreLowInitiallyThenTakeBuildingsLater(currentCTScore, currentOpponentScore,
                currentCTMultiplier, currentOpponentMultiplier, laterCTMultiplier, laterOpponentMultiplier,
                remainingMinutes, maxScorePossible);

    }

    private static void weScoreHighInitiallyThenGiveBuildingsLater(int currentCTScore, int currentOpponentScore,
                                                            int currentCTMultiplier, int currentOpponentMultiplier,
                                                            int laterCTMultiplier, int laterOpponentMultiplier,
                                                            int remainingMinutes) {
        int i = 0;
        int currentDifferenceOfScore = Math.abs((currentCTScore + (remainingMinutes * currentCTMultiplier))
                - (currentOpponentScore + (remainingMinutes * currentOpponentMultiplier)));
        int newDifferenceOFScore = 0;
        while (i < remainingMinutes) {
            i++;
            int ctScoreAtIthMin = (currentCTScore + (remainingMinutes - i) * currentCTMultiplier + i * laterCTMultiplier);
            int opponentScoreAtIthMin = (currentOpponentScore + (remainingMinutes - i) * currentOpponentMultiplier + i * laterOpponentMultiplier);
            System.out.println("Minutes remaining on the clock :- " + i +
                    ", CT score :- " + ctScoreAtIthMin +
                    ", Opponent score :- " + opponentScoreAtIthMin);
            newDifferenceOFScore = Math.abs(ctScoreAtIthMin - opponentScoreAtIthMin);
            if (newDifferenceOFScore > currentDifferenceOfScore) {
                break;
            } else {
                currentDifferenceOfScore = newDifferenceOFScore;
            }
        }
    }

    private static void weScoreLowInitiallyThenTakeBuildingsLater(int currentCTScore, int currentOpponentScore,
                                                           int currentCTMultiplier, int currentOpponentMultiplier,
                                                           int laterCTMultiplier, int laterOpponentMultiplier,
                                                           int remainingMinutes, int maxScorePossible) {
        int i = 0;
        int newMaxScoreForCT = 0;
        while (i < remainingMinutes) {
            i++;
            int ctScoreAtIthMin = (currentCTScore + (remainingMinutes - i) * currentCTMultiplier + i * laterCTMultiplier);
            int opponentScoreAtIthMin = (currentOpponentScore + (remainingMinutes - i) * currentOpponentMultiplier + i * laterOpponentMultiplier);
            System.out.println("Minutes remaining on the clock :- " + i +
                    ", CT score :- " + ctScoreAtIthMin +
                    ", Opponent score :- " + opponentScoreAtIthMin);
            if (newMaxScoreForCT >= maxScorePossible) {
                break;
            } else {
                newMaxScoreForCT  = ctScoreAtIthMin;
            }
        }

        if (newMaxScoreForCT < maxScorePossible) {
            System.out.println("Instant not possible with new multiplier");
        }
    }
}

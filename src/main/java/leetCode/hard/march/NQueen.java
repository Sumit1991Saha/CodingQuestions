package leetCode.hard.march;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueen {

    public static void main(String[] args) {
        NQueen nQueen = new NQueen();
        nQueen.solveNQueen(4);
    }

    private List<List<String>> solveNQueen(int chessBoardSize) {
        Integer[] queenColumnPositions = new Integer[chessBoardSize];
        List<Integer[]> combinations = new ArrayList<>();

        solveNQueen(0, queenColumnPositions, combinations, chessBoardSize);
        System.out.println(combinations.size());
        List<List<String>> positions = generatePositions(combinations, chessBoardSize);
        printPositions(positions);
        return positions;
    }

    private List<List<String>> generatePositions(List<Integer[]> combinations, int chessBoardSize) {
        List<List<String>> positions = new ArrayList<>();
        char dot = '.';
        char queen = 'Q';
        StringBuilder baseSeq = new StringBuilder();
        for (int i = 0; i < chessBoardSize; ++i) {
            baseSeq.append(dot);
        }
        String seqTemplate = baseSeq.toString();
        combinations.forEach(combination -> {
            List<String> queenSeq = new ArrayList<>();
            for (Integer position : combination) {
                queenSeq.add(seqTemplate.substring(0, position) + queen + seqTemplate.substring(position + 1));
            }
            positions.add(queenSeq);
        });
        return positions;
    }

    private void solveNQueen(int row, Integer[] queenColumnPositions, List<Integer[]> combinations,
                             int chessBoardSize) {
        for (int col = 0; col < chessBoardSize; ++col) {
            if (isPlacingQueenPossible(row, col, queenColumnPositions, chessBoardSize)) {
                addQueen(row, col, queenColumnPositions);
                if (row + 1 == chessBoardSize) {
                    combinations.add(Arrays.copyOf(queenColumnPositions, queenColumnPositions.length));
                } else {
                    solveNQueen(row + 1, queenColumnPositions, combinations, chessBoardSize);
                }
                removeQueen(row, queenColumnPositions);
            }
        }
    }

    private void addQueen(int row, int col, Integer[] queenColumnPositions) {
        queenColumnPositions[row] = col;
    }

    private void removeQueen(int row, Integer[] queenColumnPositions) {
        queenColumnPositions[row] = -1;
    }

    private boolean isPlacingQueenPossible(int rowToCheck, int columnToCheck,
                                           Integer[] queenColumnPositions, int chessBoardSize) {
        // Check for rows is not required,
        // since queenColumnPositions :- 1 D array acts as a boundary that queens are placed in different rows.
        for (int i = 0; i < rowToCheck; ++i) {
            int rowOccupied = i;
            int columnOccupied = queenColumnPositions[i];
            // Check for columns now
            if (columnToCheck == columnOccupied) { // case which checks if either of the column is already occupied
                return false;
            }
            //check for diagonal
            if (doesFallOnSameDiagonal(rowOccupied, columnOccupied, rowToCheck, columnToCheck, chessBoardSize)) {
                return false;
            }
        }
        return true;
    }

    private boolean doesFallOnSameDiagonal(int rowOccupied, int columnOccupied,
                                           int rowToCheck, int columnToCheck, int chessBoardSize) {
        return checkUpperLeftDiagonalForSameDiagonal(rowOccupied - 1, columnOccupied - 1,
                        rowToCheck, columnToCheck, chessBoardSize) ||
                checkLowerRightDiagonalForSameDiagonal(rowOccupied + 1, columnOccupied + 1,
                        rowToCheck, columnToCheck, chessBoardSize) ||
                checkUpperRightDiagonalForSameDiagonal(rowOccupied - 1, columnOccupied + 1,
                        rowToCheck, columnToCheck, chessBoardSize) ||
                checkLowerLeftDiagonalForSameDiagonal(rowOccupied + 1, columnOccupied - 1,
                        rowToCheck, columnToCheck, chessBoardSize);
    }

    private boolean checkUpperLeftDiagonalForSameDiagonal(int startRow, int startColumn,
                                                          int endRow, int endColumn, int chessBoardSize) {
        boolean sameDiagonal = false;
        while (pointInBoard(startRow, startColumn, chessBoardSize)) {
            if (startRow == endRow && startColumn == endColumn) {
                sameDiagonal = true;
                break;
            }
            --startRow;
            --startColumn;
        }
        return sameDiagonal;
    }

    private boolean checkLowerRightDiagonalForSameDiagonal(int startRow, int startColumn,
                                                           int endRow, int endColumn, int chessBoardSize) {
        boolean sameDiagonal = false;
        while (pointInBoard(startRow, startColumn, chessBoardSize)) {
            if (startRow == endRow && startColumn == endColumn) {
                sameDiagonal = true;
                break;
            }
            ++startRow;
            ++startColumn;
        }
        return sameDiagonal;
    }

    private boolean checkUpperRightDiagonalForSameDiagonal(int startRow, int startColumn,
                                                           int endRow, int endColumn, int chessBoardSize) {
        boolean sameDiagonal = false;
        while (pointInBoard(startRow, startColumn, chessBoardSize)) {
            if (startRow == endRow && startColumn == endColumn) {
                sameDiagonal = true;
                break;
            }
            --startRow;
            ++startColumn;
        }
        return sameDiagonal;
    }

    private boolean checkLowerLeftDiagonalForSameDiagonal(int startRow, int startColumn,
                                                          int endRow, int endColumn, int chessBoardSize) {
        boolean sameDiagonal = false;
        while (pointInBoard(startRow, startColumn, chessBoardSize)) {
            if (startRow == endRow && startColumn == endColumn) {
                sameDiagonal = true;
                break;
            }
            ++startRow;
            --startColumn;
        }
        return sameDiagonal;
    }

    private boolean pointInBoard(int i, int j, int chessBoardSize) {
        return (0 <= i && i < chessBoardSize) && (0 <= j && j < chessBoardSize);
    }

    private void printPositions(List<List<String>> positions) {
        positions.forEach(position -> {
            position.forEach(queenPosition -> System.out.println(queenPosition));
            System.out.println();
        });
    }
    private void printCombinations(List<Integer[]> combinations) {
        combinations.forEach(integers -> {
            System.out.println(Arrays.toString(integers));
        });
    }
}

package countsquaresubmatriceswithallones;

class Solution {
    public static int countSquares(int[][] matrix) {

        int total = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] != 1) {
                    continue;
                }
                total++;

                total += getTotal(i, j, matrix);

            }
        }

        return total;

    }

    private static int getTotal(int i, int j, int[][] matrix) {
        int total = 0;
        for (int k = 1; k < 1000000; k++) {

            if (i + k > matrix.length - 1 || j + k > matrix[0].length - 1) {
                return total;
            }

            int sum = (k + 1) * (k + 1);
            int actualSum = 0;
            for (int col = i; col <= i + k; col++) {
                for (int row = j; row <= j + k; row++) {
                    actualSum += matrix[col][row];
                }
            }
            if (sum == actualSum) {
                total++;
            } else {
                return total;
            }
        }

        return total;
    }


    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {0, 1, 1, 1},
                {1, 1, 1, 1},
                {0, 1, 1, 1}};

        System.out.println(countSquares(matrix));
    }
}
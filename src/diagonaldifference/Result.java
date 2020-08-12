package diagonaldifference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Result {

    /*
     * Complete the 'diagonalDifference' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts 2D_INTEGER_ARRAY arr as parameter.
     */

    public static int diagonalDifference(List<List<Integer>> arr) {

        int diagonal1 = 0;
        int diagonal2 = 0;

        int size = arr.size();
        for (int i = 0; i < size; i++) {

            diagonal1 += arr.get(i).get(i);
            diagonal2 += arr.get(size - 1 - i).get(i);
        }

        return Math.abs(diagonal1 - diagonal2);

    }

}


package pickingnumbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Result {

    public static int pickingNumbers(List<Integer> a) {

        Map<Integer, List<Integer>> subarrays = new HashMap<>();

        a.forEach(integer -> process(integer, subarrays));

        return subarrays.values().stream().map(Collections::max).max(Integer::compareTo).get();
    }

    private static void process(Integer integer, Map<Integer, List<Integer>> subarrays) {


        subarrays.keySet().forEach(number -> {
            if (Math.abs(number - integer) <= 1) {

                Integer length1= subarrays.get(number).get(0);
                Integer length2= subarrays.get(number).get(1);
                if (number >= integer) {
                    subarrays.get(number).set(0, length1 + 1);
                }

                if (number <= integer) {
                    subarrays.get(number).set(1, length2 + 1);
                }
            }
        });

        if (!subarrays.containsKey(integer)) {
            List<Integer> list = new ArrayList<>();
            list.add(1);
            list.add(1);
            subarrays.put(integer, list);
        }
    }
}


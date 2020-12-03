package reportrepair;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Before you leave, the Elves in accounting just need you to fix your expense report (your puzzle input); apparently, something isn't quite adding up.
 *
 * Specifically, they need you to find the two entries that sum to 2020 and then multiply those two numbers together.
 *
 * For example, suppose your expense report contained the following:
 *
 * 1721
 * 979
 * 366
 * 299
 * 675
 * 1456
 * In this list, the two entries that sum to 2020 are 1721 and 299. Multiplying them together produces 1721 * 299 = 514579, so the correct answer is 514579.
 *
 * Of course, your expense report is much larger. Find the two entries that sum to 2020; what do you get if you multiply them together?
 *
 * --- Part Two ---
 * The Elves in accounting are thankful for your help; one of them even offers you a starfish coin they had left over from a past vacation. They offer you a second one if you can find three numbers in your expense report that meet the same criteria.
 *
 * Using the above example again, the three entries that sum to 2020 are 979, 366, and 675. Multiplying them together produces the answer, 241861950.
 *
 * In your expense report, what is the product of the three entries that sum to 2020?
 */
public class Solution {

    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream("input.txt");
        Scanner sc = new Scanner(fis);    //file to be scanned
        List<Integer> items = new ArrayList<>();
        while(sc.hasNextLine())
        {
            items.add(Integer.parseInt(sc.nextLine()));
        }
        sc.close();     //closes the scanner

//        getSumOfTwo(items);
        getSumOfTree(items);
    }

    private static void getSumOfTwo(List<Integer> items) {
        for (Integer integer : items) {
            if (items.contains(2020 - integer)) {
                System.out.println(integer * (2020 - integer));
            }
        }
    }

    private static void getSumOfTree(List<Integer> items) {
        for (Integer integer1 : items) {
            Integer rest = 2020 - integer1;
            for (Integer integer2 : items) {
                if (items.contains(rest - integer2)) {
                    System.out.println(integer1 * integer2 * (rest - integer2));
                }
            }
        }
    }


}

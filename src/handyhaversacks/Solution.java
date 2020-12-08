package handyhaversacks;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 You land at the regional airport in time for your next flight. In fact, it looks like you'll even have time to grab some food: all flights are currently delayed due to issues in luggage processing.

 Due to recent aviation regulations, many rules (your puzzle input) are being enforced about bags and their contents; bags must be color-coded and must contain specific quantities of other color-coded bags. Apparently, nobody responsible for these regulations considered how long they would take to enforce!

 For example, consider the following rules:

 light red bags contain 1 bright white bag, 2 muted yellow bags.
 dark orange bags contain 3 bright white bags, 4 muted yellow bags.
 bright white bags contain 1 shiny gold bag.
 muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
 shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
 dark olive bags contain 3 faded blue bags, 4 dotted black bags.
 vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
 faded blue bags contain no other bags.
 dotted black bags contain no other bags.
 These rules specify the required contents for 9 bag types. In this example, every faded blue bag is empty, every vibrant plum bag contains 11 bags (5 faded blue and 6 dotted black), and so on.

 You have a shiny gold bag. If you wanted to carry it in at least one other bag, how many different bag colors would be valid for the outermost bag? (In other words: how many colors can, eventually, contain at least one shiny gold bag?)

 In the above rules, the following options would be available to you:

 A bright white bag, which can hold your shiny gold bag directly.
 A muted yellow bag, which can hold your shiny gold bag directly, plus some other bags.
 A dark orange bag, which can hold bright white and muted yellow bags, either of which could then hold your shiny gold bag.
 A light red bag, which can hold bright white and muted yellow bags, either of which could then hold your shiny gold bag.
 So, in this example, the number of bag colors that can eventually contain at least one shiny gold bag is 4.

 How many bag colors can eventually contain at least one shiny gold bag? (The list of rules is quite long; make sure you get all of it.)

 Your puzzle answer was 211.

 The first half of this puzzle is complete! It provides one gold star: *

 --- Part Two ---
 It's getting pretty expensive to fly these days - not because of ticket prices, but because of the ridiculous number of bags you need to buy!

 Consider again your shiny gold bag and the rules from the above example:

 faded blue bags contain 0 other bags.
 dotted black bags contain 0 other bags.
 vibrant plum bags contain 11 other bags: 5 faded blue bags and 6 dotted black bags.
 dark olive bags contain 7 other bags: 3 faded blue bags and 4 dotted black bags.
 So, a single shiny gold bag must contain 1 dark olive bag (and the 7 bags within it) plus 2 vibrant plum bags (and the 11 bags within each of those): 1 + 1*7 + 2 + 2*11 = 32 bags!

 Of course, the actual rules have a small chance of going several levels deeper than this example; be sure to count all of the bags, even if the nesting becomes topologically impractical!

 Here's another example:

 shiny gold bags contain 2 dark red bags.
 dark red bags contain 2 dark orange bags.
 dark orange bags contain 2 dark yellow bags.
 dark yellow bags contain 2 dark green bags.
 dark green bags contain 2 dark blue bags.
 dark blue bags contain 2 dark violet bags.
 dark violet bags contain no other bags.
 In this example, a single shiny gold bag must contain 126 other bags.

 How many individual bags are required inside your single shiny gold bag?
 */
public class Solution {

    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream("input.txt");
        Scanner sc = new Scanner(fis);    //file to be scanned
        Map<String, Map<String, Integer>> rules = new HashMap<>();
        Map<String, List<String>> canBePutIn = new HashMap<>();
        while(sc.hasNextLine())
        {
            String line = sc.nextLine();

            String[] split = line.split(" bags contain ");

            String bagName = split[0];

            String[] content = split[1].split(", ");

            String pattern = "^(\\d) (.*?) bag";
            Pattern compile = Pattern.compile(pattern);
            Map<String, Integer> contentBags = new HashMap<>();
            for (String contentBag: content) {
                Matcher matcher = compile.matcher(contentBag);
                while (matcher.find()) {
                    String number = matcher.group(1);
                    String bag = matcher.group(2);
                    contentBags.put(bag, Integer.parseInt(number));
                    List<String> strings = canBePutIn.computeIfAbsent(bag, s -> new ArrayList<>());
                    strings.add(bagName);
                    canBePutIn.put(bag, strings);
                }
            }

            rules.put(bagName, contentBags);
        }

        Integer count = getCountOptions("shiny gold", canBePutIn).size();
        Integer countBags = getCountBags("shiny gold", rules);

        System.out.println(count);
        System.out.println(countBags);

    }

    private static int getCountBags(String bag, Map<String, Map<String, Integer>> rules) {

        Map<String, Integer> strings = rules.get(bag);

        int count = 0;

        Set<Entry<String, Integer>> entries = strings.entrySet();
        for (Entry<String, Integer> entry: entries) {
            count += entry.getValue();
            count += entry.getValue() * getCountBags(entry.getKey(), rules);
        }

        return count;




    }

    private static Set<String> getCountOptions(String bag,  Map<String, List<String>> canBePut) {
        Set<String> can = new HashSet<>();
        List<String> strings = canBePut.get(bag);
        if (strings == null) {
            return can;
        }
        for (String bagNext: strings) {
            can.add(bagNext);
            can.addAll(getCountOptions(bagNext, canBePut));
        }

        return can;
    }
}

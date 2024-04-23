package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        List<String> namen = Arrays.asList("Tim", "Dylan", "Bobby", "Japie","Gaia", "Jonas", "Ckarel");
        long aantalName = namen.stream().count();
        String longestName = namen.stream().max((a, b) -> a.length() - b.length()).orElse(null);
        System.out.println(longestName);
        boolean allMatch = namen.stream().allMatch(a -> a.startsWith("A"));
        boolean noneMatch = namen.stream().noneMatch(a -> a.startsWith("A"));
        boolean anyMatch = namen.stream().anyMatch(a -> a.startsWith("G"));

        List<String> namenMet5Letters = namen.stream().filter(s -> s.length() == 5).collect(Collectors.toList());
        List<String> namenMet5Letters2 = namen.stream().filter(s -> s.length() == 5).toList();

        String alleNamen = namen.stream().collect(() -> new StringBuilder(), (sb, str) -> sb.append(str), (sb1, sb2) -> sb1.append(sb2)).toString();
        System.out.println(alleNamen);

        List<Integer> cijfers = Stream.of(3, 4, 5, 3, 5, 67, 8, 9, 0,4, 5, 6, 7, 8, 9, 0, -0 ,5, 4, 3, 32, 334, 45, 65, 56)
                .parallel()
                .map(a -> a + a)
                .collect(() -> new ArrayList<Integer>(),
                        (list, a) -> {
                           list.add((int)Math.pow(a, a));
                            System.out.println(Thread.currentThread().getId());
                        },
                        (l1, l2) -> l1.addAll(l2));
        System.out.println(cijfers);
    }
}

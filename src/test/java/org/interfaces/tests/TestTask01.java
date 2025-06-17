package org.interfaces.tests;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestTask01 {
    public static void main(String[] args) {
        String str = "-Some very simple string-";

        String revertStr = IntStream.range(0, str.length())
                .map(i -> str.length() - 1 - i)
                .mapToObj(i -> String.valueOf(str.charAt(i)))
                .collect(Collectors.joining());
        System.out.println("Original string = '" + str + "'");
        System.out.println("Reverted string = '" + revertStr + "'");
    }
}

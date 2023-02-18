package ru.yzuykov.jsandbox.application.core.util;


import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;


@UtilityClass
public class TextUtil {

    public static final String DOUBLE_QUOTES = "\"";
    public static final String SPACE = " ";

    public static String replaceDeniedSymbolsInString(String inputString) {
        String[] split = inputString.split(DOUBLE_QUOTES, -1);
        StringBuilder stringBuilder = new StringBuilder();
        List<String> strings = Arrays.asList(split);
        IntStream.range(0, strings.size()).forEach(i -> {
            String word = strings.get(i).trim();
            stringBuilder.append(word);
            if (i != strings.size() - 1) {
                if (i % 2 == 0) {
                    stringBuilder.append(SPACE).append(DOUBLE_QUOTES);
                } else {
                    stringBuilder.append(DOUBLE_QUOTES).append(SPACE);
                }
            }
        });
        return stringBuilder.toString().trim().replaceAll("\\s+", SPACE);
    }
}

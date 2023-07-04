package com.uriel.graphs.builders.utils;

import java.util.regex.Pattern;

public class DescriptorUtils {

    private static final Pattern indexPattern = Pattern.compile("\\d+");

    public static boolean isValidIndex(String s) {
        if (s == null) {
            return false;
        }
        return indexPattern.matcher(s).matches();
    }

}

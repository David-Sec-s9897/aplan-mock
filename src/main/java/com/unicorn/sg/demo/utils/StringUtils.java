package com.unicorn.sg.demo.utils;

import jakarta.inject.Named;
import jakarta.inject.Singleton;

@Named
@Singleton
public class StringUtils {

    public static final String PERIOD_VALIDATION_REGEX_STRING = "^P(([0-9]+([.,][0-9]*)?Y)?([0-9]+([.,][0-9]*)?M)?([0-9]+([.,][0-9]*)?D)?T?([0-9]+([.,][0-9]*)?H)?([0-9]+([.,][0-9]*)?M)?([0-9]+([.,][0-9]*)?S)?)|\\d{4}-?(0[1-9]|11|12)-?(?:[0-2]\\d|30|31)T((?:[0-1][0-9]|[2][0-3]):?(?:[0-5][0-9]):?(?:[0-5][0-9]|60)|2400|24:00)|.?$";
            ;

    /**
     * Capitalizes the first letter of the given string and converts the remaining characters to lowercase.
     *
     * <p>If the input string is null or empty, the method returns the input as is.</p>
     *
     * @param name The string to be capitalized.
     * @return A string with the first letter capitalized and the remaining characters in lowercase.
     *         If the input string is null or empty, it returns the original input.
     *
     * <pre>
     * Example Usage:
     * capitalizeFirstLetter("david")  // Returns "David"
     * capitalizeFirstLetter("ALICE")  // Returns "Alice"
     * capitalizeFirstLetter("jOhN")   // Returns "John"
     * </pre>
     */
    public static String capitalizeFirstLetter(String name) {
        if (name == null || name.isEmpty()) {
            return name;
        }
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

    public String getPeriodValidationRegex(){
        return PERIOD_VALIDATION_REGEX_STRING;
    }
}

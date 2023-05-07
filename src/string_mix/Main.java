package string_mix;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String firstString = "Are the kids at home? aaaaa fffff";
        String secondString = "Yes they are here! aaaaa fffff";
        String resultString = stringMix(firstString, secondString);
        System.out.println(resultString);
    }

    public static String stringMix(String firstString, String secondString) {
        // function should find the maximum occurrences of a character in both strings
        // the result should be a mix of two strings separated by a slash '/' with the following rules:
        // 1. the first string should contain only lowercase letters from the first string
        // 2. the second string should contain only lowercase letters from the second string
        // 3. the letters should be sorted in descending order by the number of occurrences in the string
        // 4. in case of equal occurrence, the letters should be sorted alphabetically
        // 5. if a letter is present in both strings with the same occurrence, it should be presented with an '='

        // create a map for each string
        Map<Character, Integer> firstStringMap = new HashMap<>();
        Map<Character, Integer> secondStringMap = new HashMap<>();

        // populate the maps
        for (char c : firstString.toCharArray()) {
            if (Character.isLowerCase(c)) {
                firstStringMap.put(c, firstStringMap.getOrDefault(c, 0) + 1);
            }
        }

        for (char c : secondString.toCharArray()) {
            if (Character.isLowerCase(c)) {
                secondStringMap.put(c, secondStringMap.getOrDefault(c, 0) + 1);
            }
        }

        // create a list of strings to store the result
        List<String> result = new ArrayList<>();

        // iterate through the first map
        for (Map.Entry<Character, Integer> entry : firstStringMap.entrySet()) {
            // get the key and value
            Character key = entry.getKey();
            int value = entry.getValue();

            // if the key is present in the second map
            if (secondStringMap.containsKey(key)) {
                // get the value from the second map
                int secondValue = secondStringMap.get(key);

                if (value < 2 && secondValue < 2) {
                    continue;
                }

                // if the value from the first map is greater than the value from the second map
                if (value > secondValue) {
                    // add the key to the result string value times
                    result.add("1:" + key.toString().repeat(value));
                }
                // if the value from the first map is less than the value from the second map
                else if (value < secondValue) {
                    // add the key to the result string value times
                    result.add("2:" + key.toString().repeat(secondValue));
                }
                // if the value from the first map is equal to the value from the second map
                else {
                    // add the key to the result string value times
                    result.add("=:" + key.toString().repeat(value));
                }
            }
            // if the key is not present in the second map
            else {
                if (value < 2) {
                    continue;
                }

                // add the key to the result string value times
                result.add("1:" + key.toString().repeat(value));
            }
        }

        // iterate through the second map
        for (Map.Entry<Character, Integer> entry : secondStringMap.entrySet()) {
            // get the key and value
            Character key = entry.getKey();
            int value = entry.getValue();

            if (value < 2) {
                continue;
            }

            // if the key is not present in the first map
            if (!firstStringMap.containsKey(key)) {
                // add the key to the result string value times
                result.add("2:" + key.toString().repeat(value));
            }
        }

        // sort the result list
        result.sort((a, b) -> {
            // get the first value
            int firstValue = a.substring(2).length();

            // get the second value
            int secondValue = b.substring(2).length();

            // if the first value is greater than the second value
            if (firstValue > secondValue) {
                // return -1
                return -1;
            }
            // if the first value is less than the second value
            else if (firstValue < secondValue) {
                // return 1
                return 1;
            }
            // if the first value is equal to the second value
            else {
                // if string starts with '=', move it to the end of the result
                if (a.charAt(0) == '=' && b.charAt(0) != '=') {
                    return 1;
                } else if (a.charAt(0) != '=' && b.charAt(0) == '=') {
                    return -1;
                }

                // else sort alphabetically
                // get the first key
                char firstKey = a.charAt(a.indexOf(":") + 1);

                // get the second key
                char secondKey = b.charAt(b.indexOf(":") + 1);

                // if the first key is greater than the second key
                // return 1
                // if the first key is less than the second key
                // return -1
                // if the first key is equal to the second key
                // return 0
                return Character.compare(firstKey, secondKey);
            }
        });

        // return the result string
        return String.join("/", result);
    }
}


